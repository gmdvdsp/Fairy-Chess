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

    @Override
    // EFFECTS: See overridden method.
    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wd.png");
        } else {
            icon = new ImageIcon("./data/assets/bd.png");
        }
    }
}
