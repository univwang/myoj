package com.univwang.myoj.judge.codesandbox.impl;

import com.univwang.myoj.judge.codesandbox.CodeSandbox;
import com.univwang.myoj.judge.codesandbox.model.ExecCuteCodeRequest;
import com.univwang.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.univwang.myoj.judge.codesandbox.model.JudgeInfo;
import com.univwang.myoj.model.enums.JudgeInfoMessageEnum;
import com.univwang.myoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest) {
        List<String> inputList = execCuteCodeRequest.getInputList();
        ExecuteCodeResponse response = new ExecuteCodeResponse();
        response.setOutputList(inputList);
        response.setMessage("测试成功");
        response.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        response.setJudgeInfo(judgeInfo);
        return response;
    }
}
