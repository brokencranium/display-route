package com.vv.buildstuff.displayroute.geoFencing;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationClient;

import java.util.List;

/**
 * Created by vvennava on 10/18/14.
 */
public class ReceiveTransitionsIntentService extends IntentService {


    public ReceiveTransitionsIntentService() {
        super("ReceiveTransitionsIntentService");
    }
    public ReceiveTransitionsIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (LocationClient.hasError(intent)){
            int errorCode = LocationClient.getErrorCode(intent);
            Log.e("ReceiveTransitionService", "Location service error" + Integer.toString(errorCode));
        }else{
            int transitionType = LocationClient.getGeofenceTransition(intent);

            if (transitionType == Geofence.GEOFENCE_TRANSITION_ENTER ||
                transitionType == Geofence.GEOFENCE_TRANSITION_EXIT){
                List<Geofence> triggerList = getTriggeringGeoFences(intent);
                String[] triggerIds = new String[triggerList.size()];
                for (int i = 0; i < triggerIds.length; i++){
                    triggerIds[i] = triggerList.get(i).getRequestId();
                    Log.i("ReceiveTransitionService", "On handle intent " + triggerIds[i] );

                }
            }else{
                Log.e("ReceiveTransitionService", "Geo fence transition error " + Integer.toString(transitionType));
            }
        }

    }

    private List<Geofence> getTriggeringGeoFences(Intent intent) {

        return null;

    }
}
