package ui;

import model.Players;
import model.Square;

import java.util.Scanner;

import static java.lang.Character.getNumericValue;

public class ChessApp {
    Players players;
    Integer proposedX;
    Integer proposedY;
    Square proposedFrom;
    Square proposedTo;
    Scanner input;
    String command;

    private String separator = ",";

    public ChessApp() {
        players = new Players();
        input = new Scanner(System.in);
        runChessApp();
    }

    private void runChessApp() {
        System.out.println("Start a new game with default board? y/n");
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("y")) {
            initDefault();
            processProposedFrom();
        } else {
            runChessApp();
        }
    }

    private void initDefault() {
        players.getGame().getBoard().defaultBoard();
    }

    private void processProposedFrom() {
        System.out.println(players.getGame().getBoard().printBoard());
        System.out.println("Captured pieces: " + players.getGame().parseCapturedPieces());
        do {
            System.out.println(players.getGame().getCurrentTurn()
                    + "'s turn. Select a piece on a square you want to move: eg: 3,3");
            command = input.next();
            command = command.toLowerCase();
        } while (!isValidInput());
        proposedFrom = players.getGame().getBoard().getSquareAt(proposedX, proposedY);
        processProposedTo();
    }

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

    private boolean isCorrectLength() {
        return (command.length() == 3);
    }

    private void endGame() {
        if (players.getGame().getEndGame()) {
            System.exit(0);
        }
    }
}
