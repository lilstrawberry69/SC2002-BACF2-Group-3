package controller;

import model.*;
import model.customer.Customer;
import model.enums.CustomerState;
import model.enums.OrderStatus;
import view.*;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    private final Restaurant restaurant;
    private final IUserInterface ui;
    private int currentTurn;
    private int nextCustomerId = 1;
    private final int MAX_TURNS = 18;

    public GameController() {
        this.restaurant = new Restaurant();
        this.ui = new CommandLineUI();
        this.currentTurn = 1;
    }

    public void startGame() {
        while (currentTurn <= MAX_TURNS) {
            executeTurn();
            currentTurn++;
        }
        checkEndConditions();
    }

    private void executeTurn() {
        restaurant.resetStaff();

        // 1. Arrivals
        if (currentTurn == 1 || currentTurn == 4 || currentTurn == 7 || 
            currentTurn == 10 || currentTurn == 13 || currentTurn == 16) {
            restaurant.addCustomer(new Customer(nextCustomerId++));
        }

        ui.displayTurnStatus(currentTurn, restaurant);

        // 2 & 3. Manager Choices (Waiter acts before Chef)
        ui.promptWaiterAction(restaurant, currentTurn);
        ui.promptChefAction(restaurant, currentTurn);

        // 4. Eating and payment
        // We process payments FIRST so customers who become READY_TO_PAY this turn must wait until next turn
        List<Table> readyToPayTables = restaurant.getTables().stream()
            .filter(t -> !t.isFree() && t.getCustomer().getState() == CustomerState.READY_TO_PAY)
            .collect(Collectors.toList());

        if (!readyToPayTables.isEmpty()) {
            Table t = readyToPayTables.get(0);
            Customer payingCustomer = t.getCustomer(); 
            double paid = restaurant.getCashier().processPayment(payingCustomer, t);
            restaurant.addRevenue(paid);
            ui.showMessage("Cashier collected $" + String.format("%.2f", paid) + " from Customer " + payingCustomer.getId());
        }

        // THEN tick eating for everyone else (so newly finished eaters aren't charged instantly)
        for (Customer c : restaurant.getAllCustomers()) {
            c.tickEating();
        }

        // 5. Waiting and abandonment
        for (Customer c : restaurant.getAllCustomers()) {
            if (c.getState() == CustomerState.WAITING || c.getState() == CustomerState.SEATED) {
                if (c.getOrder() == null || c.getOrder().getStatus() != OrderStatus.SERVED) {
                    c.reduceSatisfaction(8);
                    if (c.getState() == CustomerState.LEFT) {
                        ui.showMessage("Customer " + c.getId() + " abandoned the restaurant!");
                        restaurant.getWaitingQueue().remove(c);
                        for (Table t : restaurant.getTables()) {
                            if (t.getCustomer() == c) t.freeTable();
                        }
                    }
                }
            }
        }
    }

    private void checkEndConditions() {
        long paidCount = restaurant.getAllCustomers().stream()
                .filter(c -> c.getState() == CustomerState.PAID).count();

        double avgSat = restaurant.getAllCustomers().stream()
                .filter(c -> c.getState() == CustomerState.PAID)
                .mapToInt(Customer::getSatisfaction)
                .average().orElse(0.0);

        ui.showMessage("\n--- END OF GAME ---");
        ui.showMessage("Paid Customers: " + paidCount + " / 4");
        ui.showMessage("Revenue: $" + String.format("%.2f", restaurant.getRevenue()) + " / $45.00");
        ui.showMessage("Avg Satisfaction: " + String.format("%.1f", avgSat) + " / 60.0");

        if (paidCount >= 4 && restaurant.getRevenue() >= 45.0 && avgSat >= 60.0) {
            ui.showMessage("RESULT: VICTORY!");
        } else {
            ui.showMessage("RESULT: DEFEAT.");
        }
    }

    public static void main(String[] args) {
        new GameController().startGame();
    }
}
