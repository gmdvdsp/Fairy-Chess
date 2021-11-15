package model;

import javax.swing.*;

// Represents a princess that has some colour.
public class Princess extends BasePiece {
    // MODIFIES: this
    // EFFECTS: Makes a new princess with a colour.
    public Princess(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("I");
    }

    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wp.png");
        } else {
            icon = new ImageIcon("./data/assets/bp.png");
        }
    }
}
