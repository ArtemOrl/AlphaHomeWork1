package com.alpha.AlphaPractice_01_12_2018;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables_7 {
    public static void main(String[] args) {
        Book1 book1 = new Book1("Java frameworks");
        book1.newSale();
        book1.newSale();
        System.out.println(book1.copiesSold);
        book1.returnBook();
        System.out.println(book1.copiesSold);
    }
}

class Book1 {
    String title;
    AtomicInteger copiesSold = new AtomicInteger(0);

    Book1(String title) {
        this.title = title;
    }

    public void newSale() {
        copiesSold.incrementAndGet();
    }

    public void returnBook() {
        copiesSold.decrementAndGet();
    }
}

