package com.allenwtl.aop;

import java.lang.reflect.Method;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public abstract class ThrowHandler extends AbstractHandler {

    abstract void handleThrow(Object proxy, Method method, Object[] args, Exception e);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(getTargetObject(), args);
        } catch (Exception e){
            handleThrow(proxy, method, args, e);
        }
        return null;
    }
}
