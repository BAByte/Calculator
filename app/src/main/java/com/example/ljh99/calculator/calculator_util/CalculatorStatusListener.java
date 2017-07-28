package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public interface CalculatorStatusListener {
    public void intputSign();
    public void deleteSign();
    public void isExpression();
    public void notExpression();
    public void isResult();
    public void notResult();
    public void addLine();
    public void deleteLine();
    public void addNum();
    public void deleteNum();
    public boolean getIsExpression();
    public boolean getIsResult();
    public int getLen();
    public  int getLine();
    public boolean getIsSign();
    public  void resetLen();
}
