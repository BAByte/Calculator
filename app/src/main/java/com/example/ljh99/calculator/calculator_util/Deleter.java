package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/29 0029.
 */

public class Deleter {
    CalculatorStatusListener listener;
    StringBuilder expression;

    protected Deleter(CalculatorStatusListener listener) {
        this.listener = listener;
        expression=listener.getExpression();
    }

    //用来删除表达式中内容的方法
    protected boolean delete() {
        if (expression.length() > 0) {
            expression.delete(expression.length() - 1, expression.length());
            return true;
        }
        return false;
    }
}
