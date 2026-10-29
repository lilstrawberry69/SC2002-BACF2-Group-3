package model;

public class Dish implements IMenuItem {
    private String name;
    private double price;
    private int requiredPrepunits;

    public Dish(String name, double price, int requiredPrepunits) {
        this.name = name;
        this.price = price;
        this.requiredPrepunits = requiredPrepunits;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getRequiredPrepunits() {
        return requiredPrepunits;
    }
    
}
