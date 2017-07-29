package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public class AddNumer {
    StringBuilder expression;
    CalculatorStatusListener listener;
    protected AddNumer(StringBuilder expression,CalculatorStatusListener listener){
        this.expression=expression;
        this.listener=listener;
    }

    protected void add(String num){
            expression.append(num);
            //此时表达式最后一个不是符号了
            listener.deleteSign();
            listener.addNum();
    }
}
