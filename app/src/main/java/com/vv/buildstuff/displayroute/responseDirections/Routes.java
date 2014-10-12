package com.vv.buildstuff.displayroute.responseDirections;

import java.util.ArrayList;

/**
 * Created by vvennava on 10/4/14.
 */
public class Routes {

    private Bounds bounds;
    private String copyrights;
    private ArrayList<Legs> legs;
    private String overview_polyLine;
    private String summary;
    private ArrayList<String> warnings;
    private ArrayList<String> way_point_order;

    public Routes(){

    }

    public Routes(String summary, ArrayList<Legs> legs, ArrayList<String> way_point_order,
                  String overview_polyLine, Bounds bounds, String copyrights, ArrayList<String> warnings) {
        this.legs = legs;
        this.way_point_order = way_point_order;
        this.overview_polyLine = overview_polyLine;
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

    public ArrayList<String> getWay_point_order() {
        return way_point_order;
    }

    public void setWay_point_order(ArrayList<String> way_point_order) {
        this.way_point_order = way_point_order;
    }

    public String getOverview_polyLine() {
        return overview_polyLine;
    }

    public void setOverview_polyLine(String overview_polyLine) {
        this.overview_polyLine = overview_polyLine;
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

    @Override
    public String toString() {
        return "routes [" +
                "summary='" + summary +
                ", legs=" + legs +
                ", way_point_order=" + way_point_order +
                ", overview_polyLine='" + overview_polyLine +
                ", bounds=" + bounds +
                ", copyrights='" + copyrights +
                ", warnings=" + warnings +
                ']';
    }
}
