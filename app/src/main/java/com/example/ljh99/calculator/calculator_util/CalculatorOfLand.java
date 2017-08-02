package com.example.ljh99.calculator.calculator_util;

import android.util.Log;

import com.example.ljh99.calculator.TextViewListener;

import org.javia.arity.SyntaxException;

/**
 * Created by ljh99 on 2017/7/29 0029.
 */

public class CalculatorOfLand implements Calculator {
    AddNumer addNumer;
    AddSigner addSigner;
    Deleter deleter;
    Equaler equaler;
    TextViewListener textViewlistener;
    StringBuilder expression;
    //用来输入的内容判断是不是表达式
    boolean isExpression = true;
    //用来判断当前最下方的数字是不是上一次出现的结果
    boolean isResult = false;
    boolean isSign = false;

    public CalculatorOfLand(TextViewListener textViewlistener) {
        this.textViewlistener = textViewlistener;
        expression = new StringBuilder();
        addNumer = new AddNumer(listener);
        addSigner = new AddSigner(listener);
        deleter = new Deleter(listener);
        equaler = new Equaler(listener);
    }

    @Override
    public void addNum(String num) {
        if (isResult) {
            int len = expression.length();
            expression.setLength(0);
            String text = textViewlistener.getText();
            text = text.substring(0, text.length() - len);
            textViewlistener.delete(text);
        }

        addNumer.add(num);
        textViewlistener.addText(num);
        isSign = false;
        if ((expression.toString().indexOf("(")) != -1) {
            isExpression = true;
        }
    }

    @Override
    public void addSign(String sign) {
        if (expression.length() > 0) {
            if (sign.equals("-"))
                isSign=false;
            if (addSigner.add(sign)) {
                textViewlistener.addText(sign);
                isExpression = true;
                isResult = false;
                isSign = true;
            }
        } else if (sign.equals("-")) {
            isSign=false;
            if (addSigner.add(sign)) {
                textViewlistener.addText(sign);
                isResult = false;
                isSign = false;
            }
        }

    }

    @Override
    public void delete() {
        if (deleter.delete()) {
            String text = textViewlistener.getText();
            text = text.substring(0, text.length() - 1);
            textViewlistener.delete(text);
        }
    }

    @Override
    public void equal() throws SyntaxException {
        String result = equaler.equal();
        if (result != null) {
            textViewlistener.addText("=" + result + "\n" + result);
            isExpression = false;
            isSign = false;
        }
    }

    @Override
    public void deleteAll() {
        expression.setLength(0);
        textViewlistener.delete("");
        isExpression = false;
        isSign=false;
        isResult = false;
    }

    CalculatorStatusListener listener = new CalculatorStatusListener() {
        @Override
        public void isResult() {
            isResult = true;
        }

        @Override
        public void notResult() {
            isResult = false;
        }

        @Override
        public void addNum() {
        }

        @Override
        public boolean getIsExpression() {
            return isExpression;
        }

        @Override
        public boolean getIsSign() {
            return isSign;
        }

        @Override
        public StringBuilder getExpression() {
            return expression;
        }
    };
}
