package com.mdl.home.controller;

import cn.hutool.json.JSONUtil;
import com.mdl.home.entity.UserGuestEntity;
import com.mdl.home.exception.BaseResponse;
import com.mdl.home.exception.ResultCode;
import com.mdl.home.service.UserGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年01月09日 12:48
 * ----------------- ----------------- -----------------
 */

@RequestMapping("/guest")
@RestController
public class UserGuestController {

  @Autowired
  private UserGuestService userGuestService;

  @PostMapping("/save")
  public BaseResponse home(@RequestBody String msg) {
    UserGuestEntity guestEntity = new UserGuestEntity();
    String content = JSONUtil.parseObj(msg).get("content").toString();
    guestEntity.setContent(content);
    userGuestService.saveAndFlush(guestEntity);
    return new BaseResponse<>(ResultCode.SUCCESS);
  }

}
