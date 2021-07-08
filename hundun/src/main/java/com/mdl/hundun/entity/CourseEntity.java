package com.mdl.hundun.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Table(name = "bi_def_course")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity extends BaseEntity{

    @Column(name = "left_top_image",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String left_top_image;

    @Column(name = "action_num",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String action_num;

    @Column(name = "standard_school_time",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String standard_school_time;

    @Column(name = "course_score",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer course_score;

    @Column(name = "course_duration",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String course_duration;

    @Column(name = "live_type",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer live_type;

    @Column(name = "course_status",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer course_status;

    @Column(name = "is_buy",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer is_buy;

    @Column(name = "course_id",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String course_id;

    @Column(name = "play_stat",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String play_stat;

    @Column(name = "allow_play_title",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String allow_play_title;

    @Column(name = "duration",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String duration;

    @Column(name = "sku_shadow_image",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String sku_shadow_image;

    @Column(name = "is_open_stake",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer is_open_stake;

    @Column(name = "silent1x2_ratio",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private double silent1x2_ratio;

    @Column(name = "title",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String title;

    @Column(name = "state_control",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer state_control;

    @Column(name = "purchase_url",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String purchase_url;

    @Column(name = "buy_time",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String buy_time;

    @Column(name = "sub_title",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String sub_title;

    @Column(name = "buy_stat",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String buy_stat;

    @Column(name = "parent_id",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String parent_id;

    @Column(name = "has_shared",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer has_shared;

    @Column(name = "square_image",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String square_image;

    @Column(name = "watch_times",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String watch_times;

    @Column(name = "recommended_language",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String recommended_language;

    @Column(name = "allow_download",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer allow_download;


    @Column(name = "sku_circle_image",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String sku_circle_image;

    @Column(name = "sku_mode",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String sku_mode;

    @Column(name = "teacher_name",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String teacher_name;

    @Column(name = "cover_image_2x1",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String cover_image_2x1;

    @Column(name = "note_article_id",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String note_article_id;

    @Column(name = "has_bespeak",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer has_bespeak;

    @Column(name = "yzmall_image",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String yzmall_image;

    @Column(name = "price",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String price;

    @Column(name = "sku_name",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String sku_name;

    @Column(name = "allow_play",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer allow_play;

    @Column(name = "is_display_ask",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer is_display_ask;

    @Column(name = "cover_image_v2",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String cover_image_v2;

    @Column(name = "is_special_lesson",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer is_special_lesson;

    @Column(name = "cover_image_1x2",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String cover_image_1x2;

    @Column(name = "lesson_subject",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String lesson_subject;

    @Column(name = "is_display_reward",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer is_display_reward;

    @Column(name = "school_time",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String school_time;

    @Column(name = "teacher_position",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String teacher_position;

    @Column(name = "is_elective",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer is_elective;

    @Column(name = "type",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer type;

    @Column(name = "is_cxy_course",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer is_cxy_course;

    @Column(name = "practice_id",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String practice_id;

    @Column(name = "tag_name",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String tag_name;

    @Column(name = "time",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String time;

    @Column(name = "cover_image",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String cover_image;

    @Column(name = "watch_icon",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String watch_icon;

    @Column(name = "origin_type",columnDefinition = "INT(11) COMMENT 'xxxxxx' ")
    private Integer origin_type;

    @Column(name = "teacher_head_image",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String teacher_head_image;

    @Column(name = "course_json",columnDefinition = "text COMMENT 'json文件' ")
    private String course_json;

    @Column(name = "ppt_json",columnDefinition = "text COMMENT 'json文件' ")
    private String ppt_json;


//    private ZxjyInfoBean zxjy_info;
//    private ExampleInfoBean example_info;
//    private SeriesInfoBean series_info;
    
//    private List<MultiTitlesBean> multi_titles;
//    private List<String> state_display;
//    private List<TeacherListBean> teacher_list;
//    private List<?> tag_list;

}
