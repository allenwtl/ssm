package com.allenwtl.aop;

import java.lang.reflect.InvocationHandler;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public abstract class AbstractHandler implements InvocationHandler{

    private Object targetObject ;

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object getTargetObject() {
        return targetObject;
    }
}
