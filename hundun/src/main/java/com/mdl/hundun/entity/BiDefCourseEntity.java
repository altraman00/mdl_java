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
@TableName("bi_def_course")
@ApiModel(value="BiDefCourseEntity对象", description="")
public class BiDefCourseEntity extends Model<BiDefCourseEntity> {

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
    @TableField("action_num")
    private String actionNum;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("allow_download")
    private Integer allowDownload;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("allow_play")
    private Integer allowPlay;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("allow_play_title")
    private String allowPlayTitle;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("buy_stat")
    private String buyStat;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("buy_time")
    private String buyTime;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("course_duration")
    private String courseDuration;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("course_id")
    private String courseId;

    @ApiModelProperty(value = "json文件")
    @TableField("course_json")
    private String courseJson;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("course_score")
    private Integer courseScore;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("course_status")
    private Integer courseStatus;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("cover_image")
    private String coverImage;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("cover_image_1x2")
    private String coverImage1x2;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("cover_image_2x1")
    private String coverImage2x1;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("cover_image_v2")
    private String coverImageV2;

    @ApiModelProperty(value = "xxxxxx")
    private String duration;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("has_bespeak")
    private Integer hasBespeak;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("has_shared")
    private Integer hasShared;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("is_buy")
    private Integer isBuy;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("is_cxy_course")
    private Integer isCxyCourse;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("is_display_ask")
    private Integer isDisplayAsk;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("is_display_reward")
    private Integer isDisplayReward;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("is_elective")
    private Integer isElective;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("is_open_stake")
    private Integer isOpenStake;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("is_special_lesson")
    private Integer isSpecialLesson;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("left_top_image")
    private String leftTopImage;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("lesson_subject")
    private String lessonSubject;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("live_type")
    private Integer liveType;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("note_article_id")
    private String noteArticleId;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("origin_type")
    private Integer originType;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("play_stat")
    private String playStat;

    @ApiModelProperty(value = "json文件")
    @TableField("ppt_json")
    private String pptJson;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("practice_id")
    private String practiceId;

    @ApiModelProperty(value = "xxxxxx")
    private String price;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("purchase_url")
    private String purchaseUrl;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("recommended_language")
    private String recommendedLanguage;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("school_time")
    private String schoolTime;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("silent1x2_ratio")
    private String silent1x2Ratio;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("sku_circle_image")
    private String skuCircleImage;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("sku_mode")
    private String skuMode;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("sku_shadow_image")
    private String skuShadowImage;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("square_image")
    private String squareImage;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("standard_school_time")
    private String standardSchoolTime;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("state_control")
    private Integer stateControl;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("sub_title")
    private String subTitle;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("tag_name")
    private String tagName;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("teacher_head_image")
    private String teacherHeadImage;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("teacher_name")
    private String teacherName;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("teacher_position")
    private String teacherPosition;

    @ApiModelProperty(value = "xxxxxx")
    private String time;

    @ApiModelProperty(value = "xxxxxx")
    private String title;

    @ApiModelProperty(value = "xxxxxx")
    private Integer type;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("watch_icon")
    private String watchIcon;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("watch_times")
    private String watchTimes;

    @ApiModelProperty(value = "xxxxxx")
    @TableField("yzmall_image")
    private String yzmallImage;

    @ApiModelProperty(value = "ppt是否已经下载")
    @TableField("has_download_ppt")
    private Boolean hasDownloadPpt = false;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
