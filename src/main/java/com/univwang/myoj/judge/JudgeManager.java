package com.univwang.myoj.judge;


import com.univwang.myoj.judge.strategy.DefaultJudgeStrategy;
import com.univwang.myoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.univwang.myoj.judge.strategy.JudgeContext;
import com.univwang.myoj.judge.strategy.JudgeStrategy;
import com.univwang.myoj.judge.codesandbox.model.JudgeInfo;
import com.univwang.myoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题策略管理
 */

@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(language.equals("java")) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
