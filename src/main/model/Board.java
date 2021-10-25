package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.signum;

// Represents a board that has a maximum x-coordinate, a maximum y-coordinate, and a list of Squares that span (0,0) to
// (xmax, ymax).
public class Board implements Writable {
    int xmax;
    int ymax;
    List<Square> squareList;

    // MODIFIES: this
    // EFFECTS: Makes a new board with a list of empty Squares that span (0,0) to (xmax, ymax).
    public Board(int xmax, int ymax) {
        this.xmax = xmax;
        this.ymax = ymax;
        Square square;
        squareList = new ArrayList<>();
        for (int y = 0; y <= ymax; y++) {
            for (int x = 0; x <= xmax; x++) {
                square = new Square(x, y, null);
                squareList.add(square);
            }
        }
    }

    // Methods:
    // ===================================================
    @Override
    public JSONObject toJSon() {
        JSONObject json = new JSONObject();
        json.put("squareList", squaresToJSon());
        return json;
    }

    private JSONArray squaresToJSon() {
        JSONArray jsonArray = new JSONArray();
        for (Square s : squareList) {
            jsonArray.put(s.toJSon());
        }
        return jsonArray;
    }

    // Visual representations:
    //=========================
    // EFFECTS: Returns a visual representation of a board.
    public String printBoard() {
        StringBuilder board = new StringBuilder();
        for (int y = ymax; y >= 0; y--) {
            board.append(printRow(y)).append("\n");
        }
        return board.toString();
    }

    // EFFECTS: Returns a row of the board as a String where every square is represented as [ cP ] where c is the first
    // letter of the piece colour and P is the first letter of the piece name, and [    ] if empty.
    private String printRow(int y) {
        StringBuilder row = new StringBuilder();
        for (int x = 0; x <= xmax; x++) {
            row.append(getSquareAt(x, y).printSquare());
        }
        return row.toString();
    }

    // Coordinate to squareList index conversion:
    //=========================
    // REQUIRES: Some square on the board has coordinates (x, y).
    // EFFECTS: Converts a piece's x and y values to it's corresponding index position, starting from (0,0) as 0,
    // then (0,1) as WIDTH + 1, (0,2) as WIDTH + 1, and so on. (0,1) is index WIDTH + 1.
    private int convertCoordinateToIndex(int x, int y) {
        return ((y * (xmax + 1)) + x);
    }

    // Boolean relationships between given squares and the board:
    //=========================
    // EFFECTS: Returns true if a square is within the bounds of the game.
    public boolean isSquareOnBoard(Square square) {
        boolean xinGame = (0 <= square.getX() && square.getX() <= xmax);
        boolean yinGame = (0 <= square.getY() && square.getY() <= ymax);
        return (xinGame && yinGame);
    }

    // REQUIRES: from and to are both on the board, and both squares are either on the same rank or file.
    // EFFECTS: Returns true if every square between two squares is empty.
    public boolean isCardinalDirectionEmpty(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        if (distance.getX() == 0) {
            return (isEmptyBetweenVertical(from, distance));
        } else {
            return (isEmptyBetweenHorizontal(from, distance));
        }
    }

    // REQUIRES: from and to are both on the board, and both squares are on the same diagonal.
    // EFFECTS: Returns true if every square between two squares is empty.
    public boolean isDiagonalDirectionEmpty(Square from, Square to) {
        Square distance = from.getDistanceBetween(to);
        if (signum(distance.getX()) == signum(distance.getY())) {
            return (isEmptyBetweenDiagonalUpperRightLowerLeft(from, distance));
        } else {
            return (isEmptyBetweenDiagonalUpperLeftLowerRight(from, distance));
        }
    }

    // REQUIRES: from and to are on the same rank.
    // EFFECTS: Returns true if every square between two squares is empty.
    private boolean isEmptyBetweenHorizontal(Square from, Square distance) {
        int signOfX = (int) signum(distance.getX());
        for (int i = signOfX; i != distance.getX(); i += signOfX) {
            if (!squareList.get(convertCoordinateToIndex(from.getX() + i, from.getY())).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES: from and to are on the same file.
    // EFFECTS: Returns true if every square between two squares is empty.
    private boolean isEmptyBetweenVertical(Square from, Square distance) {
        int signOfY = (int) signum(distance.getY());
        for (int i = signOfY; i != distance.getY(); i += signOfY) {
            if (!squareList.get(convertCoordinateToIndex(from.getX(), from.getY() + i)).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES: from and to are both on the board, and both squares are on the same diagonal going from the top right
    // to the lower left.
    // EFFECTS: Returns true if every square between two squares is empty.
    private boolean isEmptyBetweenDiagonalUpperRightLowerLeft(Square from, Square distance) {
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
    private boolean isEmptyBetweenDiagonalUpperLeftLowerRight(Square from, Square distance) {
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
        for (int x = 0; x <= xmax; x++) {
            replaceSquare(new Square(x, 1, new Pawn("white")));
        }
        replaceSquare(new Square(0, 0, new Dragon("white")));
        replaceSquare(new Square(9, 0, new Princess("white")));
        for (int x = 1; x < xmax; x += 7) {
            replaceSquare(new Square(x, 0, new Rook("white")));
        }
        for (int x = 2; x < xmax; x += 5) {
            replaceSquare(new Square(x, 0, new Knight("white")));
        }
        for (int x = 3; x < xmax; x += 3) {
            replaceSquare(new Square(x, 0, new Bishop("white")));
        }
        replaceSquare(new Square(4, 0, new King("white")));
        replaceSquare(new Square(5, 0, new Queen("white")));
    }

    // MODIFIES: this
    // EFFECTS: Sets all the black pieces on their initial starting positions on a default board.
    private void defaultBoardBlack() {
        for (int x = 0; x <= xmax; x++) {
            replaceSquare(new Square(x, 6, new Pawn("black")));
        }
        replaceSquare(new Square(0, 7, new Dragon("black")));
        replaceSquare(new Square(9, 7, new Princess("black")));
        for (int x = 1; x < xmax; x += 7) {
            replaceSquare(new Square(x, 7, new Rook("black")));
        }
        for (int x = 2; x < xmax; x += 5) {
            replaceSquare(new Square(x, 7, new Knight("black")));
        }
        for (int x = 3; x < xmax; x += 3) {
            replaceSquare(new Square(x, 7, new Bishop("black")));
        }
        replaceSquare(new Square(4, 7, new King("black")));
        replaceSquare(new Square(5, 7, new Queen("black")));
    }

    // Non-simple getters:
    //=========================
    // REQUIRES: Exactly one square in squareList has coordinates x, y.
    // EFFECTS: Returns the single square in squareList that has coordinates x, y.
    public Square getSquareAt(int x, int y) {
        return squareList.get(convertCoordinateToIndex(x, y));
    }

    // Non-simple setters:
    //=========================
    // REQUIRES: Exactly one square in squareList has coordinates square.getX() and square.getY().
    // MODIFIES: this
    // EFFECTS: Replaces the square in squareList with a given square.
    public void replaceSquare(Square square) {
        squareList.set(convertCoordinateToIndex(square.getX(), square.getY()), square);
    }

    // Simple Getters:
    //=========================
    public int getXmax() {
        return xmax;
    }

    // Simple Getters:
    //=========================
    public int getYmax() {
        return ymax;
    }
}

