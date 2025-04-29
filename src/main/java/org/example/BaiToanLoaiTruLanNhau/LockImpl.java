package org.example.BaiToanLoaiTruLanNhau;

import java.util.concurrent.atomic.AtomicInteger;

public class LockImpl {
    // Algo 1
    public class Attempt1 {
        volatile boolean openDoor = true;

        public void requestCS(int i) {
            while (!openDoor);  // wait
            openDoor = false;
        }

        public void releaseCS(int i) {
            openDoor = true;
        }
    }

    // Algo 2
    public class Attempt2 {
        volatile boolean[] wantCS = {false, false};

        public void requestCS(int i) {
            wantCS[i] = true;
            while (wantCS[1 - i]);  // wait
        }

        public void releaseCS(int i) {
            wantCS[i] = false;
        }
    }

    // Peterson's Algorithm
    public class Attempt3 {
        volatile int turn = 1;
        volatile boolean wantCS[] = {false, false};
        public void requestCS(int i) {
            int j = 1 - i;
            wantCS[i] = true;
            turn = j;
            while (wantCS[j] && turn == j); // wait
        }
        public void releaseCS(int i) {
            wantCS[i] = false;
        }
    }

    public void program() {
        AtomicInteger x = new AtomicInteger(0); // Biến dùng chung giữa các thread

        Attempt3 algo = new Attempt3();

        // Thread t1 tăng x 10 lần
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                algo.requestCS(0);
                int newValue = x.incrementAndGet(); // Tăng giá trị x an toàn trong môi trường đa luồng
                System.out.println("T1 - x = " + newValue);
                algo.releaseCS(0);
            }
        });

        // Thread t2 chỉ in thông báo
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                algo.requestCS(1);
                int newValue = x.incrementAndGet(); // Tăng giá trị x an toàn trong môi trường đa luồng
                System.out.println("T2 - x = " + newValue);
                algo.releaseCS(1);
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

    public static void main(String[] args) {
        LockImpl lockImpl = new LockImpl();
        lockImpl.program();
    }
}
