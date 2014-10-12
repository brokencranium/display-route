package com.vv.buildstuff.displayroute.responseDirections;

import com.vv.buildstuff.displayroute.LatLong;

/**
 * Created by vvennava on 10/4/14.
 */
public class Step {
    private TextValue distance;
    private TextValue duration;
    private LatLong end_location;
    private String html_instructions;
    private Polylines polyline;
    private LatLong start_location;
    private String travel_mode;

    public Step() {
    }

    public Step(String html_instructions,  TextValue distance, TextValue duration,
                LatLong start_location, LatLong end_location, Polylines polyline, String travel_mode) {
        this.html_instructions = html_instructions;
        this.distance = distance;
        this.duration = duration;
        this.start_location = start_location;
        this.end_location = end_location;
        this.polyline = polyline;
        this.travel_mode = travel_mode;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public TextValue getDistance() {
        return distance;
    }

    public void setDistance(TextValue distance) {
        this.distance = distance;
    }

    public TextValue getDuration() {
        return duration;
    }

    public void setDuration(TextValue duration) {
        this.duration = duration;
    }

    public LatLong getStart_location() {
        return start_location;
    }

    public void setStart_location(LatLong start_location) {
        this.start_location = start_location;
    }

    public LatLong getEnd_location() {
        return end_location;
    }

    public void setEnd_location(LatLong end_location) {
        this.end_location = end_location;
    }

    public Polylines getPolyline() {
        return polyline;
    }

    public void setPolyline(Polylines polyline) {
        this.polyline = polyline;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    @Override
    public String toString() {
        return "step [" +
                "distance=" + distance +
                ", duration=" + duration +
                ", end_location=" + end_location +
                ", html_instructions=" + html_instructions +
                ", polyline=" + polyline +
                ", start_location=" + start_location +
                ", travel_mode=" + travel_mode +
                "]";

    }
}
