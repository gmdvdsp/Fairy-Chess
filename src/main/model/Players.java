package model;

// Represents a player that can propose moves, and move pieces.
public class Players {
    Game game;
    boolean hasSelected;

    // MODIFIES: this
    // EFFECTS: Makes a new players which are playing a game.
    public Players() {
        this.game = new Game();
        hasSelected = false;
    }

    // Methods:
    // ===================================================
    // EFFECTS: Returns true if it's legal for a player to move a piece to another square. Returns false otherwise.
    public boolean proposeMove(Square from, Square to) {
        if (proposeDestinationSquare(to) && isSelectedSquareValid(from)) {
            return game.isLegalMove(from, to);
        } else {
            return false;
        }
    }

    // EFFECTS: Moves a piece from one square to another.
    public void makeMove(Square from, Square to) {
        game.processMove(from, to);
    }

    // EFFECTS: Returns true only if both that square is on the board and the player currently has a piece on that
    // square.
    private boolean isSelectedSquareValid(Square proposed) {
        return game.getBoard().isSquareOnBoard(proposed) && game.controlsSquare(proposed);
    }

    // EFFECTS: Returns true only if both that square is on the board.
    private boolean proposeDestinationSquare(Square proposed) {
        return game.getBoard().isSquareOnBoard(proposed);
    }

    // Simple Getters:
    // ===================================================
    public Game getGame() {
        return game;
    }

    public boolean getHasSelected() {
        return hasSelected;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setHasSelected(boolean hasSelected) {
        this.hasSelected = hasSelected;
    }
}
