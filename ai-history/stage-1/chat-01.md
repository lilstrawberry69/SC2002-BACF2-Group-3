Model Gemini 3.1 pro

prompt: what is the purpose of game controller in the uml diagrame?

Response: Based on the UML diagram, the GameController acts as the central orchestrator for the entire game. Its main purposes are:
Managing the Game Loop: It tracks the progression of time (currentTurn, maxTurns) and executes the 6-step turn sequence outlined in the requirements using executeTurn().

Bridging UI and Domain: It connects the user interface (UIBoundary) with the core game logic and data (Restaurant), ensuring that game rules are kept out of the UI.

Handling Game State: It handles the start of the game (startGame()) and evaluates the final victory or defeat conditions once the 18 turns are over (checkEndConditions()).
