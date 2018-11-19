package com.alpha.AlphaPractice_11_11_2018.RailWay;

import java.util.ArrayList;
import java.util.List;

// система у нас одна так что Singleton
public class RailWaySystem {
    List<Station> listOfStations = new ArrayList<>();
    // тут метод по заполнению списка станций (БД у нас пока нет)
    List<Station> listOfPassengers = new ArrayList<>();
    // будет заполняться в процессе и удаляться тоже
    List<Request> listOfRequests = new ArrayList<>();
    // запросы
    List<Train> listOfTrains = new ArrayList<>();

    private static volatile RailWaySystem railWaySystem;
    Request request;
    private RailWaySystem() {

    }

    public static RailWaySystem getInstance() {
        if (railWaySystem == null) {
            synchronized (RailWaySystem.class) {
                if (railWaySystem == null) {

                    railWaySystem = new RailWaySystem();
                }
            }
        }
        return railWaySystem;
    }

   public void registerRequest(Request request){
        request.setRegistred(true);
        listOfRequests.add(request);

   }

   public List<Train> searchTrain (Station stationStart, Station stationFinish){
        List<Train> result = null;
        for (int i = 0; i < listOfTrains.size(); i++) {
            Train train = listOfTrains.get(i);
            List<Station> stations = train.getRoute();
           for (int j = 0; j < stations.size(); j++) {
                int matchez = 0;
                if (stations.get(i).equals(stationStart) || stations.get(i).equals(stationFinish)) matchez++;
                if (matchez==2) {
                    result.add(train);
                    break;
                }
           }
        }
        return result;
   }

   public int countNumberOfStations(Train train, Station stationStart, Station stationFinish){
        boolean isStarted = false;
        int count = 0;
        for (int i = 0; i < train.getRoute().size(); i++) {
             if (train.getRoute().get(i).equals(stationStart)) isStarted = true;
             if (isStarted == true) count++;
             if (train.getRoute().get(i).equals(stationFinish)) isStarted = false;
        }
        return count;
   }
}
