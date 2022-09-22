package g52063.humbug.model.animal;

import g52063.humbug.model.Animal;
import g52063.humbug.model.Board;
import g52063.humbug.model.Direction;
import g52063.humbug.model.Position;
import g52063.humbug.model.SquareType;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * Bumbelbee, an animal with move
 */
public class Bumbelbee extends Animal {
/**
 * constructor of a position on board
 * @param positionOnBoard 
 */
    public Bumbelbee(Position positionOnBoard) {
        super(positionOnBoard);
    }
/**
 * default constructor
 */
    public Bumbelbee() {
    }

    /**
     * move of Bumbelbee
     *
     * @param board
     * @param direction
     * @param animals
     * @return destination
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        setPositionOnBoard(getPositionOnBoard().next(direction));
        Position destination = moveOneJumping(board, direction, animals);
        if (!board.isInside(destination)) {
            setPositionOnBoard(null);
            return null;
        }
        switch (board.getSquareType(destination)) {
            case STAR:
                setOnStar(true);
                board.getSquare(destination).setType(SquareType.GRASS);
                setPositionOnBoard(destination);
                return destination;
            case GRASS:
                setPositionOnBoard(destination);
                return destination;
            default:
                setPositionOnBoard(null);
                return null;
        }
    }

    /**
     * GetInitial
     *
     * @return inital from bumbelbee
     */
    @Override
    public String getInitial() {
        return "B";
    }

}
