package model.customer;

import model.IMenuItem;
import model.Order;
import model.enums.CustomerState;
import java.util.List;

public abstract class Customer {
    private final int id;
    protected int satisfaction;
    private CustomerState state;
    private Order order;
    private boolean hasEaten = false; 

    public Customer(int id) {
        this.id = id;
        this.satisfaction = 100;
        this.state = CustomerState.WAITING;
    }

    public int getId() { return id; }
    public int getSatisfaction() { return satisfaction; }
    public CustomerState getState() { return state; }
    public Order getOrder() { return order; }

    public void setState(CustomerState state) { this.state = state; }
    public void setOrder(Order order) { this.order = order; }

    public abstract void decreaseSatisfaction();
    public abstract double calculateBill(double baseAmount);

    public void placeOrder(List<IMenuItem> menu) {
        IMenuItem chosenItem = menu.get((id - 1) % menu.size());
        this.order = new Order(chosenItem);
    }

    public void tickEating() {
        // Removed the strict 'state == SEATED' check to ensure it triggers properly
        if (order != null && order.getStatus() == model.enums.OrderStatus.SERVED && state != CustomerState.READY_TO_PAY && state != CustomerState.PAID) {
            if (!hasEaten) {
                hasEaten = true; 
            } else {
                setState(CustomerState.READY_TO_PAY); 
            }
        }
    }
}
