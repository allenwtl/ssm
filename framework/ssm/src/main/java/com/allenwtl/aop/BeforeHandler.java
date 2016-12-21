package com.allenwtl.aop;

import java.lang.reflect.Method;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public  abstract  class BeforeHandler extends  AbstractHandler{

    public abstract void handleBefore(Object proxy, Method method, Object[] args);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        handleBefore(proxy, method, args);
        return method.invoke(getTargetObject(), args);
    }
}
