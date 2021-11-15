package model;

import javax.swing.*;

// Represents a queen that has some colour.
public class Queen extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a new queen with a colour.
    public Queen(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("Q");
    }

    public void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wq.png");
        } else {
            icon = new ImageIcon("./data/assets/bq.png");
        }
    }
}
