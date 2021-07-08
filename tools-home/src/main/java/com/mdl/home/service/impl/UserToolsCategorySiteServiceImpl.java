package com.mdl.home.service.impl;

import com.mdl.home.entity.UserToolsCategorySiteEntity;
import com.mdl.home.repository.UserToolsCategorySiteRepository;
import com.mdl.home.service.UserToolsCategorySiteService;
import com.mdl.home.vo.ToolsSiteVO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserToolsCategorySiteServiceImpl implements UserToolsCategorySiteService {

  @Autowired
  private UserToolsCategorySiteRepository siteRepository;

  private static ToolsSiteVO apply(UserToolsCategorySiteEntity t) {
    ToolsSiteVO toolsSiteVO = new ToolsSiteVO();
    toolsSiteVO.setImg(t.getImg());
    toolsSiteVO.setUrl(t.getUrl());
    toolsSiteVO.setIntro(t.getIntro());
    toolsSiteVO.setTitle(t.getTitle());
    toolsSiteVO.setIntro(t.getIntro());
    toolsSiteVO.setUserName(t.getUserName());
    toolsSiteVO.setCategoryName(t.getCategoryName());
    return toolsSiteVO;
  }

  /**
   * 主页列表
   */
  @Override
  public Map<String, List<ToolsSiteVO>> findByUsername(String userName) {
    List<UserToolsCategorySiteEntity> siteList = siteRepository
        .findAllByUserName(userName);
    Map<String, List<ToolsSiteVO>> collect = siteList.parallelStream().map(t -> {
      ToolsSiteVO toolsSiteVO = apply(t);
      return toolsSiteVO;
    }).collect(Collectors.toList()).stream()
        .collect(Collectors.groupingBy(ToolsSiteVO::getCategoryName));
    return collect;
  }

  /**
   * 全网列表
   */
  @Override
  public List<ToolsSiteVO> findAll() {
    List<UserToolsCategorySiteEntity> all = siteRepository.findAll();
    List<ToolsSiteVO> collect = all.stream().map(UserToolsCategorySiteServiceImpl::apply)
        .collect(Collectors.toList());
    return collect;
  }

  /**
   * 推荐列表
   */
  @Override
  public List<ToolsSiteVO> recommendList() {
    List<UserToolsCategorySiteEntity> recommendList = siteRepository.findAllByRecommend(true);
    List<ToolsSiteVO> collect = recommendList.stream().map(UserToolsCategorySiteServiceImpl::apply)
        .collect(Collectors.toList());
    return collect;
  }

}
