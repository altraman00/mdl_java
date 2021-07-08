package com.mdl.demo.patterns.chainpattern02.server;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern02
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:29
 * ----------------- ----------------- -----------------
 */
public interface Pipeline {

  Pipeline fireTaskReceived();

  Pipeline fireTaskFiltered();

  Pipeline fireTaskExecuted();

  Pipeline fireAfterCompletion();
}
