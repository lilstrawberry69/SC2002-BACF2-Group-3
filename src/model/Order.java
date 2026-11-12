package model;

import model.enums.OrderStatus;

public class Order {
    private int prepUnitsLeft;
    private int turnBecameReady;
    private OrderStatus status;
    private final IMenuItem item;
    private double lockedPrice; // NEW for Stage 3

    public Order(IMenuItem item) {
        this.item = item;
        this.prepUnitsLeft = item.getRequiredPrepUnits();
        this.status = OrderStatus.PENDING;
        this.turnBecameReady = -1; // Moved back to the constructor for consistency!
    }

    public void setLockedPrice(double price) { this.lockedPrice = price; }
    public double getLockedPrice() { return lockedPrice; }

    public void advancePrep(int currentTurn) {
        if (prepUnitsLeft > 0) {
            prepUnitsLeft--;
            status = OrderStatus.PREPARING;
            if (prepUnitsLeft == 0) {
                status = OrderStatus.READY;
                turnBecameReady = currentTurn;
            }
        }
    }
    
    public void setServed() {
        this.status = OrderStatus.SERVED;
    }
    
    public int getPrepUnitsLeft() { return prepUnitsLeft; }
    public boolean isReady() { return status == OrderStatus.READY; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public IMenuItem getItem() { return item; }
    public int getTurnBecameReady() { return turnBecameReady; }
}