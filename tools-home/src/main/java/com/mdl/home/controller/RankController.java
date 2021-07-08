package com.mdl.home.controller;

import cn.hutool.json.JSONUtil;
import com.mdl.home.util.DateUtil;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年03月30日 14:28
 * ----------------- ----------------- -----------------
 */


@RestController
@RequestMapping("/rank")
public class RankController {

  @Autowired
  private RedisTemplate redisTemplate;


  private static String RANK_CALORIE = "rank_calorie:";
  private static String currDateStr = DateUtil.getCurrDateStr();
  private static String key = RANK_CALORIE + currDateStr;

  /**
   * 实现排序，热度，积分榜等功能，更多方法可以可以搜索zSetOperations
   */
  @GetMapping(value = "score")
  public String rank() {

    String currDateStr = DateUtil.getCurrDateStr();
    String key = RANK_CALORIE + currDateStr;

    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    zSetOperations.add(key, "one", 1);
    zSetOperations.add(key, "four", 4);
    zSetOperations.add(key, "three", 11);
    zSetOperations.add(key, "five", 5);
    zSetOperations.add(key, "six", 6);

//    //从子集中找到Smin<=score<=Smax的元素集合
//    //value在此区间中的。
//    Set set = zSetOperations.rangeByScore(key, 1, 5);
//    System.out.println("打印v1的值在100-120区间的" + set.size());
//
//    //索引start<=index<=end的元素子集，返回泛型接口（包括score和value），正序
//    //返回score和value，set中的前两个
//    Set set1 = zSetOperations.rangeWithScores(key, 0, 1);
//
//    //键为K的集合，索引start<=index<=end的元素子集，正序
//    //返回排序后的value中的前两个
//    Set set2 = zSetOperations.range(key, 0, 1);
//
//    //键为K的集合，索引start<=index<=end的元素子集，倒序
//    //返回排序后的最后两个
//    Set set3 = zSetOperations.reverseRange(key, 0, 1);

    Set set4 = zSetOperations.rangeWithScores(key, 1, -1);

    return JSONUtil.toJsonStr(set4);
  }

  /**
   * 查询top n 倒叙排列
   */
  @GetMapping(value = "rank_by_score")
  public String getRangeWithScores(@RequestParam("topCount") int topCount) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    Set set = zSetOperations.reverseRange(key, 0, topCount);
    return JSONUtil.toJsonStr(set);
  }

  /**
   * 查询某个区间的根据分数倒序排列
   */
  @GetMapping(value = "rank_between_score")
  public String getRangeBetweenScores(@RequestParam("startScore") int startScore,
      @RequestParam("endScore") int endScore) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    Set set = zSetOperations.reverseRangeWithScores(key, startScore, endScore);
    return JSONUtil.toJsonStr(set);
  }


  /**
   * 增加分数
   */
  @PostMapping(value = "add_score")
  public Double addScore(@RequestParam("name") String name, @RequestParam("score") int score) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    Double getScore = zSetOperations.score(key, name);
    System.out.println("[add_score] getScore:" + getScore);
    Double scoreVal = zSetOperations.incrementScore(key, name, score);
    System.out.println("[add_score] scoreVal:" + scoreVal);
    return scoreVal;
  }


  /**
   * 增加分数
   */
  @DeleteMapping(value = "byObj")
  public Boolean deleteByObj(@RequestParam("name") String name) {
    return redisTemplate.delete(name);
  }


}






















