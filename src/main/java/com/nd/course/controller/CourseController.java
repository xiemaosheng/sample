package com.nd.course.controller;

import com.nd.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称：课程相关接口控制层
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
@RestController
@RequestMapping(value = "/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 获取所有的课程列表
     * @return
     */
    @GetMapping(value = "")
    public Object getAllCourse(){
        return courseService.getAllCourse();
    }


}
