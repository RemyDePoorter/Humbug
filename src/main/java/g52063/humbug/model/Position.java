package g52063.humbug.model;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * One Position had a row and a column
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructor of Position.
     *
     * @param row
     * @param column
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
/**
 * default constructor
 */
    public Position() {
        this.row = 0;
        this.column = 0;
    }

    /**
     * Simple getter of type.
     *
     * @return type of row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Simple getter of type.
     *
     * @return type of column
     */
    public int getColumn() {
        return this.column;
    }

    @Override
    /**
     * Simple hashCode
     */
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.row;
        hash = 29 * hash + this.column;
        return hash;
    }

    @Override
    /**
     * Simple toString return Position with row and column
     */
    public String toString() {
        return "(" + this.row + "," + this.column + ')';
    }

    /**
     * Simple equals, test if position A = position B
     *
     * @param o
     * @return true if it's the same position
     */
    @Override
    public boolean equals(Object o) {
        return (this.row == ((Position) o).row && this.column == ((Position) o).column);
    }

    /**
     * next
     *
     * @param nextPos
     * @return the position next to it in the past direction as a parameter
     */
    public Position next(Direction nextPos) {
        return new Position(this.row + nextPos.getRow(), this.column + nextPos.getColumn());
    }
}
