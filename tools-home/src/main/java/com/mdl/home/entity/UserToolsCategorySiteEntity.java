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
@Table(name = "tb_user_tools_category_site")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserToolsCategorySiteEntity extends BaseEntity{

  @Column(name="user_id")
  private String userId;

  @Column(name="userName")
  private String userName;

  @Column(name="category_id")
  private String categoryId;

  @Column(name="category_name")
  private String categoryName;

  @Column(name="title")
  private String title;

  @Column(name="url")
  private String url;

  @Column(name="img")
  private String img;

  @Column(name="intro")
  private String intro;

  @Column(name="recommend")
  private boolean recommend;

}
