package com.univwang.myoj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.univwang.myoj.common.ErrorCode;
import com.univwang.myoj.exception.BusinessException;
import com.univwang.myoj.judge.codesandbox.CodeSandbox;
import com.univwang.myoj.judge.codesandbox.model.ExecCuteCodeRequest;
import com.univwang.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

public class RemoteCodeSandbox implements CodeSandbox {

    private static final String AUTH_REQUEST_HEAD = "auth";
    private static final String AUTH_REQUEST_SECRET = "secretKey";

    private static final String REMOTE_URL = "http://10.160.1.244:8090/executeCode";

    @Override
    public ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest) {
        System.out.println("远程沙箱执行代码");
        String json = JSONUtil.toJsonStr(execCuteCodeRequest);
        String responseStr = HttpUtil.createPost(REMOTE_URL)
                .header(AUTH_REQUEST_HEAD, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();

        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "远程沙箱执行代码失败");
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
