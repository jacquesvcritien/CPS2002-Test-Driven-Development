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

    /**
     * Test a smaller direction value
     */
    @Test
    public void smallerDirectionTest(){
        boolean result = menuValidator.directionCheck(-1);
        assertFalse("Asserting a smaller value for direction", result);
    }

    /**
     * Test a larger direction value
     */
    @Test
    public void largerDirectionTest(){
        boolean result = menuValidator.directionCheck(5);
        assertFalse("Asserting a larger value for direction", result);
    }

    /**
     * Test a direction value in range
     */
    @Test
    public void goodDirectionTest() {
        boolean result = menuValidator.directionCheck(2);
        assertTrue("Asserting a value in range for direction", result);
    }

    /**
     * Test when number of teams is greater than players
     */
    @Test
    public void teamsGreaterThanPlayer(){
        boolean result = menuValidator.amtOfTeamsValid(4,5);
        assertFalse("Asserting a larger amount of teams than players",result);
    }

    /**
     * Test when number of teams equal to number of players
     */
    @Test
    public void teamsEqualToPlayer(){
        boolean result = menuValidator.amtOfTeamsValid(4,4);
        assertFalse("Asserting an equal amount of teams to players",result);
    }

    /**
     * Test when number of teams is smaller than players
     */
    @Test
    public void teamsSmallerThanPlayer(){
        boolean result = menuValidator.amtOfTeamsValid(4,3);
        assertTrue("Asserting an smaller amount of teams than players",result);
    }

    /**
     * Test when number of teams equal to zero
     */
    @Test
    public void teamsEqualToZero(){
        boolean result = menuValidator.amtOfTeamsValid(4,0);
        assertFalse("Asserting a number of teams equal to zero",result);
    }

    /**
     * Test a smaller value than 1 or 2
     */
    @Test
    public void smallerThan1or2(){
        boolean result = menuValidator.assert1or2(0);
        assertFalse("Asserting a smaller value than 1 or 2 is false", result);
    }

    /**
     * Test a assert 1 or 2 method when 1
     */
    @Test
    public void goodAssert1or2When1(){
        boolean result = menuValidator.assert1or2(1);
        assertTrue("Asserting true for a good value", result);
    }

    /**
     * Test a assert 1 or 2 method when 2
     */
    @Test
    public void goodAssert1or2When2(){
        boolean result = menuValidator.assert1or2(2);
        assertTrue("Asserting true for a good value", result);
    }

}
