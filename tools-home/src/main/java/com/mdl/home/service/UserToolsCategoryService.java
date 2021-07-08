package com.mdl.home.service;

import com.mdl.home.entity.UserToolsCategoryEntity;

import java.util.List;

public interface UserToolsCategoryService {

    List<UserToolsCategoryEntity> findByUserId(String userId);

}
