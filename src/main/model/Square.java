package model;

import static model.Game.MAX_X_COORDINATE;
import static model.Game.MAX_Y_COORDINATE;

public class Square {
    int posX;
    int posY;
    BasePiece piece;

    public Square(int x, int y, BasePiece p) {
        this.posX = x;
        this.posY = y;
        this.piece = p;
    }

    public boolean isSquareOnBoard() {
        return (0 <= posX && this.posX <= MAX_X_COORDINATE && 0 <= posY && this.posY <= MAX_Y_COORDINATE);
    }

    // SETTERS:
    public void setCoordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }

    public void setPiece(BasePiece p) {
        this.piece = p;
    }

    // GETTERS:
    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public boolean getIsEmpty() {
        return (this.piece == null);
    }

    public String getColourOfPieceOnSquare() {
        return (piece.getColour());
    }

    public BasePiece getPieceOnSquare() {
        return piece;
    }
}
