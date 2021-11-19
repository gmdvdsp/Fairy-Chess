package model;

import javax.swing.*;

// Represents a rook that has some colour.
public class Rook extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a new rook with a colour.
    public Rook(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("R");
    }

    @Override
    // EFFECTS: See overridden method.
    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wr.png");
        } else {
            icon = new ImageIcon("./data/assets/br.png");
        }
    }
}
