package com.example.ljh99.calculator;

/**
 * Created by ljh99 on 2017/7/28 0028.
 */

public interface TextViewListener {
    public void addText(String text);
    public void delete(String text);
    public void changeTextSize(int line);
    public String getText();
}
