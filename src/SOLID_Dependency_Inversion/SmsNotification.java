package SOLID_Dependency_Inversion;

public class SmsNotification implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
    }
}
