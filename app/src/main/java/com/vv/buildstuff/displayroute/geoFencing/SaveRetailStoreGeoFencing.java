package com.vv.buildstuff.displayroute.geoFencing;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vvennava on 10/17/14.
 */
public class SaveRetailStoreGeoFencing {
    public static final String KEY_LATITUDE = "com.vv.buildstuff.displayroute.geoFencing.KEY_LATITUDE";
    public static final String KEY_LONGITUDE = "com.vv.buildstuff.displayroute.geoFencing.KEY_LONGITUDE";
    public static final String KEY_RADIUS = "com.vv.buildstuff.displayroute.geoFencing.KEY_RADIUS";
    public static final String KEY_EXPIRATION = "com.vv.buildstuff.displayroute.geoFencing.KEY_EXPIRATION";
    public static final String KEY_TRANSITION = "com.vv.buildstuff.displayroute.geoFencing.KEY_TRANSITION";
    public static final String KEY_PREFIX = "com.vv.buildstuff.displayroute.geoFencing.KEY";


    private final SharedPreferences preferences;
    private static final String SHARED_PREFERENCES = "SharedPreferences";

    public static final long INVALID_LONG_VALUE = -9991;
    public static final float INVALID_FLOAT_VALUE = -999.0F;
    public static final int INVALID_INT_VALUE = -999;


    private RetailStoreGeoFencing geoFence;


    public RetailStoreGeoFencing getGeoFence(String id) {
        double lat = preferences.getFloat(getGeoFenceFieldKey(id, KEY_LATITUDE), INVALID_FLOAT_VALUE);
        double lng = preferences.getFloat(getGeoFenceFieldKey(id, KEY_LONGITUDE), INVALID_FLOAT_VALUE);
        float radius = preferences.getFloat(getGeoFenceFieldKey(id, KEY_RADIUS), INVALID_FLOAT_VALUE);
        long expiration = preferences.getLong(getGeoFenceFieldKey(id, KEY_EXPIRATION), INVALID_LONG_VALUE);
        int transition = preferences.getInt(getGeoFenceFieldKey(id, KEY_TRANSITION), INVALID_INT_VALUE);

        if (lat == INVALID_FLOAT_VALUE || lng == INVALID_FLOAT_VALUE || radius == INVALID_FLOAT_VALUE ||
                expiration == INVALID_LONG_VALUE || transition == INVALID_INT_VALUE) {
            return null;
        } else {
            return new RetailStoreGeoFencing(id, lat, lng, radius, expiration, transition);
        }
    }



    public void setGeoFence(String id, RetailStoreGeoFencing geoFence) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(getGeoFenceFieldKey(id,KEY_LATITUDE), (float) geoFence.getLatitude());
        editor.putFloat(getGeoFenceFieldKey(id, KEY_LONGITUDE), (float) geoFence.getLongitude());
        editor.putFloat(getGeoFenceFieldKey(id,KEY_RADIUS), geoFence.getRadius());
        editor.putLong(getGeoFenceFieldKey(id, KEY_EXPIRATION), geoFence.getExpiration());
        editor.putInt(getGeoFenceFieldKey(id, KEY_TRANSITION), geoFence.getTransition());
        editor.commit();
        this.geoFence = geoFence;
    }

    public void clearGeoFence(String id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(getGeoFenceFieldKey(id, KEY_LATITUDE));
        editor.remove(getGeoFenceFieldKey(id,KEY_LONGITUDE));
        editor.remove(getGeoFenceFieldKey(id,KEY_RADIUS));
        editor.remove(getGeoFenceFieldKey(id,KEY_EXPIRATION));
        editor.remove(getGeoFenceFieldKey(id,KEY_TRANSITION));
        editor.commit();
    }


    public String getGeoFenceFieldKey(String id, String fieldName) {
     return  KEY_PREFIX + "_" + id + "_" + fieldName;
    }

    public SaveRetailStoreGeoFencing(Context context) {
        this.preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }


}
