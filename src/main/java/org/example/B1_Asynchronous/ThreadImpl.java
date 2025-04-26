package org.example.B1_Asynchronous;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadImpl {
    public static void main(String[] args) {
        AtomicInteger x = new AtomicInteger(0); // Biến dùng chung giữa các thread

        // Thread t1 tăng x 10 lần
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                x.incrementAndGet(); // Tăng giá trị x an toàn trong môi trường đa luồng
                System.out.println("T1 - x = " + x.get());
            }
        });

        // Thread t2 chỉ in thông báo
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                x.incrementAndGet(); // Tăng giá trị x an toàn trong môi trường đa luồng
                System.out.println("T2 - x = " + x.get());
            }
        });

        // Bắt đầu cả hai thread
        t1.start();
        t2.start();

        // Chờ cả hai thread kết thúc
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // In giá trị cuối cùng của x
        System.out.println("Final value of x = " + x.get());
    }
}
