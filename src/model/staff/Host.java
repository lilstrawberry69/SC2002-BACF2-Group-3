package model.staff;

import model.Table;
import model.customer.Customer;
import model.enums.CustomerState;

public class Host extends Staff {
    public boolean seatCustomer(Customer c, Table t) {
        if (t.isFree() && c.getState() == CustomerState.WAITING) {
            t.seatCustomer(c); 
            c.setState(CustomerState.SEATED);
            setActed();
            return true;
        }
        return false;
    }
}