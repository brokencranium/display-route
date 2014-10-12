package com.vv.buildstuff.displayroute.responseDirections;

/**
 * Created by vvennava on 10/4/14.
 */
public class TextValue {
    private String text;
    private int value;


    public TextValue() {
    }

    public TextValue(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" +
                "text=" + text +
                ",value=" + value +
                ']';
    }
}
