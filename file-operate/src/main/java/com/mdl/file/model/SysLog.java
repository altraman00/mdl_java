package com.mdl.file.model;

import java.util.Date;
import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.file.model
 * @Author : xiekun
 * @Create Date : 2020年12月03日 14:33
 * ----------------- ----------------- -----------------
 */

@Data
public class SysLog {

  private String id;
  private String username;
  private String operation;
  private String method;
  private Date createDate;

  public SysLog() {
  }

  public SysLog(String id, String username, String operation, String method,
      Date createDate) {
    this.id = id;
    this.username = username;
    this.operation = operation;
    this.method = method;
    this.createDate = createDate;
  }

}
