package model;

public class Players {
    String playerColour;
    Game game;

    public Players(String playerColour) {
        this.playerColour = playerColour;
        this.game = new Game();
    }

    // Methods:
    // ===================================================
    public boolean proposeFromSquare(Square proposed) {
        return proposed.isSquareOnBoard() && game.controlsSquare(proposed);
    }

    public boolean proposeToSquare(Square proposed) {
        return proposed.isSquareOnBoard();
    }

    public void proposeMove(Square from, Square to) {
        game.processMove(from, to);
    }

    public Game getGame() {
        return game;
    }
}
