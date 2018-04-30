package connectfour;

/**
 * 12252511 Zhesi NING
 *
 */
import java.util.Random;

public class ComputerPlayer extends Player {

    private final Random random = new Random();
    private final int effortLevel;

    public ComputerPlayer(int effortLevel) {
        this.effortLevel = effortLevel;
    }

    public char randomTrial(Board board) {
        char[] rootMoves = board.validMoves();
        double[][] ratios = new double[rootMoves.length][2];

        for (int i = 0; i < effortLevel; i++) {
            GameBoard currentBoard = board.getBoardCopy();
            int rootMove = random.nextInt(rootMoves.length);

            boolean isThisPlayer = true;
            char[] currentMoveSet = rootMoves;
            int currentMove = rootMove;
            while (true) {
                Character winMove = getPossibleWinningMove(currentBoard);
                if (winMove == null) {
                    winMove = currentMoveSet[currentMove];
                }
                currentBoard.move(winMove, isThisPlayer ? symbol : oppoSymbol);

                if (currentBoard.isGameOver()) {
                    break;
                }
                isThisPlayer = !isThisPlayer;
                currentMoveSet = currentBoard.validMoves();
                currentMove = random.nextInt(currentMoveSet.length);
            }
            if (currentBoard.getWinner() != null && currentBoard.getWinner() == symbol) {
                ratios[rootMove][0]++;
            }
            ratios[rootMove][1]++;
        }

        int bestChoiceIndex = 0;
        double ratio = 0;
        for (int i = 0; i < ratios.length; i++) {
            double r = ratios[i][0] / ratios[i][1];

            if (r > ratio) {
                ratio = r;

                bestChoiceIndex = i;
            }
        }
        return rootMoves[bestChoiceIndex];
    }

    public Character getPossibleWinningMove(Board board) {
        for (char move : board.validMoves()) {
            GameBoard currBoard = board.getBoardCopy();
            currBoard.move(move, symbol);
            if (currBoard.isGameOver()) {
                return move;
            }
        }
        return null;
    }

    @Override
    public char getMove(Board board) {

        Character winningMove = getPossibleWinningMove(board);
        if (winningMove == null) {
            winningMove = randomTrial(board);
        }
        return winningMove;
    }

    @Override
    public String toString() {
        return String.format("%d", effortLevel);
    }

}
