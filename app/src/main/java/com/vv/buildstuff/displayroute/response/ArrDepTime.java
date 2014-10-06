package com.vv.buildstuff.displayroute.response;

/**
 * Created by vvennava on 10/4/14.
 */
public class ArrDepTime {
    private String text;
    private String value;
    private String timeZone;

    public ArrDepTime() {
    }

    public ArrDepTime(String text, String value, String timeZone) {
        this.text = text;
        this.value = value;
        this.timeZone = timeZone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "ArrDepTime [" +
                "text='" + text + '\'' +
                ", value='" + value + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ']';
    }
}
