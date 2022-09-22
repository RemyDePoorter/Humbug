package g52063.humbug.view.text;

import g52063.humbug.model.*;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * Interface of View
 */
public interface InterfaceView {

    public void displayBoard(Board board, Animal... animals);

    public Position askPosition();

    public Direction askDirection();

    public void displayError(String message);
}
