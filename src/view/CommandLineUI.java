package view;

import model.Restaurant;
import model.Table;
import model.customer.Customer;
import model.enums.OrderStatus;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandLineUI implements IUserInterface {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayTurnStatus(int turn, Restaurant r) {
        System.out.println("\n=== Turn " + turn + " / 18 ===");
        System.out.println("Revenue: $" + String.format("%.2f", r.getRevenue()));
        System.out.print("Queue: ");
        r.getWaitingQueue().forEach(c -> System.out.print("[C" + c.getId() + " Sat:" + c.getSatisfaction() + "] "));
        System.out.println();

        for (Table t : r.getTables()) {
            System.out.print("Table " + t.getId() + ": ");
            if (t.isFree()) System.out.println("Empty");
            else {
                Customer c = t.getCustomer();
                String status = c.getState().toString();
                // Overriding status for Ordered and Served display
                if (c.getOrder() != null) {
                    if (c.getOrder().getStatus() == OrderStatus.SERVED) {
                        status = "Served";
                    } else {
                        status = "Ordered";
                    }
                }
                System.out.println("C" + c.getId() + " (" + status + ") Sat: " + c.getSatisfaction());
            }
        }
    }

    @Override
    public void promptWaiterAction(Restaurant r, int currentTurn) {
        while (!r.getWaiter().hasActedThisTurn()) {
            System.out.println("\nWaiter Action (1: Seat, 2: Order, 3: Serve, 4: Wait): ");
            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    if (!r.getWaitingQueue().isEmpty() && r.getTables().stream().anyMatch(Table::isFree)) {
                        Customer c = r.getWaitingQueue().poll();
                        Table t = r.getTables().stream().filter(Table::isFree).findFirst().get();
                        r.getWaiter().seatCustomer(c, t);
                        System.out.println("Seated C" + c.getId() + " at Table " + t.getId());
                    } else System.out.println("Invalid: No customers or no free tables.");
                } else if (choice.equals("2")) {
                    List<Customer> readyToOrder = r.getTables().stream()
                        .filter(t -> !t.isFree() && t.getCustomer().getOrder() == null)
                        .map(Table::getCustomer).collect(Collectors.toList());

                    if (!readyToOrder.isEmpty()) {
                        System.out.print("Select customer to take order from (Available: ");
                        readyToOrder.forEach(c -> System.out.print("C" + c.getId() + " "));
                        System.out.println("): ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Customer target = readyToOrder.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

                        if (target != null && r.getWaiter().takeOrder(target, r.getMenu())) {
                            System.out.println("Took order for C" + target.getId() + ": " + target.getOrder().getItem().getName());
                        } else {
                            System.out.println("Invalid customer selection.");
                        }
                    } else System.out.println("Invalid: No seated customers ready to order.");
                } else if (choice.equals("3")) {
                    List<Customer> readyToServe = r.getTables().stream()
                        .filter(t -> !t.isFree() && t.getCustomer().getOrder() != null && 
                                     t.getCustomer().getOrder().getStatus() == OrderStatus.READY &&
                                     t.getCustomer().getOrder().getTurnBecameReady() < currentTurn)
                        .map(Table::getCustomer).collect(Collectors.toList());

                    if (!readyToServe.isEmpty()) {
                        System.out.print("Select customer to serve (Available: ");
                        readyToServe.forEach(c -> System.out.print("C" + c.getId() + " "));
                        System.out.println("): ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Customer target = readyToServe.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

                        if (target != null && r.getWaiter().serveOrder(target, currentTurn)) {
                            System.out.println("Served C" + target.getId());
                        } else {
                            System.out.println("Invalid customer selection.");
                        }
                    } else System.out.println("Invalid: No orders ready to serve from previous turns.");
                } else if (choice.equals("4")) {
                    r.getWaiter().setActed();
                    System.out.println("Waiter waits.");
                } else {
                    System.out.println("Invalid input.");
                }
            } catch (Exception e) {
                System.out.println("Error processing Waiter action. Please ensure you type a valid number.");
            }
        }
    }

    @Override
    public void promptChefAction(Restaurant r, int currentTurn) {
        while (!r.getChef().hasActedThisTurn()) {
            System.out.println("Chef Action (1: Cook, 2: Wait): ");
            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    List<Customer> readyToCook = r.getTables().stream()
                        .filter(t -> !t.isFree() && t.getCustomer().getOrder() != null && 
                                     t.getCustomer().getOrder().getStatus() == OrderStatus.PREPARING)
                        .map(Table::getCustomer).collect(Collectors.toList());

                    if (!readyToCook.isEmpty()) {
                        System.out.print("Select customer order to cook (Available: ");
                        readyToCook.forEach(c -> System.out.print("C" + c.getId() + " (" + c.getOrder().getItem().getName() + ") "));
                        System.out.println("): ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Customer target = readyToCook.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

                        if (target != null && r.getChef().advancePreparation(target.getOrder(), currentTurn)) {
                            System.out.println("Chef cooked for C" + target.getId() + ". Prep left: " + target.getOrder().getPrepUnitsLeft());
                        } else {
                            System.out.println("Invalid customer selection.");
                        }
                    } else System.out.println("Invalid: No orders to cook.");
                } else if (choice.equals("2")) {
                    r.getChef().setActed();
                    System.out.println("Chef waits.");
                } else {
                    System.out.println("Invalid input.");
                }
            } catch (Exception e) {
                 System.out.println("Error processing Chef action. Please ensure you type a valid number.");
            }
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
