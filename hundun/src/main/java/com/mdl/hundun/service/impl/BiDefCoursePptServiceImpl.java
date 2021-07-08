package com.mdl.hundun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdl.hundun.entity.BiDefCourseEntity;
import com.mdl.hundun.entity.BiDefCoursePptEntity;
import com.mdl.hundun.mapper.BiDefCoursePptMapper;
import com.mdl.hundun.service.BiDefCoursePptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdl.hundun.service.BiDefCourseService;
import com.mdl.hundun.utils.DownLoadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiekun
 * @since 2020-02-16
 */

@Transactional
@Service
public class BiDefCoursePptServiceImpl extends ServiceImpl<BiDefCoursePptMapper, BiDefCoursePptEntity> implements BiDefCoursePptService {

    private static final Logger logger = LoggerFactory.getLogger(BiDefCoursePptServiceImpl.class);

    /**
     * ppt存放路径
     **/
    private static final String filePath = "D:\\xiekun\\混沌研习社";

    /**
     * 图片涉及到的图片格式
     **/
    String[] picFormats = {"jpg", "png", "jpeg"};

    @Autowired
    private BiDefCourseService biDefCourseService;

    @Autowired
    private BiDefCoursePptMapper biDefCoursePptMapper;

    @Override
    public String downloadCoursePPtsByCourseId(String courseId,Boolean must) {
        BiDefCourseEntity byCourseId = biDefCourseService.findByCourseId(courseId);
        Boolean hasDownloadPpt = byCourseId.getHasDownloadPpt();

        if (!hasDownloadPpt || must) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("course_id", courseId);
            List<BiDefCoursePptEntity> list = biDefCoursePptMapper.selectList(wrapper);

            String title = byCourseId.getTitle().trim();
            String cousePptSavePath = filePath + "\\" + title;
            File file = new File(filePath + "\\" + title);
            if (!file.exists()) {
                file.mkdir();
                logger.debug("课程ppt存放路径：{}", file.getPath());
            }

            for (int i = 0; i < list.size(); i++) {
                BiDefCoursePptEntity t = list.get(i);
                String pptUrl = t.getPptUrl();
                String[] pptSplit = pptUrl.split("\\.");
                String picFormat = pptSplit[pptSplit.length - 1];
                boolean hasFormatFlag = Arrays.asList(picFormats).contains(picFormat);
                String filename;
                if(!hasFormatFlag){
                    filename = i + "-" + t.getVideoName() + ".png";
                }else{
                    filename = i + "-" + t.getVideoName() + "." + picFormat;
                }
                try {
                    logger.debug("\nppt-pic download,pptUrl:{}\nfilename:{}\ncousePptSavePath:{}", pptUrl, filename, cousePptSavePath);
                    DownLoadUtil.download(pptUrl, filename, cousePptSavePath);
                } catch (Exception e) {
                    logger.error("图片下载异常", e);
                }
            }

            //更新状态
            byCourseId.setHasDownloadPpt(true);
            biDefCourseService.updateById(byCourseId);

            return list.size()+"";
        }else{
            return byCourseId.getTitle()+"-->的ppt已经下载过";
        }

    }


    /**
     * 多线程下载ppt图片
     */
    @Override
    public void downloadAllCoursePPtsByCourseId() {
        List<BiDefCourseEntity> allCourses = biDefCourseService.findAllCourses();
        List<String> allCourseIds = allCourses.stream().map(t -> t.getCourseId()).collect(Collectors.toList());
        List<CompletableFuture<Void>> collectContentFutures = allCourseIds.stream().map(t -> downloadPptHandle(t)).collect(Collectors.toList());

        long start = System.currentTimeMillis();
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                collectContentFutures.toArray(new CompletableFuture[collectContentFutures.size()])
        );

        allFutures.join();
        logger.info("所有线程已执行完[{}]",allFutures.isDone());
        allFutures.whenComplete(new BiConsumer<Void,Throwable>(){
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                logger.info("执行最后一步操作");
                long end = System.currentTimeMillis();
                logger.info("耗时:"+ (end-start)/1000L );
            }
        });

    }

    /**
     * 异步 -- 下载ppt图片
     * @param courseId
     * @return
     */
    CompletableFuture<Void> downloadPptHandle(String courseId){
        ExecutorService downloadPptExecutor = Executors.newFixedThreadPool(150);
        return CompletableFuture.runAsync(() -> {
            downloadCoursePPtsByCourseId(courseId,true);
        },downloadPptExecutor).exceptionally(new Function<Throwable, Void>(){
            @Override
            public Void apply(Throwable throwable) {
                logger.info("线程[{}]发生了异常, 继续执行其他线程,错误详情[{}]",Thread.currentThread().getName(),throwable.getMessage());
                return null;
            }
        }) ;
    }


}
