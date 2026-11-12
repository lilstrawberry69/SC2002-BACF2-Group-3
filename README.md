# SC2002 Restaurant Rush - Stage 1

* **Repo URL: https://github.com/lilstrawberry69/SC2002-BACF2-Group-3.git**

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

## Stage 3 Description (New Role and Menu Policy)
Stage 3 introduces new staff, promotional mechanics, and complex menu items:
* **Host Role:** A new staff member who can seat queued customers at free tables, freeing up the Waiter's actions

* **Combo Meals (Composite Pattern):**  Customers whose ID is a multiple of 3 (e.g., C6) deterministically order a Combo Meal. This combines two menu items, sums their prep units, and applies a 10% discount to their combined base price.

* **Happy Hour:** The manager can activate Happy Hour once per game. It lasts for two turns, offering a 20% discount on orders placed during this time (stacking with VIP discounts) and recovering 15 satisfaction points for waiting/seated customers. Prices are firmly locked into the Order when taken.

## Stage Transition Logs

### Stage 1 -> Stage 2 Transition

* **New Files Added:**
  * `RegularCustomer.java`, `VIPCustomer.java`, `CriticCustomer.java` (Added to implement specific customer rules).

* **Existing Files Modified:**
  * `Customer.java`: Converted to an `abstract` class. Introduced abstract methods `decreaseSatisfaction()` and `calculateBill()` to establish a contract for subclasses.
  * `GameController.java`: Updated Step 1 arrival logic to spawn a VIP on Turn 4 and a Critic on Turn 7. Modified Step 5 waiting logic to call `c.decreaseSatisfaction()` polymorphically.
  * `Cashier.java`: Updated `processPayment()` to call the polymorphic `c.calculateBill()` for accurate billing.
  * `CommandLineUI.java`: Updated display methods to dynamically show customer subclass types (e.g., VIP, Critics) next to IDs.

  ### Stage 2 -> Stage 3 Transition
  * **New Files Added:**
   * `Host.java` ( New staff role to manage seating.), `ComboMeal.java`(Implements IMenuItem as a composite of two dishes.).

  * **Existing Files Modified:**
  * `Restraunt.java`:  Added Happy Hour state tracking and the Host instance.
  * `Order.java`: Added `lockedPrice` to freeze Happy Hour and VIP discounts at the time of ordering.
  * `Customer.java`: Added Happy Hour satisfaction recovery and Combo Meal modulo `(id % 3 == 0)` logic.
  * `Waiter.java`: Updated to calculate and lock the final price during order taking.
  * `Caisher.java`: Updated to retrieve the `lockedPrice` instead of recalculating the bill.
  * `Chef.java`: Adjusted order status checks to handle `PENDING` orders robustly.
  * `GameController.java`: Intertwined Happy Hour prompts, satisfaction recovery, and Host actions into the turn loop.
  * `CommandLineUI.java`: Added visual cues for Happy Hour and prompts for Host interactions.

## Build and Run Instructions

Run `GameController.java` under the controller subfolder.

* **Compile:** `javac src/model/**/*.java src/model/*.java src/view/*.java src/controller/*.java`

* **Run:** `java -cp src controller.GameController`
