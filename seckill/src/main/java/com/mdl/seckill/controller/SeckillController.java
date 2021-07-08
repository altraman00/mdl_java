package com.mdl.seckill.controller;


import com.mdl.seckill.entity.SeckillEntity;
import com.mdl.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 秒杀库存表 前端控制器
 * </p>
 *
 * @author xiekun
 * @since 2021-03-07
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

  @Autowired
  private SeckillService seckillService;

  @GetMapping("/by")
  public SeckillEntity queryBySeckillId(long id) {
    return seckillService.queryBySeckillId(id);
  }

}
