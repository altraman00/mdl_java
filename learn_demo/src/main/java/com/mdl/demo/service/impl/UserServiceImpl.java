package com.mdl.demo.service.impl;

import com.mdl.demo.entity.SysUserEntity;
import com.mdl.demo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @Project : mobvoi-demo
 * @Package Name : com.mdl.mdl.service.impl
 * @Description : TODO
 * @Author : knight
 * @Create Date : 2020年08月24日 16:12
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public String findById(String id) {
    String hsql = "select * from sys_user where id = ?";
    BeanPropertyRowMapper<SysUserEntity> rowMapper = new BeanPropertyRowMapper<>(
        SysUserEntity.class);
    List<SysUserEntity> query = jdbcTemplate.query(hsql, rowMapper, id);
    Integer count = jdbcTemplate
        .queryForObject("select count(1) from sys_user where id=?", Integer.class, id);
    System.out.println("count--->" + count);
    String collect = query.stream().map(SysUserEntity::getName).collect(Collectors.joining(","));
    System.out.println("name collect--->" + collect);
    return collect;
  }

//  @Autowired
//  private SysUserRepository sysUserRepository;
//
//  @Override
//  public String findById(String id) {
//
//    Optional<SysUserEntity> byId = sysUserRepository.findById(id);
//
//    String name = byId.get().getName();
//
//    SysUserEntity byName = sysUserRepository.findByName(name);
//
//    String age = byName.getAge();
//
//    List<SysUserEntity> allByName = sysUserRepository.findAllByAge(age);
//
//    String collect = allByName.stream().map(SysUserEntity::getName).collect(Collectors.joining("，"));
//
//    return collect;
//  }


  public static void main(String[] args) {
//    LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
//    ZoneId zoneId = ZoneId.systemDefault();
//    ZonedDateTime zdt = localDateTime.atZone(zoneId);
//    Date date = Date.from(zdt.toInstant());
//    System.out.println(date);

    String filename = "sssss.xxxx.opus";

    String[] split = filename.split("\\.");

    System.out.println(split[split.length-1]);

  }


}
