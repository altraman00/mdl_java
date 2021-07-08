package com.mdl.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;
/**
 * @Project : mobvoi-demo
 * @Package Name : com.mdl.mdl.entity
 * @Description : TODO
 * @Author : knight
 * @Create Date : 2020年08月24日 16:13
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class BaseEntity {

  @Id
  @GenericGenerator(name="uuid",strategy = "uuid")
  @GeneratedValue(generator = "uuid")
  @Column(name = "id",columnDefinition = "char(32) COMMENT '主键' ")
  private String id;

  @Column(name="deleted",columnDefinition = "tinyint(1) default 0 COMMENT '是否删除' ")
  private boolean deleted = false;

  @CreatedDate
  @Column(name="create_dt",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ")
  private Timestamp createDt = new Timestamp(System.currentTimeMillis());

  @LastModifiedDate
  @Column(name="modify_dt",updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间' ")
  private Timestamp modifyDt = new Timestamp(System.currentTimeMillis());

  @Column(name="version",columnDefinition = "VARCHAR(100) COMMENT '版本' ")
  private String version = "0";

  @Column(name="sort_no",columnDefinition = "int(11) default 1 COMMENT '排序号' ")
  protected Integer sortNo = 1;

}
