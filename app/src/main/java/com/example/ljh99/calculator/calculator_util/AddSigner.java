package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public class AddSigner {
    StringBuilder expression;
    CalculatorStatusListener listener;
    protected AddSigner(StringBuilder expression,CalculatorStatusListener listener){
        this.expression=expression;
        this.listener=listener;
    }

    protected boolean add(String num){
        if (!listener.getIsSign()&&expression.length()>0) {
            expression.append(num);
            //表达式中最后一个已经式符号了
            listener.intputSign();
            //有符号了就是表达式了，至于对不对就看计算结果了
            listener.isExpression();
            //用户直接点计算符号，说明用户想用上一次的结果来计算
            //表达式中已经有了运算符，所以这个时候表达式中已经不是上一次的结果了
            //而是一个新的表达式
            listener.notResult();
            return true;
        }
        return false;
    }
}
