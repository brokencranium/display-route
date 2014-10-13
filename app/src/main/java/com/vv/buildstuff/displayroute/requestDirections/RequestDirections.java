package com.vv.buildstuff.displayroute.requestDirections;

import com.google.gson.Gson;
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

    public RequestDirections() {
        this.urlString = "http://maps.googleapis.com/maps/api/directions/json?" +
                "origin=Seattle&destination=Spokane";
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


    public void getDirectionsResponse() {
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            final DirectionsResponse directionsResponse = readInputStream(reader);

//         InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//         readXMLInputStream(inputStream);
//         readJSONInputStream(inputStream);

            final ArrayList<Routes> routes = directionsResponse.getRoutes();
            for (Routes route : routes) {
                ArrayList<Legs> legs = route.getLegs();
                for (Legs leg : legs) {
                    ArrayList<Step> steps = leg.getSteps();
                    for (Step step : steps) {
                        System.out.println("latitude " + step.getStart_location().getLat() + " Longitude " + step.getEnd_location().getLng());

                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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



}
