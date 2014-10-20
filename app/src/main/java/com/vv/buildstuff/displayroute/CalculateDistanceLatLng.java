package com.vv.buildstuff.displayroute;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by vvennava on 10/19/14.
 */
public class CalculateDistanceLatLng {

    private float maxDistance;
    private float currDistance;
    private ArrayList<DistanceBetweenLatLng> listDist;
    private LatLng startPoint;
    private LatLng latLngStart;
    private LatLng latLngEnd;


    public CalculateDistanceLatLng(float maxDistance) {
        listDist = new ArrayList<DistanceBetweenLatLng>();
        this.maxDistance = maxDistance;
    }

    public LatLng setCalculateDistance(LatLng latLng) {
        float[] distance = new float[10];

        if (startPoint == null) {
            startPoint = latLng;
        }

        if (latLngStart == null) {
            latLngStart = latLng;
            return latLng;
        }

        if (latLngEnd == null) {
            latLngEnd = latLng;
        }


        Location.distanceBetween(latLngStart.latitude, latLngStart.longitude, latLngEnd.latitude, latLngEnd.longitude, distance);
        currDistance = currDistance + distance[0];


        if (currDistance > maxDistance) {

            distance[0] = currDistance;
            final DistanceBetweenLatLng distLatLng = new DistanceBetweenLatLng(startPoint, latLngEnd, distance);
            startPoint = null;
            currDistance = 0;
            listDist.add(distLatLng);
            return latLngEnd;
        } else {
            return null;
        }
    }


    public ArrayList<DistanceBetweenLatLng> getListDistance() {
        return listDist;
    }

}
