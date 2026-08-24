package Multithreading.basic;

public class DaemonUserThread {
    static void main() {
        Thread backGrounfThread = new Thread(new DaemonHelper());
        Thread userThread = new Thread(new UserThreadHelper());
        backGrounfThread.setDaemon(true);
        backGrounfThread.start();
        userThread.start();


    }
}

class DaemonHelper implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (count < 500){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Daemon helper running ...");
        }
    }
}

class UserThreadHelper implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User thread done with execution");
    }
}
