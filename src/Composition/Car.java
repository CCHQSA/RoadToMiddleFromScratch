package Composition;

public class Car {
    private Engine engine;
    private Wheel[] wheels;
    private Transmission transmission;
    public Car(Engine engine, Wheel[] wheels, Transmission transmission) {
        this.engine = engine;
        this.wheels = wheels;
        this.transmission = transmission;
    }

    public void start(){
        System.out.println("Starting Car");
    }

    public void stop(){
        System.out.println("Stopping Car");
    }
}
