package com.mdl.home.controller;


import com.mdl.home.service.UserToolsCategorySiteService;
import com.mdl.home.vo.ToolsSiteVO;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@Controller
public class UserToolsCategorySiteController {

  @Autowired
  private UserToolsCategorySiteService siteService;

  @RequestMapping("/site/{username}")
  public String home(Model model
      , @PathVariable(value = "username",required = false) String username) {
    username = StringUtils.isEmpty(username) ? "admin" : username;
    Map<String, List<ToolsSiteVO>> categorySiteList = siteService.findByUsername(username);
    model.addAttribute("categorySiteList", categorySiteList);
    return "tools_site";
  }

  @RequestMapping("/site/all")
  public String allSite(Model model) {
    List<ToolsSiteVO> all = siteService.findAll();
    model.addAttribute("allSiteList", all);
    return "allsites";
  }

  @RequestMapping("/site/guest")
  public String guest(Model model) {
    List<ToolsSiteVO> recommendList = siteService.recommendList();
    model.addAttribute("recommendList", recommendList);
    return "guest_book";
  }

}
