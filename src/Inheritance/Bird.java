package Inheritance;

public class Bird extends Animal{
    @Override
    public void makeSound() {
        System.out.println("Bird is making a sound");
    }
    @Override
    public void move(){
        System.out.println("Bird is moving");
    }
}
