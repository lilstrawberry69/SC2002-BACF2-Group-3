package model.staff;

public abstract class Staff {
    protected boolean hasActed;

    public Staff() {
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
