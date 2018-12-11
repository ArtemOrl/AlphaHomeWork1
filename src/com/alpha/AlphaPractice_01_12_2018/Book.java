package AlphaPractice_01_12_2018;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;

public class Book {
    String title;
//    DoubleAdder copesSold = new DoubleAdder();
    AtomicInteger copiesSold = new AtomicInteger();

    public Book(String title) {
        this.title = title;
    }
    public void copiesSold(){}

    public void newSale(){
        copiesSold.incrementAndGet();
    }
    public void returnBook(){copiesSold.decrementAndGet();}
}
