package model;

import javax.swing.*;
import java.io.IOException;

// Represents a bishop that has some colour.
public class Bishop extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a bishop with a colour.
    public Bishop(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("Asset files not found for bishops.");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("B");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wb.png");
        } else {
            icon = new ImageIcon("./data/assets/bb.png");
        }
    }
}
