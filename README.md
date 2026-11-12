# SC2002 Restaurant Rush - Stage 1

## Stage 1 Description

Stage 1 delivers a fully playable, command-line turn-based restaurant management game. It includes:
* **Basic Restaurant Operation:** Implementation of the core game loop (18 turns maximum).
* **Core Entities:** `Regular` customers, three base menu items (Salad, Burger, Pasta).
* **Staff Roles:** `Waiter`, `Chef`, and an automated `Cashier`.
* **Game Flow:** Full implementation of the 6-step turn sequence, tracking customer satisfaction, handling abandonment, processing payments, and evaluating victory/defeat conditions.


## Stage 2 Description (Customer Behaviour Extension)

Stage 2 builds upon the core loop by introducing diverse customer behaviors through polymorphism, strictly avoiding type-checking in the game controller:

* **VIP Customer:** Arrives on Turn 4. Loses only 5 satisfaction points per waiting turn and automatically receives a 10% discount on their final bill.

* **Critic Customer:** Arrives on Turn 7. Loses 12 satisfaction points per waiting turn. Demonstrates special behavior by always ordering the most expensive item on the menu, overriding the standard deterministic ordering rule.

* **Polymorphic Dispatch:** Customer-specific rules (satisfaction decay, billing, ordering) are handled internally by the respective `Customer` subclasses (`RegularCustomer`, `VIPCustomer`, `CriticCustomer`), ensuring the `GameController` remains clean.

## Build and Run Instructions

Run `GameController.java` under the controller subfolder.

* **Compile:** `javac src/model/**/*.java src/model/*.java src/view/*.java src/controller/*.java`

* **Run:** `java -cp src controller.GameController`

## Stage Transition Logs

### Stage 1 -> Stage 2 Transition

* **New Files Added:**
  * `RegularCustomer.java`, `VIPCustomer.java`, `CriticCustomer.java` (Added to implement specific customer rules).

* **Existing Files Modified:**
  * `Customer.java`: Converted to an `abstract` class. Introduced abstract methods `decreaseSatisfaction()` and `calculateBill()` to establish a contract for subclasses.
  * `GameController.java`: Updated Step 1 arrival logic to spawn a VIP on Turn 4 and a Critic on Turn 7. Modified Step 5 waiting logic to call `c.decreaseSatisfaction()` polymorphically.
  * `Cashier.java`: Updated `processPayment()` to call the polymorphic `c.calculateBill()` for accurate billing.
  * `CommandLineUI.java`: Updated display methods to dynamically show customer subclass types (e.g., VIP, Critics) next to IDs.
