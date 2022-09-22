package g52063.humbug.model;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * assemble the Game and build the facade
 */
public class Game implements Model {

    private Board board;
    private Animal[] animals;
    private int remainingMoves;
    private int currentLevel;

    /**
     * Constructor of board lvl one
     */
    public Game() {
        this.board = new Board(new Square[3][3]);
        currentLevel = 1;
    }

    /**
     * startLevel1
     *
     * @param level
     */
    @Override
    public void startLevel(int level) {
        try {
            Level currentLevel = Level.getLevel(level);
            this.animals = currentLevel.getAnimals();
            this.board = currentLevel.getBoard();
            this.remainingMoves = currentLevel.getnMoves();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * getLevelStatus
     *
     * @return status of level
     */
    @Override
    public LevelStatus getLevelStatus() {
        try {
            if (this.remainingMoves == Level.getLevel(currentLevel).getnMoves()) {
                return LevelStatus.NOT_STARTED;
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        for (Animal animal : this.animals) {
            if (animal.getPositionOnBoard() == null) {
                return LevelStatus.FAIL;
            }
        }
        for (Animal animal : this.animals) {
            if (!animal.isOnStar()) {
                return LevelStatus.IN_PROGRESS;
            }
        }
        return LevelStatus.WIN;
    }

    /**
     * move one animal
     *
     * @param position
     * @param direction
     */
    @Override
    public void move(Position position, Direction direction) {
        Animal animalDeplacer = null;
        if (position == null || direction == null) {
            throw new IllegalArgumentException("la position ou la direction est nulle");
        }
        if (getLevelStatus() == getLevelStatus().FAIL
                || getLevelStatus() == getLevelStatus().WIN) {
            throw new IllegalStateException("Le déplacement est impossible");
        }
        for (Animal anim : this.animals) {
            if (anim.getPositionOnBoard().getColumn() == position.getColumn()
                    && anim.getPositionOnBoard().getRow() == position.getRow()) {
                animalDeplacer = anim;
            }
        }
        animalDeplacer.move(this.board, direction, animals);
        remainingMoves--;
    }

    /**
     * Simple getter of type
     *
     * @return board
     */
    @Override
    public Board getBoard() {
        return this.board;
    }

    /**
     * Simple getter of type
     *
     * @return animals;
     */
    @Override
    public Animal[] getAnimals() {
        return this.animals;
    }

    /**
     * Simple getter of type
     *
     * @return remainingMoves;
     */
    @Override
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * Simple getter of type
     *
     * @return currentLevel;
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Simple setter of type
     *
     * @param currentLevel
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

}
