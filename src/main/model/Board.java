package model;

import java.util.ArrayList;
import java.util.List;

import static model.Game.*;

public class Board {
    private List<Square> squareList;

    public Board() {
        Square square;
        squareList = new ArrayList<>();
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            for (int x = 0; x < SQUARES_ON_ROW; x++) {
                square = new Square(x, y);
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

    //===================================================================================================

    // EFFECTS: Returns the amount of pieces on a board.
    public Integer countBoard() {
        // for (Square square : squareList)
        // if (square hasPiece)
        // i++
        return 0;
    }

    public boolean isEmptyAt() {
        for (Square square : squareList) {
            if (square.getIsEmpty()) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Converts a piece's x and y values to it's corresponding index position, starting from (0,0) as 0,
    //          then (0,1) as WIDTH + 1, (0,2) as WIDTH + 1, and so on.
    private int convertPiecePositionToIndex(int x, int y) {
        return (x * (MAX_X_COORDINATE + 1)) + y;
    }

    // EFFECTS:
    private int convertIndexToPieceX(int i) {
        return (i - (i / MAX_X_COORDINATE + 1));
    }

    // EFFECTS:
    private int convertIndexToPieceY(int i) {
        return (i / MAX_Y_COORDINATE + 1);
    }

    // SETTERS:
    public List<Square> getSquareList() {
        return squareList;
    }
}

