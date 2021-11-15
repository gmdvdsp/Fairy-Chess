package model;

import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;

// Represents a chess piece that has a colour.
public abstract class BasePiece implements Writable {
    protected String colour;
    protected ImageIcon icon;

    // MODIFIES: this
    // EFFECTS: Makes some piece with some colour.
    public BasePiece(String colour) {
        this.colour = colour;
    }

    // Methods:
    // ===================================================
    // EFFECTS: Converts a piece to a JSONObject with name and colour.
    @Override
    public JSONObject toJSon() {
        JSONObject json = new JSONObject();
        json.put("pieceName", this.getClass().getSimpleName());
        json.put("colour", colour);
        return json;
    }

    // EFFECTS: Prints the first letter of the piece's name as a capital letter.
    protected abstract String printPiece();

    // EFFECTS: prints the first letter of the piece's colour as a lowercase letter.
    public String printColourOneCharacter() {
        if (colour.equals("white")) {
            return ("w");
        } else {
            return ("b");
        }
    }

    // Simple Getters:
    // ===================================================
    public String getColour() {
        return colour;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    // Simple Setters:
    // ===================================================
    public void setColour(String colour) {
        this.colour = colour;
    }
}
