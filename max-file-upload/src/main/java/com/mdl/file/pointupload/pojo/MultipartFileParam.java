package com.mdl.file.pointupload.pojo;

import java.io.Serializable;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.file.pointupload.pojo
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月20日 15:54
 * ----------------- ----------------- -----------------
 */

@Data
public class MultipartFileParam implements Serializable {

  /**
   * 当前为第几分片
   */
  private int chunk;
  /**
   * 分片总数
   */
  private int chunkTotal;
  /**
   * 当前文件名
   */
  private String name;
  /**
   * 分块文件传输对象
   */
  private MultipartFile file;
  /**
   * 文件MD5
   */
  private String md5;


  @Override
  public String toString() {
    return "MultipartFileParam{" +
        ", chunk=" + chunk +
        ", chunkTotal=" + chunkTotal +
        ", name='" + name + '\'' +
        ", file=" + file +
        ", md5='" + md5 + '\'' +
        '}';
  }

}
