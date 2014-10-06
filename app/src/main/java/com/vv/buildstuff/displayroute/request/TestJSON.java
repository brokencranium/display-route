package com.vv.buildstuff.displayroute.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vv.buildstuff.displayroute.response.Bounds;
import com.vv.buildstuff.displayroute.response.Distance;
import com.vv.buildstuff.displayroute.response.DurationInTraffic;
import com.vv.buildstuff.displayroute.response.NorthEast;
import com.vv.buildstuff.displayroute.response.SouthWest;

/**
 * Created by vvennava on 10/5/14.
 */
public class TestJSON {

    public Object GSONToObject(String inputJson, Object obj){

        Gson gson = new GsonBuilder().create();

        return( gson.fromJson(inputJson, obj.getClass()));
    }


    public String objectToGSON(Object obj){
        Gson gson = new GsonBuilder().create();
       return gson.toJson(obj);
    }


    public void testNorthEast(){
//northeast, create JSON string
        NorthEast setNe = new NorthEast();
        setNe.setLat(47.6077279);
        setNe.setLng(-122.2642187);
        String jsonString = this.objectToGSON(setNe);
        System.out.println("North East VV " + jsonString);

// north east, build North East object from JSON string
        NorthEast ne = (NorthEast) this.GSONToObject(jsonString, setNe);
        System.out.println("Latitude " + ne.getLat() + " Longitude " + ne.getLng());

    }


    public void testSouthWest(){
//southwest, create JSON string
        SouthWest setSw = new SouthWest();
        setSw.setLat(50.6077279);
        setSw.setLng(-111.2642187);
        String jsonString = this.objectToGSON(setSw);
        System.out.println("South West VV " + jsonString);

//southwest, build South West object from JSON string
      SouthWest se = (SouthWest) this.GSONToObject(jsonString, setSw);
       System.out.println("Latitude " + se.getLat() + " Longitude " + se.getLng());
    }

    public void testBounds(){
        NorthEast setNe = new NorthEast();
        setNe.setLat(47.6077279);
        setNe.setLng(-122.2642187);

        SouthWest setSw = new SouthWest();
        setSw.setLat(50.6077279);
        setSw.setLng(-111.2642187);


        Bounds setBound = new Bounds();
        setBound.setNortheast(setNe);
        setBound.setSouthwest(setSw);
        String jsonString = this.objectToGSON(setBound);
        System.out.println("Bounds VV " + jsonString);

        Bounds bound = (Bounds) this.GSONToObject(jsonString, setBound);
        System.out.println("North East VV " + bound.getNortheast() + " South West VV " + bound.getSouthwest());
    }

    public void testDistance(){
        Distance setDistance = new Distance();
        setDistance.setText("5.3 mi");
        setDistance.setValue(25);

        String jsonString = this.objectToGSON(setDistance);
        System.out.println("Distance VV " + jsonString);

        Distance distance = (Distance) this.GSONToObject(jsonString, setDistance);
        System.out.println("Distance VV " + distance);
    }

    public void testDuration(){
        DurationInTraffic setDuration = new DurationInTraffic();
        setDuration.setText("2 mins");
        setDuration.setValue(101);

        String jsonString = this.objectToGSON(setDuration);
        System.out.println("Duration VV " + jsonString);

        DurationInTraffic duration = (DurationInTraffic) this.GSONToObject(jsonString, setDuration);
        System.out.println("Duration VV " + duration);
    }

    public void startLocation(){


    }

    public void endLocation(){

    }

    public static void main(String[] args){

        TestJSON testJSON = new TestJSON();
        testJSON.testNorthEast();
        testJSON.testSouthWest();
        testJSON.testBounds();
        testJSON.testDistance();
        testJSON.testDuration();


    }



}
