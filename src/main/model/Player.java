package model;

public class Player {
    Game game;
    String playerColour;

    public Player() {
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

    public boolean proposeMove(Square fromSquare, Square toSquare) {
        if (toSquare.isSquareOnBoard() && game.isLegalMove(fromSquare, toSquare)) {
            game.makeMove(fromSquare, toSquare);
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
