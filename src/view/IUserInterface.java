package view;
import model.Restaurant;

public interface IUserInterface {
    void displayTurnStatus(int turn, Restaurant r);
    void promptWaiterAction(Restaurant r, int turn);
    void promptChefAction(Restaurant r, int turn);
    void promptHostAction(Restaurant r, int currentTurn); // STAGE 3
    void promptHappyHour(Restaurant r); // STAGE 3
    void showMessage(String message);
}
