package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

// Represents a knight that has some colour.
public class Knight extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a knight with a colour.
    public Knight(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("Knight assets not found!");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("N");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wn.png");
        } else {
            icon = new ImageIcon("./data/assets/bn.png");
        }
    }
}
