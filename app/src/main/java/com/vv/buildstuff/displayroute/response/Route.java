package com.vv.buildstuff.displayroute.response;

import java.util.ArrayList;

/**
 * Created by vvennava on 10/4/14.
 */
public class Route {

    private String summary;
    private ArrayList<Legs> legs;
    private ArrayList<String> wayPointOrder;
    private String overviewPolyLine;
    private Bounds bounds;
    private String copyrights;
    private ArrayList<String> warnings;

    public Route(){

    }

    public Route(String summary, ArrayList<Legs> legs, ArrayList<String> wayPointOrder,
                 String overviewPolyLine, Bounds bounds, String copyrights, ArrayList<String> warnings) {
        this.legs = legs;
        this.wayPointOrder = wayPointOrder;
        this.overviewPolyLine = overviewPolyLine;
        this.bounds = bounds;
        this.copyrights = copyrights;
        this.warnings = warnings;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<Legs> getLegs() {
        return legs;
    }

    public void setLegs(ArrayList<Legs> legs) {
        this.legs = legs;
    }

    public ArrayList<String> getWayPointOrder() {
        return wayPointOrder;
    }

    public void setWayPointOrder(ArrayList<String> wayPointOrder) {
        this.wayPointOrder = wayPointOrder;
    }

    public String getOverviewPolyLine() {
        return overviewPolyLine;
    }

    public void setOverviewPolyLine(String overviewPolyLine) {
        this.overviewPolyLine = overviewPolyLine;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public ArrayList<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(ArrayList<String> warnings) {
        this.warnings = warnings;
    }
}
