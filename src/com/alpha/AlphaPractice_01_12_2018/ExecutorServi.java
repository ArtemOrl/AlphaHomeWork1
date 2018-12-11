package AlphaPractice_01_12_2018;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ExecutorServi{}
class ConcurrentHashMapDemo {
    public static final ConcurrentHashMap<Integer, String> conHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new WriteThreasOne());
        service.execute(new WriteThreasTwo());
        service.execute(new ReadThread());
    }
}

class WriteThreasOne implements Runnable {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            ConcurrentHashMapDemo.conHashMap.putIfAbsent(i, "A" + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }
}

class WriteThreasTwo implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            ConcurrentHashMapDemo.conHashMap.put(i, "B" + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        }
    }
}

class ReadThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            Iterator<Integer> ite = ConcurrentHashMapDemo.conHashMap.keySet().iterator();
            while (ite.hasNext()) {
                Integer key = ite.next();
                System.out.println(key + " : " + ConcurrentHashMapDemo.conHashMap.get(key));
            }
        }
    }
}
