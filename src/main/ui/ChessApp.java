package ui;

import model.Game;
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
        players = new Players("white");
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
            System.out.println(players.getGame().getCurrentTurn() + "'s turn. Select a piece on a square you want to move: eg: 3,3");
            command = input.next();
            command = command.toLowerCase();
            proposedX = getNumericValue(command.charAt(0));
            proposedY = getNumericValue(command.charAt(2));
        } while (!isValidInput());
        proposedX = getNumericValue(command.charAt(0));
        proposedY = getNumericValue(command.charAt(2));
        processFromSquare();
    }

    private void processProposedTo() {
        do {
            System.out.println(players.getGame().getCurrentTurn() + "'s turn. Select a square you want to move to: eg: 3,3");
            command = input.next();
            command = command.toLowerCase();
            proposedX = getNumericValue((command.charAt(0)));
            proposedY = getNumericValue(command.charAt(2));
        } while (!isValidInput());
        proposedX = getNumericValue(command.charAt(0));
        proposedY = getNumericValue(command.charAt(2));
        processToSquare();
    }

    private void processFromSquare() {
        proposedFrom = players.getGame().getBoard().getSquareAt(proposedX, proposedY);
        if (players.proposeFromSquare(proposedFrom)) {
            processProposedTo();
        } else {
            processProposedFrom();
        }
    }

    private void processToSquare() {
        proposedTo = players.getGame().getBoard().getSquareAt(proposedX, proposedY);
        if (players.proposeToSquare(proposedTo)) {
            processLegality();
        } else {
            processProposedTo();
        }
    }

    private void processLegality() {
        players.proposeMove(proposedFrom, proposedTo);
        processProposedFrom();
    }

    // check at once
    private boolean isValidInput() {
        String substring = ",";
        boolean s = command.substring(1,2).equals(substring);
        boolean isDigit1 = proposedX >= 0 && proposedX <= 9;
        boolean isDigit2 = proposedY >= 0 && proposedY <= 9;
        return ((command.length() == 3) && isDigit1 && s && isDigit2);
    }

}
