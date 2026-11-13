Summary of AI Assistance

In Stage 1, the AI was used to establish the foundational architecture of the Restaurant Rush game, generate the initial UML class diagram, and debug core game loop issues.

Prompts & AI Contributions

Architecture & UML Generation:

Prompt: Asked the AI to generate a UML class diagram based on the assignment brief, highlighting core entities (Waiter, Chef, Cashier, Customer, Table, Order, Dish).

Contribution: The AI proposed a Model-View-Controller (MVC) structure and recommended using an IUserInterface and IMenuItem to ensure scalability for future stages.

Code Generation:

Prompt: Requested the boilerplate Java code and package structure.

Contribution: The AI generated the initial .java files with constructors and basic attributes.

Debugging the Payment Loop:

Prompt: Fed an error log showing a NullPointerException when the Cashier processed payments.

Contribution: The AI identified that the table was being freed (setting the customer to null) before the UI could read the customer's ID for the success message. It provided a fix to cache the customer reference before freeing the table.

Refining Waiter Logic:

Prompt: Pointed out a bug where the Waiter automatically took orders based on an arbitrary list rather than allowing the manager to choose.

Contribution: The AI updated the CommandLineUI to properly list available seated customers and accept integer inputs for targeted Waiter actions.
