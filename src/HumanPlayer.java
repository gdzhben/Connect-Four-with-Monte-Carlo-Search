package connectfour;
/**12252511
 * Zhesi NING
 **/
import static java.lang.System.out;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private final Scanner scanner;

    public HumanPlayer() {
        scanner = new Scanner(System.in);
    }

    @Override
    public char getMove(Board board) {
        out.println("pls enter your move: (A B C...)");
        char move;
        while (true) {
            String nextLine = scanner.nextLine();
            if (nextLine.length() != 1) {
                out.println("pls enter your column:");
            } else {
                move = nextLine.charAt(0);
                if (board.isValidMove(move)) {
                    break;
                }
                out.println(String.format("%s is not valid", move));
            }
        }
        return move;
    }

    @Override
    public String toString() {
        return String.format("Human");
    }
}
