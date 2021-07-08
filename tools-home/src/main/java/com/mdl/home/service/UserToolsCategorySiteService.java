package com.mdl.home.service;

import com.mdl.home.vo.ToolsSiteVO;
import java.util.List;
import java.util.Map;

public interface UserToolsCategorySiteService {

    Map<String, List<ToolsSiteVO>> findByUsername(String userName);

    List<ToolsSiteVO> findAll();

    List<ToolsSiteVO> recommendList();

}
