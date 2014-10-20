package com.vv.buildstuff.displayroute;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vvennava on 10/19/14.
 */
public class DistanceBetweenLatLng {

    private LatLng startLatLng;
    private LatLng endLatLng;
    private float[] distanceMeters;

    public DistanceBetweenLatLng(LatLng startLatLng, LatLng endLatLng, float[] distanceMeters) {
        this.startLatLng = startLatLng;
        this.endLatLng = endLatLng;
        this.distanceMeters = distanceMeters;
    }

    public LatLng getStartLatLng() {
        return startLatLng;
    }

    public void setStartLatLng(LatLng startLatLng) {
        this.startLatLng = startLatLng;
    }

    public LatLng getEndLatLng() {
        return endLatLng;
    }

    public void setEndLatLng(LatLng endLatLng) {
        this.endLatLng = endLatLng;
    }

    public float[] getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(float[] distanceMeters) {
        this.distanceMeters = distanceMeters;
    }
}
