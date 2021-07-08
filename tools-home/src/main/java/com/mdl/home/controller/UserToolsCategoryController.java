package com.mdl.home.controller;


import com.mdl.home.entity.UserToolsCategoryEntity;
import com.mdl.home.service.UserToolsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/home")
@Controller
public class UserToolsCategoryController {

    @Autowired
    private UserToolsCategoryService categoryService;

    @RequestMapping("/category")
    public String home(Model model,String userId) {
        List<UserToolsCategoryEntity> categoryList = categoryService.findByUserId(userId);

        model.addAttribute("msg", "我是第一个页面");
        model.addAttribute("categoryList", categoryList);
        return "home";
    }

}
