package g52063.humbug.controller;

import g52063.humbug.model.*;
import g52063.humbug.view.text.View;

/**
 *
 * @author Rémy
 */
public class Controller {

    private final Game game;
    private final View view;

    /**
     * Constructor of Controller
     *
     */
    public Controller() {
        this.game = new Game();
        this.view = new View();
        startGame(1);
    }

    /**
     * simple getter
     *
     * @return getRemainingMoves
     */
    public int getRemainingMoves() {
        return game.getRemainingMoves();
    }

    /**
     * playGame
     */
    public void playGame() {
        game.startLevel(game.getCurrentLevel());
        do {
            view.displayBoard(game.getBoard(), game.getAnimals());
            view.displayRemainingMoves(game.getRemainingMoves());
            Position position = view.askPosition();
            Direction direction = view.askDirection();
            try {
                game.move(position, direction);
            } catch (Exception e) {
                view.displayError("une erreur est survenue : " + e);
            }
        } while (game.getLevelStatus() == game.getLevelStatus().IN_PROGRESS
                || game.getLevelStatus() == game.getLevelStatus().NOT_STARTED);
        if (game.getLevelStatus() == LevelStatus.WIN) {
            view.displayBoard(game.getBoard(), game.getAnimals());
            view.displayMessage("Bravo vous avez réussi le level " + game.getCurrentLevel());
            game.setCurrentLevel(game.getCurrentLevel() + 1);
        } else {
            view.displayMessage("Un animal est tombé dans le vide, vous avez perdu.");
        }
        if (game.getCurrentLevel() > 50) {
            view.displayMessage("Bravo vous avez réussi le dernier niveau.");
        } else {
            playGame();
        }
    }

    /**
     * startGame
     *
     * @param nLevel
     */
    public void startGame(int nLevel) {
        game.setCurrentLevel(nLevel);
        playGame();
    }
}
