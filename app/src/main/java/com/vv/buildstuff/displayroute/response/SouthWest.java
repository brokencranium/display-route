package com.vv.buildstuff.displayroute.response;

/**
 * Created by vvennava on 10/4/14.
 */
public class SouthWest {
    private double lat;
    private double lng;

    public SouthWest() {
    }

    public SouthWest(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "southwest [" +
                "lat='" + lat +
                ", lng='" + lng +
                ']';
    }
}
