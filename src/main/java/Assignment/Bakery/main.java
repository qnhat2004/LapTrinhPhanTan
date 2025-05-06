//package Assignment.Bakery;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main{

    static int x = 0;
    public static void main(String[] args) throws InterruptedException {
        Bakery algo = new Bakery(2);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                algo.requestCS(0);
                int newValue = ++x;
                System.out.println("T1 - x = " + newValue);
                algo.releaseCS(0);
                Thread.yield();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                algo.requestCS(1);
                int newValue = ++x;
                System.out.println("T2 - x = " + newValue);
                algo.releaseCS(1);
                Thread.yield();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value of x = " + x);
    }
}