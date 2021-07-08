package com.mdl.home.repository;

import com.mdl.home.entity.UserGuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home.repository
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年01月09日 12:45
 * ----------------- ----------------- -----------------
 */
public interface UserGuestRepository extends JpaRepository<UserGuestEntity, String> {

}
