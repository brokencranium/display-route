package com.vv.buildstuff.displayroute.requestDirections;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.vv.buildstuff.displayroute.Miscellaneous;
import com.vv.buildstuff.displayroute.responseDirections.DirectionsResponse;
import com.vv.buildstuff.displayroute.responseDirections.Legs;
import com.vv.buildstuff.displayroute.responseDirections.Routes;
import com.vv.buildstuff.displayroute.responseDirections.Step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import util.PolyUtil;

import static util.UtilityMethods.replaceSpaceWithCommas;

/**
 * Created by vvennava on 10/5/14.
 */

public class RequestDirections {

    private String urlString;
    private HttpURLConnection urlConnection;
    private URL url;
    private String origin;
    private String destination;
    private String waypoints;
    private String alternatives;

    public String getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String waypoints) {
        this.waypoints = waypoints;
    }

    public String getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(String alternatives) {
        this.alternatives = alternatives;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    private String avoid;
    private String units;
    private String region;
    private String departure_time;
    private String arrival_time;

    public RequestDirections() {
//        this.urlString = "http://maps.googleapis.com/maps/api/directions/json?" +
//                "origin=Seattle&destination=Spokane";
//                +
//               "&key=AIzaSyDXGbPGjbLwBgMrc2yivRP6NGQwOi_-Mrc";
    }

    public RequestDirections(String urlString) {
        this.urlString = urlString;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public void setUrlString() {
        this.urlString = urlBuilder();
    }


    public ArrayList<LatLng> getDirectionsResponse() {
        ArrayList<LatLng> latLngList = new ArrayList<LatLng>();
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            final DirectionsResponse directionsResponse = readInputStream(reader);

//         InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//         readXMLInputStream(inputStream);
//         readJSONInputStream(inputStream);
            System.out.println("URL path " + urlString);
            final ArrayList<Routes> routes = directionsResponse.getRoutes();
            for (Routes route : routes) {
                ArrayList<Legs> legs = route.getLegs();
                for (Legs leg : legs) {
                    ArrayList<Step> steps = leg.getSteps();
                    for (Step step : steps) {
//                        System.out.println("Start latitude " + step.getStart_location().getLat() + " Longitude " + step.getStart_location().getLng());
//                        System.out.println(" End latitude " + step.getStart_location().getLat() + " Longitude " + step.getStart_location().getLng());
                        latLngList.addAll(PolyUtil.decode(step.getPolyline().getPoints()));

                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latLngList;
    }

    private DirectionsResponse readInputStream(Reader reader) {
        Gson gson = new Gson();

        return gson.fromJson(reader, DirectionsResponse.class);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = replaceSpaceWithCommas(origin);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = replaceSpaceWithCommas(destination);
    }


    private String urlBuilder() {
        if (origin == null) {
            origin = Miscellaneous.DEFAULT_LOCATION.getText();
        }

        if (destination == null) {
            destination = Miscellaneous.DEFAULT_DESTINATION.getText();
        }

        StringBuilder urlBuilder = new StringBuilder();
        final String urlValue = urlBuilder.append(Miscellaneous.DIRECTIONS_URL)
                .append("origin=")
                .append(origin)
                .append("&destination=")
                .append(destination)
                .toString();
        System.out.println(urlValue);
        return urlValue;
    }
}
