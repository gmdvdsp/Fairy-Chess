package model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static model.Game.*;

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

    // METHODS:

    //===================================================================================================
    public void updateBoardAt(int x, int y, Square square) {
        squareList.set(convertPiecePositionToIndex(x, y), square);
    }

    public void replaceSquare(Square square) {
        squareList.set(convertPiecePositionToIndex(square.getX(), square.getY()), square);
    }

    public Square getSquareAt(int x, int y) {
        return squareList.get(convertPiecePositionToIndex(x, y));
    }

    // RANK/FILE/DIAGONAL RELATIONSHIPS:
    //===================================================================================================
    public boolean isOnSameRank(Square from, Square to) {
        return (from.getY() == to.getY());
    }

    public boolean isOnSameFile(Square from, Square to) {
        return (from.getX() == to.getX());
    }

    public boolean isOnSameDiagonal(Square from, Square to) {
        return (abs(getDistanceToY(from, to)) == abs(getDistanceToX(from, to)));
    }

    // DISTANCES:
    //===================================================================================================
    public int getDistanceToX(Square fromSquare, Square toSquare) {
        return (toSquare.getX() - fromSquare.getX());
    }

    public int getDistanceToY(Square fromSquare, Square toSquare) {
        return (toSquare.getY() - fromSquare.getY());
    }

    // Returns a square that has (x, y) equal to the distances between from and to's respective (x, y).
    public Square getDistanceBetween(Square from, Square to) {
        return (new Square(getDistanceToX(from, to), getDistanceToY(from, to), null));
    }

    public boolean isCardinalDirectionEmpty(Square from, Square to, Square distance) {
        if (distance.getX() == 0) {
            return (isEmptyBetweenVertical(from, to));
        } else {
            return (isEmptyBetweenHorizontal(from, to));
        }
    }

    public boolean isDiagonalDirectionEmpty(Square from, Square to, Square distance) {
        if (signum(distance.getX()) == signum(distance.getY())) {
            return (isEmptyBetweenDiagonalUpperRightLowerLeft(from, to));
        } else {
            return (isEmptyBetweenDiagonalUpperLeftLowerRight(from, to));
        }
    }

    private boolean isEmptyBetweenHorizontal(Square from, Square to) {
        Square distanceSquare = getDistanceBetween(from, to);
        int signOfX = (int) signum(distanceSquare.getX());

        for (int x = from.getX() + signOfX; x != to.getX(); x += signOfX) {
            if (!squareList.get(convertPiecePositionToIndex(x, from.getY())).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyBetweenVertical(Square from, Square to) {
        Square distanceSquare = getDistanceBetween(from, to);
        int signOfY = (int) signum(distanceSquare.getY());

        for (int y = from.getY() + signOfY; y != to.getY(); y += signOfY) {
            if (!squareList.get(convertPiecePositionToIndex(from.getX(), y)).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES: distanceSquare has x == y, and the sign of x == sign of y.
    private boolean isEmptyBetweenDiagonalUpperRightLowerLeft(Square from, Square to) {
        Square distanceSquare = getDistanceBetween(from, to);
        int signOf = (int) signum(distanceSquare.getX());

        for (int i = signOf; i != distanceSquare.getX(); i += signOf) {
            if (!squareList.get(convertPiecePositionToIndex(from.getX() + i,from.getY() + i)).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES: distanceSquare has x == y, and the sign of x != sign of y.
    private boolean isEmptyBetweenDiagonalUpperLeftLowerRight(Square from, Square to) {
        Square distanceSquare = getDistanceBetween(from, to);
        int signOfX = (int) signum(distanceSquare.getX());
        for (int i = signOfX; i != distanceSquare.getX(); i += signOfX) {
            if (!squareList.get(convertPiecePositionToIndex(from.getX() + i, from.getY() - i)).getIsEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void defaultBoard() {
        defaultBoardWhite();
        defaultBoardBlack();
    }

    private void defaultBoardWhite() {
        for (int x = 0; x < SQUARES_ON_ROW; x++) {
            updateBoardAt(x, 1, new Square(x, 1, new Pawn("white")));
        }
        updateBoardAt(0, 0, new Square(0, 0, new Dragon("white")));
        updateBoardAt(9, 0, new Square(9, 0, new Princess("white")));
        for (int x = 1; x < MAX_X_COORDINATE; x += 7) {
            updateBoardAt(x, 0, new Square(x, 0, new Rook("white")));
        }
        for (int x = 2; x < MAX_X_COORDINATE; x += 5) {
            updateBoardAt(x, 0, new Square(x, 0, new Knight("white")));
        }
        for (int x = 3; x < MAX_X_COORDINATE; x += 3) {
            updateBoardAt(x, 0, new Square(x, 0, new Bishop("white")));
        }
        updateBoardAt(4, 0, new Square(4, 0, new King("white")));
        updateBoardAt(5, 0, new Square(5, 0, new Queen("white")));
    }

    private void defaultBoardBlack() {
        for (int x = 0; x < SQUARES_ON_ROW; x++) {
            updateBoardAt(x, 6, new Square(x, 6, new Pawn("black")));
        }
        updateBoardAt(0, 7, new Square(0, 7, new Dragon("black")));
        updateBoardAt(9, 7, new Square(9, 7, new Princess("black")));
        for (int x = 1; x < MAX_X_COORDINATE; x += 7) {
            updateBoardAt(x, 7, new Square(x, 7, new Rook("black")));
        }
        for (int x = 2; x < MAX_X_COORDINATE; x += 5) {
            updateBoardAt(x, 7, new Square(x, 7, new Knight("black")));
        }
        for (int x = 3; x < MAX_X_COORDINATE; x += 3) {
            updateBoardAt(x, 7, new Square(x, 7, new Bishop("black")));
        }
        updateBoardAt(4, 7, new Square(4, 7, new King("black")));
        updateBoardAt(5, 7, new Square(5, 7, new Queen("black")));
    }

    //===================================================================================================

    // REQUIRES: 0 <= x <= WIDTH and 0 <= y <= HEIGHT
    // EFFECTS: Converts a piece's x and y values to it's corresponding index position, starting from (0,0) as 0,
    //          then (0,1) as WIDTH + 1, (0,2) as WIDTH + 1, and so on.
    private int convertPiecePositionToIndex(int x, int y) {
        return ((y * SQUARES_ON_ROW) + x);
    }

    // SETTERS:
    public List<Square> getSquareList() {
        return squareList;
    }

    public String printBoard() {
        StringBuilder board = new StringBuilder();
        for (int y = MAX_Y_COORDINATE; y >= 0; y--) {
            board.append(printRow(y)).append("\n");
        }
        return board.toString();
    }

    private String printRow(int y) {
        StringBuilder row = new StringBuilder();
        for (int x = 0; x < SQUARES_ON_ROW; x++) {
            row.append(getSquareAt(x, y).printSquare());
        }
        return row.toString();
    }
}

