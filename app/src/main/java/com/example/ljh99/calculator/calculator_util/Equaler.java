package com.example.ljh99.calculator.calculator_util;

import android.util.Log;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

import java.text.DecimalFormat;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public class Equaler {
    StringBuilder expression;
    CalculatorStatusListener listener;

    protected Equaler(CalculatorStatusListener listener) {
        this.expression = listener.getExpression();
        this.listener=listener;
    }

    protected String equal() throws SyntaxException{
        String back=null;
        //先判断是不是表达式
        if (listener.getIsExpression()) {
            //arity库中用来计算的对象
            Symbols s = new Symbols();
            //用来记录大数字
            String resultBig = null;

            //记录浮点型结果
            double results = s.eval(expression.toString());
            //整型结果
            int result = (int) results;

            resultBig = results + "";
            if (results > 100000) {
                //如果结果是大数字就格式化
                DecimalFormat d = new DecimalFormat("#.####E0");
                resultBig = d.format(results);
            } else {
                //否则就保留4位小数
                resultBig = String.format("%.4f", results);
            }

            if ((results / result) > 1 || (results < 0 && results > -1)) {
                //输出了结果后说明已经完成了一次完整的计算，所以将上一次的表达式去掉
                expression.setLength(0);
                //用户有时候会在计算结果后直接用该结果进行下一次计算，所以先放到表达式中
                expression.append(resultBig);
                back=resultBig;
            } else {
                expression.setLength(0);
                expression.append(result);
                back=result+"";
            }

            //最后的数字现在是计算出来的结果
            listener.isResult();
            return back;
        }
        return back;
    }
}
