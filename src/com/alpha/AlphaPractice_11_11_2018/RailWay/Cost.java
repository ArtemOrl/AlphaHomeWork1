package com.alpha.AlphaPractice_11_11_2018.RailWay;

public class Cost {

    private long costPerStation = (long) 0.52;
    int numberOfStations;

    private static volatile Cost cost;
    Request request;
    private Cost() {

    }

    public long getCostPerStation() {
        return costPerStation;
    }

    public void setCostPerStation(long costPerStation) {
        this.costPerStation = costPerStation;
    }

    public static Cost getInstance() {
        if (cost == null) {
            synchronized (RailWaySystem.class) {
                if (cost == null) {

                    cost = new Cost();
                }
            }
        }
        return cost;
    }

    public void registerRequest(Request request){
        request.setRegistred(true);
        RailWaySystem.getInstance().listOfRequests.add(request);

    }

    public long countCost(long cost, int numberOfStations){
        return cost * numberOfStations;
    }
}
