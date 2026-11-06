package model;

import model.customer.Customer;
import model.enums.OrderStatus;

public class Order {
    private final Customer customer;
    private final MenuItem item;
    private int remainingPrepUnits;
    private OrderStatus status;

    public Order(Customer customer, MenuItem item) {
        this.customer = customer;
        this.item = item;
        this.remainingPrepUnits = item.getRequiredPrepunits();
        this.status = OrderStatus.TAKEN;
    }
    public void advancePrep(){
        if (remainingPrepUnits > 0){
            remainingPrepUnits--;
            if (remainingPrepUnits == 0){
                this.status = OrderStatus.READY;
            } else{
                this.status = OrderStatus.IN_PREPARATION;
            }
        }
    }
    public Customer getCustomer(){
        return customer;
    }
    public MenuItem getItem(){
        return item;
    }
    public OrderStatus getStatus(){
        return status;
    }
    public void setStatus(OrderStatus status){
        this.status = status;
    }
    public int getRemainingPrepUnits(){
        return remainingPrepUnits;
    }
}


