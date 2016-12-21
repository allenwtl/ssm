package com.allenwtl.aop;


import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public class Client {

    public static void main(String[] args) {
        //委托类
        Calculator calculator = new CalculatorImpl();
        InvocationHandler handler = new SomeHandler(calculator);

//        try {
//            //详细做法
//            ClassLoader loader = CalculatorImpl.class.getClassLoader();
//            Class<?> [] interfaces = CalculatorImpl.class.getInterfaces() ;
//            Class clazz = Proxy.getProxyClass(loader, interfaces);
//            Constructor constructor = clazz.getConstructor(new Class[] { InvocationHandler.class });
//            Calculator proxy = (Calculator) constructor.newInstance(new Object[]{handler});
//            proxy.calculate(1,2);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        Calculator proxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(), CalculatorImpl.class.getInterfaces(),
//                handler);
//
//
//        Method [] methods = proxy.getClass().getMethods();

//        for (Method method : methods){
//            System.out.println(method);
//        }
//
//        int r = proxy.calculate(1,2);
//        System.out.println(r);
//        int mul = proxy.multiplute(2,3);
//        System.out.println(mul);


//        Calculator calculator = new CalculatorImpl();
//
//        BeforeHandler beforeHandler = new BeforeHandler(){
//            @Override
//            public void handleBefore(Object proxy, Method method, Object[] args) {
//                System.out.println("handleBefore");
//            }
//        };
//
//        AfterHandler afterHandler = new AfterHandler() {
//            @Override
//            public void handleAfter(Object proxy, Method method, Object[] args) {
//                System.out.println("handleAfter");
//            }
//        };
//
//        List<AbstractHandler> handlerList = new ArrayList<>();
//        handlerList.add(beforeHandler);
//        handlerList.add(afterHandler);
//
//        Calculator proxy = (Calculator) ProxyFactory.getProxy(calculator, handlerList);
//
//        System.out.println(proxy.calculate(1,3));


        //Around
//        AroundHandler aroundHandler = new AroundHandler() {
//            @Override
//            void handleBefore(Object proxy, Method method, Object[] args) {
//                System.out.println("handleBefore");
//            }
//
//            @Override
//            void handleAfter(Object proxy, Method method, Object[] args) {
//                System.out.println("handleAfter");
//            }
//        };
//
//        aroundHandler.setTargetObject(calculator);
//        Calculator proxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(), CalculatorImpl.class.getInterfaces(),
//                aroundHandler);
//        proxy.calculate(1,3);

        //Throw
        ThrowHandler throwHandler = new ThrowHandler() {
            @Override
            void handleThrow(Object proxy, Method method, Object[] args, Exception e) {
                System.out.println(e.getCause());
            }
        };

        throwHandler.setTargetObject(calculator);
        Calculator proxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(), CalculatorImpl.class.getInterfaces(),
                throwHandler);
        System.out.println(proxy.divide(1, 0));

    }

}
