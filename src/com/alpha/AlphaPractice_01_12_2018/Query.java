package com.alpha.AlphaPractice_01_12_2018;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Query extends Thread {

    public List<Request> list = new ArrayList<>();

    public List<Request> getList() {
        return list;
    }

    public void run(){
//        System.out.println("New request" + new Date());

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(list.add(new Request(new Date())));
        }
    }


    public static void main(String[] args) throws InterruptedException{

        Obrabotka main = new Obrabotka();
        main.listThread = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Query query = new Query();

            Thread thread = new Thread();
            thread.start();
            main.listThread.add(query);
            Thread.sleep(100);

        }
        new Thread(main).start();
    }


}

class Request {
    Date createTime;
    public Date responseTime;



    public Request(Date createTime) {
        this.createTime = createTime;
    }

}

class Obrabotka implements Runnable{
    public List<Query> listThread;
    public List<Thread> goodListThread = new ArrayList<>();
    public List<Thread> badListThread = new ArrayList<>();
    @Override
    public void run() {
        while (true){
            for (int i = 0; i < listThread.size(); i++) {
                Query query = listThread.get(i);
                List<Request> list = query.list;
                for (int j = 0; j < list.size(); j++) {
                    Request request = list.remove(0);
                    if (new Date().getTime()-request.createTime.getTime()<10000){

                    }
                }
            }
        }
    }
}