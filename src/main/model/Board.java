package model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class Board {
    public static final int MAX_X_COORDINATE = 9;
    public static final int MAX_Y_COORDINATE = 7;
    public static final int SQUARES_ON_ROW = MAX_X_COORDINATE + 1;
    public static final int SQUARES_ON_COLUMN = MAX_Y_COORDINATE + 1;

    private List<Square> squareList;

    public Board() {
        Square square;
        squareList = new ArrayList<>();
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                square = new Square(x, y, null);
                squareList.add(square);
            }
        }
    }

    // Methods:
    // ===================================================
    // REQUIRES: Some square in squareList has coordinates square.getX() and square.getY().
    // MODIFIES: this
    // EFFECTS: Replaces the square in squareList with a given square.
    public void replaceSquare(Square square) {
        squareList.set(convertCoordinateToIndex(square.getX(), square.getY()), square);
    }

    // REQUIRES: Some square in squareList has coordinates x, y.
    // EFFECTS: Returns the square in squareList that has coordinates x, y.
    public Square getSquareAt(int x, int y) {
        return squareList.get(convertCoordinateToIndex(x, y));
    }

    // EFFECTS: Returns a visual representation of a board.
    public String printBoard() {
        StringBuilder board = new StringBuilder();
        for (int y = MAX_Y_COORDINATE; y >= 0; y--) {
            board.append(printRow(y)).append("\n");
        }
        return board.toString();
    }

    // EFFECTS: Returns a row of the board as a String where every square is represented as [ cP ] where c is the first
    // letter of the piece colour and P is the first letter of the piece name, and [    ] if empty.
    private String printRow(int y) {
        StringBuilder row = new StringBuilder();
        for (int x = 0; x < SQUARES_ON_ROW; x++) {
            row.append(getSquareAt(x, y).printSquare());
        }
        return row.toString();
    }

    // REQUIRES: Some square on the board has coordinates (x, y).
    // EFFECTS: Converts a piece's x and y values to it's corresponding index position, starting from (0,0) as 0,
    // then (0,1) as WIDTH + 1, (0,2) as WIDTH + 1, and so on. (0,1) is index WIDTH + 1.
    private int convertCoordinateToIndex(int x, int y) {
        return ((y * SQUARES_ON_ROW) + x);
    }

    // Boolean relationships between two squares (maybe move these into squares):
    //=========================
    // EFFECTS: Returns true if both squares have the same y value.
    public boolean isOnSameRank(Square from, Square to) {
        return (from.getY() == to.getY());
    }

    // EFFECTS: Returns true if both squares have the same x value.
    public boolean isOnSameFile(Square from, Square to) {
        return (from.getX() == to.getX());
    }

    // EFFECTS: Returns true if both squares are on the same diagonal; that is, both have abs(y - y) == abs(x - x)
    public boolean isOnSameDiagonal(Square from, Square to) {
        return (abs(getDistanceToY(from, to)) == abs(getDistanceToX(from, to)));
    }

    // Distances between two squares:
    //=========================
    // EFFECTS: Returns the distances between the x-values of the square; positive if to is to the left, and negative
    // if vice-versa.
    public int getDistanceToX(Square fromSquare, Square toSquare) {
        return (toSquare.getX() - fromSquare.getX());
    }

    // EFFECTS: Returns the distances between the y-values of the square; positive if to is above, and negative
    // if vice-versa.
    public int getDistanceToY(Square fromSquare, Square toSquare) {
        return (toSquare.getY() - fromSquare.getY());
    }

    // EFFECTS: Returns a Square who's x and y components exactly equal the respective x and y differences between
    // two squares.
    public Square getDistanceBetween(Square from, Square to) {
        return (new Square(getDistanceToX(from, to), getDistanceToY(from, to), null));
    }

    // Boolean relationships involving emptiness between two squares on a board.
    //=========================
    // REQUIRES: from and to are both on the board, and both squares are either on the same rank or file.
    // EFFECTS: Returns true if every square between two squares is empty.
    public boolean isCardinalDirectionEmpty(Square from, Square to) {
        Square distance = getDistanceBetween(from, to);
        if (distance.getX() == 0) {
            return (isEmptyBetweenVertical(from, to, distance));
        } else {
            return (isEmptyBetweenHorizontal(from, to, distance));
        }
    }

    // REQUIRES: from and to are both on the board, and both squares are on the same diagonal.
    // EFFECTS: Returns true if every square between two squares is empty.
    public boolean isDiagonalDirectionEmpty(Square from, Square to) {
        Square distance = getDistanceBetween(from, to);
        if (signum(distance.getX()) == signum(distance.getY())) {
            return (isEmptyBetweenDiagonalUpperRightLowerLeft(from, to, distance));
        } else {
            return (isEmptyBetweenDiagonalUpperLeftLowerRight(from, to, distance));
        }
    }

    // REQUIRES: from and to are on the same rank.
    // EFFECTS: Returns true if every square between two squares is empty.
    private boolean isEmptyBetweenHorizontal(Square from, Square to, Square distance) {
        int signOfX = (int) signum(distance.getX());
        for (int x = from.getX() + signOfX; x != to.getX(); x += signOfX) {
            if (!squareList.get(convertCoordinateToIndex(x, from.getY())).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES: from and to are on the same file.
    // EFFECTS: Returns true if every square between two squares is empty.
    private boolean isEmptyBetweenVertical(Square from, Square to, Square distance) {
        int signOfY = (int) signum(distance.getY());

        for (int y = from.getY() + signOfY; y != to.getY(); y += signOfY) {
            if (!squareList.get(convertCoordinateToIndex(from.getX(), y)).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES: from and to are both on the board, and both squares are on the same diagonal going from the top right
    // to the lower left.
    // EFFECTS: Returns true if every square between two squares is empty.
    private boolean isEmptyBetweenDiagonalUpperRightLowerLeft(Square from, Square to, Square distance) {
        int signOf = (int) signum(distance.getX());

        for (int i = signOf; i != distance.getX(); i += signOf) {
            if (!squareList.get(convertCoordinateToIndex(from.getX() + i,from.getY() + i)).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES: from and to are both on the board, and both squares are on the same diagonal going from the top left
    // to the lower right.
    // EFFECTS: Returns true if every square between two squares is empty.
    private boolean isEmptyBetweenDiagonalUpperLeftLowerRight(Square from, Square to, Square distance) {
        int signOfX = (int) signum(distance.getX());
        for (int i = signOfX; i != distance.getX(); i += signOfX) {
            if (!squareList.get(convertCoordinateToIndex(from.getX() + i, from.getY() - i)).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Default initialization:
    //=========================
    // MODIFIES: this
    // EFFECTS: Makes a new board with pieces in their default locations.
    public void defaultBoard() {
        defaultBoardWhite();
        defaultBoardBlack();
    }

    // MODIFIES: this
    // EFFECTS: Sets all the white pieces on their initial starting positions on a default board.
    private void defaultBoardWhite() {
        for (int x = 0; x < SQUARES_ON_ROW; x++) {
            replaceSquare(new Square(x, 1, new Pawn("white")));
        }
        replaceSquare(new Square(0, 0, new Dragon("white")));
        replaceSquare(new Square(9, 0, new Princess("white")));
        for (int x = 1; x < MAX_X_COORDINATE; x += 7) {
            replaceSquare(new Square(x, 0, new Rook("white")));
        }
        for (int x = 2; x < MAX_X_COORDINATE; x += 5) {
            replaceSquare(new Square(x, 0, new Knight("white")));
        }
        for (int x = 3; x < MAX_X_COORDINATE; x += 3) {
            replaceSquare(new Square(x, 0, new Bishop("white")));
        }
        replaceSquare(new Square(4, 0, new King("white")));
        replaceSquare(new Square(5, 0, new Queen("white")));
    }

    // MODIFIES: this
    // EFFECTS: Sets all the black pieces on their initial starting positions on a default board.
    private void defaultBoardBlack() {
        for (int x = 0; x < SQUARES_ON_ROW; x++) {
            replaceSquare(new Square(x, 6, new Pawn("black")));
        }
        replaceSquare(new Square(0, 7, new Dragon("black")));
        replaceSquare(new Square(9, 7, new Princess("black")));
        for (int x = 1; x < MAX_X_COORDINATE; x += 7) {
            replaceSquare(new Square(x, 7, new Rook("black")));
        }
        for (int x = 2; x < MAX_X_COORDINATE; x += 5) {
            replaceSquare(new Square(x, 7, new Knight("black")));
        }
        for (int x = 3; x < MAX_X_COORDINATE; x += 3) {
            replaceSquare(new Square(x, 7, new Bishop("black")));
        }
        replaceSquare(new Square(4, 7, new King("black")));
        replaceSquare(new Square(5, 7, new Queen("black")));
    }
}

