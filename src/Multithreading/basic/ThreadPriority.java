package Multithreading.basic;

public class ThreadPriority {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName() + " says hi!");

        Thread one = new Thread(()->{
            System.out.println("Thread one");
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();
    }
}
