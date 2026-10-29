package model;

import model.customer.Customer;

public class Table {
    private int tableId;
    private boolean isOccupied;
    private Customer seatedCustomer;

    public Table(int id) {
        this.tableId = id;
        this.isOccupied = false;
        this.seatedCustomer = null;
    }

    public void occupy(Customer c) {
        this.isOccupied = true;
        this.seatedCustomer = c;
    }

    public void freeTable() {
        this.isOccupied = false;
        this.seatedCustomer = null;
    }

    public boolean isOccupied() { return isOccupied; }
    public Customer getCustomer() { return seatedCustomer; }
}
