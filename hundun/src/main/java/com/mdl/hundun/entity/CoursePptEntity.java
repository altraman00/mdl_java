package com.mdl.hundun.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@Table(name = "bi_def_course_ppt")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CoursePptEntity extends BaseEntity{

    @Column(name = "def_course_id",columnDefinition = "VARCHAR(200) COMMENT 'bi_def_course的id' ")
    private String def_course_id;

    @Column(name = "course_id",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String course_id;

    @Column(name = "course_name",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String course_name;

    @Column(name = "teacher_name",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String teacher_name;

    @Column(name = "video_id",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String video_id;

    @Column(name = "video_name",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String video_name;

    @Column(name = "ppt_url",columnDefinition = "VARCHAR(200) COMMENT 'xxxxxx' ")
    private String ppt_url;

    @Column(name = "json_detail",columnDefinition = "TEXT COMMENT 'xxxxxx' ")
    private String json_detail;

}
