package SOLID_Dependency_Inversion;

import SOLID.Open_Closed_Principle.Discounts.Order;

public interface MessageSender {
    public void sendMessage(String message);
}
