package com.vv.buildstuff.displayroute.response;

/**
 * Created by vvennava on 10/4/14.
 */
public class SouthWest {
    private String lat;
    private String lng;

    public SouthWest() {
    }

    public SouthWest(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
