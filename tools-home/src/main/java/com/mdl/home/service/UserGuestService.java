package com.mdl.home.service;

import com.mdl.home.entity.UserGuestEntity;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home.service
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年01月09日 12:44
 * ----------------- ----------------- -----------------
 */
public interface UserGuestService {

  void saveAndFlush(UserGuestEntity userGuestEntity);

}
