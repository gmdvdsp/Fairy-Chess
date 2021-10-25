package ui;

import model.Players;
import model.Square;
import persistence.JSonReader;
import persistence.JSonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Character.getNumericValue;

// Represents a chess console facing UI.
public class ChessApp {
    private static final String JSON_STORE = "./data/game.json";
    private JSonWriter jsonWriter;
    private JSonReader jsonReader;
    Players players;
    Integer proposedX;
    Integer proposedY;
    Square proposedFrom;
    Square proposedTo;
    Scanner input;
    String command;

    private String separator = ",";

    // Makes a new ChessApp game with players and prepares to read inputs.
    public ChessApp() {
        players = new Players();
        input = new Scanner(System.in);
        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);
        runChessApp();
    }

    //  EFFECTS: Asks the user until they type "y" to start a new game, and when they do, initialize the default board
    //  and process white's first move.
    private void runChessApp() {
        System.out.println("Start a new game with default board, or load board? (y/l)");
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("y")) {
            initDefault();
            processProposedFrom();
        } else if (command.equals("l")) {
            loadGame();
            processProposedFrom();
        } else {
            runChessApp();
        }
    }

    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(players.getGame());
            jsonWriter.close();
            System.out.println("Saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadGame() {
        try {
            players.setGame(jsonReader.read());
            System.out.println("Game loaded.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Initializes a default board with pieces at their default locations.
    private void initDefault() {
        players.getGame().getBoard().defaultBoard();
    }

    // EFFECTS: Asks the current player for a square they wish to move, until their input is valid.
    private void processProposedFrom() {
        System.out.println(players.getGame().getBoard().printBoard());
        System.out.println("Captured pieces: " + players.getGame().parseCapturedPieces());
        do {
            System.out.println(players.getGame().getCurrentTurn()
                    + "'s turn. Select a piece on a square you want to move: eg: 3,3. Or type s to save board.");
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("s")) {
                saveGame();
            }
        } while (!isValidInput());
        proposedFrom = players.getGame().getBoard().getSquareAt(proposedX, proposedY);
        processProposedTo();
    }

    // EFFECTS: Asks the current player for a square they wish to move to, until their input is valid.
    private void processProposedTo() {
        do {
            System.out.println(players.getGame().getCurrentTurn()
                    + "'s turn. Select a square you want to move to: eg: 3,3");
            command = input.next();
            command = command.toLowerCase();
        } while (!isValidInput());
        proposedTo = players.getGame().getBoard().getSquareAt(proposedX, proposedY);
        processLegality();
    }

    // EFFECTS: If a player could move a piece from a from square to a to square, make that move, and print "Move made,"
    // and check if the game is ended, and if not that, print invalid move and asks for a new from move in either case.
    private void processLegality() {
        if (players.proposeMove(proposedFrom, proposedTo)) {
            players.makeMove(proposedFrom, proposedTo);
            System.out.println("Move made.");
            endGame();
        } else {
            System.out.println("Invalid move. Please try again.");
        }
        processProposedFrom();
    }

    // EFFECTS: Returns true if command is the correct length, the command is separated by "," and the two integers in
    // the command are both integers in the range of 0 to 9.
    private boolean isValidInput() {
        if (isCorrectLength()) {
            proposedX = getNumericValue((command.charAt(0)));
            proposedY = getNumericValue(command.charAt(2));
            boolean isCorrectSeparator = command.substring(1, 2).equals(separator);
            boolean isValidCoordinateX = proposedX >= 0 && proposedX <= 9;
            boolean isValidCoordinateY = proposedY >= 0 && proposedY <= 9;
            return (isCorrectLength() && isValidCoordinateX && isCorrectSeparator && isValidCoordinateY);
        } else {
            return false;
        }
    }

    // EFFECTS: Returns true if command is exactly 3 characters.
    private boolean isCorrectLength() {
        return (command.length() == 3);
    }

    // EFFECTS: Ends game if the game status has ended.
    private void endGame() {
        if (players.getGame().getEndGame()) {
            System.exit(0);
        }
    }
}
