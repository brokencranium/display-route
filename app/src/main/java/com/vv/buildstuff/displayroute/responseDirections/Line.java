package com.vv.buildstuff.displayroute.responseDirections;

/**
 * Created by vvennava on 10/4/14.
 */
public class Line {
    private String name;
    private String shortName;
    private String color;
    private Agencies agencies;
    private String url;
    private String icon;
    private String textColor;
    private Vehicle vehicle;

    public Line() {
    }

    public Line(String name, String shortName, String color,
                Agencies agencies, String url, String icon,
                String textColor, Vehicle vehicle) {
        this.name = name;
        this.shortName = shortName;
        this.color = color;
        this.agencies = agencies;
        this.url = url;
        this.icon = icon;
        this.textColor = textColor;
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Agencies getAgencies() {
        return agencies;
    }

    public void setAgencies(Agencies agencies) {
        this.agencies = agencies;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Line [" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", color='" + color + '\'' +
                ", agencies=" + agencies +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", textColor='" + textColor + '\'' +
                ", vehicle=" + vehicle +
                ']';
    }
}
