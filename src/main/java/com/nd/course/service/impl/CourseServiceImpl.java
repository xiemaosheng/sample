package com.nd.course.service.impl;

import com.nd.course.enums.CourseEnum;
import com.nd.course.model.CourseEntity;
import com.nd.course.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：接口实现类
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
@Service
public class CourseServiceImpl implements CourseService{

//    @Autowired
//    private CourseRepository courseRepository;

    @Override
    public List<CourseEntity> getAllCourse() {
        List<CourseEntity> courses = new ArrayList<>();
        for (CourseEnum courseEnum : CourseEnum.values()) {
            CourseEntity item = new CourseEntity(courseEnum);
            courses.add(item);
        }
        return courses;
    }

//    @Override
//    public CourseEntity save(CourseEntity courseEntity) {
//        CourseEntity result = courseRepository.findByCourseName(courseEntity.getCourseName());
//        if(result!=null){
//            throw new NiceException(NiceErrorCodes.COURSE_HAS_EXIST);
//        }
//        return courseRepository.save(courseEntity);
//    }
//
//    @Override
//    public void delete(String courseId) {
//        courseRepository.delete(courseId);
//    }
}
