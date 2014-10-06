package com.vv.buildstuff.displayroute.response;

/**
 * Created by vvennava on 10/4/14.
 */
public class TransitDetails {
    private LocationStop arrivalStop;
    private LocationStop  departureStop;
    private ArrDepTime arrivalTime;
    private ArrDepTime departureTime;
    private String headSign;
    private String headWay;
    private String numStops;
    private Line line;

    public TransitDetails() {
    }

    public TransitDetails(LocationStop arrivalStop, LocationStop departureStop,
                          ArrDepTime arrivalTime, ArrDepTime departureTime, String headSign,
                          String headWay, String numStops, Line line) {
        this.arrivalStop = arrivalStop;
        this.departureStop = departureStop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.headSign = headSign;
        this.headWay = headWay;
        this.numStops = numStops;
        this.line = line;
    }

    public LocationStop getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(LocationStop arrivalStop) {
        this.arrivalStop = arrivalStop;
    }

    public LocationStop getDepartureStop() {
        return departureStop;
    }

    public void setDepartureStop(LocationStop departureStop) {
        this.departureStop = departureStop;
    }

    public ArrDepTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ArrDepTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ArrDepTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ArrDepTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getHeadSign() {
        return headSign;
    }

    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    public String getHeadWay() {
        return headWay;
    }

    public void setHeadWay(String headWay) {
        this.headWay = headWay;
    }

    public String getNumStops() {
        return numStops;
    }

    public void setNumStops(String numStops) {
        this.numStops = numStops;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "TransitDetails [" +
                "arrivalStop=" + arrivalStop +
                ", departureStop=" + departureStop +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", headSign='" + headSign + '\'' +
                ", headWay='" + headWay + '\'' +
                ", numStops='" + numStops + '\'' +
                ", line=" + line +
                ']';
    }
}

