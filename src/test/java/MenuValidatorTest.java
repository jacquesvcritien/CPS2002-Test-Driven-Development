import menu.MenuValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MenuValidatorTest {

    MenuValidator menuValidator = new MenuValidator();

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
        assertFalse("Asserting a lower amount of players", result);
    }

    /**
     * Test for amount of players with in the range for example 5
     */
    @Test
    public void amtPlayersValTest(){
        boolean result = menuValidator.amtPlayersValidator(5);
        assertTrue("Asserting a lower amount of players", result);
    }
}
