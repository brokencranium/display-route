package com.vv.buildstuff.displayroute.response;

import java.util.ArrayList;

/**
 * Created by vvennava on 10/4/14.
 */
public class DirectionsResponse {
    private Status status;
    private ArrayList<Route> routes;

    public DirectionsResponse() {
    }

    public DirectionsResponse(Status status, ArrayList<Route> routes) {
        this.status = status;
        this.routes = routes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }
}
