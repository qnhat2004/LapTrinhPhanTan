package org.example.B1_Asynchronous;

public class Fibonacci extends Thread {
    int n;
    int result;
    public Fibonacci(int n) {
        this.n = n;
    }
    public void run() {
        if ((n == 0) || (n == 1)) {
            result = n;
            return;
        }
        else {
            Fibonacci f1 = new Fibonacci(n - 1);
            Fibonacci f2 = new Fibonacci(n - 2);
            f1.start();
            f2.start();
            try {
                f1.join();
                f2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = f1.result + f2.result;
        }
    }
    public int getResult() {
        return result;
    }
    public static void main(String[] args) {
        int n = 5;
        Fibonacci f1 = new Fibonacci(n);
        f1.start();
        try {
            f1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fibonacci of n = " + n + ": " + f1.getResult());
    }
}
