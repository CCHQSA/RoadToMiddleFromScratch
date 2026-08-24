package Multithreading;

public class WaitAndNotify {

    private static final Object LOCK = new Object();

    static void main() {
        Thread one = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two = new Thread(() -> {
            try {
                two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        one.start();
        two.start();
    }

    private static void one() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Method 1");
            LOCK.wait();
            System.out.println("Back");
        }
    }

    private static void two() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Method 2");
            LOCK.notify();
            System.out.println("Notify");
        }
    }
}
