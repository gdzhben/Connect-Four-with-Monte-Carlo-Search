package connectfour;
/**12252511
 * Zhesi NING
 **/
public interface Board {

    GameBoard getBoardCopy();

    Character getWinner();
    
    boolean isGameOver();
    
    boolean isFilled(char col);
   
    boolean isValidMove(char col);

    void reset();

    @Override
    String toString();

    char[] validMoves();
}
