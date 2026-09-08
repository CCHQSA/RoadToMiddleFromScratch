package Multithreading.ExecuterService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 1000; i++) {
                service.execute(new TaskOne(i));
            }
        }
    }
}

class TaskOne implements Runnable {
    private final int id;

    TaskOne(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task: " + id + " being executed by: " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
