package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public interface CalculatorStatusListener {
    public void isResult();
    public void notResult();
    public void addNum();
    public boolean getIsExpression();
    public boolean getIsSign();
    public StringBuilder getExpression();
}
