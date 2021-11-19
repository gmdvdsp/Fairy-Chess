package model;

import javax.swing.*;
import java.io.IOException;

// Represents a knight that has some colour.
public class Knight extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a knight with a colour.
    public Knight(String colour) {
        super(colour);
        setIcon();
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("N");
    }

    @Override
    // EFFECTS: See overridden method.
    protected void setIcon() {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wn.png");
        } else {
            icon = new ImageIcon("./data/assets/bn.png");
        }
    }
}
