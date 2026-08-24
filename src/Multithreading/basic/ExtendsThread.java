package Multithreading.basic;

public class ExtendsThread {
    static void main() {
        Thread t = new Thread3();
        Thread t2 =  new Thread4();
        t.start();
        t2.start();
    }
}

class Thread3 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread 3: " + i);
        }
    }
}


class Thread4 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread 4: " + i);
        }
    }
}

