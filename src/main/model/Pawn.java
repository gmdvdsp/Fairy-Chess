package model;

import javax.swing.*;

// Represents a pawn that has some colour.
public class Pawn extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a pawn with a colour.
    public Pawn(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("P");
    }

    @Override
    // EFFECTS: See overridden method.
    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wp.png");
        } else {
            icon = new ImageIcon("./data/assets/bp.png");
        }
    }
}
