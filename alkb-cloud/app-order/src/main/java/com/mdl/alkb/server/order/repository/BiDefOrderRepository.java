package com.mdl.alkb.server.order.repository;

import com.mdl.alkb.server.order.entity.BiDefOrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Project : cloudService
 * @Package Name : com.mdl.alkb.fn.repository
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月31日 14:50
 * ----------------- ----------------- -----------------
 */
public interface BiDefOrderRepository extends JpaRepository<BiDefOrderEntity, String> {

  BiDefOrderEntity findByName(String name);

  List<BiDefOrderEntity> findAllByPrice(String price);

}
