package model.staff;

public abstract class Staff {
    protected boolean hasActed;
    protected String name;

    public Staff(String name) {
        this.name = name;
        this.hasActed = false;
    }

    public boolean hasActedThisTurn() {
        return hasActed;
    }

    public void resetAction() {
        this.hasActed = false;
    }

    public void setHasActed() {
        this.hasActed = true;
    }
}
