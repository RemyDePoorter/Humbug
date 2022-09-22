package g52063.humbug.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import g52063.humbug.model.animal.Bumbelbee;
import g52063.humbug.model.animal.Grasshopper;
import g52063.humbug.model.animal.Ladybird;
import g52063.humbug.model.animal.Snail;
import g52063.humbug.model.animal.Spider;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * an animal know where he is on the board
 *
 */
@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = Bumbelbee.class),
    @Type(value = Grasshopper.class),
    @Type(value = Ladybird.class),
    @Type(value = Snail.class),
    @Type(value = Spider.class),})
public abstract class Animal {

    private Position positionOnBoard;
    private boolean onStar;

    /**
     * Constructor of Animal on Position
     *
     * @param positionOnBoard
     */
    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        onStar = false;
    }

    /**
     * default constructor
     */
    public Animal() {
    }

    /**
     * isOnStar
     *
     * @return onStar
     */
    public boolean isOnStar() {
        return this.onStar;
    }

    /**
     * Simple setter of type.
     *
     * @param onStar
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Simple getter of type.
     *
     * @return positionOnboard
     */
    public Position getPositionOnBoard() {
        return this.positionOnBoard;
    }

    /**
     * Simple setter of type.
     *
     * @param positionOnBoard
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * move, change animal position
     *
     * @param board
     * @param direction
     * @param animals
     * @return
     */
    public abstract Position move(Board board,
            Direction direction, Animal... animals);

    /**
     *
     * @return inital from animal
     */
    public abstract String getInitial();

    protected Position moveOneCrawling(Board board, Direction direction, Animal... animals) {
        Position destination = getPositionOnBoard();
        if (!board.isInside(destination.next(direction))) {
            setPositionOnBoard(null);
            return destination.next(direction);
        }

        if (board.getSquare(destination.next(direction)).hasWall(direction.opposite())) {
            return getPositionOnBoard();
        } else if (board.getSquare(destination.next(direction)).hasWall(direction)) {
            return destination.next(direction);
        }
        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().getRow()
                    == destination.next(direction).getRow()
                    && animal.getPositionOnBoard().getColumn()
                    == destination.next(direction).getColumn()) {
                setPositionOnBoard(destination);
                if (board.getSquareType(destination) == SquareType.STAR) {
                    board.getSquare(destination).setType(SquareType.GRASS);
                    setOnStar(true);
                }
                return getPositionOnBoard();
            }
        }
        destination = destination.next(direction);

        return destination;
    }

    /**
     * move one jumping
     *
     * @param board
     * @param direction
     * @param animals
     * @return destination
     */
    protected Position moveOneJumping(Board board, Direction direction, Animal... animals) {
        Position destination = getPositionOnBoard();
        if (!board.isInside(destination.next(direction))) {
            setPositionOnBoard(null);
            return destination.next(direction);
        }

        if (board.getSquare(destination.next(direction)).hasWall(direction.opposite())
                || board.getSquare(destination.next(direction)).hasWall(direction)) {
            return destination.next(direction);
        }
        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().getRow()
                    == destination.next(direction).getRow()
                    && animal.getPositionOnBoard().getColumn()
                    == destination.next(direction).getColumn()) {
                setPositionOnBoard(destination.next(direction));
                return moveOneJumping(board, direction, animals);
            }
        }

        return destination.next(direction);
    }
}
