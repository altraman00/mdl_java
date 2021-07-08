package com.mdl.hundun.controller;


import com.mdl.hundun.exception.BaseResponse;
import com.mdl.hundun.exception.ResultCode;
import com.mdl.hundun.service.BiDefCoursePptService;
import com.mdl.hundun.service.BiDefCourseService;
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
@RequestMapping("/bi-def-course-ppt-entity")
public class BiDefCoursePptController {

    @Autowired
    private BiDefCoursePptService biDefCoursePptService;

    /**
     * 单个课程 -- 下载某个课程的ppt图片
     * @param courseId
     * @param must true:即使已经下载过也要强制性下载
     * @return
     */
    @GetMapping("/{courseId}/ppt_download")
    public BaseResponse<String> downloadCoursePPtsByCourseId(@PathVariable("courseId") String courseId
            ,@RequestParam(required = false,defaultValue = "false") Boolean must) {
        String result = biDefCoursePptService.downloadCoursePPtsByCourseId(courseId,must);
        return new BaseResponse<>(ResultCode.SUCCESS,result);
    }

    /**
     * 多线程 -- 下载所有课程的ppt图片
     * @return
     */
    @GetMapping("/all_courses_ppts_download")
    public BaseResponse<String> downloadAllCoursePPtsByCourseId() {
        biDefCoursePptService.downloadAllCoursePPtsByCourseId();
        return new BaseResponse<>(ResultCode.SUCCESS);
    }





}
