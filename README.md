# Tic Tac Toe Game

A Java desktop application for playing Tic-tac-toe (Noughts and Crosses) with support for:
- Player vs Player mode
- Player vs Computer mode

## Prerequisites

- Java 11 or higher
- Maven

## Building the Project

To build the project, run:

```bash
mvn clean package
```

## Running the Game

After building, you can run the game using:

```bash
java -jar target/tictactoe-1.0-SNAPSHOT.jar
```

## How to Play

1. Select game mode:
   - Player vs Player: Play against another human player
   - Player vs Computer: Play against the computer

2. The game board is a 3x3 grid
   - Player X goes first
   - Click on any empty cell to make your move
   - The game will indicate whose turn it is

3. Win conditions:
   - Get three of your symbols (X or O) in a row horizontally, vertically, or diagonally
   - If no player achieves this and the board is full, the game is a draw

4. Start a new game:
   - Select the game mode again to start a new game at any time
