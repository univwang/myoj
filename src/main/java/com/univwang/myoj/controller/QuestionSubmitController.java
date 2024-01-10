package com.univwang.myoj.controller;

import com.univwang.myoj.common.BaseResponse;
import com.univwang.myoj.common.ErrorCode;
import com.univwang.myoj.common.ResultUtils;
import com.univwang.myoj.exception.BusinessException;
import com.univwang.myoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.univwang.myoj.model.entity.User;
import com.univwang.myoj.service.QuestionSubmitService;
import com.univwang.myoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 *  
 * @from <a href="https://univwang.top">  </a>
 */
@RestController
//@RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {

//    @Resource
//    private QuestionSubmitService questionSubmitService;
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 提交题目
//     *
//     * @param questionSubmitAddRequest
//     * @param request
//     * @return resultNum 本次点赞变化数
//     */
//    @PostMapping("/")
//    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//                                                  HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        final User loginUser = userService.getLoginUser(request);
//        long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(result);
//    }

}
