package com.nd.user.controller;

import com.nd.common.exception.NiceErrorCodes;
import com.nd.common.exception.NiceException;
import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.user.model.UserReq;
import com.nd.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取老师详细信息
     *
     * @param userInfo
     * @return
     */
    @GetMapping(value = "/teachers/detail")
    public Object getTeacherDetail(@AuthenticationPrincipal UserInfo userInfo) {
        return userService.findUserDetail(userInfo.getUserId());
    }

    /**
     * 获取学生详细信息
     *
     * @param userInfo
     * @return
     */
    @GetMapping(value = "/students/detail")
    public Object getStudentDetail(@AuthenticationPrincipal UserInfo userInfo) {
        return userService.findUserDetail(userInfo.getUserId());
    }

    @PostMapping(value = "/students/actions/register")
    public Object registerStudent(@AuthenticationPrincipal UserInfo userInfo,
                                  @RequestBody UserReq userReq) {
        if (StringUtils.isBlank(userReq.getRealName()) ||
                StringUtils.isBlank(userReq.getLoginName()) ||
                StringUtils.isBlank(userReq.getPassword()) ||
                StringUtils.isBlank(userReq.getNickName())){
            throw new NiceException(NiceErrorCodes.INVALID_ARGUMENT);
        }
        return userService.createStudent(userInfo.getUserId(), userReq);
    }
}
