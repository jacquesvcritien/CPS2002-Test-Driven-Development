import menu.MenuValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MenuValidatorTest {

    MenuValidator menuValidator;

    /**
     * Test for amount of players, one lower than range
     */
    @Test
    public void lowerAmtPlayersValTest(){
        boolean result = menuValidator.amtPlayersValidator(1);
        assertFalse("Asserting a lower amount of players", result);
    }

    /**
     * Test for amount of players, one higher than range
     */
    @Test
    public void higherAmtPlayersValTest(){
        boolean result = menuValidator.amtPlayersValidator(9);
        assertFalse("Asserting a higher amount of players", result);
    }

    /**
     * Test for amount of players with in the range for example 5
     */
    @Test
    public void amtPlayersValTest(){
        boolean result = menuValidator.amtPlayersValidator(5);
        assertTrue("Asserting an amount of players in the range", result);
    }

    /**
     * Test the map size function with a smaller amount of player
     */
    @Test
    public void smallerAmtPlayerMapSize(){
        boolean result = menuValidator.mapSize(1,4);
        assertFalse("Asserting a lower amount of players", result);
    }

    /**
     * Testing a smaller value for map size with players 2-4
     */
    @Test
    public void smallValueSmallPlayersMapSizeTest(){
        boolean result = menuValidator.mapSize(3,4);
        assertFalse("Asserting a lower map size for players in range 2-4", result);
    }

    /**
     * Testing a larger value for map size with players 2-4
     */
    @Test
    public void largeValueSmallPlayerMapSizeTest(){
        boolean result = menuValidator.mapSize(3,51);
        assertFalse("Asserting a larger map size for players in range 2-4", result);
    }

    /**
     * Testing a value for map size in range with players 2-4
     */
    @Test
    public void inRangeValueSmallPlayerMapSizeTest(){
        boolean result = menuValidator.mapSize(3,25);
        assertTrue("Asserting a map size in range for players in range 2-4", result);
    }

    /**
     * Testing a smaller value for map size with players 5-8
     */
    @Test
    public void smallValueBigPlayerMapSizeTest(){
        boolean result = menuValidator.mapSize(6,7);
        assertFalse("Asserting a smaller map size for players in range 5-8", result);
    }

    /**
     * Testing a larger value for map size with players 5-8
     */
    @Test
    public void largeValueBigPlayerMapSizeTest(){
        boolean result = menuValidator.mapSize(6,51);
        assertFalse("Asserting a larger map size for players in range 5-8", result);
    }

    /**
     * Testing a value for map size in range with players 5-8
     */
    @Test
    public void inRangeBigPlayerMapSizeTest(){
        boolean result = menuValidator.mapSize(6,20);
        assertTrue("Asserting a map size in range for players in range 5-8", result);
    }

}
