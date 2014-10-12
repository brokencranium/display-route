package com.vv.buildstuff.displayroute.responseDirections;

import com.vv.buildstuff.displayroute.LatLong;

import java.util.ArrayList;

/**
 * Created by vvennava on 10/4/14.
 */
public class Legs {
    private TextValue distance;
    private TextValue duration;
    private TextValue duration_in_traffic;
    private String end_address;
    private LatLong end_location;
    private String start_address;
    private LatLong start_location;
    private ArrayList<Step> steps;
    private String arrival_time;
    private String departure_time;

    public Legs() {
    }

    public Legs(ArrayList<Step> steps, TextValue distance, TextValue duration, TextValue duration_in_traffic,
                String arrival_time, String departure_time, LatLong start_location,
                LatLong end_location, String start_address, String end_address) {
        this.steps = steps;
        this.distance = distance;
        this.duration = duration;
        this.duration_in_traffic = duration_in_traffic;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.start_location = start_location;
        this.end_location = end_location;
        this.start_address = start_address;
        this.end_address = end_address;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }


    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public TextValue getDistance() {
        return distance;
    }

    public void setTextValue(TextValue direction) {
        this.distance = direction;
    }


    public TextValue getDuration() {
        return duration;
    }


    public void setDuration(TextValue duration) {
        this.duration = duration;
    }

    public TextValue getDuration_in_traffic() {
        return duration_in_traffic;
    }

    public void setDuration_in_traffic(TextValue duration_in_traffic) {
        this.duration_in_traffic = duration_in_traffic;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
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

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public void setDistance(TextValue distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Legs [" +
                " distance=" + distance +
                ", duration=" + duration +
                ", duration_in_traffic=" + duration_in_traffic +
                ", end_address=" + end_address +
                ", end_location=" + end_location +
                ", start_address=" + start_address +
                ", start_location=" + start_location +
                ", steps=" + steps +
                ", arrival_time=" + arrival_time +
                ", departure_time=" + departure_time +
                ']';
    }
}
