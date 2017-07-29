package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/29 0029.
 */

public class Deleter {
    StringBuilder expression;
    protected Deleter(StringBuilder expression){
        this.expression=expression;
    }

    //用来删除表达式中内容的方法
    protected void delete() {
        expression.delete(expression.length() - 1, expression.length());
    }
}
