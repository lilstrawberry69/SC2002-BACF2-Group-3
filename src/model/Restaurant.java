package model;

import model.customer.Customer;
import model.staff.*;
import java.util.*;

public class Restaurant {
    private final List<Table> tables;
    private final Queue<Customer> waitingQueue;
    private final List<Customer> allCustomers;
    private final List<IMenuItem> menu;

    private final Waiter waiter;
    private final Chef chef;
    private final Cashier cashier;

    private double revenue;
    // Added Host staff member
    private model.staff.Host host = new model.staff.Host();
    private boolean happyHourActive = false;
    private boolean happyHourUsed = false;
    private int happyHourTurnsLeft = 0;

    public Restaurant() {
        tables = Arrays.asList(new Table(1), new Table(2));
        waitingQueue = new LinkedList<>();
        allCustomers = new ArrayList<>();

        menu = Arrays.asList(
            new Dish("Salad", 9.00, 1),
            new Dish("Burger", 15.00, 1),
            new Dish("Pasta", 18.00, 2)
        );

        waiter = new Waiter();
        chef = new Chef();
        cashier = new Cashier();
        revenue = 0.0;
    }

    public void addCustomer(Customer c) {
        waitingQueue.add(c);
        allCustomers.add(c);
    }

    public model.staff.Host getHost() { return host; }

    public void activateHappyHour() {
        if (!happyHourUsed) {
            happyHourActive = true;
            happyHourUsed = true;
            happyHourTurnsLeft = 2; // Lasts for this turn and the next
        }
    }

    public boolean isHappyHourActive() { return happyHourActive; }
    public boolean hasHappyHourBeenUsed() { return happyHourUsed; }
    public int getHappyHourTurnsLeft() { return happyHourTurnsLeft; }

    public void tickHappyHour() {
        if (happyHourActive) {
            happyHourTurnsLeft--;
            if (happyHourTurnsLeft <= 0) {
                happyHourActive = false;
            }
        }
    }

    public void resetStaff() {
        getWaiter().resetAction();
        getChef().resetAction();
        getCashier().resetAction();
        getHost().resetAction();
    }

    public List<Table> getTables() { return tables; }
    public Queue<Customer> getWaitingQueue() { return waitingQueue; }
    public List<Customer> getAllCustomers() { return allCustomers; }
    public List<IMenuItem> getMenu() { return menu; }
    public Waiter getWaiter() { return waiter; }
    public Chef getChef() { return chef; }
    public Cashier getCashier() { return cashier; }
    public double getRevenue() { return revenue; }
    public void addRevenue(double amount) { this.revenue += amount; }
}
