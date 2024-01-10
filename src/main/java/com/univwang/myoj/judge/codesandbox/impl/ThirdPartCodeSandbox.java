package com.univwang.myoj.judge.codesandbox.impl;

import com.univwang.myoj.judge.codesandbox.CodeSandbox;
import com.univwang.myoj.judge.codesandbox.model.ExecCuteCodeRequest;
import com.univwang.myoj.judge.codesandbox.model.ExecuteCodeResponse;

public class ThirdPartCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest) {
        System.out.println("第三方沙箱执行代码");
        return null;
    }
}
