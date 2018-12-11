package com.alpha.AlphaPractice_01_12_2018;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Query_0 extends Thread {

    private BlockingQueue<Request_0> queue;

    public Query_0 (BlockingQueue<Request_0> queue){
        this.queue = queue;
    }

    public void run(){

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                queue.put(new Request_0(new Date()));
                System.out.println("Request added" + i + " queue size " + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<Request_0> queue = new ArrayBlockingQueue<Request_0>(800);

        for (int i = 0; i < 8; i++) {
           Query_0 query = new Query_0(queue);
           query.start();

        }
        for (int i = 0; i < 40; i++) {
            Obrabotka_0 main = new Obrabotka_0(queue);
            new Thread(main).start();
        }

    }

}

class Request_0 {
    Date createTime;
    Date responseTime;

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Request_0(Date createTime) {
        this.createTime = createTime;
    }

}

class Obrabotka_0 implements Runnable{

    public List<Request_0> goodListThread = new ArrayList<>();
    public List<Request_0> badListThread = new ArrayList<>();

    private BlockingQueue<Request_0> queue;

    public Obrabotka_0(BlockingQueue<Request_0> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!queue.isEmpty()){

            try {
                Request_0 request = queue.take();
//                System.out.println(new Date().getTime()-request.createTime.getTime());
                if (new Date().getTime()-request.createTime.getTime()<10000){

                    Thread.sleep(1000);
                    request.setResponseTime(new Date());
                    goodListThread.add(request);


                } else badListThread.add(request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Good requests " + goodListThread.size());
//        for (Request_0 request_0 : goodListThread) {
//            System.out.println(request_0);
//            System.out.println(request_0.responseTime.getTime() - request_0.createTime.getTime());
//        }
        System.out.println("Bad requests " + badListThread.size());
    }
}
