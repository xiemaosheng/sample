package com.nd.user.model;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
public class UserReq {
    private String nickName;
    private String password;
    private String realName;
    private String loginName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
