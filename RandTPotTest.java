import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class RandTPotTest.
 *
 * @Eric Weber
 * @3/16/16
 */
public class RandTPotTest
{
    private RandTPot rPot;
    private Token t;
    private Die d;
    private int boardEnd;
    /**
     * Default constructor for test class RandTPotTest
     */
    public RandTPotTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        rPot = new RandTPot(30);
        t = new Token();
        d = new Die(10);
        boardEnd = 15;
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testDefaultCanMove()
    {
        assertEquals(true, rPot.canMove());
    }
    @Test
    public void testCanMove()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token();
            int tokenIndex = t.getIndex();
            rPot.takeTurn(t, d, boardEnd);
            if (tokenIndex + d.prevRoll() > 0 && tokenIndex + d.prevRoll() < boardEnd) {
                assertTrue(rPot.canMove());
            }
            else if (tokenIndex + d.prevRoll() == boardEnd) {
                assertTrue(rPot.canMove());
            }
            else {
                assertFalse(rPot.canMove());
            }
        }
    }
    
    @Test
    public void testGetStatus()
    {
        int pieces = 30;
        assertEquals("R.Pot|" + pieces, rPot.getStatus());
        while (pieces > 0) {
            rPot.land(t, d);
            pieces -= d.prevRoll();
            if (pieces > 0) {
                assertEquals("R.Pot|" + pieces, rPot.getStatus());
            }
            else {
                assertEquals("R.Pot|0", rPot.getStatus());
            }
        }
    }
    
    @Test
    public void testLand()
    {
        rPot = new RandTPot(3000);
        int pieces = 0;
        while (pieces < 3000) {
            rPot.land(t, d);
            pieces += d.prevRoll();
            assertEquals(pieces, t.getPieces());
        }
        rPot.land(t, d);
        assertEquals(pieces, t.getPieces());
    }
    @Test
    public void testTakeTurn()
    {
        for (int i = 0; i < 100; i ++) {
            t = new Token();
            int tokenIndex = t.getIndex();
            boolean takeTurnOutput = rPot.takeTurn(t, d, boardEnd);
            if (tokenIndex + d.prevRoll() > 0 && tokenIndex + d.prevRoll() < boardEnd) {
                assertEquals(false, takeTurnOutput);
                assertEquals(t.getIndex(), (tokenIndex + d.prevRoll()));
            }
            else if (tokenIndex + d.prevRoll() == boardEnd) {
                assertEquals(true, takeTurnOutput);
                assertEquals(t.getIndex(), boardEnd);
            }
            else {
                assertEquals(t.getIndex(), tokenIndex);
            }
        }
    }
}
