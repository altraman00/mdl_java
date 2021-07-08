package com.mdl.home.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年01月01日 22:34
 * ----------------- ----------------- -----------------
 */

@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_user_tools_category")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SysUserEntity extends BaseEntity{

  @Column(name="user_name")
  private String userName;

  @Column(name="open_id")
  private String openId;

}
