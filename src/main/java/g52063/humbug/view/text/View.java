package g52063.humbug.view.text;

import g52063.humbug.model.*;
import java.util.Scanner;

/**
 * @author g52063 (Rémy De Poorter) <52063@etu.he2b.be>
 */
/**
 * InterfaceView, print board, read user answers
 */
public class View implements InterfaceView {

    /**
     * addGrass
     *
     * @param tab
     * @param element
     * @return tab
     */
    public static String[][] addGrass(String[][] tab, String element) {
        String[] tab1 = {TerminalColor.BG_GREEN_FG_WHITE + "_", "_", "_", "_" + TerminalColor.DEFAULT};
        String[] tab2 = {TerminalColor.BG_GREEN_FG_WHITE + " ", " ", " ", "|" + TerminalColor.DEFAULT};
        String[] tab3 = {TerminalColor.BG_GREEN_FG_WHITE + " ", element, " ", "|" + TerminalColor.DEFAULT};
        for (int i = 0; i < 4; i++) {
            tab[0][i] = tab1[i];
            tab[1][i] = tab2[i];
            tab[2][i] = tab3[i];
            tab[3][i] = tab2[i];
        }
        return tab;
    }

    /**
     * add void square
     *
     * @param tab
     * @return tab
     */
    public static String[][] addVoid(String[][] tab) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tab[i][j] = " ";
            }
        }
        tab[0][0] = TerminalColor.DEFAULT + " ";
        return tab;
    }

    /**
     * Constructor of view
     */
    public View() {
    }

    /**
     * display board, print board
     *
     * @param board
     * @param animals
     */
    @Override
    public void displayBoard(Board board, Animal... animals) {
        int size = board.getNbColumn();
        String[][][][] tab = new String[size][size][4][4];
        tab = initBoard(size, tab, board, animals);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board.isInside(new Position(i, j - 1)) && board.isInside(new Position(i, j))) {
                    System.out.print(TerminalColor.BG_GREEN_FG_WHITE + "_" + TerminalColor.DEFAULT);
                }
                for (int k = 0; k < 4; k++) {
                    if (board.isInside(new Position(i - 1, j)) && !board.isInside(new Position(i, j))) {
                        System.out.print(TerminalColor.BG_GREEN_FG_WHITE + "_" + TerminalColor.DEFAULT);
                    } else {
                        System.out.print(tab[i][j][0][k]);
                    }
                }
            }
            System.out.println("");
            for (int j = 0; j < size; j++) {
                if (!board.isInside(new Position(i, j - 1)) && board.isInside(new Position(i, j))) {
                    System.out.print(TerminalColor.BG_GREEN_FG_WHITE + "|" + TerminalColor.DEFAULT);
                }
                for (int k = 0; k < 4; k++) {
                    System.out.print(tab[i][j][1][k]);
                }
            }
            System.out.println("");
            for (int j = 0; j < size; j++) {
                if (!board.isInside(new Position(i, j - 1)) && board.isInside(new Position(i, j))) {
                    System.out.print(TerminalColor.BG_GREEN_FG_WHITE + "|" + TerminalColor.DEFAULT);
                }
                for (int k = 0; k < 4; k++) {
                    System.out.print(tab[i][j][2][k]);
                }
            }
            System.out.println("");
            for (int j = 0; j < size; j++) {
                if (!board.isInside(new Position(i, j - 1)) && board.isInside(new Position(i, j))) {
                    System.out.print(TerminalColor.BG_GREEN_FG_WHITE + "|" + TerminalColor.DEFAULT);
                }
                for (int k = 0; k < 4; k++) {

                    System.out.print(tab[i][j][3][k]);
                }

            }
            System.out.println("");
        }
    }

    /**
     * initialize board
     *
     * @param size
     * @param tab
     * @param board
     * @param animals
     * @return
     */
    public String[][][][] initBoard(int size, String[][][][] tab, Board board, Animal... animals) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                try {
                    switch (board.getSquareType(new Position(x, y))) {
                        case GRASS:
                            for (Animal animal : animals) {
                                if ((x == animal.getPositionOnBoard().getRow())
                                        && (y == animal.getPositionOnBoard().getColumn())
                                        && (!animal.isOnStar())) {
                                    tab[x][y] = addGrass(tab[x][y], animal.getInitial());
                                    break;
                                } else {
                                    tab[x][y] = addGrass(tab[x][y], " ");
                                }
                            }
                            break;
                        case STAR:
                            tab[x][y] = addGrass(tab[x][y], TerminalColor.RED
                                    + "*" + TerminalColor.DEFAULT
                                    + TerminalColor.BG_GREEN_FG_WHITE);
                            break;
                        default:
                            tab[x][y] = addVoid(tab[x][y]);
                            ;
                    }
                    Direction tabDirection[] = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
                    for (Direction direction : tabDirection) {
                        if (board.getSquare(new Position(x, y)).hasWall(direction)) {
                            tab[x][y] = addWall(direction, tab[x][y]);
                        }
                    }
                } catch (Exception e) {
                    tab[x][y] = addVoid(tab[x][y]);
                }
            }
        }
        return tab;
    }

    @Override
    /**
     * print message error in red
     */
    public void displayError(String message) {
        System.out.println(TerminalColor.RED + message + TerminalColor.DEFAULT);
    }

    /**
     * print message
     *
     * @param message
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * ask to user one position if position aren't ok, ask again one position
     *
     * @return position
     */
    @Override
    public Position askPosition() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Veuillez entrez une position au format x y");
        String pos = keyboard.nextLine();
        String[] splittext = pos.split(" ", 2);
        try {
            Position position = new Position(Integer.parseInt(splittext[0]),
                    Integer.parseInt(splittext[1]));
            if (position.getRow() >= 0 && position.getColumn() >= 0) {
                return position;
            } else {
                System.out.println("position non valide");
                return askPosition();
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            return askPosition();
        }
    }

    /**
     * ask one direction to user, if direction aren't ok, ask again one
     * direction
     *
     * @return
     */
    @Override
    public Direction askDirection() {
        System.out.println("Veuillez entrer une direction (NORTH, SOUTH, EAST, WEAST)");
        Scanner keyboard = new Scanner(System.in);
        String direction = keyboard.nextLine();
        switch (direction.toUpperCase()) {
            case "NORTH":
                return Direction.NORTH;

            case "SOUTH":
                return Direction.SOUTH;

            case "EAST":
                return Direction.EAST;

            case "WEST":
                return Direction.WEST;
            case "N":
                return Direction.NORTH;

            case "S":
                return Direction.SOUTH;

            case "E":
                return Direction.EAST;

            case "W":
                return Direction.WEST;
            default:
                return askDirection();
        }

    }

    /**
     * addWall
     *
     * @param dir
     * @param string
     * @return tab
     */
    public String[][] addWall(Direction dir, String[][] string) {
        switch (dir) {
            case NORTH:
                string[1][0] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[1][1] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[1][2] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                break;
            case SOUTH:
                string[3][0] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[3][1] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[3][2] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                break;
            case EAST:
                string[1][0] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[2][0] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[3][0] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                break;
            case WEST:
                string[1][2] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[2][2] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                string[3][2] = TerminalColor.BG_RED_FG_WHITE + " " + TerminalColor.BG_GREEN_FG_WHITE;
                break;
        }
        return string;
    }

    /**
     * display nb of remaining moves
     *
     * @param nMoves
     */
    public void displayRemainingMoves(int nMoves) {
        System.out.println("Il reste " + nMoves + " déplacements possibles");
    }

}
