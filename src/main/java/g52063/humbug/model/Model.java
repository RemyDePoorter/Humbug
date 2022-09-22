package g52063.humbug.model;

/**
 *
 * @author Rémy
 */
/**
 * interface of Model
 */
public interface Model {

    Board getBoard();

    Animal[] getAnimals();

    int getRemainingMoves();
    
    void startLevel(int level);

    LevelStatus getLevelStatus();

    void move(Position position, Direction direction);
}
