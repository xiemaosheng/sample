package com.nd.uc.repository;

import com.nd.common.enums.IdentityEnum;
import com.nd.common.enums.StateEnum;
import com.nd.user.model.UserReq;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 类名称：t_user表
 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/10 0010
 */
@Entity
@Table(name = "t_user")
public class UserEntity {

    private String userId;
    private String userName;
    private String nickName;
    private String realName;
    private String orgUserCode;
    private Integer status;
    private String role;
    private String createBy;
    private String updateBy;
    private Timestamp createDate;
    private Timestamp updateDate;

    public UserEntity(){}

    public UserEntity(UserReq userReq) {
        this.nickName = userReq.getNickName();
        this.realName = userReq.getRealName();
        this.userName = userReq.getLoginName();
        this.orgUserCode = userReq.getLoginName();
        this.status = StateEnum.ON.getCode();
        this.role = IdentityEnum.STUDENT.getCode();
        Date now = new Date();
        this.createDate = new Timestamp(now.getTime());
        this.updateDate = new Timestamp(now.getTime());
    }

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "org_user_code")
    public String getOrgUserCode() {
        return orgUserCode;
    }

    public void setOrgUserCode(String orgUserCode) {
        this.orgUserCode = orgUserCode;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_date")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
        if (realName != null ? !realName.equals(that.realName) : that.realName != null) return false;
        if (orgUserCode != null ? !orgUserCode.equals(that.orgUserCode) : that.orgUserCode != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        return updateDate != null ? updateDate.equals(that.updateDate) : that.updateDate == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (orgUserCode != null ? orgUserCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        return result;
    }
}
