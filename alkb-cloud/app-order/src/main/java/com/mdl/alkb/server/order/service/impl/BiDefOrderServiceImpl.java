package com.mdl.alkb.server.order.service.impl;

import com.mdl.alkb.server.order.entity.BiDefOrderEntity;
import com.mdl.alkb.server.order.repository.BiDefOrderRepository;
import com.mdl.alkb.server.order.service.BiDefOrderService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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

public class BiDefOrderServiceImpl implements BiDefOrderService {


  @Autowired
  private BiDefOrderRepository orderRepository;

  @Override
  public String findById(String id) {

    Optional<BiDefOrderEntity> byId = orderRepository.findById(id);

    String name = byId.get().getName();

    BiDefOrderEntity byName = orderRepository.findByName(name);

    String price = byName.getPrice();

    List<BiDefOrderEntity> allByName = orderRepository.findAllByPrice(price);

    String collect = allByName.stream().map(BiDefOrderEntity::getName).collect(Collectors.joining("，"));

    return collect;
  }

  public static void main(String[] args) {
    System.out.println(TimeUnit.HOURS.toMillis(100));
    System.out.println(TimeUnit.HOURS.toMillis(1000));
  }

}
