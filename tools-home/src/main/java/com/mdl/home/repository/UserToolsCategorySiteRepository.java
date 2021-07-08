package com.mdl.home.repository;

import com.mdl.home.entity.UserToolsCategorySiteEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToolsCategorySiteRepository extends JpaRepository<UserToolsCategorySiteEntity, String> {

    List<UserToolsCategorySiteEntity> findAllByUserName(String username);

    List<UserToolsCategorySiteEntity> findAllByRecommend(boolean recommend);

}