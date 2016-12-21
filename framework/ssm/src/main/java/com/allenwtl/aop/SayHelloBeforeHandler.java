package com.allenwtl.aop;

import java.lang.reflect.Method;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public class SayHelloBeforeHandler extends BeforeHandler {
    @Override
    public void handleBefore(Object proxy, Method method, Object[] args) {
        System.out.println("methodName:"+method.getName());
        System.out.println("args:"+args[0]);
        System.out.println("proxy:"+proxy.getClass());
    }
}
