package com.mdl.hundun.service;

import com.mdl.hundun.entity.BiDefCoursePptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiekun
 * @since 2020-02-16
 */
public interface BiDefCoursePptService extends IService<BiDefCoursePptEntity> {


    /**
     * 下载某个课程的ppt图片
     * @param courseId
     * @param must
     * @return
     */
    String downloadCoursePPtsByCourseId(String courseId,Boolean must);

    /**
     * 获取所有课程的ppt图片
     * @return
     */
    void downloadAllCoursePPtsByCourseId();

}
