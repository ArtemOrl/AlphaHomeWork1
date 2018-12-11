package com.alpha.AlphaPractice_01_12_2018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


// Мишина реализация уходит в бесконечный цикл создания и обработки запросов

public class Queue_Concur_5 {
}

class LoadServer {
    public static void main(String args[]) {
        BlockingQueue<Request1> queue = new ArrayBlockingQueue<>(3);
        Client client = new Client(queue);
        Server server = new Server(queue);
        new Thread(client).start();
        new Thread(server).start();
    }
}

class Request1 {
}

class Client implements Runnable {
    private BlockingQueue<Request1> queue;

    Client(BlockingQueue<Request1> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Request1 req = null;
            while (true) {
                req = new Request1();
                queue.put(req);
                System.out.println("added request - " + req);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}

class Server implements Runnable {
    private BlockingQueue<Request1> queue;

    Server(BlockingQueue<Request1> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("processing .. " + queue.take());
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}