package com.example.ljh99.calculator.calculator_util;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public class AddSigner {
    StringBuilder expression;
    CalculatorStatusListener listener;

    protected AddSigner(CalculatorStatusListener listener) {
        this.expression = listener.getExpression();
        this.listener = listener;
    }

    protected boolean add(String sign) {
        if (sign.equals("×"))
            sign = "*";
        else if (sign.equals("÷"))
            sign = "/";
        if (!listener.getIsSign()) {
            expression.append(sign);
            return true;
        }
        return false;
    }
}
