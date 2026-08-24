package Multithreading.threadSynchronisation;

public class LockWithCustomObjects {
    private static int counter1  = 0;
    private static int counter2 = 0;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main() {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increament1();
            }
        });


        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increament2();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(counter1 + " " + counter2);

    }

    private static void increament1() {
        synchronized (lock1) {
            counter1++;
        }

    }

    private static void increament2() {
        synchronized (lock2){
            counter2++;
        }
    }


}
