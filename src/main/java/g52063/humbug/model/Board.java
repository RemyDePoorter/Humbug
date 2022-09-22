package g52063.humbug.model;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * Board is a array of squares if not squares return null
 */
public class Board {

    private Square[][] squares;

    /**
     * Constructor of board
     *
     * @param squares
     */
    public Board(Square[][] squares) {
        this.squares = squares;
    }

    /**
     * default constructor
     */
    public Board() {
    }

    /**
     * Simple setter of Squares
     *
     * @param squares
     */
    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    /**
     * Test if position is inside of board (position = GRASS of STAR)
     *
     * @param position1
     * @throws IllegalArgumentException if the position is null
     * @return true if position is inside the board else return false
     */
    public boolean isInside(Position position1) {
        if (position1 == null) {
            throw new IllegalArgumentException("la position est null");
        }
        try {
            return ((position1.getRow() <= this.squares.length
                    && position1.getRow() >= 0)
                    && (position1.getColumn() <= this.squares[0].length
                    && position1.getColumn() >= 0)
                    && (getSquareType(position1) == SquareType.GRASS
                    || getSquareType(position1) == SquareType.STAR));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * getSquareType
     *
     * @param position1
     * @throws IllegalArgumentException if the position is out of the board
     * @return the type of a square whose position is passed as a parameter
     */
    public SquareType getSquareType(Position position1) {

        if (position1 == null) {
            throw new IllegalArgumentException("la position est en dehors du plateau");
        }
        try {
            SquareType object = this.squares[position1.getRow()][position1.getColumn()].getType();
            return object;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);

        }

    }

    /**
     * getNbRow
     *
     * @return number of row from board
     */
    public int getNbRow() {
        return this.squares.length;
    }

    /**
     * getNbColumn
     *
     * @return number of Column from board
     */
    public int getNbColumn() {
        return this.squares[0].length;
    }

    /**
     * getSquare of position
     *
     * @param position
     * @return square
     */
    public Square getSquare(Position position) {
        return this.squares[position.getRow()][position.getColumn()];
    }

    /**
     * getSquare
     *
     * @return square
     */
    public Square[][] getSquares() {
        return squares;
    }
}
