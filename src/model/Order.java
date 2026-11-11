package model;
import model.enums.OrderStatus;

public class Order {
    private final IMenuItem item;
    private int prepUnitsLeft;
    private OrderStatus status;
    private int turnBecameReady;

    public Order(IMenuItem item) {
        this.item = item;
        this.prepUnitsLeft = item.getRequiredPrepUnits();
        this.status = OrderStatus.PREPARING;
        this.turnBecameReady = -1;
    }

    public IMenuItem getItem() { return item; }
    public int getPrepUnitsLeft() { return prepUnitsLeft; }
    public OrderStatus getStatus() { return status; }
    public int getTurnBecameReady() { return turnBecameReady; }

    // Advances the preparation of the order by 1 unit.
    // Records the exact turn it finishes so we can enforce the "serve next turn at earliest" rule.
    public void advancePrep(int currentTurn) {
        if (prepUnitsLeft > 0) {
            prepUnitsLeft--;
            if (prepUnitsLeft == 0) {
                status = OrderStatus.READY;
                turnBecameReady = currentTurn;
            }
        }
    }

    public void setServed() {
        this.status = OrderStatus.SERVED;
    }
}


