package model;

import javax.swing.*;
import java.io.IOException;

// Represents a rook that has some colour.
public class Rook extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a new rook with a colour.
    public Rook(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("Rook assets not found!");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("R");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wr.png");
        } else {
            icon = new ImageIcon("./data/assets/br.png");
        }
    }
}
