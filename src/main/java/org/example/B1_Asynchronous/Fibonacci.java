package org.example.B1_Asynchronous;

public class Fibonacci extends Thread {
    int n;
    int result;
    public Fibonacci(int n) {
        this.n = n;
        System.out.println("Fibonacci of " + n);
    }
    public void run() {
        if ((n == 0) || (n == 1)) {
            result = n;
            return;
        }
        else {
            Fibonacci f1 = new Fibonacci(n - 1);
            Fibonacci f2 = new Fibonacci(n - 2);
            f1.start(); // Dùng start để 2 thread chạy song song
            f2.start();
            try {   // Dùng join để chờ cho 2 thread kết thúc
                f1.join();  // Khi thread f1 kết thúc thì mới tiếp tục
                f2.join();  // Nếu không dùng join th chương trình sẽ lấy kết quả khi 2 thread chưa được tính toán xong, dẫn đến kết quả sai
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = f1.result + f2.result; // Khi 2 thread f1 và f2 kết thúc thì mới tính toán
        }
    }
    public int getResult() {
        return result;
    }
    public static void main(String[] args) {    // Luồng chính
        int n = 5;
        Fibonacci f1 = new Fibonacci(n);    // Tạo luồng con f1 từ luồng chính, chờ cho luồng con f1 chạy xong bằng f1.join()
        f1.start();
        try {
            f1.join();  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fibonacci of n = " + n + ": " + f1.getResult());
    }
}
