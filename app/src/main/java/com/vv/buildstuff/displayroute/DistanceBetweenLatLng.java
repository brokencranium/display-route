package com.vv.buildstuff.displayroute;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vvennava on 10/19/14.
 */
public class DistanceBetweenLatLng {
    private LatLng startLatLng;
    private LatLng endLatLng;
    private float[] distanceMeters;


    public DistanceBetweenLatLng(LatLng startLatLng) {
        this.startLatLng = startLatLng;
        this.endLatLng = endLatLng;
        Location.distanceBetween(startLatLng.latitude,startLatLng.longitude,endLatLng.latitude,endLatLng.longitude,distanceMeters);
    }
}
