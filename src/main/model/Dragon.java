package model;

import javax.swing.*;
import java.io.IOException;

// Represents a dragon that has some colour.
public class Dragon extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a dragon with a colour.
    public Dragon(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("D");
    }

    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wp.png");
        } else {
            icon = new ImageIcon("./data/assets/bp.png");
        }
    }
}
