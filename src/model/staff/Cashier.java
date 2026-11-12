package model.staff;

import model.Table;
import model.customer.Customer;
import model.enums.CustomerState;

public class Cashier extends Staff {
    public double processPayment(Customer c, Table t) {
        c.setState(CustomerState.PAID);
        // Uses the polymorphic calculateBill method defined in Stage 2
        double paidAmount = c.calculateBill(c.getOrder().getItem().getPrice()); 
        t.freeTable();
        return paidAmount;
    }
}
