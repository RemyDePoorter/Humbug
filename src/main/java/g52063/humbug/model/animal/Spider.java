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
 * Spider, an animal with move
 */
public class Spider extends Animal {

    /**
     * constructor of a position on board
     *
     * @param positionOnBoard
     */
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * default constructor
     */
    public Spider() {
    }

    /**
     * move of Spider
     *
     * @param board
     * @param direction
     * @param animals
     * @return destination
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position start, destination = getPositionOnBoard();
        do {
            if (!board.isInside(destination.next(direction))) {
                setPositionOnBoard(null);
                return null;
            }
            start = destination;
            setPositionOnBoard(destination);
            destination = super.moveOneCrawling(board, direction, animals);

        } while ((start.next(direction).getRow() == destination.getRow()
                && start.next(direction).getColumn() == destination.getColumn())
                && !board.getSquare(destination).hasWall(direction));

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
     * @return inital from spider
     */
    @Override
    public String getInitial() {
        return "A";
    }
}
