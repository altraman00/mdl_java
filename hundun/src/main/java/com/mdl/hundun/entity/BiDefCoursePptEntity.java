package com.mdl.hundun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiekun
 * @since 2020-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bi_def_course_ppt")
@ApiModel(value="BiDefCoursePptEntity对象", description="")
public class BiDefCoursePptEntity extends Model<BiDefCoursePptEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_dt")
    private LocalDateTime createDt;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("modify_dt")
    private LocalDateTime modifyDt;

    @ApiModelProperty(value = "排序号")
    @TableField("sort_no")
    private Integer sortNo;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("course_id")
    private String courseId;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("json_detail")
    private String jsonDetail;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("ppt_url")
    private String pptUrl;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("video_id")
    private String videoId;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("video_name")
    private String videoName;

    @TableField("def_course_id")
    private String defCourseId;

    @TableField("course_name")
    private String courseName;

    @TableField("teacher_name")
    private String teacherName;

    @ApiModelProperty(value = "ppt是否已经下载")
    @TableField("has_download_ppt")
    private Boolean hasDownloadPpt = false;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
