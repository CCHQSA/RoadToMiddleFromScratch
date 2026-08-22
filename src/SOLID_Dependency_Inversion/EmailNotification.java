package SOLID_Dependency_Inversion;

public class EmailNotification implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Email Notification: " + message);
    }
}
