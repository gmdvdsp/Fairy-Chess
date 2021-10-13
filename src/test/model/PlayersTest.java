package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayersTest {
    String arbitraryColour = "white";
    String oppositeColour = "black";

    Pawn pawn;
    Pawn otherPawn;

    Players players;
    Square arbitraryFromSquare;
    Square arbitraryToSquare;

    @BeforeEach
    public void runBefore() {
        players = new Players();
        pawn = new Pawn(arbitraryColour);
        otherPawn = new Pawn(oppositeColour);
        arbitraryFromSquare = new Square(0, 0, null);
        arbitraryToSquare = new Square(0, 0, null);
    }

    @Test
    public void Player() {
        players = new Players();
    }

}
