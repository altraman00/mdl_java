package com.mdl.hundun.controller;


import com.mdl.hundun.entity.BiDefCourseEntity;
import com.mdl.hundun.exception.BaseResponse;
import com.mdl.hundun.exception.ResultCode;
import com.mdl.hundun.service.BiDefCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiekun
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/bi-def-course-entity")
public class BiDefCourseController {

    private static final Logger logger = LoggerFactory.getLogger(BiDefCourseController.class);

    @Autowired
    private BiDefCourseService biDefCourseService;

    @GetMapping("/hello")
    public BaseResponse<String> hello(@RequestParam(required = false, defaultValue = "999") String str) {
        logger.debug("str:{}", str);
        return new BaseResponse<>(ResultCode.SUCCESS, str);
    }


    /**
     * 获取并保存所有课程
     *
     * @return
     */
    @GetMapping("/save")
    public BaseResponse<Integer> getCourseList() {
        Integer count = biDefCourseService.getAndSaveAllCourse();
        return new BaseResponse<>(ResultCode.SUCCESS, count);
    }


    /**
     * 多线程 -- 获取并保存所有课程的ppt
     *
     * @return
     */
    @GetMapping("/ppt_save")
    public BaseResponse<Integer> getAndSaveCourseList() {
        Integer count = biDefCourseService.getAndSaveCoursePpts();
        return new BaseResponse<>(ResultCode.SUCCESS, count);
    }

    /**
     * 单个课程 -- 根据课程id获取课程详情
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}")
    public BaseResponse<BiDefCourseEntity> getCourseById(@PathVariable(value = "courseId") String courseId) {
        BiDefCourseEntity byId = biDefCourseService.findByCourseId(courseId);
        return new BaseResponse<>(ResultCode.SUCCESS, byId);
    }


    /**
     * 单个课程 -- 获取并保存具体某个课程的ppt
     *
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/ppt")
    public BaseResponse<String> getCoursePPtsByCourseId(@PathVariable("courseId") String courseId) {
        String coursePPT = biDefCourseService.getCoursePPTByCourseId(courseId);
        return new BaseResponse<>(ResultCode.SUCCESS, coursePPT);
    }



}
