package connectfour;
/**12252511
 * Zhesi NING
 **/
public abstract class Player {

    protected char symbol;
    
    protected char oppoSymbol;
    
    abstract char getMove(Board board);

    public char getSymbol() {
        return symbol;
    }
    public char getOppoSymbol() {
        return oppoSymbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public void setOppoSymbol(char opponentSymbol) {
        this.oppoSymbol = opponentSymbol;
    }
    

}
