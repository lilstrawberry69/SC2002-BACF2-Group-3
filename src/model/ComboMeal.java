package model;

public class ComboMeal implements IMenuItem {
    private final IMenuItem item1;
    private final IMenuItem item2;

    public ComboMeal(IMenuItem item1, IMenuItem item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public String getName() {
        return "Combo (" + item1.getName() + " + " + item2.getName() + ")";
    }

    @Override
    public double getPrice() {
        // 90% of combined price, rounded to nearest cent
        double combined = item1.getPrice() + item2.getPrice();
        return Math.round((combined * 0.90) * 100.0) / 100.0;
    }

    @Override
    public int getRequiredPrepUnits() {
        return item1.getRequiredPrepUnits() + item2.getRequiredPrepUnits();
    }
}