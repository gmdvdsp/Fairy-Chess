package model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static model.Game.*;

public class Board {
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

    public void move(Square fromSquare, Square toSquare) {
        // updateBoard(fromSquare, toSquare);
    }

    public void updateBoard(Square fromSquare, Square toSquare) {
        //  setSquare(fromSquare);
        //  setSquare(toSquare);
    }

    public void updateBoardAt(int x, int y, Square square) {
        squareList.set(convertPiecePositionToIndex(x, y), square);
    }

    public int getDistanceToX(Square fromSquare, Square toSquare) {
        return (toSquare.getX() - fromSquare.getX());
    }

    public int getDistanceToY(Square fromSquare, Square toSquare) {
        return (toSquare.getY() - fromSquare.getY());
    }

    public int getPieceAtCoordinate() {
        return 0;
    }

    public boolean isSquareAboveEmpty(Square from) {
        boolean atUpperBound = (from.getY() == MAX_Y_COORDINATE);
        return (!atUpperBound && squareList.get(convertPiecePositionToIndex(from.getX(), from.getY() + 1)).getIsEmpty());
    }

    public boolean isSquareBelowEmpty(Square from) {
        boolean atLowerBound = (from.getY() == 0);
        return (!atLowerBound && squareList.get(convertPiecePositionToIndex(from.getX(), from.getY() - 1)).getIsEmpty());
    }
    public boolean isSquareRightEmpty(Square from) {
        boolean atRightBound = (from.getY() == MAX_X_COORDINATE);
        return (!atRightBound && squareList.get(convertPiecePositionToIndex(from.getX() + 1, from.getY())).getIsEmpty());
    }

    public boolean isSquareLeftEmpty(Square from) {
        boolean atLeftBound = (from.getY() == 0);
        return (!atLeftBound && squareList.get(convertPiecePositionToIndex(from.getX() - 1, from.getY())).getIsEmpty());
    }


    public boolean isOnSameRank(Square from, Square to) {
        return (from.getY() == to.getY());
    }

    public boolean isOnSameFile(Square from, Square to) {
        return (from.getX() == to.getX());
    }

    public boolean isOnSameDiagonal(Square from, Square to) {
        return (abs(getDistanceToY(from, to)) == abs(getDistanceToX(from, to)));
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
}

