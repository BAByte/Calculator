package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public class AddNumer {
    StringBuilder expression;
    CalculatorStatusListener listener;
    public AddNumer(StringBuilder expression,CalculatorStatusListener listener){
        this.expression=expression;
        this.listener=listener;
    }

    public boolean add(String num){
        //限定一行输入的数字只能是13个
        if ((listener.getLen() < 13)) {
            expression.append(num);
            //此时表达式最后一个不是符号了
            listener.deleteSign();
            listener.addNum();
            return true;
        }
        return false;
    }
}
