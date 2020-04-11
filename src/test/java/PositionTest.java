import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    Position emptyPosition;
    Position position;
    @Before
    public void setup()
    {
        emptyPosition = new Position();
        position = new Position(1,2);
    }

    @After
    public void teardown()
    {
        position = null;
    }

    /**
     * Test for getter for x coordinate
     */
    @Test
    public void testXGetter()
    {
        int x = position.getxCoordinate();
        assertEquals("Asserting x coordinate", 1, x);
    }

    /**
     * Test for getter for y coordinate
     */
    @Test
    public void testYGetter()
    {
        int y = position.getyCoordinate();
        assertEquals("Asserting y coordinate", 2, y);
    }

    /**
     * Test for setter for x coordinate
     */
    @Test
    public void testXSetter()
    {
        emptyPosition.setxCoordinate(2);
        assertEquals("Asserting x coordinate", 2, emptyPosition.getxCoordinate());
    }

    /**
     * Test for setter for y coordinate
     */
    @Test
    public void testYSetter()
    {
        emptyPosition.setyCoordinate(4);
        assertEquals("Asserting y coordinate", 4, emptyPosition.getyCoordinate());
    }

    /**
     * Test for equals method when not equal
     */
    @Test
    public void testNotEquals()
    {
        emptyPosition.setxCoordinate(4);
        emptyPosition.setyCoordinate(6);
        assertFalse("Asserting not equal", emptyPosition.equals(position));
    }

    /**
     * Test for equals method when not equal
     */
    @Test
    public void testNotEquals2()
    {
        emptyPosition.setyCoordinate(1);
        emptyPosition.setxCoordinate(1);
        assertFalse("Asserting not equal", emptyPosition.equals(position));
    }

    /**
     * Test for equals method when not equal
     */
    @Test
    public void testNotEquals3()
    {
        emptyPosition.setyCoordinate(2);
        emptyPosition.setxCoordinate(4);
        assertFalse("Asserting not equal", emptyPosition.equals(position));
    }

    /**
     * Test for equals method when equal
     */
    @Test
    public void testEquals()
    {
        emptyPosition.setxCoordinate(1);
        emptyPosition.setyCoordinate(2);
        assertTrue("Asserting equal", emptyPosition.equals(position));
    }

    /**
     * Test for equals method when empty
     */
    @Test
    public void testEqualsEmpty()
    {
        assertFalse("Asserting not equal", position.equals(null));
    }

    /**
     * Test for equals method when not a position
     */
    @Test
    public void testEqualsNotPosiiton()
    {
        assertFalse("Asserting not equal", position.equals("Just a string"));
    }


}