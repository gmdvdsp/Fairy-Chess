package model;

import static java.lang.Math.abs;
import static model.Board.MAX_X_COORDINATE;
import static model.Board.MAX_Y_COORDINATE;

public class Square {
    int posX;
    int posY;
    BasePiece piece;

    public Square(int x, int y, BasePiece p) {
        this.posX = x;
        this.posY = y;
        this.piece = p;
    }

    // EFFECTS: Returns true if a square is within the bounds of the game.
    public boolean isSquareOnBoard() {
        return (0 <= posX && posX <= MAX_X_COORDINATE && 0 <= posY && posY <= MAX_Y_COORDINATE);
    }

    // EFFECTS: Returns a visual representation of a square as represented by [ cP ] where c is the first letter of the
    // piece colour and P is the first letter of the piece name, and [    ] if empty.
    public String printSquare() {
        String square;
        if (piece != null) {
            square = ("[ " + piece.printColourOneCharacter() + piece.printPiece() + " ]");
        } else {
            square = ("[    ]");
        }
        return square;
    }

    // Getters:
    // ===================================================
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

    // Setters:
    // ===================================================
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
}
