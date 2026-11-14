package model.staff;
import model.customer.Customer;
import model.Table;
import model.enums.CustomerState;
import model.enums.OrderStatus;

public class Waiter extends Staff {

    public boolean seatCustomer(Customer c, Table t) {
        if (c.getState() == CustomerState.WAITING && t.isFree()) {
            t.seatCustomer(c);
            c.setState(CustomerState.SEATED);
            setActed();
            return true;
        }
        return false;
    }

    public boolean takeOrder(Customer c, java.util.List<model.IMenuItem> menu, boolean isHappyHour) {
        if (c.getOrder() == null) {
            c.placeOrder(menu);
            
            // Calculate locked price: VIP discount first, then Happy Hour
            double basePrice = c.getOrder().getItem().getPrice();
            double discountedPrice = c.calculateBill(basePrice); // applies VIP discount if applicable
            
            if (isHappyHour) {
                discountedPrice = discountedPrice * 0.80; // 20% off
            }
            
            // Lock the final price
            c.getOrder().setLockedPrice(Math.round(discountedPrice * 100.0) / 100.0);
            
            setActed();
            return true;
        }
        return false;
    }

    // Ensures the strict timing rule: an order cannot be served on the exact same turn it finishes prep.
    public boolean serveOrder(Customer c, int currentTurn) {
        if (c.getOrder() != null && c.getOrder().getStatus() == OrderStatus.READY) {
            // Check that the turn it became ready is strictly BEFORE the current turn
            if (c.getOrder().getTurnBecameReady() < currentTurn) {
                c.getOrder().setServed();
                c.setState(CustomerState.SERVED);
                setActed();
                return true;
            }
        }
        return false;
    }
}
    

