package com.mdl.alkb.server.gateway.config;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Project : alkb-cloud
 * @Package Name : com.mdl.alkb.server.gateway.config
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年09月04日 11:34
 * ----------------- ----------------- -----------------
 */
@Slf4j
@Component
public class alkbFilter extends ZuulFilter {

  /**
   * fileType：返回一个字符串代表过滤器的类型，在zuul中定义了四中不同生命周期的过滤器类型，具体如下：
   * pre：路由之前
   * routing：路由之时
   * post：路由之后
   * error：发送错误调用
   */
  @Override
  public String filterType() {
    return "pre";
  }


  /**
   * 过滤的顺序
   */
  @Override
  public int filterOrder() {
    return 0;
  }


  /**
   * shoudFilter：这里可以写逻辑判断，是否要过滤，本文true，永远过滤
   */
  @Override
  public boolean shouldFilter() {
    return true;
  }


  /**
   * run :过滤器的具体逻辑，可以很复杂，包括查询sql，nosql去判断请求到底有没有访问权限
   * 这里是判断请求有没有token参数
   */
  @Override
  public Object run() {
//    RequestContext ctx = RequestContext.getCurrentContext();
//    HttpServletRequest request = ctx.getRequest();
//    log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
//    String token = request.getParameter("token");
//    if (token == null) {
//      log.warn("token is empty");
//      ctx.setSendZuulResponse(false);
//      ctx.setResponseStatusCode(401);
//      try {
//        ctx.getResponse().getWriter().write("token is empty");
//      } catch (IOException e) {
//        return null;
//      }
//    }
//    log.info("ok");
    return null;
  }
}
