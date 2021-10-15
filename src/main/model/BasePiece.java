package model;

// Represents a chess piece that has a colour.
public abstract class BasePiece {
    protected String colour;

    // MODIFIES: this
    // EFFECTS: Makes some piece with some colour.
    public BasePiece(String colour) {
        this.colour = colour;
    }

    // Methods:
    // ===================================================
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

    // Simple Setters:
    // ===================================================
    public void setColour(String colour) {
        this.colour = colour;
    }
}
