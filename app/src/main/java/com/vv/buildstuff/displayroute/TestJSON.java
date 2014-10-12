package com.vv.buildstuff.displayroute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vv.buildstuff.displayroute.requestDirections.RequestDirections;
import com.vv.buildstuff.displayroute.responseDirections.Bounds;
import com.vv.buildstuff.displayroute.responseDirections.Legs;
import com.vv.buildstuff.displayroute.responseDirections.Polylines;
import com.vv.buildstuff.displayroute.responseDirections.Step;
import com.vv.buildstuff.displayroute.responseDirections.TextValue;

import java.util.ArrayList;

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


    public Bounds testBounds(){
        LatLong setNe = new LatLong();
        setNe.setLat(47.6077279);
        setNe.setLng(-122.2642187);

        LatLong setSw = new LatLong();
        setSw.setLat(50.6077279);
        setSw.setLng(-111.2642187);


        Bounds setBound = new Bounds();
        setBound.setNortheast(setNe);
        setBound.setSouthwest(setSw);
        String jsonString = this.objectToGSON(setBound);
        System.out.println("Bounds VV " + jsonString);

        Bounds bound = (Bounds) this.GSONToObject(jsonString, setBound);
        System.out.println("North East VV " + bound.getNortheast() + " South West VV " + bound.getSouthwest());
        return setBound;
    }

    public TextValue testDistance(){
        TextValue setDistance = new TextValue();
        setDistance.setText("5.3 mi");
        setDistance.setValue(25);

        String jsonString = this.objectToGSON(setDistance);
        System.out.println("Distance VV " + jsonString);

        TextValue textValue = (TextValue) this.GSONToObject(jsonString, setDistance);
        System.out.println("Distance VV " + textValue);
       return setDistance;
    }

    public TextValue testDuration(){
        TextValue setDuration = new TextValue();
        setDuration.setText("2 mins");
        setDuration.setValue(101);

        String jsonString = this.objectToGSON(setDuration);
        System.out.println("Duration VV " + jsonString);

        TextValue duration = (TextValue) this.GSONToObject(jsonString, setDuration);
        System.out.println("Duration VV " + duration);
        return setDuration;
    }

    public Step testSteps(){
        Step setStep = new Step();

        TextValue setDistance = new TextValue();
        setDistance.setText("5.3 mi");
        setDistance.setValue(25);
        setStep.setDistance(setDistance);


        TextValue setDuration = new TextValue();
        setDuration.setText("2 mins");
        setDuration.setValue(101);
        setStep.setDuration(setDuration);


        {
            LatLong latLong = new LatLong();
            latLong.setLat(47.6077279);
            latLong.setLng(-122.2642187);
            setStep.setStart_location(latLong);
        }

        setStep.setHtml_instructions("Head \u003cb\u003esouthwest\u003c/b\u003e on \u003cb\u003eMadison St\u003c/b\u003e toward \u003cb\u003e4th Ave\u003c/b\u003e");

        Polylines polyline = new Polylines();
        polyline.setPoints("ybqaHj_tiVL\\\\j@jB");
        setStep.setPolyline(polyline);

        {
            LatLong latLong = new LatLong();
            latLong.setLat(33.6077279);
            latLong.setLng(-111.2642187);
            setStep.setEnd_location(latLong);
        }

        setStep.setTravel_mode("DRIVING");

        String jsonString = this.objectToGSON(setStep);
        System.out.println("Duration VV " + jsonString);

        Step step = (Step) this.GSONToObject(jsonString, setStep);
        System.out.println("Duration VV " + step);

        return setStep;
    }


    public void testLegs(){
        ArrayList<Step> setSteps = new ArrayList();
        Step setStep =  this.testSteps();
        setSteps.add(setStep);
        setSteps.add(setStep);
        setSteps.add(setStep);

        TextValue setDistance = this.testDistance();
        TextValue setDuration = this.testDuration();
        String endAddress = "Tacoma, WA" ;
        LatLong end_location = testSteps().getEnd_location();
        String startAddress = "Seattle, WA";
        LatLong start_location = testSteps().getStart_location();
        String arrival_time = "22:30";
        String departure_time = "10.30";

        Legs setLegs = new Legs();
        setLegs.setDistance(setDistance);
        setLegs.setDuration(setDuration);
        setLegs.setEnd_address(endAddress);
        setLegs.setEnd_location(end_location);
        setLegs.setStart_address(startAddress);
        setLegs.setStart_location(start_location);
        setLegs.setArrival_time(arrival_time);
        setLegs.setDeparture_time(departure_time);
        setLegs.setSteps(setSteps);


        String jsonString = this.objectToGSON(setLegs);
        System.out.println("Duration VV " + jsonString);

        Legs legs = (Legs) this.GSONToObject(jsonString, setLegs);
        System.out.println("Duration VV " + legs);

    }


    public void testOrigDestFormatting(){
        String origin = "This is a test 12345 and will this pass";
        String destination = "I will get to the destination regardless of the journey";
        RequestDirections requestDirections = new RequestDirections();
        requestDirections.setDestination(destination);
        requestDirections.setOrigin(origin);

        System.out.println("Comma delimited origin " + requestDirections.getOrigin());
        System.out.println("Comma delimited destination " + requestDirections.getDestination());

    }

//    public static void main(String[] args){
//
//        TestJSON testJSON = new TestJSON();
//        testJSON.testBounds();
//        testJSON.testDistance();
//        testJSON.testDuration();
//        testJSON.testSteps();
//        testJSON.testLegs();

//        RequestDirections requestDirections = new RequestDirections();
//        requestDirections.getDirectionsResponse();

//        testJSON.testOrigDestFormatting();
//    }



}
