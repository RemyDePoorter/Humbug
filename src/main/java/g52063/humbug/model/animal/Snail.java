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
 * Snail, an animal with move
 */
public class Snail extends Animal {

    /**
     *
     * constructor of a position on board
     *
     * @param positionOnBoard
     */
    public Snail(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * default constructor
     */
    public Snail() {
    }

    /**
     * move of snail
     *
     * @param board
     * @param direction
     * @param animals
     * @return destination
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        if (!board.isInside(getPositionOnBoard().next(direction))) {
            setPositionOnBoard(null);
            return null;
        }
        Position destination = super.moveOneCrawling(board, direction, animals);

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
     * @return inital from snail
     */
    @Override
    public String getInitial() {
        return "E";
    }
}
