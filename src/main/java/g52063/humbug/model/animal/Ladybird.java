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
 * Ladybird, an animal with move
 */
public class Ladybird extends Animal {

    /**
     * constructor of a position on board
     *
     * @param positionOnBoard
     */
    public Ladybird(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * default constructor
     */
    public Ladybird() {
    }

    /**
     * move of Ladybird
     *
     * @param board
     * @param direction
     * @param animals
     * @return destination
     */
    @Override

    public Position move(Board board, Direction direction, Animal... animals) {
        Position start = getPositionOnBoard();
        Position destination = super.moveOneCrawling(board, direction, animals);
        if (!board.isInside(destination)) {
            setPositionOnBoard(null);
            return null;
        }
        if ((start.next(direction).getRow() == destination.getRow()
                && start.next(direction).getColumn() == destination.getColumn())
                && !board.getSquare(destination).hasWall(direction)) {

            setPositionOnBoard(destination);

            destination = super.moveOneCrawling(board, direction, animals);

        }
        switch (board.getSquareType(destination)) {
            case STAR:
                setOnStar(true);
                board.getSquare(destination).setType(SquareType.GRASS);
                setPositionOnBoard(null);
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
     * @return inital from ladybird
     */
    @Override
    public String getInitial() {
        return "C";
    }

}
