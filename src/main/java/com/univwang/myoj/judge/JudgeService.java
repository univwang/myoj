package com.univwang.myoj.judge;

import com.univwang.myoj.model.entity.QuestionSubmit;

public interface JudgeService {
    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
