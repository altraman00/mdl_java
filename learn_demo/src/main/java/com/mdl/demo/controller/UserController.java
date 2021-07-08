package com.mdl.demo.controller;

import com.mdl.demo.patterns.chainpattern03.model.Task;
import com.mdl.demo.patterns.chainpattern05.CostHandler;
import com.mdl.demo.patterns.chainpattern05.ExecuteHandler;
import com.mdl.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mobvoi-demo
 * @Package Name : com.mdl.mobvoi
 * @Description : TODO
 * @Author : knight
 * @Create Date : 2020年08月24日 16:11
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@Api(value = "UserController", tags = "用户相关")
@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private List<com.mdl.demo.patterns.chainpattern03.handler.CostHandler> filters;

  @Autowired
  private ExecuteHandler executeHandler;

  @ApiOperation(value = "根据id查询")
  @GetMapping("/name")
  public String bridgeInfo(String id) {
    String byId = userService.findById(id);
    return byId;
  }

  @ApiOperation(value = "检验责任链的order注解")
  @GetMapping("/chain")
  public String testChain() {
    Task task = new Task();
    for (com.mdl.demo.patterns.chainpattern03.handler.CostHandler filter : filters) {
      if (!filter.filter(task)) {
        return "error";
      }
    }
    return "ok";
  }

  /**
   * 测试责任链模式
   */
  @GetMapping("/test/{num}")
  public String testHandler(@PathVariable float num) {
    CostHandler handler = executeHandler.getHandler();
    handler.dealRequest(num);
    return "ok";
  }


//  public static void main(String[] args) {
//    LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
//
//    LocalDateTime midnight2 = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
//    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    String strDate2 = dtf2.format(midnight2);
//    System.out.println(strDate2);
//
//    long millSeconds = ChronoUnit.MILLIS.between(LocalDateTime.now(),midnight);
//    long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
//    System.out.println("当天剩余毫秒3：" + millSeconds);
//    System.out.println("当天剩余秒3：" + seconds);
//
//    long l = TimeUnit.HOURS.toMillis(1);
//
//    System.out.println(l);
//
//  }


  private static String parseProductId(String iosProductId) {
    int lastPointIndex = iosProductId.lastIndexOf('.');
    if (lastPointIndex != -1) {
      String packageName = iosProductId.substring(0, lastPointIndex);
      String productId = iosProductId.substring(lastPointIndex + 1);
      System.out.println("productId:" + productId + "---" + "packageName:" + packageName);
      String packageNameLowerCase = packageName.toLowerCase();
      if(packageNameLowerCase.contains(IOS_HOME_PACKAGE.toLowerCase())
          || packageNameLowerCase.contains(IOS_OVERSEAS_PACKAGE.toLowerCase())){
        return productId;
      }
    }
    return null;
  }

  /**
   * IOS国内支付包名验证
   **/
  private static final String IOS_HOME_PACKAGE = "com.mdl.One";
  /**
   * IOS海外支付包名验证
   **/
  private static final String IOS_OVERSEAS_PACKAGE = "com.mdl.companion.aw";

  public static void main(String[] args) {
//    String packageaName = "com.mdl.One.v2.202001006";
    String packageaName = "com.mdl.companion.aw.202001006";
    String s = parseProductId(packageaName);
    System.out.println("productId：" + s);

  }


}
