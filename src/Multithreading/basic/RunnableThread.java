package Multithreading.basic;

public class RunnableThread {
    static void main() {
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new  Thread2());
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable: " + i);
            }
        });
        t1.start();
        t2.start();
        t3.start();

    }
}

class Thread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread 1:" + i);
        }
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread 2: " + i);
        }
    }
}
