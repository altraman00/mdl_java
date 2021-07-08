package com.mdl.hundun.service;

import com.mdl.hundun.entity.BiDefCourseEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mdl.hundun.entity.CourseEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiekun
 * @since 2020-02-16
 */
public interface BiDefCourseService extends IService<BiDefCourseEntity> {

    /**
     * 根据课程id查找课程
     * @param courseId
     * @return
     */
    BiDefCourseEntity findByCourseId(String courseId);

    /**
     * 获取所有课程
     * @return
     */
    List<BiDefCourseEntity> findAllCourses();

    /**
     * 获取课程列表
     * @return
     */
    Integer getAndSaveAllCourse();

    /**
     * 获取并保存课程列表
     * @return
     */
    Integer getAndSaveCoursePpts();

    /**
     * 获取课程ppt
     * @param courseEntity
     */
    String getCoursePPTByCourse(BiDefCourseEntity courseEntity);


    /**
     * 根据课程id获取并保存课程ppt
     * @param courseId
     * @return
     */
    String getCoursePPTByCourseId(String courseId);

}
