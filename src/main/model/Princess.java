package model;

import javax.swing.*;
import java.io.IOException;

// Represents a princess that has some colour.
public class Princess extends BasePiece {
    // MODIFIES: this
    // EFFECTS: Makes a new princess with a colour.
    public Princess(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("Princess files not found.");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("I");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wp.png");
        } else {
            icon = new ImageIcon("./data/assets/bp.png");
        }
    }
}
