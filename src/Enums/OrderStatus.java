package Enums;

public enum OrderStatus {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus nextStatus) {
        return switch (this) {
            case CREATED -> nextStatus == PAID || nextStatus == CANCELLED;
            case PAID -> nextStatus == SHIPPED || nextStatus == CANCELLED;
            case SHIPPED -> nextStatus == DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
    }
}
