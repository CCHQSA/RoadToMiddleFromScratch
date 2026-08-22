package Composition;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine("V8");
        Wheel[] wheels = {
                new Wheel(1.0, "Summer"),
                new Wheel(1.0, "Summer"),
                new Wheel(1.0, "Summer"),
                new Wheel(1.0, "Summer")
        };
        Transmission transmission = new Transmission("Manual");
        Car car = new Car(engine, wheels, transmission);

    }
}
