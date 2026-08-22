package Inheritance;

public class Cat extends Animal{
    @Override
    public void makeSound() {
        System.out.println("Cat is making a sound");
    }
    @Override
    public void move(){
        System.out.println("Cat is moving");
    }
}
