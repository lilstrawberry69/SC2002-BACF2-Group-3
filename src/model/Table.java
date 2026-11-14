package model;
import model.customer.Customer;

public class Table {
    private final int id;
    private Customer occupiedBy;

    public Table(int id) {
        this.id = id;
        this.occupiedBy = null;
    }

    public int getId() { return id; }
    public Customer getCustomer() { return occupiedBy; }
    public boolean isFree() { return occupiedBy == null; }

    public void seatCustomer(Customer c) { this.occupiedBy = c; }
    public void freeTable() { this.occupiedBy = null; }
}
