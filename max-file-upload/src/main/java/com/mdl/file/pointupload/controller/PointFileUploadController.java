package com.mdl.file.pointupload.controller;

import com.mdl.file.pointupload.pojo.MultipartFileParam;
import com.mdl.file.pointupload.pojo.ResultStatus;
import com.mdl.file.pointupload.pojo.ResultVo;
import com.mdl.file.pointupload.utils.Constants;
import com.mdl.file.pointupload.utils.FileUploadUtils;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.file.pointupload
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月20日 15:52
 * ----------------- ----------------- -----------------
 */

@Controller
@RequestMapping("/point_file")
public class PointFileUploadController {

  private static final Logger logger = LoggerFactory.getLogger(PointFileUploadController.class);

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private FileUploadUtils fileUploadUtils;

  @PostMapping("checkFileMd5")
  @ResponseBody
  public Object checkFileMd5(String md5) throws IOException {
    //根据md5查询当前文件处理状态
//    Object processingObj = stringRedisTemplate.opsForHash().get(Constants.FILE_UPLOAD_STATUS, md5);
    Object processingObj = null;
    //若未查询到对象 则返回错误提示 文件未上传过
    if (processingObj == null) {
      return new ResultVo(ResultStatus.NO_HAVE);
    }
    String processingStr = processingObj.toString();
    //当前文件上传状态
    boolean processing = Boolean.parseBoolean(processingStr);
    String value = stringRedisTemplate.opsForValue().get(Constants.FILE_MD5_KEY + md5);
    if (processing) {
      return new ResultVo(ResultStatus.IS_HAVE, value);
    } else {
      File confFile = new File(value);
      byte[] completeList = FileUtils.readFileToByteArray(confFile);
      LinkedList<String> missChunkList = new LinkedList<>();
      for (int i = 0; i < completeList.length; i++) {
        if (completeList[i] != Byte.MAX_VALUE) {
          missChunkList.add(i + "");
        }
      }
      return new ResultVo<>(ResultStatus.ING_HAVE, missChunkList);
    }
  }


  @PostMapping("fileUpload")
  @ResponseBody
  public ResponseEntity fileUpload(MultipartFileParam param, HttpServletRequest request) {
    boolean multipartContent = ServletFileUpload.isMultipartContent(request);
    if (multipartContent) {
      logger.info("上传文件start");
      try {
//        //方式一 较慢
//        fileUploadUtils.uploadFileRandomAccessFile(param);

        //方式二 较快
        fileUploadUtils.uploadFileByMappedByteBuffer(param);
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("上传文件错误");
      }
      logger.info("上传文件结束");
    }
    return ResponseEntity.ok().body("上传成功");
  }

}
