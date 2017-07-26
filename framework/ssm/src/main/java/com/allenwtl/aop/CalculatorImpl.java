package com.allenwtl.aop;

/**
 * Created by tianlun.wu on 2016/12/21.
 */
public class CalculatorImpl implements Calculator {

    @Override
    public int calculate(int a, int b) {
        System.out.println("Im doing");
        return a+b;
    }

    @Override
    public int multiplute(int a, int b) {
        return a*b;
    }

    @Override
    public int divide(int a, int b) {
        return a/b;
    }
}
