package com.mdl.aop.proxy;
import java.lang.reflect.Method;
public class $Proxy1 implements com.mdl.aop.server.UserMgr{
    public $Proxy1(InvocationHandler h) {
        this.h = h;
    }
    com.mdl.aop.reflect.InvocationHandler h;
    @Override
    public  void addUser() {
        try {
        Method md = com.mdl.aop.server.UserMgr.class.getMethod("addUser");
        h.invoke(this, md);
        }catch(Exception e) {e.printStackTrace();}
    }
    @Override
    public  void delUser() {
        try {
        Method md = com.mdl.aop.server.UserMgr.class.getMethod("delUser");
        h.invoke(this, md);
        }catch(Exception e) {e.printStackTrace();}
    }

}