package model.customer;

import model.enums.CustomerState;

public class VIPCustomer extends Customer {
    public VIPCustomer(int id) {
        super(id);
    }

    @Override
    public void decreaseSatisfaction() {
        satisfaction = Math.max(0, satisfaction - 5);
        boolean isServed = (getOrder() != null && getOrder().getStatus() == model.enums.OrderStatus.SERVED);
        
        if (satisfaction == 0 && !isServed && getState() != CustomerState.READY_TO_PAY) {
            setState(CustomerState.LEFT);
        }
    }

    @Override
    public double calculateBill(double baseAmount) {
        return baseAmount * 0.90; 
    }
}
