package model.staff;

import model.Restaurant;
import model.Table;
import model.customer.Customer;
import model.enums.CustomerState;

public class Cashier extends Staff{
    public Cashier(String name) {
        super(name);
    }
    public boolean processPayment(Restaurant restaurant){
        for (Table t : restaurant.getTables()){
            if (t.isOccupied()){
                Customer customer = t.getSeatedCustomer();
                if (customer.getState() == CustomerState.READY_TO_PAY){
                    int bill = (int) Math.round(customer.calculateBill());
                    restaurant.addRevenue(bill);
                    customer.setState(CustomerState.PAID);
                    restaurant.getPaidCustomers().add(customer);
                    t.freeTable();
                    hasActed = true;
                    return true;
                }
            }
        }
        return false;
    }
}


    

