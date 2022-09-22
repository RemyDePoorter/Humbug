package g52063.humbug.model;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * Square on the board. A square has a type grass or star and it’s all. square
 * doesn’t know where it is on the board.
 */
public class Square {

    boolean northWall;
    boolean southWall;
    boolean westWall;
    boolean eastWall;
    private SquareType type;

    /**
     * Constructor of Square on board.
     *
     * @param type Square is grass or star
     */
    public Square(SquareType type) {
        northWall = false;
        southWall = false;
        westWall = false;
        eastWall = false;
        this.type = type;
    }

    /**
     * default constructor
     */
    public Square() {
    }

    /**
     * Simple setter of type.
     *
     * @param northWall
     */
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    /**
     * Simple setter of type.
     *
     * @param southWall
     */
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    /**
     * Simple setter of type.
     *
     * @param westWall
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * Simple setter of type.
     *
     * @param eastWall
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }

    /**
     * Simple getter of type.
     *
     * @return type of Square
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Simple setter of type.
     *
     * @param type
     */
    public void setType(SquareType type) {
        this.type = type;
    }

    /**
     * test if there is a wall in the received direction
     *
     * @param direction
     * @return
     */
    public boolean hasWall(Direction direction) {
        switch (direction) {
            case NORTH:
                return northWall;
            case SOUTH:
                return southWall;
            case EAST:
                return eastWall;
            case WEST:
                return westWall;
            default:
                return false;
        }
    }

}
