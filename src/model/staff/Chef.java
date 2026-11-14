package model.staff;

import model.Order;
import model.enums.OrderStatus;

public class Chef extends Staff {
    public boolean advancePreparation(Order o, int currentTurn) {
        if (o.getStatus() == OrderStatus.PENDING) {
            o.setStatus(OrderStatus.PREPARING);
        }
        
        if (o.getStatus() == OrderStatus.PREPARING && o.getPrepUnitsLeft() > 0) {
            o.advancePrep(currentTurn);
            setActed();
            return true;
        }
        return false;
    }
}