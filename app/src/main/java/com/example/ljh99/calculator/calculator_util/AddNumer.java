package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public class AddNumer {
    StringBuilder expression;
    CalculatorStatusListener listener;
    protected AddNumer(CalculatorStatusListener listener){
        this.expression=listener.getExpression();
        this.listener=listener;
    }

    protected void add(String num){
            expression.append(num);
            listener.addNum();
            listener.notResult();
    }
}
