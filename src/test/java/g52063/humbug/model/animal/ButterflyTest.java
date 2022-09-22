/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g52063.humbug.model.animal;

import g52063.humbug.model.Animal;
import g52063.humbug.model.Board;
import g52063.humbug.model.Direction;
import g52063.humbug.model.Position;
import g52063.humbug.model.Square;
import static g52063.humbug.model.SquareType.GRASS;
import static g52063.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Rémy
 */
public class ButterflyTest {

    private Board board;
    private Animal[] animals;

    public ButterflyTest() {
    }

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR), new Square(GRASS)},
            {null, null, new Square(STAR), new Square(GRASS)}
        });
        animals = new Animal[]{
            new Butterfly(new Position(0, 0)),
            new Snail(new Position(2, 2))
        };
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove() {
        System.out.println("move and fall");
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_tootheranimal() {
        System.out.println("move to other animal");
        animals[1] = new Snail(new Position(0, 1));
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Butterfly instance = (Butterfly) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_next_notinside() {
        System.out.println("move next case null and fall");
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_passOnStar() {
        System.out.println("move and pass on star without win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Butterfly(new Position(0, 0)),};
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertFalse(animals[0].isOnStar());
        assertFalse(board.getSquareType(new Position(0, 1)) == GRASS);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_nextOnStar() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(STAR)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMoveWithWall() {
        board.getSquare(new Position(0, 1)).setWestWall(true);

        System.out.println("move_WithWallWest");
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMoveWithWallOtherSide() {
        board.getSquare(new Position(0, 1)).setEastWall(true);

        System.out.println("move_WithWallEast");
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        System.out.println(result);
        assertEquals(expResult, result);
    }

}
