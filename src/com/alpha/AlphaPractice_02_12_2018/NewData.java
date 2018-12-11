package com.alpha.AlphaPractice_02_12_2018;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewData {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable myComputation = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 5 + 6;
            }
        };
        FutureTask task = new FutureTask<>(myComputation);
        Thread t = new Thread(task);
        t.start();// это Runnable t.start();
        Integer result = (Integer) task.get(); // это Future
        System.out.println(result);
    }
}
