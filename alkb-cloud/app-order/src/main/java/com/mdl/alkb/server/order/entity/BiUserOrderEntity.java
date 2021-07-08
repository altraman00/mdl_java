package com.mdl.alkb.server.order.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.order.entity
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月01日 16:36
 * ----------------- ----------------- -----------------
 */

@Data
@Entity
@Table(name = "bi_user_order")
public class BiUserOrderEntity extends BaseEntity{

  @ApiModelProperty("用户id")
  @Column(name = "user_id")
  private String userId;

  @ApiModelProperty("名称")
  @Column(name = "name")
  private String name;

  @ApiModelProperty("价格")
  @Column(name = "price")
  private String price;

}
