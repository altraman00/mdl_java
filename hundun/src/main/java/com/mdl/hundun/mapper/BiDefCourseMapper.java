package com.mdl.hundun.mapper;

import com.mdl.hundun.entity.BiDefCourseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiekun
 * @since 2020-02-16
 */
@Repository
public interface BiDefCourseMapper extends BaseMapper<BiDefCourseEntity> {

    /**
     * 查询暂时没有ppt链接的课程
     * @return
     */
    List<BiDefCourseEntity> getCourseNonPpt();

}
