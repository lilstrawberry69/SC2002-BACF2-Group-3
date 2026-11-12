package model.customer;

import model.IMenuItem;
import model.Order;
import model.enums.CustomerState;
import java.util.List;

public class CriticCustomer extends Customer {
    public CriticCustomer(int id) {
        super(id);
    }

    @Override
    public void decreaseSatisfaction() {
        satisfaction = Math.max(0, satisfaction - 12);
        boolean isServed = (getOrder() != null && getOrder().getStatus() == model.enums.OrderStatus.SERVED);
        
        if (satisfaction == 0 && !isServed && getState() != CustomerState.READY_TO_PAY) {
            setState(CustomerState.LEFT);
        }
    }

    @Override
    public double calculateBill(double baseAmount) {
        return baseAmount; 
    }

    @Override
    public void placeOrder(List<IMenuItem> menu) {
        IMenuItem mostExpensive = menu.get(0);
        for (IMenuItem item : menu) {
            if (item.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = item;
            }
        }
        this.setOrder(new Order(mostExpensive));
    }
}
