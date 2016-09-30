package com.nd.course.dao;

import com.nd.course.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口名称：课程类仓储
 * 接口描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    CourseEntity findByCourseName(String courseName);
}
