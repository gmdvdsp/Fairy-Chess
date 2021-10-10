package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    String arbitraryColour = "white";
    String oppositeColour = "black";

    Pawn pawn;
    Pawn otherPawn;

    Player player;
    Square arbitraryFromSquare;
    Square arbitraryToSquare;

    @BeforeEach
    public void runBefore() {
        player = new Player();
        pawn = new Pawn(arbitraryColour, false);
        otherPawn = new Pawn(oppositeColour, false);
        arbitraryFromSquare = new Square(0, 0);
        arbitraryToSquare = new Square(0, 0);
    }

    @Test
    public void Player() {
        player = new Player();
    }

    @Test
    public void testProposeMovePawn() {
        player = new Player();
        if (player.getGame().isLegalMove(arbitraryFromSquare, arbitraryToSquare)) {
            assertTrue(player.proposeMove(arbitraryFromSquare, arbitraryToSquare));
        } else {
            assertFalse(player.proposeMove(arbitraryFromSquare, arbitraryToSquare));
        }
    }

    @Test
    public void testChangeTurn() {
        player = new Player();
        player.setPlayerColour(arbitraryColour);
        player.changePlayerColour();
        assertEquals(player.getPlayerColour(), oppositeColour);
    }

}
