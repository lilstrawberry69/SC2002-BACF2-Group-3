package model.customer;

import model.MenuItem;
import model.enums.CustomerState;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    protected  String id;
    protected int satisfaction;
    protected CustomerState state;
    protected List<MenuItem> order;

    public Customer(String id) {
        this.id = id;
        this.satisfaction = 100;
        this.state = CustomerState.WAITING;
        this.order = new ArrayList<>();
    }
    public void applySatisfactionChange(){
        if (state == CustomerState.WAITING || state == CustomerState.SEATED){
            this.satisfaction = Math.max(0, this.satisfaction - 8);
        }
            
    }
    public double calculateBill(){
        return order.stream().mapToDouble(MenuItem::getPrice).sum();
    }
    public String getId(){
        return id;
    }
    public int getSatisfaction(){
        return satisfaction;
    }
    public CustomerState getState(){
        return state;
    }
    public void setState(CustomerState state){
        this.state = state;
    }
    public List<MenuItem> getOrder(){
        return order;
    }
}

