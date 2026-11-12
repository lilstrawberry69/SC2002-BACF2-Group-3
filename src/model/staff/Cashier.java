package model.staff;

import model.Table;
import model.customer.Customer;
import model.enums.CustomerState;

public class Cashier extends Staff {
    // Update to use the locked price
    public double processPayment(Customer c, Table t) {
        c.setState(CustomerState.PAID);
        t.freeTable();
        setActed();
        // Return the locked price from the order instead of calculating it here
        return c.getOrder().getLockedPrice();
    }

    
}
