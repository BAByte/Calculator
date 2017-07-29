package com.example.ljh99.calculator.calculator_util;

import org.javia.arity.SyntaxException;

/**
 * Created by ljh99 on 2017/7/29 0029.
 */

public interface CalculatorListener {
    public void addNum(String num);
    public void addSign(String sign);
    public void delete();
    public void equal() throws SyntaxException;
    public void deleteAll();
}
