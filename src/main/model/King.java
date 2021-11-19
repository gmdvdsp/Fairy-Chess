package model;

import javax.swing.*;

// Represents a king that has some colour.
public class King extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a king with a colour.
    public King(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("K");
    }

    @Override
    // EFFECTS: See overridden method.
    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wk.png");
        } else {
            icon = new ImageIcon("./data/assets/bk.png");
        }
    }
}
