package com.univwang.myoj.judge.strategy;

import com.univwang.myoj.model.dto.question.JudgeCase;
import com.univwang.myoj.judge.codesandbox.model.JudgeInfo;
import com.univwang.myoj.model.entity.Question;
import com.univwang.myoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 定义策略中的上下文
 */

@Data
public class JudgeContext {
    private Integer exitCode;
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}
