package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

// Represents a king that has some colour.
public class King extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a king with a colour.
    public King(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("King assets not found!");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("K");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wk.png");
        } else {
            icon = new ImageIcon("./data/assets/bk.png");
        }
    }
}
