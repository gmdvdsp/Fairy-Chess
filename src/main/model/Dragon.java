package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

// Represents a dragon that has some colour.
public class Dragon extends BasePiece {

    // MODIFIES: this
    // EFFECTS: Makes a dragon with a colour.
    public Dragon(String colour) {
        super(colour);
        try {
            setIcon();
        } catch (IOException e) {
            System.out.println("Dragon assets not found!");
        }
    }

    @Override
    // EFFECTS: See overridden method.
    protected String printPiece() {
        return ("D");
    }

    private void setIcon() throws IOException {
        if (colour.equals("white")) {
            icon = new ImageIcon("./data/assets/wp.png");
        } else {
            icon = new ImageIcon("./data/assets/bp.png");
        }
    }
}
