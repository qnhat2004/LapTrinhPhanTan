package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadImpl extends Thread {
    private final AtomicInteger x;

    public ThreadImpl(AtomicInteger x) {
        this.x = x;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            x.incrementAndGet();
        }
        System.out.println("Done one thread, x = " + x.get());
    }
}
public class Main  {
    public static void main(String[] args) {
        AtomicInteger x = new AtomicInteger(0);

        Thread t1 = new ThreadImpl(x);
        Thread t2 = new ThreadImpl(x);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final x = " + x.get());
    }
}