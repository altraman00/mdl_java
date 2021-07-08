package com.mdl.alkb.server.order.entity;

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
@Table(name = "bi_def_order")
public class BiDefOrderEntity extends BaseEntity {

  @ApiModelProperty("名称")
  @Column(name = "name", columnDefinition = "VARCHAR(255) COMMENT '名称' ")
  private String name;

  @ApiModelProperty("价格")
  @Column(name = "price", columnDefinition = "char(32) COMMENT '年龄' ")
  private String price;

}
