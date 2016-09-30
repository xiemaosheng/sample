package com.nd.uc.config;

import com.nd.gaea.client.ApplicationContextUtil;
import com.nd.gaea.rest.security.authens.UserCenterRoleDetails;
import com.nd.gaea.rest.security.authens.UserCenterUserDetails;
import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.gaea.rest.security.services.impl.UserCenterUserDetailsCacheService;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：用户权限重写
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/10 0010
 */
public class UserCenterUserDetailsCacheServiceExtend extends UserCenterUserDetailsCacheService {
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    private UserInfoService userInfoService;

    @Override
    public UserCenterUserDetails getUserCenterUserDetails(UserInfo user, String realm) {
        List<UserCenterRoleDetails> roleDetailsCollection = this.getUserRoleList(user.getUserId(), null);
        return new UserCenterUserDetails(user, roleDetailsCollection);
    }

    /**
     * 重写UC的roleList的实现
     * @param userId
     * @param realm
     * @return
     */
    @Override
    public List<UserCenterRoleDetails> getUserRoleList(String userId, String realm) {
        if(userInfoService==null){
            userInfoService= ApplicationContextUtil.getApplicationContext().getBean(UserInfoService.class);
        }
        List<UserCenterRoleDetails> roleDetailsCollection=new ArrayList<>();
        List<String> roleList=userInfoService.getUserRoleList(userId);
        for (String role : roleList) {
            UserCenterRoleDetails u=new UserCenterRoleDetails();
            u.setRoleName("ROLE_"+role);
            roleDetailsCollection.add(u);
        }

        return roleDetailsCollection;
    }
}
