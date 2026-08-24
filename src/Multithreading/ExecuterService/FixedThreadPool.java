package Multithreading.ExecuterService;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    static void main() {
        try(ExecutorService service = Executors.newFixedThreadPool(2)){
            for (int i = 0; i < 7; i++) {
                service.execute(new Work(i));
            }
        }
    }
}

class Work implements Runnable {
    private final int id;
    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task " + id +
                " is running by thread"
                +  Thread.currentThread().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
