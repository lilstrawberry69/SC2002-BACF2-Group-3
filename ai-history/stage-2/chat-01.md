Summary of AI Assistance

In Stage 2, the AI assisted in refactoring the existing codebase to support polymorphism, updating the UML diagram, and fixing a critical state machine bug related to the eating phase.

Prompts & AI Contributions

UML Updates for Inheritance:

Prompt: Asked the AI to update the Stage 1 UML to include VIPCustomer and CriticCustomer.

Contribution: The AI converted Customer to an abstract class and added the polymorphic methods decreaseSatisfaction() and calculateBill(), ensuring no instanceof type checks were needed in the GameController.

Implementing Customer Behaviors:

Prompt: Requested the Java code for the new Stage 2 customer classes.

Contribution: The AI generated RegularCustomer, VIPCustomer, and CriticCustomer. It also specifically coded the Critic's special behavior to always order the most expensive item on the menu by overriding placeOrder().

Debugging the Eating State:

Prompt: Explained that customers were getting stuck at the table with the [Served] status and never paying.

Contribution: The AI diagnosed that the tickEating() method was failing to advance the customer to READY_TO_PAY. It provided a fix by reordering the Cashier and Eating steps in the game loop and replacing the eating counter with a hasEaten boolean.
