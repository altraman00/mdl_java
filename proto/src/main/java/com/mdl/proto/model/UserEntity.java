package com.mdl.proto.model;

import com.mdl.proto.model.UserProto.UserInfo;
import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.proto.model
 * @Author : xiekun
 * @Create Date : 2020年11月27日 18:00
 * ----------------- ----------------- -----------------
 */

@Data
public class UserEntity{

  private static final long serialVersionUID = 1L;

  /**
   * 用户ID
   */
  private String name;

  /**
   * 用户名
   */
  private String age;

  /**
   * 创建时间
   */
  private String sex;


  public UserInfo toProto() {
    return UserInfo.newBuilder()
        .setName(name)
        .setAge(age)
        .setSex(sex)
        .build();
  }

}
