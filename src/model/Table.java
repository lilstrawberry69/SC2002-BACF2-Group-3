package model;

import model.customer.Customer;

public class Table {
    private final int tableNum;
    private boolean isOccupied;
    private Customer seatedCustomer;

    public Table(int Num) {
        this.tableNum = Num;
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

    public int getTableNum() { return tableNum; }
    public Customer getSeatedCustomer() { return seatedCustomer; }
}
