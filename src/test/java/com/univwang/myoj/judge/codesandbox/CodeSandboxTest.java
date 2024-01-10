package com.univwang.myoj.judge.codesandbox;

import com.univwang.myoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.univwang.myoj.judge.codesandbox.model.ExecCuteCodeRequest;
import com.univwang.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.univwang.myoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


@SpringBootTest
class CodeSandboxTest {

    @Value("${codeSandbox.type:example}")
    private String type;
    @Test
    void executeCode() {
        //更加通用的方式，工厂模式
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello World!\");\n" +
                "    }\n" +
                "}";
        String language = "java";
        List<String> list = Arrays.asList("1 2", "3 4");
        ExecCuteCodeRequest execCuteCodeRequest = ExecCuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(list)
                .build();
        Assertions.assertNotNull(codeSandbox.executeCode(execCuteCodeRequest));
    }

    @Test
    void executeCodeByProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        //从args中获取输入的两个数a和b
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果:\" + (a + b));\n" +
                "    }\n" +
                "}\n";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> list = Arrays.asList("1 2", "3 4");
        ExecCuteCodeRequest execCuteCodeRequest = ExecCuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(list)
                .build();

        ExecuteCodeResponse response = codeSandbox.executeCode(execCuteCodeRequest);
        Assertions.assertNotNull(response);

    }
}