package model;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static java.lang.Math.abs;

import static model.Board.*;
import static model.Game.SQUARES_ON_COLUMN;
import static model.Game.SQUARES_ON_ROW;
import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    // Arbitrary value of isEmpty
    boolean arbitraryIsEmpty = true;

    Square square;

    @BeforeEach
    public void runBefore() {
        square = new Square(0, 0, null);
    }

    // Tests for setters and getters.
    @Test
    public void testSetXGetX() {
        for (int x = 0; x < SQUARES_ON_ROW; x++) {
            square.setX(x);
            assertEquals(square.getX(), x);
        }
    }

    @Test
    public void testSetYGetY() {
        for (int y = 0; y < SQUARES_ON_COLUMN; y++) {
            square.setY(y);
            assertEquals(square.getY(), y);
        }
    }

    /*
    @Test
    public void testSetIsEmptyGetIsEmpty() {
        square.setIsEmpty(arbitraryIsEmpty);
        assertTrue(square.getIsEmpty());
    }
     */
}
