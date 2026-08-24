package SOLID.SOLID_Dependency_Inversion;

import java.util.List;

public class NotificationService {
    private final List<MessageSender> senders;

    public NotificationService(List<MessageSender> senders) {
        this.senders = senders;
    }

    public void notifyAllChannels(String message) {
        for (MessageSender sender : senders) {
            sender.sendMessage(message);
        }
    }
}