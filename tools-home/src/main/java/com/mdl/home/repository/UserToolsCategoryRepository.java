package com.mdl.home.repository;

import com.mdl.home.entity.UserToolsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToolsCategoryRepository extends JpaRepository<UserToolsCategoryEntity, String> {

    List<UserToolsCategoryEntity> findAllByUserId(String userId);

}