package com.mdl.alkb.server.user.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * @Project : cloudService
 * @Package Name : com.mdl.alkb.fn.entity
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月31日 14:50
 * ----------------- ----------------- -----------------
 */

@Data
@Entity
@Table(name = "sys_user")
public class SysUserEntity extends BaseEntity {

  @ApiModelProperty("名称")
  @Column(name = "name", columnDefinition = "VARCHAR(255) COMMENT '名称' ")
  private String name;

  @ApiModelProperty("年龄")
  @Column(name = "age", columnDefinition = "char(32) COMMENT '年龄' ")
  private String age;

}
