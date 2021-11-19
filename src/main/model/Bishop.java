package model;

import javax.swing.*;

// Represents a bishop that has some colour.
public class Bishop extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a bishop with a colour.
    public Bishop(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("B");
    }

    @Override
    // EFFECTS: See overridden method.
    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wb.png");
        } else {
            icon = new ImageIcon("./data/assets/bb.png");
        }
    }
}
