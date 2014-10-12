package com.vv.buildstuff.displayroute.responseDirections;

import java.util.ArrayList;

/**
 * Created by vvennava on 10/4/14.
 */
public class DirectionsResponse {
    private ArrayList<Routes> routes;
    private String status;

    public DirectionsResponse() {
    }

    public DirectionsResponse(String status, ArrayList<Routes> routes) {
        this.status = status;
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Routes> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "DirectionsResponse [" +
                "status=" + status +
                ", routes=" + routes +
                ']';
    }
}
