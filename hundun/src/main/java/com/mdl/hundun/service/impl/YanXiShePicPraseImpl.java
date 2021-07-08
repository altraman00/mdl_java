package com.mdl.hundun.service.impl;

import com.mdl.hundun.dto.course.CourseDetailDTO;
import com.mdl.hundun.repository.CourseRepository;
import com.mdl.hundun.service.YanXiShePicPrase;
import com.mdl.hundun.utils.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * 研习社课程列表
 */
@Slf4j
@Service
public class YanXiShePicPraseImpl implements YanXiShePicPrase {

    @Value("${sys.hundun.course.list.url}")
    private String courseListUrl;

    @Value("${sys.hundun.course.detail.url}")
    private String courseDetailUrl;

    @Value("${sys.hundun.course.ppt.url}")
    private String coursePptUrl;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * 获取课程列表
     *
     * @return
     */
    @Override
    public String getCourseList(String userId,Integer page) {
        String courseListUrlFormat = String.format(courseListUrl, userId,page);
        String courseListStr = null;
        try {
            courseListStr = HttpClient.doGet(courseListUrlFormat);
            log.debug("获取的课程列表:{}",courseListStr);
        } catch (IOException e) {
            log.error("获取课程列表网络请求异常", e);
        }
        return courseListStr;
    }


    /**
     * 获取课程ppt列表
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    public String getCoursePptsById(String userId, String courseId) {
        String coursePptUrlFormat = String.format(coursePptUrl, userId,courseId);
        String coursePptStr = null;
        try {
            coursePptStr = HttpClient.doGet(coursePptUrlFormat);
//            log.debug("获取的课程ppt列表:{}",coursePptStr);
        } catch (IOException e) {
            log.error("获取课程ppt列表网络请求异常", e);
        }
        return coursePptStr;
    }


    /**
     * 获取课程详情
     *
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    public CourseDetailDTO getCourseDetailById(String userId, String courseId) {

        return null;
    }


//    public static void main(String[] args) {
//        String html = null;
//        String userId = "0b86375e2e9c5fc3c1b9731ae0148173        ";
//        //获取课程列表
//        String courseList = "https://www.hundun.cn/api/course/course/sku_course_list?clientType=pcweb&versionName=&imei=&net=&user_id=0b86375e2e9c5fc3c1b9731ae0148173        &sku_mode=yxs&page=0";
//        //获取课程详情
//        String courseDetailUrl = "https://www.hundun.cn/api/course/get_course_detail?clientType=pcweb&versionName=&imei=&net=&user_id=0b86375e2e9c5fc3c1b9731ae0148173        &course_id=bed22c5672087fb29dee6ccbf8d406ab";
//        //获取ppt列表
//        String url = "https://www.hundun.cn/api/course/ppt/get_ppt_list?clientType=pcweb&versionName=&imei=&net=&user_id=0b86375e2e9c5fc3c1b9731ae0148173        &course_id=bed22c5672087fb29dee6ccbf8d406ab";
//
//        try {
//            html = HttpClient.doGet(url);
//        } catch (IOException e) {
//            log.error("网络请求异常",e);
//            e.printStackTrace();
//        }
//
//        //解析HTML字符串返回一个Document实现
//        if(StringUtils.isNotEmpty(html)){
//            Document doc = Jsoup.parse(html);
//            log.debug("doc:{}",doc);
//            System.out.println();
//        }
//    }

}
