package model.staff;
import model.customer.Customer;
import model.Table;
import model.enums.CustomerState;

public class Cashier extends Staff {
    public double processPayment(Customer c, Table t) {
        if (c.getState() == CustomerState.READY_TO_PAY) {
            c.setState(CustomerState.PAID);
            t.freeTable();
            setActed();
            return c.getOrder().getItem().getPrice();
        }
        return 0.0;
    }
}