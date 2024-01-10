package com.univwang.myoj.judge;

import cn.hutool.json.JSONUtil;
import com.univwang.myoj.common.ErrorCode;
import com.univwang.myoj.exception.BusinessException;
import com.univwang.myoj.judge.codesandbox.CodeSandbox;
import com.univwang.myoj.judge.codesandbox.CodeSandboxFactory;
import com.univwang.myoj.judge.codesandbox.CodeSandboxProxy;
import com.univwang.myoj.judge.codesandbox.model.ExecCuteCodeRequest;
import com.univwang.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.univwang.myoj.judge.strategy.JudgeContext;
import com.univwang.myoj.model.dto.question.JudgeCase;
import com.univwang.myoj.judge.codesandbox.model.JudgeInfo;
import com.univwang.myoj.model.entity.Question;
import com.univwang.myoj.model.entity.QuestionSubmit;
import com.univwang.myoj.model.enums.QuestionSubmitStatusEnum;
import com.univwang.myoj.service.QuestionService;
import com.univwang.myoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    QuestionSubmitService questionSubmitService;

    @Resource
    QuestionService questionService;

    @Value("${codeSandbox.type:example}")
    private String type;

    @Resource
    JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        //1.获取题目提交和题目信息
        QuestionSubmit submit = questionSubmitService.getById(questionSubmitId);
        if(submit == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目提交不存在");
        }
        Question question = questionService.getById(submit.getQuestionId());
        if(question == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目不存在");
        }
        if(!submit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目已经判题");
        }
        //2.更改题目提交状态
        submit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(submit);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新题目提交状态失败");
        }
        //3.调用沙箱接口
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = submit.getCode();
        String language = submit.getLanguage();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecCuteCodeRequest execCuteCodeRequest = ExecCuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse response = codeSandbox.executeCode(execCuteCodeRequest);
        //4. 判断沙箱接口返回的结果
        List<String> outputList = response.getOutputList();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(response.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(submit);
        judgeContext.setExitCode(response.getExitCode());
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        //5. 修改数据库题目提交状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(submit.getId());
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean updateById = questionSubmitService.updateById(questionSubmitUpdate);
        if(!updateById){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新题目提交状态失败");
        }
        //6. 返回题目提交信息
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(submit.getId());
        return questionSubmitResult;
    }
}
