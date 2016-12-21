package com.allenwtl.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public class SomeHandler implements InvocationHandler {

    private Calculator targetObject ;

    public SomeHandler(Calculator targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class clazz = proxy.getClass();
//
//        System.out.println(Modifier.isFinal(clazz.getModifiers()));
//        System.out.println(clazz.getTypeName());
//        System.out.println(proxy.getClass().getSuperclass());
//        System.out.println();
        Object result = method.invoke(targetObject, args);
        return result;
    }
}
