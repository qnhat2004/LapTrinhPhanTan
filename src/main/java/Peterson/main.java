package Peterson;

public class main{

    static int x = 0;
    public static void main(String[] args) throws InterruptedException {
        Peterson algo = new Peterson();

        // Thread t1 tăng x 10 lần
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                algo.requestCS(0);
                int newValue = ++x;
                System.out.println("T1 - x = " + newValue);
                algo.releaseCS(0);
            }
        });

        // Thread t2 chỉ in thông báo
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                algo.requestCS(1);
                int newValue = ++x;
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
        System.out.println("Final value of x = " + x);
    }
}