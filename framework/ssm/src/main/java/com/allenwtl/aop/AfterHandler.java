package com.allenwtl.aop;

import java.lang.reflect.Method;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public abstract class AfterHandler extends AbstractHandler{

    public abstract void handleAfter(Object proxy, Method method, Object[] args);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = method.invoke(getTargetObject(), args);
        handleAfter(proxy, method, args);
        return object;
    }
}
