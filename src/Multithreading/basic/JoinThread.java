package Multithreading.basic;

public class JoinThread {
    static void main() throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                System.out.println("Thread 2: " + i);
            }
        });
        System.out.println("Before executing");
        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("Done executing");

    }
}


