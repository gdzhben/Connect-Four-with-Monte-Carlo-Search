package connectfour;
/**
 * 12252511 Zhesi NING
 *
 */
import java.util.Arrays;
import java.util.Collections;

public class GameBoard implements Board {

    private char[] board = null;
    private int numOfCols = 0;
    private int maxHeight = 0;
    private int noOfMovesLeft = 0;
    public static final char XS_CELL = 'X';
    public static final char XS_IGNORE_CELL = 'x';
    public static final char O_CELL = 'O';
    public static final char O_IGNORE_CELL = 'o';
    public static final char START_COL = 'A';
    public static final char UNPLAYABLE_CELL = '#';
    public static final char NULL_CELL = ' ';
    public static final char NULL_IGNORE_CELL = '*';
    public static final int MIN_COLUMN = 6;
    public static final int MAX_COLUMN = 11;
    public static final int NUM_IGNORE_CELLS = 2;

    private GameBoard(char[] board, int numOfCols, int maxHeight,
            int numOfMoves) {
        this.board = board;
        this.numOfCols = numOfCols;
        this.maxHeight = maxHeight;
        this.noOfMovesLeft = numOfMoves;
    }

    public void move(char col, char XorO) {
        int colNo = (col - START_COL);
        for (int j = 0; j < maxHeight; j++) {
            int index = pos(colNo, j);
            if (board[index] == NULL_CELL) {
                board[index] = XorO;
                noOfMovesLeft--;
                break;
            } else if (board[index] == NULL_IGNORE_CELL) {
                board[index] = Character.toLowerCase(XorO);
                noOfMovesLeft--;
                break;
            }
        }
    }

    @Override
    public void reset() {
        noOfMovesLeft = 0;
        for (int i = 0; i < board.length; i++) {
            switch (board[i]) {
                case XS_CELL:
                case O_CELL:
                    board[i] = NULL_CELL;
                    noOfMovesLeft++;
                    break;
                case XS_IGNORE_CELL:
                case O_IGNORE_CELL:
                    board[i] = NULL_IGNORE_CELL;
                    noOfMovesLeft++;
                    break;
                case NULL_CELL:
                case NULL_IGNORE_CELL:
                    noOfMovesLeft++;
                    break;
                default:
            }
        }
    }

    @Override
    public boolean isGameOver() {
        return getWinner() != null || noOfMovesLeft == 0;
    }

    @Override
    public Character getWinner() {
        for (int i = 0; i < numOfCols; i++) {
            for (int j = 0; j < maxHeight; j++) {
                char curr = board[pos(i, j)];
                if (curr == NULL_CELL || curr == NULL_IGNORE_CELL
                        || curr == UNPLAYABLE_CELL) {
                    break;
                }
                if (j + 3 < maxHeight) {
                    if (curr == board[pos(i, j + 1)]
                            && curr == board[pos(i, j + 2)]
                            && curr == board[pos(i, j + 3)]) {
                        return curr;
                    }
                    if (i - 3 >= 0) {
                        if (curr == board[pos(i - 1, j + 1)]
                                && curr == board[pos(i - 2, j + 2)]
                                && curr == board[pos(i - 3, j + 3)]) {
                            return curr;
                        }
                    }                    
                }
                if (i + 3 < numOfCols) {
                    if (curr == board[pos(i + 1, j)]
                            && curr == board[pos(i + 2, j)]
                            && curr == board[pos(i + 3, j)]) {
                        return curr;
                    }
                    if (j + 3 < numOfCols) {
                        if (curr == board[pos(i + 1, j + 1)]
                                && curr == board[pos(i + 2, j + 2)]
                                && curr == board[pos(i + 3, j + 3)]) {
                            return curr;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean isValidMove(char col) {
        return (col - START_COL) < numOfCols && !isFilled(col);
    }

    @Override
    public char[] validMoves() {
        String str = "";
        for (int i = 0; i < numOfCols; i++) {
            char col = (char) (START_COL + i);
            if (isValidMove(col)) {
                str += Character.toString(col);
            }
        }
        return str.toCharArray();
    }

    @Override
    public boolean isFilled(char col) {
        int colNo = (col - START_COL);
        for (int j = 0; j < maxHeight; j++) {
            int index = pos(colNo, j);
            if (board[index] == NULL_CELL
                    || board[index] == NULL_IGNORE_CELL) {
                return false;
            } else if (board[index] == UNPLAYABLE_CELL) {
                break;
            }
        }
        return true;
    }

    @Override
    public GameBoard getBoardCopy() {
        return new GameBoard(Arrays.copyOf(board, board.length),
                numOfCols, maxHeight,
                noOfMovesLeft
        );
    }

    private int pos(int col, int row) {
        return maxHeight * col + row;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int n = numOfCols * 4 - 1;
        String sep = "+" + String.join("", Collections.nCopies(n, "-")) + "+";
        builder.append(sep);
        builder.append("\n");
        for (int j = maxHeight - 1; j >= 0; j--) {
            builder.append('|');
            for (int i = 0; i < numOfCols; i++) {
                builder.append(' ')
                        .append(board[pos(i, j)])
                        .append(' ')
                        .append('|');
            }
            builder.append("\n");
            builder.append(sep);
            builder.append("\n");
        }
        return builder.toString();
    }

    public static GameBoard create(String heightStr, String IgnoreCellsString)
            throws Exception {
        char[] board;
        int numOfCols = 0, maxHeight = 0, numOfMoves = 0;

        String[] split = heightStr.split("[ ]+");
        int[] heights = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            heights[i] = Integer.parseInt(split[i]);
        }
        maxHeight = Arrays.stream(heights).max()
                .getAsInt();
        numOfCols = heights.length;
        board = new char[numOfCols * maxHeight];
        for (int i = 0; i < numOfCols; i++) {
            int height = heights[i];
            for (int j = 0; j < height; j++) {
                board[maxHeight * i + j] = NULL_CELL;
                numOfMoves++;
            }
            for (int j = maxHeight - 1; j >= height; j--) {
                board[maxHeight * i + j] = UNPLAYABLE_CELL;
            }
        }

        split = IgnoreCellsString.split("[ ]+");
        for (String ignoreCell : split) {
            int col = ignoreCell.charAt(0) - START_COL;
            int row = Integer.parseInt(ignoreCell.substring(1));
            board[maxHeight * col + row] = NULL_IGNORE_CELL;
            numOfMoves++;
        }
        return new GameBoard(board, numOfCols, maxHeight,
                numOfMoves);
    }
}
