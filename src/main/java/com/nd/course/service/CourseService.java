package com.nd.course.service;

import com.nd.course.model.CourseEntity;

import java.util.List;

/**
 * 接口名称：
 * 接口描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
public interface CourseService {

    /**
     * 获取所有课程列表
     * @return
     */
    List<CourseEntity> getAllCourse();

    /**
     * 创建课程
     * @param courseEntity
     * @return
     */
//    CourseEntity save(CourseEntity courseEntity);

    /**
     * 删除课程
     * @param courseId
     */
//    void delete(String courseId);
}
