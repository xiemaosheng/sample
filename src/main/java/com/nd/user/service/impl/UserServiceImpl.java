package com.nd.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nd.Constant;
import com.nd.common.enums.StateEnum;
import com.nd.common.exception.NiceErrorCodes;
import com.nd.common.exception.NiceException;
import com.nd.uc.UcApiService;
import com.nd.uc.repository.UserEntity;
import com.nd.user.dao.UserRepository;
import com.nd.user.model.UserReq;
import com.nd.user.model.UserResp;
import com.nd.user.service.UserService;
import com.nd.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UcApiService ucApiService;

    @Override
    public UserResp findUserDetail(String userId) {
        UserEntity user = userRepository.findOne(userId);
        if(user ==null) return null;
        return new UserResp(user);
    }

    @Override
    public UserEntity createStudent(String teacherId, UserReq userReq) {
        //查询本地库中是否存在登录名相同的用户
        UserEntity user = userRepository.findByOrgUserCode(userReq.getLoginName());
        if (user != null) {
            throw new NiceException(NiceErrorCodes.USER_HAS_EXIST);
        }
        JSONObject student = new JSONObject();
        JSONObject orgExinfo = new JSONObject();
        student.put("org_exinfo", orgExinfo);
        student.put("nick_name", userReq.getNickName());
        student.put("password", MD5Util.encryptMD5_ND(userReq.getPassword()));
        //默认注册使用状态用户
        student.put("enable_status", StateEnum.ON.getCode());
        orgExinfo.put("real_name", userReq.getRealName());
        orgExinfo.put("org_user_code", userReq.getLoginName());

        String orgId = Constant.getOrgId();
        String studentNodeId = Constant.getStudentNode().getString("node_id");
        //注册UC
        JSONObject result = ucApiService.createOrgUser(orgId, studentNodeId, student);
        String studentId;
        if (result.getJSONArray("success_user").size() > 0) {
            studentId = result.getJSONArray("success_user").getJSONObject(0).getString("user_id");
        } else {
            logger.error(result.getJSONArray("fail_user").toJSONString());
            JSONObject fail = ((JSONObject) result.getJSONArray("fail_user").get(0));
            String errorCode = fail.getString("error_code");
            if (errorCode != null && "UC/NODE_EXISTED_USER_CODE".equals(errorCode)) {
                throw new NiceException(NiceErrorCodes.USER_HAS_EXIST);
            }
            throw new NiceException(NiceErrorCodes.CREATE_USER_FAILD);
        }
        //同步本地库
        user = new UserEntity(userReq);
        user.setUserId(studentId);
        user.setCreateBy(teacherId);
        UserEntity userEntity = userRepository.save(user);
        return userEntity;
    }
}
