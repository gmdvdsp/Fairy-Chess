package model;

import org.json.JSONObject;
import persistence.Writable;
import javax.swing.*;

import java.awt.*;

import static java.lang.Math.abs;

// Represents a square that has some coordinate (x,y) and either has a piece on it, or has null.
public class Square extends JButton implements Writable {
    int posX;
    int posY;
    BasePiece piece;
    Color originalColor;

    // MODIFIES: this
    // EFFECTS: Makes a square that has some coordinate, (x,y) and some piece on it.
    public Square(int x, int y, BasePiece p) {
        this.posX = x;
        this.posY = y;
        this.piece = p;
        if (piece != null) {
            setIcon(piece.getIcon());
        }
    }

    // Methods:
    // ==================================================
    // EFFECTS: Converts a square to a JSONObject with x, y and piece on it. If no piece is on it, do not add this.
    @Override
    public JSONObject toJSon() {
        JSONObject json = new JSONObject();
        json.put("x", posX);
        json.put("y", posY);
        if (!getIsEmpty()) {
            json.put("piece", piece.toJSon());
        }
        return json;
    }
    // Visual representation:
    //=========================
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

    // Boolean relationships between two squares on a board:
    //=========================
    // EFFECTS: Returns true if both squares have the same y value.
    public boolean isOnSameRank(Square to) {
        return (posY == to.posY);
    }

    // EFFECTS: Returns true if both squares have the same x value.
    public boolean isOnSameFile(Square to) {
        return (posX == to.posX);
    }

    // EFFECTS: Returns true if both squares are on the same diagonal; that is, both have abs(y - y) == abs(x - x)
    public boolean isOnSameDiagonal(Square to) {
        return (abs(getDistanceToY(to)) == abs(getDistanceToX(to)));
    }

    // Distances between two squares:
    //=========================
    // EFFECTS: Returns a Square who's x and y components exactly equal the respective x and y differences between
    // two squares.
    public Square getDistanceBetween(Square to) {
        return (new Square(getDistanceToX(to), getDistanceToY(to), null));
    }

    // EFFECTS: Returns the distances between the x-values of the square; positive if to is to the left, and negative
    // if vice-versa.
    private int getDistanceToX(Square to) {
        return (to.posX - this.posX);
    }

    // EFFECTS: Returns the distances between the y-values of the square; positive if to is above, and negative
    // if vice-versa.
    private int getDistanceToY(Square to) {
        return (to.posY - this.posY);
    }

    public void setOriginalColor(Color originalColor) {
        this.originalColor = originalColor;
    }

    // Non-simple getters:
    // ===================================================
    // EFFECTS: Returns true only if the square has a piece occupying it.
    public boolean getIsEmpty() {
        return (this.piece == null);
    }

    // Simple getters:
    // ===================================================
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public BasePiece getPieceOnSquare() {
        return piece;
    }

    public java.awt.Color getOriginalColor() {
        return originalColor;
    }

    // Simple setters:
    // ===================================================
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
