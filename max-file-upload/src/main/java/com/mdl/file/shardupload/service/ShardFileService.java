package com.mdl.file.shardupload.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdl.file.shardupload.mapper.ShardFileMapper;
import com.mdl.file.shardupload.model.ShardFileEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.file.shardupload.service
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月19日 16:54
 * ----------------- ----------------- -----------------
 */

@Service
public class ShardFileService {

  @Autowired
  private ShardFileMapper shardFileMapper;

  //保存文件
  public void save(ShardFileEntity file1){
    //根据 数据库的 文件标识来查询 当前视频 是否存在
    LambdaQueryWrapper<ShardFileEntity> lambda = new QueryWrapper<ShardFileEntity>().lambda();
    lambda.eq(ShardFileEntity::getFileKey,file1.getFileKey());
    List<ShardFileEntity> shardFileEntities = shardFileMapper.selectList(lambda);
    //如果存在就话就修改
    if(shardFileEntities.size()!=0){
      //根据key来修改
      LambdaQueryWrapper<ShardFileEntity> lambda1 = new QueryWrapper<ShardFileEntity>().lambda();
      lambda1.eq(ShardFileEntity::getFileKey,file1.getFileKey());
      shardFileMapper.update(file1,lambda1);
    }else
    {
      //不存在就添加
      shardFileMapper.insert(file1);
    }
  }

  //检查文件
  public List<ShardFileEntity> check(String key){
    LambdaQueryWrapper<ShardFileEntity> lambda = new QueryWrapper<ShardFileEntity>().lambda();
    lambda.eq(ShardFileEntity::getFileKey,key);
    List<ShardFileEntity> dtos = shardFileMapper.selectList(lambda);
    return dtos;
  }

}
