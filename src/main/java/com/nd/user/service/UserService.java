package com.nd.user.service;

import com.nd.uc.repository.UserEntity;
import com.nd.user.model.UserReq;
import com.nd.user.model.UserResp;

/**
 * 接口名称：
 * 接口描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
public interface UserService {
    /**
     * 获取当前登录用户信息
     * @param userId
     * @return
     */
    UserResp findUserDetail(String userId);

    UserEntity createStudent(String userId, UserReq userReq);
}
