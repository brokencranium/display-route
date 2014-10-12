package com.vv.buildstuff.displayroute.responseDirections;

/**
 * Created by vvennava on 10/6/14.
 */
public class Polylines {
    private String points;

    public Polylines() {
    }

    public Polylines(String points) {
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "polyline[" +
                "points='" + points +
                ']';
    }
}
