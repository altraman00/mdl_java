package com.mdl.home.vo;

import java.util.List;
import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home.vo
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年01月07日 22:07
 * ----------------- ----------------- -----------------
 */

@Data
public class UserToolsSiteVO {

  private String categoryName;

  private List<ToolsSiteVO> sites;

}
