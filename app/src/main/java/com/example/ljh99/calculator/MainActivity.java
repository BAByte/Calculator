package com.example.ljh99.calculator;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ljh99.calculator.calculator_util.Calculator;
import org.javia.arity.SyntaxException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //显示计算过程
    TextView textView;
    //控制ScrollView自动滚动的对象
    Handler handler;
    //TextView中的过程很长，为了够完全显示，所以加了个ScrollVView让它能够滚动
    ScrollView scrollView;
    //播放音频
    MediaPlayer mediaPlayer;
    //计算器
    Calculator calculator;

    //给计算器的监听器
    TextViewListener listener = new TextViewListener() {
        //计算过程变化时，计算器会回调这个方法来更改显示器的内容
        @Override
        public void addText(String text) {
            textView.append(text);
            scrollToBottom(scrollView, textView);
        }

        //如果是删除的话，就回调这个方法来显示内容
        @Override
        public void delete(String text) {
            textView.setText(text);
            scrollToBottom(scrollView, textView);
        }

        //计算器计算的是真正的表达式，显示器显示的是过程，为了
        //实现真正的表达式删除字符时，显示器的过程也能正确的删除
        //就设置这个回调方法将当前计算过程给计算器，计算器会去处理
        @Override
        public String getText() {
            return textView.getText().toString();
        }

        //自动改变字体大小
        @Override
        public void changeTextSize(int line) {
            //改变字体大小，超过3行就改小否则改大
            if (line > 2)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            else
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = new Calculator(listener);
        scrollView = (ScrollView) findViewById(R.id.screen);
        textView = (TextView) findViewById(R.id.text);
        mediaPlayer = MediaPlayer.create(this, R.raw.button);
        handler = new Handler();
    }


    //按钮点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                calculator.addNum("1");
                break;
            case R.id.tow:
                calculator.addNum("2");
                break;
            case R.id.three:
                calculator.addNum("3");
                break;
            case R.id.four:
                calculator.addNum("4");
                break;
            case R.id.five:
                calculator.addNum("5");
                break;
            case R.id.six:
                calculator.addNum("6");
                break;
            case R.id.seven:
                calculator.addNum("7");
                break;
            case R.id.eight:
                calculator.addNum("8");
                break;
            case R.id.nine:
                calculator.addNum("9");
                break;
            case R.id.zero:
                calculator.addNum("0");
                break;
            case R.id.plus:
                calculator.addSign("+");
                break;
            case R.id.sub:
                calculator.addSign("-");
                break;
            case R.id.mul:
                calculator.addSign("*");
                break;
            case R.id.div:
                calculator.addSign("/");
                break;
            case R.id.poin:
                calculator.addNum(".");
                break;
            case R.id.delete:
                calculator.delete();
                break;
            case R.id.equal:
                try {
                    calculator.equal();
                } catch (SyntaxException e) {
                    Toast.makeText(this, "表达式错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.c:
                calculator.deleteAll();
                break;
        }
        //点击一次按钮，播放一次声音
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        } else
            mediaPlayer.start();
    }

    //将计算过程自动滚动到最后
    private void scrollToBottom(final ScrollView scrollView, final View view) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (scrollView == null || view == null) {
                    return;
                }
                // offset偏移量。是指当textview中内容超出 scrollview的高度
                // 那么超出部分就是偏移量
                int offset = view.getMeasuredHeight()
                        - scrollView.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                //scrollview开始滚动
                scrollView.scrollTo(0, offset);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}