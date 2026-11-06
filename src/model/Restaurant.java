package model;

import java.util.ArrayList;
import java.util.List;
import model.customer.Customer;

public class Restaurant {
    private final List<Table> tables;
    private final  List<MenuItem> menu;
    private final List<Customer> waitingQueue;
    private final List<Customer> paidCustomers;
    private final List<Order> activeOrders;
    private int totalRevenue;

    public Restaurant(){
        this.tables = new ArrayList<>();
        tables.add(new Table(1));
        tables.add(new Table(2));
        
        this.menu = new ArrayList<>();
        menu.add(new Dish("Salad", 9.00, 1));
        menu.add(new Dish("Burger", 15.00, 1));
        menu.add(new Dish("Pasta", 18.00, 2));

        this.waitingQueue = new ArrayList<>();
        this.paidCustomers = new ArrayList<>();
        this.activeOrders = new ArrayList<>();
        this.totalRevenue = 0;
    }

    public Table getAvailableTable(){
        for (Table t : tables){
            if (!t.isOccupied())
                return t;
        }
        return null;
    }

    public List<Table> getTables() {
        return tables;
    }
    public List<MenuItem> getMenu() {
        return menu;
    }
    public List<Customer> getWaitingQueue() {
        return waitingQueue;
    }
    public List<Customer> getPaidCustomers() {
        return paidCustomers;
    }
    public List<Order> getActiveOrders() {
        return activeOrders;
    }
    public int getTotalRevenue() {
        return totalRevenue;
    }
    public void addRevenue(int cents){
        this.totalRevenue += cents;
    }
}

