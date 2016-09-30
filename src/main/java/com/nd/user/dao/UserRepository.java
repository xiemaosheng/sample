package com.nd.user.dao;

import com.nd.uc.repository.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口名称：user类仓储
 * 接口描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByOrgUserCode(String loginName);
}
