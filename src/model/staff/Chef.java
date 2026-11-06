package model.staff;

import model.Order;

public class Chef extends Staff{
    public Chef(String name) {
        super(name);
    }   
    public boolean advanceOrderPrep(Order order){
        if (hasActed)
            return false;
        order.advancePrep();
        hasActed = true;
        return true;
    }
}
    

