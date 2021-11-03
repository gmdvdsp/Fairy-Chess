package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

// Represents a pawn that has some colour.
public class Pawn extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a pawn with a colour.
    public Pawn(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("Pawn assets not found!");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("P");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wp.png");
        } else {
            icon = new ImageIcon("./data/assets/bp.png");
        }
    }

}
