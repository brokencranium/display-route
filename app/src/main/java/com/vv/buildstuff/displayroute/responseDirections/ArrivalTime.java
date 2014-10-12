package com.vv.buildstuff.displayroute.responseDirections;

/**
 * Created by vvennava on 10/4/14.
 */
public class ArrivalTime {
private String arrival_time;

    public ArrivalTime() {
    }

    public ArrivalTime(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    @Override
    public String toString() {
        return "ArrivalTime [" +
                "arrival_time='" + arrival_time + '\'' +
                ']';
    }
}
