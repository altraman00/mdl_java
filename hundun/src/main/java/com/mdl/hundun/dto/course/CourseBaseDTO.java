package com.mdl.hundun.dto.course;


import lombok.Data;

import java.util.List;

@Data
public class CourseBaseDTO {

    private String left_top_image;
    private String action_num;
    private String standard_school_time;
    private int course_score;
    private String course_duration;
    private int live_type;
    private int course_status;
    private int is_buy;
    private String course_id;
    private String play_stat;
    private String allow_play_title;
    private String duration;
    private String sku_shadow_image;
    private int is_open_stake;
    private double silent1x2_ratio;
    private String title;
    private int state_control;
    private String purchase_url;
    private String buy_time;
    private String sub_title;
    private String buy_stat;
    private String parent_id;
    private int has_shared;
    private String square_image;
    private String watch_times;
    private String recommended_language;
    private int allow_download;
    private SeriesInfoBean series_info;
    private String sku_circle_image;
    private String sku_mode;
    private String teacher_name;
    private String cover_image_2x1;
    private String note_article_id;
    private int has_bespeak;
    private String yzmall_image;
    private String price;
    private String sku_name;
    private int allow_play;
    private int is_display_ask;
    private String cover_image_v2;
    private int is_special_lesson;
    private String cover_image_1x2;
    private String lesson_subject;
    private int is_display_reward;
    private String school_time;
    private String teacher_position;
    private ZxjyInfoBean zxjy_info;
    private int is_elective;
    private ExampleInfoBean example_info;
    private int type;
    private int is_cxy_course;
    private String practice_id;
    private String tag_name;
    private String time;
    private String cover_image;
    private String watch_icon;
    private int origin_type;
    private String teacher_head_image;
    private List<MultiTitlesBean> multi_titles;
    private List<String> state_display;
    private List<TeacherListBean> teacher_list;
    private List<?> tag_list;

}
