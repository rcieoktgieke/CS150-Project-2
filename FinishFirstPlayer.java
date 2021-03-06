import java.util.*;
/**
 * A FinishFirstPlayer advances its furthest token (when it may advance) until it comes within a single die roll of the finish, at which point it moves its other tokens in a similar fashion until it rolls a number required to end the game and would have the highest score if it ended the game.
 * 
 * @Eric Weber
 * @4/7/16
 */
public class FinishFirstPlayer extends Player {
    
    public FinishFirstPlayer(int number, int numTokens) {
        super(number, numTokens);
    }
    public Token whichToken(int roll, Board board, int boardEnd, int dRange, LinkedList<Player> players, int w, double p) {
        Token tokenToMove = tokens.get(0);
        
        boolean winning = true;
        double points = this.getPoints(boardEnd, w, p);
        Iterator<Player> playerIter = players.iterator();
        while (playerIter.hasNext()) {
            if (playerIter.next().getPoints(boardEnd, w, p) > points) {
                winning = false;
            }
        }
        
        int tokenIndex = 0;
        Iterator<Token> tokenIter = tokens.iterator();
        while (tokenIter.hasNext()) {
            Token cToken = tokenIter.next();
            if (board.get(cToken.getIndex()).canMove(cToken, roll, boardEnd)) {
                if (winning || cToken.getIndex() + dRange < boardEnd) {
                    if (cToken.getIndex() >= tokenIndex) {
                        tokenToMove = cToken;
                        tokenIndex = cToken.getIndex();
                    }
                }
            }
        }
        return tokenToMove;
    }
    public String toString() {
        return "FinishFirstPlayer"+number;
    }
}