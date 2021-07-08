package com.mdl.alkb.server.order.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Project : cloudService
 * @Package Name : com.mdl.alkb.fn.entity
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月31日 14:50
 * ----------------- ----------------- -----------------
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
