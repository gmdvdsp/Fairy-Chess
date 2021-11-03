package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

// Represents a queen that has some colour.
public class Queen extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a new queen with a colour.
    public Queen(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("Queen assets not found!");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("Q");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wq.png");
        } else {
            icon = new ImageIcon("./data/assets/bq.png");
        }
    }
}
