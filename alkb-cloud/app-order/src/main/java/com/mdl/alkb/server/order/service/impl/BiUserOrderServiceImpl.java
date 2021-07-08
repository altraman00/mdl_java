package com.mdl.alkb.server.order.service.impl;

import com.mdl.alkb.server.order.entity.BiUserOrderEntity;
import com.mdl.alkb.server.order.repository.BiUserOrderRepository;
import com.mdl.alkb.server.order.service.BiUserOrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.order.service.impl
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月01日 16:35
 * ----------------- ----------------- -----------------
 */

@Service
public class BiUserOrderServiceImpl implements BiUserOrderService {

  @Autowired
  private BiUserOrderRepository userOrderRepository;

  @Override
  public BiUserOrderEntity findById(String id) {
    return userOrderRepository.getOne(id);
  }

  @Override
  public List<BiUserOrderEntity> findByUserId(String userId) {
    return userOrderRepository.findByUserId(userId);
  }
}
