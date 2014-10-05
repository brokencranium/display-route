package com.vv.buildstuff.displayroute.response;

/**
 * Created by vvennava on 10/4/14.
 */
public class Step {
    private String htmlInstructions;
    private String distance;
    private String duration;
    private LatLong startLocation;
    private LatLong endLocation;
    private String polyLine;
    private TransitDetails transitDetails;

    public Step() {
    }

    public Step(String htmlInstructions, String distance, String duration,
                LatLong startLocation, LatLong endLocation, String polyLine, TransitDetails transitDetails) {
        this.htmlInstructions = htmlInstructions;
        this.distance = distance;
        this.duration = duration;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.polyLine = polyLine;
        this.transitDetails = transitDetails;
    }

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LatLong getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLong startLocation) {
        this.startLocation = startLocation;
    }

    public LatLong getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLong endLocation) {
        this.endLocation = endLocation;
    }

    public String getPolyLine() {
        return polyLine;
    }

    public void setPolyLine(String polyLine) {
        this.polyLine = polyLine;
    }

    public TransitDetails getTransitDetails() {
        return transitDetails;
    }

    public void setTransitDetails(TransitDetails transitDetails) {
        this.transitDetails = transitDetails;
    }
}
