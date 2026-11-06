package model.staff;

import model.MenuItem;
import model.Order;
import model.Restaurant;
import model.Table;
import model.customer.Customer;
import model.enums.CustomerState;
import model.enums.OrderStatus;

public class Waiter extends Staff{
    public Waiter(String name){
        super(name);
    }
    public boolean seatCustomer(Restaurant restaurant){
        if (hasActed) 
            return false;
        if (restaurant.getWaitingQueue().isEmpty())
            return false;
        Table availableTable = restaurant.getAvailableTable();
        if (availableTable == null)
            return false;
        Customer customer = restaurant.getWaitingQueue().remove(0);
        availableTable.occupy(customer);
        customer.setState(CustomerState.SEATED);
        hasActed = true;
        return true;
    }
    public boolean takeOrder(Restaurant restaurant, Customer customer, MenuItem item){
        if (hasActed)
            return false;
        if (customer.getState() != CustomerState.SEATED)
            return false;
        customer.getOrder().add(item);
        Order order = new Order(customer, item);
        restaurant.getActiveOrders().add(order);
        hasActed = true;
        return true;
    }
    public boolean serveOrder(Restaurant restaurant, Order order){
        if (hasActed)
            return false;
        if (order.getStatus() != OrderStatus.READY)
            return false;
        order.setStatus(OrderStatus.SERVED);
        order.getCustomer().setState(CustomerState.SERVED);
        hasActed = true;
        return true;
    }


}
    

