package AlphaPractice_01_12_2018;

public class EThread_1 {
    public static void main(String[] args) {
        Thread bug = new Thread(() -> System.out.print("check bugs"));
        Thread reportQA = new Thread(bug);
        reportQA.run();
    }
}
