/**
 * Spaces are parts of the board at specific indecies.
 * 
 * @Eric Weber
 * @3/16/16
 */
public interface Space {
    
    /**
     * Land toen on space.
     * 
     * Executes appropriate operations for when a token lands on the space.
     * @param t token that is landing on this space.
     * @param d die that is used for the game.
     */
    public void land(Token t, Die d);
    /**
     * Check if the token last passed to takeTurn can move.
     * 
     * @return if token can move.
     */
    public boolean canMove();
    /**
     * Return the type and vital information of the space.
     * 
     * @return the type and vital information of the space.
     */
    public String getStatus();
    /**
     * Take the turn of the token t.
     * 
     * Executes appropriate operations for the token to take its turn.
     * @param t token that is taking its turn.
     * @param d die that is used for the game.
     * @param boardEnd the index of the final space on the board.
     * 
     * @return if token has reached the final space by exact count.
     */
    public boolean takeTurn(Token t, Die d, int boardEnd);
}