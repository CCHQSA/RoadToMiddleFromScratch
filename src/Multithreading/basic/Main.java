package Multithreading.basic;

public class Main {
    Thread thread1 = new Thread(() -> {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
        }
    });

    Thread thread2 = new Thread(() -> {
        for (int i = 101; i <= 200; i++) {
            System.out.println(i);
        }
    });

    static void main() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 101; i <= 200; i++) {
                System.out.println(i);
            }
        });

        thread1.start();
        thread2.start();
    }
}
