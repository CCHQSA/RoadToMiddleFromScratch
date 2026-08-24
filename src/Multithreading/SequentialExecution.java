package Multithreading;

public class SequentialExecution {
    static void main() {
        demo1();
        demo2();
    }

    private static void demo1() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Demo 1");
        }
    }

    private static void demo2() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Demo 2");
        }
    }
}
