package com.mdl.demo.patterns.chainpattern05;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern05
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 22:06
 * ----------------- ----------------- -----------------
 */
public abstract class CostHandler {

  public abstract CostHandler getNextHandler();

  public abstract void setNextHandler(CostHandler nextHandler);

  public abstract void dealRequest(float num);

}
