package com.vv.buildstuff.displayroute;

import android.location.Location;
import android.util.Log;

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
    private LatLng latLngPrevious;


    public CalculateDistanceLatLng(float maxDistance) {
        listDist = new ArrayList<DistanceBetweenLatLng>();
        this.maxDistance = maxDistance;
    }

    public LatLng setCalculateDistance(LatLng latLngCurrent) {
        float[] distance = new float[10];

        if (startPoint == null || latLngPrevious == null) {
            startPoint = latLngCurrent;
            latLngPrevious = latLngCurrent;
//            return latLngCurrent;
        }


        Log.i("VV Coordinates ", "Start LatLng " + latLngPrevious.latitude + "_" + latLngPrevious.longitude +
                " End LatLng " + latLngCurrent.latitude + "_" + latLngCurrent.longitude);

        Location.distanceBetween(latLngPrevious.latitude, latLngPrevious.longitude, latLngCurrent.latitude, latLngCurrent.longitude, distance);

        Log.i("VV Distance[0] ", String.valueOf(distance[0]));

        currDistance = currDistance + distance[0];
        latLngPrevious = latLngCurrent;

        if (currDistance > maxDistance) {
            Log.i("VV currDistance ", String.valueOf(currDistance));
            distance[0] = currDistance;
            listDist.add(new DistanceBetweenLatLng(startPoint, latLngCurrent, distance));
            startPoint = null;
            currDistance = 0;
            return latLngCurrent;
        } else {
            return null;
        }
    }


    public ArrayList<DistanceBetweenLatLng> getListDistance() {
        return listDist;
    }

}
