package model.staff;
import model.Order;
import model.enums.OrderStatus;

public class Chef extends Staff {
    public boolean advancePreparation(Order o, int currentTurn) {
        if (o != null && o.getStatus() == OrderStatus.PREPARING) {
            o.advancePrep(currentTurn);
            setActed();
            return true;
        }
        return false;
    }
}
