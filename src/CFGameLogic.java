package connectfour;
/**
 * 12252511 Zhesi NING
 *
 */
import java.util.Random;
import static java.lang.String.format;

public class CFGameLogic {

    public static final int MAX_NO_PLAYER = 2;
    
    private final Player[] playerSet = new Player[MAX_NO_PLAYER];
    
    private final char[] symbol = new char[]{
        
        GameBoard.XS_CELL,
        GameBoard.O_CELL
    };
    private final GameBoard board;
    private final Random random = new Random();

    public CFGameLogic(Player player1, Player player2, GameBoard board) {
        this.playerSet[0] = player1;
        player1.setSymbol(symbol[0]);
        player1.setOppoSymbol(symbol[1]);

        this.playerSet[1] = player2;
        player2.setSymbol(symbol[1]);
        player2.setOppoSymbol(symbol[0]);

        this.board = board;
        
    }

    public Player play(boolean isBoard) {
        board.reset();

        int randomInt;

        randomInt = random.nextInt(MAX_NO_PLAYER);
        
        if (isBoard) {
        } else {
            System.out.println(format("%s (%s) make the first move. ",
                    playerSet[randomInt], symbol[randomInt]));
        }

        while (!board.isGameOver()) {
            Player curr = playerSet[randomInt];
            if (isBoard) {
            } else {
                System.out.println(board.toString());
            }

            if (isBoard) {
            } else {
                System.out.println(format("%s (%s) 's move. ", curr.toString(), 
                        symbol[randomInt]));
            }

            char move = curr.getMove(board);
            board.move(move, symbol[randomInt]);
            randomInt = ++randomInt % MAX_NO_PLAYER;
        }

        if (isBoard) {
        } else {
            System.out.println(board.toString());
        }
        Player winner = null;
        if (board.getWinner() == null) {

            if (isBoard) {
            } else {
                System.out.println("Draw.");
            }
        } else if (board.getWinner() == symbol[0]) {
            winner = playerSet[0];
            if (isBoard) {
            } else {
                System.out.println(winner.toString() + " Win.");
            }

        } else if (board.getWinner() == symbol[1]) {
            winner = playerSet[1];
            if (isBoard) {
            } else {
                System.out.println(winner.toString() + " Win.");
            }

        }

        return winner;
    }
}
