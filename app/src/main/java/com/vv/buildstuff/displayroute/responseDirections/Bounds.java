package com.vv.buildstuff.displayroute.responseDirections;

import com.vv.buildstuff.displayroute.LatLong;

/**
 * Created by vvennava on 10/4/14.
 */
public class Bounds {
    private LatLong southwest;
    private LatLong northeast;

    public Bounds() {
    }

    public Bounds(LatLong southwest, LatLong northeast) {
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public LatLong getSouthwest() {
        return southwest;
    }

    public void setSouthwest(LatLong southwest) {
        this.southwest = southwest;
    }

    public LatLong getNortheast() {
        return northeast;
    }

    public void setNortheast(LatLong northeast) {
        this.northeast = northeast;
    }

    @Override
    public String toString() {
        return "Bounds [" +
                "southwest=" + southwest +
                ", northeast=" + northeast +
                ']';
    }
}
