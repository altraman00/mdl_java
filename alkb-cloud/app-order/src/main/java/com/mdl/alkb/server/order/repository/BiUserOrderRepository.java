package com.mdl.alkb.server.order.repository;

import com.mdl.alkb.server.order.entity.BiUserOrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.order.repository
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月01日 16:38
 * ----------------- ----------------- -----------------
 */
public interface BiUserOrderRepository extends JpaRepository<BiUserOrderEntity, String> {

  List<BiUserOrderEntity> findByUserId(String userId);

}
