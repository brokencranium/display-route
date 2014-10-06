package com.vv.buildstuff.displayroute.response;

/**
 * Created by vvennava on 10/4/14.
 */
public class Bounds {
    private SouthWest southwest;
    private NorthEast northeast;

    public Bounds() {
    }

    public Bounds(SouthWest southwest, NorthEast northeast) {
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public SouthWest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(SouthWest southwest) {
        this.southwest = southwest;
    }

    public NorthEast getNortheast() {
        return northeast;
    }

    public void setNortheast(NorthEast northeast) {
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
