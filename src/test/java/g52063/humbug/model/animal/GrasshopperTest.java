/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g52063.humbug.model.animal;

import g52063.humbug.model.*;
import static g52063.humbug.model.SquareType.GRASS;
import static g52063.humbug.model.SquareType.STAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rémy
 */
public class GrasshopperTest {

    /**
     *
     * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
     */
    private Board board;
    private Animal[] animals;

    public GrasshopperTest() {
    }

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Grasshopper(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Grasshopper instance = (Grasshopper) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("move next on star");
        Grasshopper instance = (Grasshopper) animals[1];
        Position expResult = new Position(2, 2);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Grasshopper.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("move next case null");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper to west with wall.
     */
    @Test
    public void testMoveWithWall() {
        board.getSquare(new Position(0, 1)).setWestWall(true);
        System.out.println("move_WithWallWest");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Grasshopper to east with wall.
     */
    @Test
    public void testMoveWithWallOtherSide() {
        board.getSquare(new Position(0, 1)).setEastWall(true);
        System.out.println("move_WithWallEast");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
}
