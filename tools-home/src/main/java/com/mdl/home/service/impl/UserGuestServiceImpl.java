package com.mdl.home.service.impl;

import com.mdl.home.entity.UserGuestEntity;
import com.mdl.home.repository.UserGuestRepository;
import com.mdl.home.service.UserGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home.service.impl
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年01月09日 12:45
 * ----------------- ----------------- -----------------
 */

@Service
public class UserGuestServiceImpl implements UserGuestService {

  @Autowired
  private UserGuestRepository userGuestRepository;

  @Override
  public void saveAndFlush(UserGuestEntity userGuestEntity) {
    userGuestRepository.saveAndFlush(userGuestEntity);
  }

}
