package com.mdl.hundun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.mdl.hundun.dto.course.CourseBaseDTO;
import com.mdl.hundun.dto.ppt.CoursePptDTO;
import com.mdl.hundun.entity.BiDefCourseEntity;
import com.mdl.hundun.entity.BiDefCoursePptEntity;
import com.mdl.hundun.mapper.BiDefCourseMapper;
import com.mdl.hundun.mapper.BiDefCoursePptMapper;
import com.mdl.hundun.service.BiDefCourseService;
import com.mdl.hundun.service.YanXiShePicPrase;
import com.mdl.hundun.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiekun
 * @since 2020-02-16
 */
@Service
public class BiDefCourseServiceImpl extends ServiceImpl<BiDefCourseMapper, BiDefCourseEntity> implements BiDefCourseService {

    private static final Logger logger = LoggerFactory.getLogger(BiDefCourseServiceImpl.class);

    @Autowired
    private BiDefCourseMapper biDefCourseMapper;

    @Autowired
    private BiDefCoursePptMapper biDefCoursePptMapper;

    @Autowired
    private YanXiShePicPrase yanXiShePicPrase;

    /**用户id**/
    private final String finalUserId = "0b86375e2e9c5fc3c1b9731ae0148173";

    /**定义多个线程用于获取并保存课程ppt**/
    ExecutorService executor = Executors.newFixedThreadPool(50);

    @Override
    public BiDefCourseEntity findByCourseId(String courseId) {
        QueryWrapper<BiDefCourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        return biDefCourseMapper.selectOne(wrapper);
    }

    /**
     * 获取所有课程
     * @return
     */
    @Override
    public List<BiDefCourseEntity> findAllCourses() {
        QueryWrapper<BiDefCourseEntity> wrapper = new QueryWrapper();
        wrapper.eq("has_download_ppt",false)
                .eq("ppt_json","ppt");
        return biDefCourseMapper.selectList(wrapper);
    }

    /**
     * 获取课程列表
     *
     * @return
     */
    @Override
    public Integer getAndSaveAllCourse() {
        Integer page = 0;
        //总条数
        int totalCount = 0;
        //总页数
        int totalPage = 0;
        int perSize = 15;
        do {
            logger.debug("当前页数page:{}", page);
            System.out.println("当前页数--->" + page);
            String courseListStr = yanXiShePicPrase.getCourseList(finalUserId, page);
            ++page;
            //转换成对象
            JsonNode courseNum = JSONUtils.getFieldJsonNodeFromJson(courseListStr, "data.course_num");
            totalCount = Integer.valueOf(courseNum.asText().trim());
            totalPage = totalCount / perSize;
            int lastSize = totalCount % perSize;
            if (page == 0 && lastSize > 0) {
                totalPage = totalPage + 1;
            }
            JsonNode course_list = JSONUtils.getFieldJsonNodeFromJson(courseListStr, "data.course_list");
            String courses = JSONUtils.objectToJson(course_list);
            List<CourseBaseDTO> courseBaseDTOS = JSONUtils.jsonToList(courses, CourseBaseDTO.class);

            for (int i = 0; i < courseBaseDTOS.size(); i++) {
                CourseBaseDTO t = courseBaseDTOS.get(i);
                BiDefCourseEntity courseEntity = new BiDefCourseEntity();
                BeanUtils.copyProperties(t, courseEntity);
                logger.debug("课程序号:{} -->名字:{}", i + 1, courseEntity.getTitle());
                biDefCourseMapper.insert(courseEntity);
            }

        } while (totalPage >= page);

        return totalCount;
    }


    /**
     * 获取并保存课程列表
     *
     * @return
     */
    @Override
    public Integer getAndSaveCoursePpts() {
        long start = System.currentTimeMillis();
        //获取所有的课程
        QueryWrapper<BiDefCourseEntity> queryWrapper = new QueryWrapper<>();
//        List<BiDefCourseEntity> allCourse = biDefCourseMapper.selectList(null);
        List<BiDefCourseEntity> allCourse = biDefCourseMapper.getCourseNonPpt();

        List<CompletableFuture<Void>> collect = allCourse.stream()
                .map(t -> ansyGetAndSaveCoursePpt(t))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                collect.toArray(new CompletableFuture[collect.size()])
        );

        allFutures.join();
        logger.info("所有线程已执行完[{}]", allFutures.isDone());

        allFutures.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                logger.info("执行最后一步操作");
                // doSth();
                long end = System.currentTimeMillis();
                logger.info("耗时:" + (end - start) / 1000L);
            }
        });
        return null;
    }


    /**
     * 获取并保存课程ppt
     * executor 可不传入,则默认最多3个线程
     *
     * @param courseEntity
     */
    public CompletableFuture<Void> ansyGetAndSaveCoursePpt(BiDefCourseEntity courseEntity) {
        return CompletableFuture.runAsync(() -> {
            logger.info("当前线程名称:{} --> courseName:{},", Thread.currentThread().getName(), courseEntity.getTitle());
            String courseId = courseEntity.getCourseId();
            //获取课程的ppt
            getCoursePPTByCourse(courseEntity);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                logger.error("ansyGetAndSaveCoursePpt异常",e);
//            }
        }, executor).exceptionally(new Function<Throwable, Void>() {
            //捕捉异常,不会导致整个流程中断
            @Override
            public Void apply(Throwable throwable) {
                logger.info("线程[{}]发生了异常, 继续执行其他线程,错误详情[{}]"
                        , Thread.currentThread().getName(), throwable.getMessage());
                return null;
            }
        });
    }


    /**
     * 获取ppt列表
     *
     * @param courseEntity
     */
    @Override
    public String getCoursePPTByCourse(BiDefCourseEntity courseEntity) {
        String courseId = courseEntity.getCourseId();
        String coursePptsById = yanXiShePicPrase.getCoursePptsById(finalUserId, courseId);
        //转换成对象
        JsonNode ppt_info = JSONUtils.getFieldJsonNodeFromJson(coursePptsById, "data.ppt_info");
        String course_ppt_info = JSONUtils.objectToJson(ppt_info);
        List<CoursePptDTO> coursePptDTOS = JSONUtils.jsonToList(course_ppt_info, CoursePptDTO.class);
        if(coursePptDTOS != null && coursePptDTOS.size()>0){
            coursePptDTOS.forEach(t -> {
                List<String> ppt_urls = t.getPpt_urls();
                ppt_urls.forEach(p -> {
                    BiDefCoursePptEntity coursePptEntity = new BiDefCoursePptEntity();
                    coursePptEntity.setCourseName(courseEntity.getTitle());
                    coursePptEntity.setTeacherName(courseEntity.getTeacherName());
                    coursePptEntity.setCourseId(courseId);
                    coursePptEntity.setPptUrl(p);
                    coursePptEntity.setVideoId(t.getVideo_id());
                    coursePptEntity.setVideoName(t.getVideo_name());
                    biDefCoursePptMapper.insert(coursePptEntity);
                });
            });
        }else{
            logger.debug("no-ppt----->courseName:{}----->courseId:{}",courseEntity.getTitle(),courseEntity.getCourseId());
            courseEntity.setPptJson("no-ppt");
            biDefCourseMapper.updateById(courseEntity);
        }
        return course_ppt_info;
    }


    /**
     * 根据课程id获取课程的ppt
     * @param courseId
     * @return
     */
    @Override
    public String getCoursePPTByCourseId(String courseId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        BiDefCourseEntity biDefCourseEntity = biDefCourseMapper.selectOne(wrapper);
        String coursePPTByCourse = getCoursePPTByCourse(biDefCourseEntity);
        return coursePPTByCourse;
    }

}
