package view;
import model.Restaurant;

public interface IUserInterface {
    void displayTurnStatus(int turn, Restaurant r);
    void promptWaiterAction(Restaurant r, int turn);
    void promptChefAction(Restaurant r, int turn);
    void showMessage(String message);
}
