package com.vv.buildstuff.displayroute;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.vv.buildstuff.displayroute.geoFencing.ReceiveTransitionsIntentService;
import com.vv.buildstuff.displayroute.geoFencing.RetailStoreGeoFencing;
import com.vv.buildstuff.displayroute.geoFencing.SaveRetailStoreGeoFencing;
import com.vv.buildstuff.displayroute.requestDirections.RequestDirections;
import com.vv.buildstuff.displayroute.requestPlaces.RequestPlacesNearbySearch;
import com.vv.buildstuff.displayroute.responsePlaces.Results;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        LocationClient.OnAddGeofencesResultListener,
        LocationClient.OnRemoveGeofencesResultListener {

    private static final String TEST_PROVIDER = "TEST_PROVIDER";

    private static final long SECONDS_PER_HOUR = 60;
    private static final long MILLISECONDS_PER_SECOND = 1000;
    private static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;
    private static final float RADIUS_GEO_FENCE = 100;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationManager locationManager;

    private String provider;
    private PolylineOptions polylineOptions;
    private CameraPosition cameraPosition;
    private Marker marker;

    private LatLong initLoc = new LatLong();
    private LatLong storeGeoMarker;

    private Polyline selectedStorePolyLine;
    private Polyline rawStorePolyLine;

    private SaveRetailStoreGeoFencing saveGeoFence;
    private ArrayList<Geofence> listGeoFence;
    private List<String> removeListGeoFences;

    private String counter = "0";


    public enum REQUEST_TYPE {
        ADD,
        REMOVE_INTENT,
        REMOVE_LIST;
    }

    private REQUEST_TYPE requestType;

    private LocationClient locationClient;
    private PendingIntent pendingIntent;
    private boolean isRequestInProgress;


    private static final long GEOFENCE_EXPIRATION_TIME =
            SECONDS_PER_HOUR + MILLISECONDS_PER_SECOND + GEOFENCE_EXPIRATION_IN_HOURS;

    //    private ArrayList<DistanceBetweenLatLng> distLatLngList;
    private CalculateDistanceLatLng calcDistLatLng;

    /**
     *
     */
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateMapLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    /**
     * This is where the magic begins ...
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listGeoFence = new ArrayList<Geofence>();
//        distLatLngList = new ArrayList<DistanceBetweenLatLng>();
        calcDistLatLng = new CalculateDistanceLatLng(RADIUS_GEO_FENCE);
        saveGeoFence = new SaveRetailStoreGeoFencing(this);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        setUpListener();
        setAllStoreMarkersIfNeeded();
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        initLoc.setLat(marker.getPosition().latitude);
//        initLoc.setLng(marker.getPosition().longitude);

        AsyncDirections asyncDirections = new AsyncDirections();

        String startingPoint = initLoc.getLat() + "," + initLoc.getLng();
        String endPoint = marker.getPosition().latitude + "," + marker.getPosition().longitude;
        asyncDirections.execute(startingPoint, endPoint);
        return false;
    }


    @Override
    public void onConnected(Bundle bundle) {
        switch (requestType) {
            case ADD:
                pendingIntent = getTransitionPendingIntent();
                locationClient.addGeofences(listGeoFence, pendingIntent, this);
                break;
            case REMOVE_INTENT:
                locationClient.removeGeofences(pendingIntent, this);
                break;
            case REMOVE_LIST:
                if (removeListGeoFences.size() > 0)
                    locationClient.removeGeofences(removeListGeoFences, this);
                break;
        }
    }

    @Override
    public void onDisconnected() {
        isRequestInProgress = false;
        locationClient = null;

    }


//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }

    @Override
    public void onAddGeofencesResult(int statusCode, String[] geoFenceRequestIds) {
        String out = "Add geo fence";
        if (geoFenceRequestIds[0] != null) {
            out = out + " - " + geoFenceRequestIds[0];
        }

        if (LocationStatusCodes.SUCCESS == statusCode) {
//            update UI or send a broadcast intent
            out = "Success" + out;

        } else {

            out = "Try again" + out;
        }

        Toast.makeText(getApplicationContext(),
                out,
                Toast.LENGTH_LONG).show();
        isRequestInProgress = false;
        locationClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        isRequestInProgress = false;
        locationClient = null;

    }

    @Override
    public void onRemoveGeofencesByRequestIdsResult(int statusCode, String[] strings) {
        String out = "Remove all geo fences";
        if (LocationStatusCodes.SUCCESS == statusCode) {
            out = "Success" + out;
        } else {

            out = "Try remove all geo fences again" + out;
        }
        Toast.makeText(getApplicationContext(),
                out,
                Toast.LENGTH_LONG).show();

        isRequestInProgress = false;
        locationClient.disconnect();
    }

    @Override
    public void onRemoveGeofencesByPendingIntentResult(int statusCode, PendingIntent pendingIntent) {
        String out = "Remove geo fence";


        if (LocationStatusCodes.SUCCESS == statusCode) {
//            update UI or send a broadcast intent
            out = "Success" + out;

        } else {

            out = "Try remove geo fence again" + out;
        }
        Toast.makeText(getApplicationContext(),
                out,
                Toast.LENGTH_LONG).show();

        isRequestInProgress = false;
        locationClient.disconnect();

    }
//************************************************

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {

                setUpMap();
            }
        }

    }

    private void setUpMap() {
        polylineOptions = new PolylineOptions().width(5).color(Color.rgb(97, 125, 77)).geodesic(true);
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setBuildingsEnabled(true);

    }

    private void setAllStoreMarkersIfNeeded() {
        if (storeGeoMarker == null) {
            // Set all store markers
            setAllStoreMarkers();
        }
    }

    private void setUpListener() {
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(context);

        if (locationManager.getProvider(TEST_PROVIDER) != null &&
                locationManager.isProviderEnabled(TEST_PROVIDER)) {
            provider = TEST_PROVIDER;
        } else {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);
            provider = locationManager.getBestProvider(criteria, true);
        }
        Log.i(this.getClass().getSimpleName(), "Provider Name" + provider);

        if (locationManager.getLastKnownLocation(provider) != null) {
            initLoc.setLat(locationManager.getLastKnownLocation(provider).getLatitude());
            initLoc.setLng(locationManager.getLastKnownLocation(provider).getLongitude());
            String out = "Lat & long " + locationManager.getLastKnownLocation(provider).getLatitude() + " " + locationManager.getLastKnownLocation(provider).getLatitude();
            Toast.makeText(getApplicationContext(),
                    out,
                    Toast.LENGTH_LONG).show();
        }
        locationManager.requestLocationUpdates(provider, 1500, 10, locationListener);
    }


    private void updateMapLocation(Location location) {
        //    Log.i(this.getClass().getSimpleName(),"lat + long" + location.getLatitude() + " " + location.getLongitude());
        final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (marker != null) {
            marker.remove();
        }
        marker = mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .position(latLng)
                        .alpha(0.7f)
//                        .flat(true)
//                        .anchor(.5f,.5f)
//                        .rotation(90)
        );

        cameraPosition = CameraPosition.builder()
                .target(latLng)
                .zoom(mMap.getCameraPosition().zoom)
                .bearing(90)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1500, null);

        polylineOptions.add(latLng);
        mMap.addPolyline(polylineOptions);

    }

    private void setAllStoreMarkers() {
        double lat;
        double lng;
        if (mMap == null) {
            return;
        }
        mMap.setOnMarkerClickListener(this);
        if ((initLoc.getLat() != 0) && (initLoc.getLng() != 0)) {
            lat = initLoc.getLat();
            lng = initLoc.getLng();
        } else {
            lat = Double.parseDouble(Miscellaneous.DEFAULT_LATITUDE.getText());
            lng = Double.parseDouble(Miscellaneous.DEFAULT_LONGITUDE.getText());
        }
        storeGeoMarker = new LatLong(lat, lng);
        AsyncNearBySearch asyncSearch = new AsyncNearBySearch();
        asyncSearch.execute(storeGeoMarker);

    }

    public void setGeoFencing(String geoFenceId, LatLng latLng, Float radius, Long expiration, int transition) {
        final RetailStoreGeoFencing geoFencing = new RetailStoreGeoFencing(geoFenceId, latLng.latitude, latLng.longitude, radius, expiration, transition);
        listGeoFence.add(geoFencing.toGeofence());
        saveGeoFence.setGeoFence(geoFenceId, geoFencing);
    }

    private PendingIntent getTransitionPendingIntent() {
        Intent intent = new Intent(this, ReceiveTransitionsIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    public void addGeoFence() {
        requestType = REQUEST_TYPE.ADD;

        locationClient = new LocationClient(this, this, this);

        if (!isRequestInProgress) {
            isRequestInProgress = true;
            locationClient.connect();
        }

    }

    public void removeGeoFence(PendingIntent requestIntent) {
        requestType = REQUEST_TYPE.REMOVE_INTENT;
        pendingIntent = requestIntent;

        locationClient = new LocationClient(this, this, this);
        if (!isRequestInProgress) {
            isRequestInProgress = true;
            locationClient.connect();
        } else {
            Log.i(this.getClass().getSimpleName().toString(), "remove geo fence location client request is in process");
        }

    }

    public void removeGeofences(List<Geofence> geoFenceList) {

        for (Geofence geofence : geoFenceList) {
            removeListGeoFences.add(geofence.getRequestId());
        }


        requestType = REQUEST_TYPE.REMOVE_LIST;
        locationClient = new LocationClient(this, this, this);

        if (!isRequestInProgress) {
            isRequestInProgress = true;
            locationClient.connect();
        } else {
            Log.e("Maps activity, remove geo fences", "Location client is in progress");
        }

    }


//************************************************

    /**
     * Local class for searching stores based on the geolocation
     */
    private class AsyncNearBySearch extends AsyncTask<LatLong, String, ArrayList<Results>> {

        @Override
        protected ArrayList<Results> doInBackground(LatLong... latLongs) {
            RequestPlacesNearbySearch search = new RequestPlacesNearbySearch();
            search.setUrlString();
            return search.getPlacesResponse();
        }

        @Override
        protected void onPostExecute(ArrayList<Results> results) {
            for (Results result : results) {
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.starbuckslogo))
                        .position(new LatLng(result.getGeometry().getLocation().getLat(), result.getGeometry().getLocation().getLng()))
                        .alpha(0.7f));
            }
            super.onPostExecute(results);
        }
    }

//************************************************

    /**
     * Local class for getting directions based on the current and the end location
     */
    private class AsyncDirections extends AsyncTask<String, String, ArrayList<ArrayList<LatLng>>> {


        @Override
        protected ArrayList<ArrayList<LatLng>> doInBackground(String... coordinates) {
            RequestDirections requestDirections = new RequestDirections();
            requestDirections.setOrigin(coordinates[0]);
//            String out = coordinates[0] + " - " + coordinates[1];
            requestDirections.setDestination(coordinates[1]);
            requestDirections.setUrlString();
            return requestDirections.getDirectionsResponse();
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<LatLng>> latLngLists) {
            int count = Integer.parseInt(counter);


            PolylineOptions linesDir = new PolylineOptions().width(5).color(Color.rgb(212, 125, 210)).geodesic(true);
//            PolylineOptions rawLinesDir = new PolylineOptions().width(5).color(Color.rgb(28, 17, 50)).geodesic(true);

            if (latLngLists.size() >= 1) {
                linesDir.addAll(latLngLists.get(0));
//                rawLinesDir.addAll(latLngLists.get(1));
                for (LatLng latLng : latLngLists.get(0)) {
                    Log.i(this.getClass().getSimpleName().toString(), "VV Lat Lng" + latLng.latitude + " - " + latLng.longitude);
                    count = count + 1;
                    counter = String.valueOf(count);
                    LatLng calcLatLng = calcDistLatLng.setCalculateDistance(latLng);
                    if (calcLatLng != null) {
                        setGeoFencing(counter, calcLatLng, RADIUS_GEO_FENCE, GEOFENCE_EXPIRATION_TIME, Geofence.GEOFENCE_TRANSITION_ENTER);
                    }
                }
                addGeoFence();


                if (selectedStorePolyLine != null) {
                    selectedStorePolyLine.remove();
                }

//                if (rawStorePolyLine != null) {
//                    rawStorePolyLine.remove();
//                }
//                rawStorePolyLine = mMap.addPolyline(rawLinesDir);
                selectedStorePolyLine = mMap.addPolyline(linesDir);
            }

            super.onPostExecute(latLngLists);
        }
    }
}


