package com.vv.buildstuff.displayroute.response;

import java.util.ArrayList;

/**
 * Created by vvennava on 10/4/14.
 */
public class Legs {
    private ArrayList<Step> steps;
    private Distance distance;
    private DurationInTraffic durationInTraffic;
    private  ArrivalTime arrivalTime;
    private String departureTime;
    private String startLocation;
    private String endLocation;
    private String startAddress;
    private String endAddress;

    public Legs() {
    }

    public Legs(ArrayList<Step> steps, Distance distance, DurationInTraffic durationInTraffic,
                ArrivalTime arrivalTime, String departureTime, String startLocation,
                String endLocation, String startAddress, String endAddress) {
        this.steps = steps;
        this.distance = distance;
        this.durationInTraffic = durationInTraffic;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public DurationInTraffic getDurationInTraffic() {
        return durationInTraffic;
    }

    public void setDurationInTraffic(DurationInTraffic durationInTraffic) {
        this.durationInTraffic = durationInTraffic;
    }

    public ArrivalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ArrivalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }
}
