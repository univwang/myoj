package com.univwang.myoj.judge.codesandbox;

import com.univwang.myoj.judge.codesandbox.model.ExecCuteCodeRequest;
import com.univwang.myoj.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox  {
    /**
     * 执行代码
     * @param execCuteCodeRequest
     * @return ExecuteCodeResponse
     */
    ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest);

}
