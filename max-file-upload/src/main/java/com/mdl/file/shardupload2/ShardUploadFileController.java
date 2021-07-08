package com.mdl.file.shardupload2;

import com.mdl.file.shardupload.config.Result;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.file.shardupload2
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月20日 18:57
 * ----------------- ----------------- -----------------
 */

@CrossOrigin
@Controller
@RequestMapping("/api/upload")
public class ShardUploadFileController {

  @Value("${yakir.upload.dir}")
  private String finalDirPath;

  @PostMapping("/part")
  @ResponseBody
  public Result bigFile(HttpServletRequest request, HttpServletResponse response, String guid,
      Integer chunk, MultipartFile file, Integer chunks) {
    String soundPartUrl = "";
    try {
      String projectUrl = System.getProperty("user.dir").replaceAll("\\\\", "/");
//      String projectUrl = finalDirPath;
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
      if (isMultipart) {
        if (chunk == null) {
          chunk = 0;
        }
        // 临时目录用来存放所有分片文件
        String tempFileDir = projectUrl + "/upload/" + guid;
        File parentFileDir = new File(tempFileDir);
        if (!parentFileDir.exists()) {
          parentFileDir.mkdirs();
        }
        // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
        File tempPartFile = new File(parentFileDir, guid + "_" + chunk + ".part");
        FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);

        soundPartUrl = parentFileDir.getAbsolutePath();
      }

    } catch (Exception e) {
      return Result.fail(e.getMessage());
    }
    return Result.ok("上次成功:" + soundPartUrl);
  }

  @RequestMapping("merge")
  @ResponseBody
  public Result mergeFile(String guid, String fileName) {
    String soundFileUrl = "";
    // 得到 destTempFile 就是最终的文件
    String projectUrl = System.getProperty("user.dir").replaceAll("\\\\", "/");
//    String projectUrl = finalDirPath;
    try {
      String sname = fileName.substring(fileName.lastIndexOf("."));
      //时间格式化格式
      Date currentTime = new Date();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
      //获取当前时间并作为时间戳
      String timeStamp = simpleDateFormat.format(currentTime);
      //拼接新的文件名
      String newName = timeStamp + sname;
      simpleDateFormat = new SimpleDateFormat("yyyyMM");
      String path = projectUrl + "/upload/";
      String tmp = simpleDateFormat.format(currentTime);
      File parentFileDir = new File(path + guid);
      if (parentFileDir.isDirectory()) {
        File destTempFile = new File(path + tmp, newName);
        if (!destTempFile.exists()) {
          //先得到文件的上级目录，并创建上级目录，在创建文件
          destTempFile.getParentFile().mkdir();
          try {
            destTempFile.createNewFile();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        for (int i = 0; i < parentFileDir.listFiles().length; i++) {
          File partFile = new File(parentFileDir, guid + "_" + i + ".part");
          FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
          //遍历"所有分片文件"到"最终文件"中
          FileUtils.copyFile(partFile, destTempfos);
          destTempfos.close();
        }
        // 删除临时目录中的分片文件
        FileUtils.deleteDirectory(parentFileDir);

        soundFileUrl = destTempFile.getAbsolutePath();
        return Result.ok("合并成功:" + soundFileUrl);
      } else {
        return Result.fail("没找到目录");
      }

    } catch (Exception e) {
      return Result.fail(e.getMessage());
    }
  }

}
