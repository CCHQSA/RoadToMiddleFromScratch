package Inheritance;

public class Dog extends Animal{
    @Override
    public void makeSound() {
        System.out.println("Dog is making a sound");
    }
    @Override
    public void move(){
        System.out.println("Dog is moving");
    }
}
