package com.vv.buildstuff.displayroute;

import android.content.Context;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.vv.buildstuff.displayroute.requestDirections.RequestDirections;
import com.vv.buildstuff.displayroute.requestPlaces.RequestPlacesNearbySearch;
import com.vv.buildstuff.displayroute.responsePlaces.Results;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private PolylineOptions polylineOptions;
    private String provider;
    private CameraPosition cameraPosition;
    private Marker marker;
    private LocationManager locationManager;
    private LatLong storeGeoMarker;
    private LatLong initLoc = new LatLong();

    private static final String TEST_PROVIDER = "TEST_PROVIDER";

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
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        setUpListener();
        setAllStoreMarkersIfNeeded();
    }

    /**
     *
     */
    private void setAllStoreMarkersIfNeeded() {
        if (storeGeoMarker == null) {
            // Set all store markers
            setAllStoreMarkers();
        }
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
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

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        polylineOptions = new PolylineOptions().width(5).color(Color.rgb(97, 125, 77)).geodesic(true);
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    /**
     *
     */
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

    /**
     * @param location
     */
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
                .tilt(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1500, null);

        polylineOptions.add(latLng);
        mMap.addPolyline(polylineOptions);

    }

    /**
     *
     */
    private void setAllStoreMarkers() {
        double lat;
        double lng;

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

    /**
     * @return
     */
    public LatLong getStoreGeoMarker() {
        return storeGeoMarker;
    }

    /**
     * @param storeGeoMarker
     */
    public void setStoreGeoMarker(LatLong storeGeoMarker) {
        this.storeGeoMarker = storeGeoMarker;
    }

    /**
     * @return
     */
    public LatLong getInitLoc() {
        return initLoc;
    }

    /**
     * @param initLoc
     */
    public void setInitLoc(LatLong initLoc) {
        this.initLoc = initLoc;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        initLoc.setLat(marker.getPosition().latitude);
        initLoc.setLng(marker.getPosition().longitude);

        AsyncDirections asyncDirections = new AsyncDirections();

        String startingPoint = initLoc.getLat() + "," + initLoc.getLng();
        String endPoint = marker.getPosition().latitude + "," + marker.getPosition().longitude;
        String out = startingPoint + endPoint;
        asyncDirections.execute(startingPoint,endPoint);
        return false;
    }

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

    /**
     * Local class for getting directions based on the current and the end location
     */
    private class AsyncDirections extends AsyncTask<String, String, ArrayList<LatLng>> {


        @Override
        protected ArrayList<LatLng> doInBackground(String... coordinates) {
            RequestDirections requestDirections = new RequestDirections();
            requestDirections.setOrigin(coordinates[0]);
            String out = coordinates[0] + " - " + coordinates[1];
            requestDirections.setDestination(coordinates[1]);
            return requestDirections.getDirectionsResponse();
        }

        @Override
        protected void onPostExecute(ArrayList<LatLng> latlngList) {
            PolylineOptions linesDir = new PolylineOptions().width(5).color(Color.rgb(212, 125, 210)).geodesic(true);

            linesDir.addAll(latlngList);
            for(LatLng latLng : latlngList ){
                System.out.println("VV Lat Lng" + latLng.latitude + " - " + latLng.longitude);
            }
            mMap.addPolyline(linesDir);
            super.onPostExecute(latlngList);
        }
    }


}