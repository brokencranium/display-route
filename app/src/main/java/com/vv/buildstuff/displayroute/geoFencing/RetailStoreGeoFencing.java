package com.vv.buildstuff.displayroute.geoFencing;

import com.google.android.gms.location.Geofence;

/**
 * Created by vvennava on 10/17/14.
 */
public class RetailStoreGeoFencing {
    private String geoFenceId;
    private double latitude;
    private double longitude;
    private float radius;
    private long expiration;
    private int transition;


    public RetailStoreGeoFencing(String geoFenceId, double latitude, double longitude, float radius, long expiration, int transition) {
        this.geoFenceId = geoFenceId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.expiration = expiration;
        this.transition = transition;
    }

    public String getGeoFenceId() {
        return geoFenceId;
    }

    public void setGeoFenceId(String geoFenceId) {
        this.geoFenceId = geoFenceId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public int getTransition() {
        return transition;
    }

    public void setTransition(int transition) {
        this.transition = transition;
    }

    /**
     * Create a location services geo-fence object
     */

    public Geofence toGeofence(){
        return new Geofence.Builder().setRequestId(getGeoFenceId())
                                     .setTransitionTypes(getTransition())
                                     .setCircularRegion(getLatitude(),getLongitude(),getRadius())
                                     .setExpirationDuration(getExpiration())
                                     .build();
    }


    /**
     *  Store geo-fencing values
     */

}
