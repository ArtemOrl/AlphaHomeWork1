package com.alpha.AlphaPractice_01_12_2018;

class ELock {
}

class EPaper implements Runnable {
    public void run() {
        synchronized (ELock.class) {
            System.out.println("Hand made paper");
        }
    }

    public static void main(String args[]) throws Exception {
        Thread epaper = new Thread(new EPaper());
        epaper.start();
        synchronized (ELock.class) {
            epaper.join();
        }
    }
}
