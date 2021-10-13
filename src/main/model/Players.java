package model;

public class Players {
    Game game;
    String playerColour;

    public Players() {
        game = new Game();
        playerColour = "white";
    }

    // METHODS:

    public void changePlayerColour() {
        if (playerColour.equals("white")) {
            playerColour = "black";
        } else {
            playerColour = "white";
        }
    }

    public boolean proposeSquare(Square proposed) {
        return proposed.isSquareOnBoard();
    }

    public boolean proposeMove(Square from, Square to) {
        if (game.controlsSquare(from, playerColour) && game.isLegalMove(from, to)) {
            game.makeMove(from, to);
            changePlayerColour();
            return true;
        } else {
            return false;
        }
    }

    // SETTERS AND GETTERS:

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayerColour(String colour) {
        playerColour = colour;
    }

    public String getPlayerColour() {
        return playerColour;
    }

    public Game getGame() {
        return game;
    }
}
