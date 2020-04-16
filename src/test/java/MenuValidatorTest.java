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
     * Test a smaller value than map size required for players 2-4
     */
    @Test
    public void lowerSmallMapSizeTest(){
        boolean result = menuValidator.smallMapSize(4);
        assertFalse("Asserting a lower map size", result);
    }

    /**
     * Test a larger value than map size required for players 2-4
     */
    @Test
    public void higherSmallMapSizeTest(){
        boolean result = menuValidator.smallMapSize(51);
        assertFalse("Asserting a larger map size", result);
    }

    /**
     * Test map size value in range for players 2-4
     */
    @Test
    public void smallMapSizeTest(){
        boolean result = menuValidator.smallMapSize(20);
        assertTrue("Asserting a map size in range as expected", result);
    }

    /**
     * Test a smaller value than map size required for players 5-8
     */
    @Test
    public void lowerLargeMapSizeTest(){
        boolean result = menuValidator.bigMapSize(7);
        assertFalse("Asserting a lower map size", result);
    }

    /**
     * Test a larger value than map size required for players 5-8
     */
    @Test
    public void higherLargeMapSizeTest(){
        boolean result = menuValidator.bigMapSize(51);
        assertFalse("Asserting a larger map size", result);
    }

    /**
     * Test map size value in range for players 5-8
     */
    @Test
    public void bigMapSizeTest(){
        boolean result = menuValidator.bigMapSize(25);
        assertTrue("Asserting a map size in range as expected", result);
    }
}
