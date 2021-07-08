package com.mdl.file.shardupload.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.file.shardupload.model
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月19日 16:53
 * ----------------- ----------------- -----------------
 */

@Data
@TableName(value = "shard_file")
public class ShardFileEntity {

  /**
   * id
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * 相对路径
   */
  private String path;

  /**
   * 文件名
   */
  private String name;

  /**
   * 后缀
   */
  private String suffix;

  /**
   * 大小|字节B
   */
  private Integer size;

  /**
   * 已上传分片
   */
  private Integer shardIndex;

  /**
   * 分片大小|B
   */
  private Integer shardSize;

  /**
   * 分片总数
   */
  private Integer shardTotal;

  /**
   * 文件标识
   */
  private String fileKey;

  /**
   * 创建时间
   */
  private Date createdAt;

  /**
   * 修改时间
   */
  private Date updatedAt;

}
