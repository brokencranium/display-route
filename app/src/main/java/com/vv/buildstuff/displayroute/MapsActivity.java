package com.vv.buildstuff.displayroute;

import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private PolylineOptions polylineOptions;
    private String provider;
    private CameraPosition cameraPosition;
    private LatLng latLng;
    private float zoom = 0;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zoom = 15;
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        setUpListener();
    }

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
        polylineOptions = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        mMap.setMyLocationEnabled(true);

    }

    private void setUpListener() {
        String context = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) getSystemService(context);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        provider = locationManager.getBestProvider(criteria, true);
        Log.i(this.getClass().getSimpleName(), "Provider Name" + provider);
        locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 5, 20, locationListener);
    }

    private void updateMapLocation(Location location) {
        //    Log.i(this.getClass().getSimpleName(),"lat + long" + location.getLatitude() + " " + location.getLongitude());
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        zoom = mMap.getCameraPosition().zoom;

     //   mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        polylineOptions.add(latLng);
        mMap.addPolyline(polylineOptions);


        mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.arrow_up_float))
                        .position(latLng)
                        .flat(true)
                        .rotation(0)
        );
        cameraPosition = CameraPosition.builder()
                .target(latLng)
                .zoom(zoom)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 10, null);



    }

}
