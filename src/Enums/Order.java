package Enums;

public class Order {
    private OrderStatus status;

    public Order(OrderStatus status) {
        this.status = OrderStatus.CREATED;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void changeStatus(OrderStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException("Invalid transition: from " + this.status + " to " + newStatus);
        }
        this.status = newStatus;
    }


}
