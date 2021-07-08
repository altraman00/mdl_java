package com.mdl.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
