package com.example.ljh99.calculator.calculator_util;

import com.example.ljh99.calculator.TextViewListener;
import org.javia.arity.SyntaxException;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public class Calculator {
    //用来判断上一次输入的字符是不是符号
    boolean isSign = false;
    //用来输入的内容判断是不是表达式
    boolean isExpression = false;
    //用来判断当前最下方的数字是不是上一次出现的结果
    boolean isResult = false;
    //用来记录一次输入的数字长度
    int len = 0;
    //用来记录当前过程的行数，因为行数一多就要自动缩小字体大小
    int line = 0;
    //一次完整计算的分割线
    String fengexian;
    //表达式
    StringBuilder expression;
    //向表达式添加数字的类
    AddNumer addNumer;
    //向表达式添加运算符的类
    AddSigner addSigner;
    //计算表达是的结果
    Equaler equaler;
    //显示过程的TextView的监听器
    TextViewListener textViewlistener;


    public Calculator(TextViewListener textViewlistener) {
        this.textViewlistener=textViewlistener;
        expression = new StringBuilder();
        addNumer = new AddNumer(expression, listener);
        addSigner = new AddSigner(expression, listener);
        equaler = new Equaler(expression, listener);
        fengexian = "-------------------------------";
    }

    //向表达式和回调向过程添加数字的方法
    public void addNum(String num) {
        if (isResult) {
            int len = expression.length();
            for (int i = 0; i < len; i++)
                delete();
            isResult = false;
        }
        if (addNumer.add(num)) {
            textViewlistener.addText(num);
            textViewlistener.changeTextSize(line);
        }
    }

    //向表达式和回调向过程添加字符的方法
    public void addSign(String sign) {
        if (addSigner.add(sign)) {
            textViewlistener.addText("\n" + sign + "      ");
            textViewlistener.changeTextSize(line);
        }
    }

    //计算表达式的结果，回调显示过程以及结果
    public void equal() throws SyntaxException {
        String back;
        back=equaler.equal();
        if (back!=null) {
            textViewlistener.addText("\n" + "=      " + back
                    + "\n" + fengexian + "\n" + back);
            textViewlistener.changeTextSize(line);
        }
    }

    //删除表达式和过程中所有内容的方法
    public void deleteAll(){
        expression.setLength(0);
        len = 0;
        line = 0;
        isSign = false;
        isExpression = false;
        isResult = false;
        textViewlistener.delete("");
        textViewlistener.changeTextSize(line);
    }

    //计算器各项计算状态的监听器，给计算器各个组成回调用的
    CalculatorStatusListener listener = new CalculatorStatusListener() {
        @Override
        public void intputSign() {
            isSign = true;
        }

        @Override
        public void deleteSign() {
            isSign = false;
        }

        @Override
        public void isExpression() {
            isExpression = true;
        }

        @Override
        public void notExpression() {
            isExpression = false;
        }

        @Override
        public void isResult() {
            isResult = true;
        }

        @Override
        public void notResult() {
            isResult = false;
        }

        @Override
        public void addLine() {
            line++;
        }

        @Override
        public void deleteLine() {
            line--;
        }

        @Override
        public void addNum() {
            len++;
        }

        @Override
        public void deleteNum() {
            len--;
        }

        public boolean getIsSign() {
            return isSign;
        }

        public boolean getIsExpression() {
            return isExpression;
        }

        public boolean getIsResult() {
            return isResult;
        }

        public int getLen() {
            return len;
        }

        public int getLine() {
            return line;
        }

        public void resetLen() {
            len = 0;
        }
    };


    //用来删除表达式中和textView内容的方法
    public void delete() {
        //如果表达式中有东西才删除
        // 由于一次完整的计算后，表达式的内容就是上一次的计算结果
        // textView内容是完整的计算过程，里面最后结尾还是的计算结果
        // 这样的好处是，你可以删除新表达式中的内容
        // 但是你不能删除TextView中上一次计算结果后面的计算过程
        //比如表达式中得到上一次的结果是22，计算过程中添加的就不止22这两个数字了
        //还会添加上一次计算的过程 :10+10=22/n-----/n22，但是我们删除的时候只想删除表达式中的内容，正好是22
        //删除两次。而计算过程中也正好是22结尾，这样你只能删除计算过程的22
        //就是删除两次，两次后表达式已经是空的了，这个时候你就删除不了计算过程中22前面的东西了
        if (expression.length() > 0) {
            String num = textViewlistener.getText();
            //获取计算过程中最后一个字符
            String last = num.substring(num.length() - 1);

            //只要点了删除，然后表达式中有东西，就肯定要删除
            expression.delete(expression.length() - 1, expression.length());

            //最后一个字符是空格说明是运算符
            //在运算过程中运算符会转换还带有空格，所以删除的字数和表达式中删除的字数不一样
            if (last.equals(" ")) {
                num = num.substring(0, num.length() - 8);

                //删除运算符后，说明少了一行，判断要不要改字体大小
                if (line > 0)
                    line--;
                textViewlistener.delete(num);

                //删除了运算符，说明最后一个就不是运算符，判断是不是表达式
                isSign = false;
                isExpression = chargeExpression();
            } else {
                num = num.substring(0, num.length() - 1);
                textViewlistener.delete(num);
            }

            if (len > 0)
                len--;
            textViewlistener.changeTextSize(line);
        } else
            isResult = false; //如果表达式中毛都没有，肯定不是上一次的结果啦
    }

    //判断sb中有没有运算符，没有就不是表达式
    public boolean chargeExpression() {
        int p = expression.toString().indexOf("+");
        int s = expression.toString().indexOf("-");
        int d = expression.toString().indexOf("/");
        int m = expression.toString().indexOf("*");
        if (p != -1 || s != -1 || d != -1 || m != -1)
            return true;
        else
            return false;
    }

}
