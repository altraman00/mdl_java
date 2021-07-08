package com.mdl.hundun.service;

import com.mdl.hundun.dto.course.CourseBaseDTO;
import com.mdl.hundun.dto.course.CourseDetailDTO;
import com.mdl.hundun.dto.ppt.CoursePptDTO;

import java.util.List;

public interface YanXiShePicPrase {

    /**
     * 获取课程列表
     * @return
     */
    String getCourseList(String userId,Integer page);

    /**
     * 获取课程详情
     * @param courseId
     * @return
     */
    CourseDetailDTO getCourseDetailById(String userId, String courseId);

    /**
     * 获取课程的ppt图片
     * @return
     */
    String getCoursePptsById(String userId,String courseId);


}
