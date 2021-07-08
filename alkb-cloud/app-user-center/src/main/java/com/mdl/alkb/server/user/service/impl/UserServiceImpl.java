package com.mdl.alkb.server.user.service.impl;

import com.mdl.alkb.server.user.entity.SysUserEntity;
import com.mdl.alkb.server.user.repository.SysUserRepository;
import com.mdl.alkb.server.user.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project : cloudService
 * @Package Name : com.mdl.alkb.fn.service
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月31日 14:50
 * ----------------- ----------------- -----------------
 */

@Service
public class UserServiceImpl implements UserService {


  @Autowired
  private SysUserRepository sysUserRepository;

  @Override
  public String findById(String id) {

    Optional<SysUserEntity> byId = sysUserRepository.findById(id);

    String name = byId.get().getName();

    SysUserEntity byName = sysUserRepository.findByName(name);

    String age = byName.getAge();

    List<SysUserEntity> allByName = sysUserRepository.findAllByAge(age);

    String collect = allByName.stream().map(SysUserEntity::getName).collect(Collectors.joining("，"));

    return collect;
  }

}
