package com.mdl.home.service.impl;

import com.mdl.home.entity.UserToolsCategoryEntity;
import com.mdl.home.repository.UserToolsCategoryRepository;
import com.mdl.home.service.UserToolsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserToolsCategoryServiceImpl implements UserToolsCategoryService {

    @Autowired
    private UserToolsCategoryRepository categoryRepository;

    @Override
    public List<UserToolsCategoryEntity> findByUserId(String userId) {
        return categoryRepository.findAllByUserId(userId);
    }
}
