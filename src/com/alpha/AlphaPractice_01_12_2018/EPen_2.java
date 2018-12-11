package AlphaPractice_01_12_2018;


public class EPen_2 implements Runnable {
    public void run() {
        System.out.println("eJava");
        //start();
    }

    public static void main(String... args) {
        new Thread(new EPen_2()).start();
    }
}
