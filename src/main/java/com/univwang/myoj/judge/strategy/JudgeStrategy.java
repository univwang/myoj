package com.univwang.myoj.judge.strategy;

import com.univwang.myoj.judge.codesandbox.model.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);

}
