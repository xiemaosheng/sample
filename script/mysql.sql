CREATE TABLE `t_user` (
  `user_id` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户id-uc提供',
  `user_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `org_user_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '工号/学号',
  `real_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 1-启用，0-停用',
  `role` varchar(10) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色',
  `create_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '当前时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `t_class_student` (
  `class_id` varchar(20) NOT NULL COMMENT '班级id',
  `student_id` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '学生id',
  PRIMARY KEY (`class_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级和学生关联表';

CREATE TABLE `t_course` (
  `course_id` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `course_name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '课程名',
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `unique(course_name)` (`course_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';

CREATE TABLE `t_knowledge` (
  `knowledge_id` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `knowledge _name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '知识点名称',
  `parent _id` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '父知识点id',
  `child_id` varchar(20) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '子知识点id',
  `course` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '课程id',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`knowledge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='知识点';

CREATE TABLE `t_school_class` (
  `class_id` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `class_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '班级名称',
  `teacher_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '助学老师id',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级表';

