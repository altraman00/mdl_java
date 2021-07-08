package com.mdl.alkb.server.order.service;

import com.mdl.alkb.server.order.entity.BiUserOrderEntity;
import java.util.List;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.order.service
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月01日 16:35
 * ----------------- ----------------- -----------------
 */
public interface BiUserOrderService {

  BiUserOrderEntity findById(String id);

  List<BiUserOrderEntity> findByUserId(String userId);

}
