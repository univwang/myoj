package com.univwang.myoj.judge.codesandbox;

import com.univwang.myoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.univwang.myoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.univwang.myoj.judge.codesandbox.impl.ThirdPartCodeSandbox;

/**
 * 代码沙箱工厂（根据字符串创建对应的沙箱）
 * 静态工厂方法
 */
public class CodeSandboxFactory {
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdPart":
                return new ThirdPartCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
