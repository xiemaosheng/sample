package com.nd.course.model;

import com.nd.course.enums.CourseEnum;

import javax.persistence.*;

/**
 * 课程类实体
 * Created by ${zhiqiangXu}
 * on 2016/9/6 0006.
 */
@Entity
@Table(name = "t_course")
public class CourseEntity {
    @Id
    @Column(name = "course_id")
    private String courseId;
    @Basic
    @Column(name = "course_name")
    private String courseName;

    public CourseEntity(){}
    public CourseEntity(CourseEnum courseEnum) {
        this.courseId = courseEnum.getCode();
        this.courseName = courseEnum.getName();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
