package model.customer;

import model.IMenuItem;
import model.Order;
import model.enums.CustomerState;
import java.util.List;

public class Customer {
    private final int id;
    private int satisfaction;
    private CustomerState state;
    private Order order;
    private int turnsEating;

    public Customer(int id) {
        this.id = id;
        this.satisfaction = 100;
        this.state = CustomerState.WAITING;
        this.turnsEating = 0;
    }

    public int getId() { return id; }
    public int getSatisfaction() { return satisfaction; }
    public CustomerState getState() { return state; }
    public Order getOrder() { return order; }

    public void setState(CustomerState state) { this.state = state; }

    public void reduceSatisfaction(int amount) {
        this.satisfaction = Math.max(0, this.satisfaction - amount);
        if (this.satisfaction == 0 && (state == CustomerState.WAITING || state == CustomerState.SEATED)) {
            this.state = CustomerState.LEFT;
        }
    }

    public void placeOrder(List<IMenuItem> menu) {
        // Deterministic choice based on ID to meet assignment requirements without random flakiness
        IMenuItem chosenItem = menu.get((id - 1) % menu.size());
        this.order = new Order(chosenItem);
    }

    // Handles the transition from eating to being ready to pay after 1 full turn
    public void tickEating() {
        if (this.state == CustomerState.SERVED) {
            turnsEating++;
            if (turnsEating >= 1) {
                this.state = CustomerState.READY_TO_PAY;
            }
        }
    }
}
