package g52063.humbug.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * give the level of the game
 *
 */
public class Level {

    public static Level getLevel(int nbLevel) throws IOException {
        var objectMapper = new ObjectMapper();
        var inputStream = Level.class.getResourceAsStream("/data/level-" + nbLevel + ".json");
        var level = objectMapper.readValue(inputStream, Level.class);
        return new Level(level.getBoard(), level.getAnimals(), level.getnMoves());
    }
/**
 * readLevel
 * @param n
 * @return level
 */
    private static Level readLevel(int n) {
        try {
            var objectMapper = new ObjectMapper();
            var inputStream = Level.class.getResourceAsStream(
                    "/data/level-" + n + ".json");
            var level = objectMapper.readValue(inputStream, Level.class);
            return level;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    private Board board;
    private Animal[] animals;
    private int nMoves;

    /**
     * Constructor of level
     *
     * @param board
     * @param animals
     * @param nMoves
     */
    public Level(Board board, Animal[] animals, int nMoves) {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * default constructor
     */
    public Level() {
    }

    /**
     * getNbBoard
     *
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * getAnimals
     *
     * @return animals
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * getnMoves
     *
     * @return nMoves
     */
    public int getnMoves() {
        return nMoves;
    }

}
