package com.vv.buildstuff.displayroute.response;

/**
 * Created by vvennava on 10/4/14.
 */
public class Bounds {
    private SouthWest southWest;
    private NorthEast northEast;

    public Bounds() {
    }

    public Bounds(SouthWest southWest, NorthEast northEast) {
        this.southWest = southWest;
        this.northEast = northEast;
    }

    public SouthWest getSouthWest() {
        return southWest;
    }

    public void setSouthWest(SouthWest southWest) {
        this.southWest = southWest;
    }

    public NorthEast getNorthEast() {
        return northEast;
    }

    public void setNorthEast(NorthEast northEast) {
        this.northEast = northEast;
    }
}
