package g52063.humbug.model.animal;

import g52063.humbug.model.Animal;
import g52063.humbug.model.Board;
import g52063.humbug.model.Direction;
import g52063.humbug.model.Position;
import g52063.humbug.model.SquareType;

/**
 *
 * @author Rémy
 */
/**
 * Butterfly, an animal with move
 */
public class Butterfly extends Animal {

    /**
     * constructor of a position on board
     *
     * @param positionOnBoard
     */
    public Butterfly(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * default constructor
     */
    public Butterfly() {
    }

    /**
     * move of Butterfly
     *
     * @param board
     * @param direction
     * @param animals
     * @return destination
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {

        setPositionOnBoard(getPositionOnBoard().next(direction).next(direction));
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
     * @return inital from butterfly
     */
    @Override
    public String getInitial() {
        return "P";
    }

}
