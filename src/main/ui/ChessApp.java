package ui;

import model.Players;
import model.Square;

import java.util.Scanner;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;
import static model.Board.SQUARES_ON_COLUMN;
import static model.Board.SQUARES_ON_ROW;

public class ChessApp {
    Players players;

    Integer proposedX;
    Integer proposedY;
    Square proposedFrom;
    Square proposedTo;

    Scanner input;
    String command;

    public ChessApp() {
        players = new Players();
        input = new Scanner(System.in);
        runChessApp();
    }

    private void runChessApp() {
        System.out.println("Start a new game with default board or customize board? (df/cb)");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("df")) {
            initDefault();
            processProposedFrom();
        } else if (command.equals("cb")) {
            //initCustom();
        } else {
            runChessApp();
        }
    }

    private void initDefault() {
        players.getGame().getBoard().defaultBoard();
    }

    private void processProposedFrom() {
        System.out.println(players.getGame().getBoard().printBoard());
        do {
            System.out.println(players.getPlayerColour() + "'s turn. Select a piece on a square you want to move: eg: 3,3");
            command = input.next();
            command = command.toLowerCase();
            proposedX = getNumericValue(command.charAt(0));
            proposedY = getNumericValue(command.charAt(2));
        } while (!isValidInput() && !processValidSquare());
        proposedX = getNumericValue(command.charAt(0));
        proposedY = getNumericValue(command.charAt(2));
        proposedFrom = players.getGame().getBoard().getSquareAt(proposedX, proposedY);
        processProposedTo();
    }

    private void processProposedTo() {
        do {
            System.out.println(players.getPlayerColour() + "'s turn. Select a square you want to move to: eg: 3,3");
            command = input.next();
            command = command.toLowerCase();
            proposedX = getNumericValue(command.charAt(0));
            proposedY = getNumericValue(command.charAt(2));
        } while (!isValidInput() && !processValidSquare());
        proposedX = getNumericValue(command.charAt(0));
        proposedY = getNumericValue(command.charAt(2));
        proposedTo = players.getGame().getBoard().getSquareAt(proposedX, proposedY);
        processLegality();
    }

    private boolean processValidSquare() {
        return (players.proposeSquare(new Square(proposedX, proposedY, null)));
    }

    private void processLegality() {
        if (players.proposeMove(proposedFrom, proposedTo)) {
            System.out.println("Move made." + players.getPlayerColour() + "'s turn.");
        } else {
            System.out.println("Invalid move. Try again.");
        }
        processProposedFrom();
    }

    private boolean isValidInput() {
        String substring = ",";
        boolean charAt1 = command.substring(1,2).equals(substring);
        return ((command.length() == 3) && isDigit(proposedX) && charAt1 && isDigit(proposedY));
    }

}
