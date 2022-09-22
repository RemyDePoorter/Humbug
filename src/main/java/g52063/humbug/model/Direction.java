package g52063.humbug.model;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * Direction represents the direction of a move Direction are NORTH, SOUTH, EAST
 * or WEST
 */
public enum Direction {
    NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

    private final int deltaRow;
    private final int deltaColumn;

    /**
     * Constructor of Position.
     *
     * @param type int, row and column
     */
    private Direction(int row, int column) {
        this.deltaRow = row;
        this.deltaColumn = column;
    }

    /**
     * opposite direction
     *
     * @return opposite direction of direction
     */
    public Direction opposite() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                return NORTH;
        }

    }

    /**
     * Simple getter of type.
     *
     * @return type of deltaRow
     */
    public int getRow() {
        return this.deltaRow;
    }

    /**
     * Simple getter of type.
     *
     * @return type of deltaColumn
     */
    public int getColumn() {
        return this.deltaColumn;
    }
}
