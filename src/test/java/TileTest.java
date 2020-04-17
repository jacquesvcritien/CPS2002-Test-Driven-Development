import map.BlueTile;
import map.GreenTile;
import map.TileType;
import map.TreasureTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import player.Position;

import static org.junit.Assert.*;

public class TileTest {

    GreenTile greenTile;
    BlueTile blueTile;
    TreasureTile treasureTile;
    @Before
    public void setup()
    {
        greenTile = new GreenTile();
        blueTile = new BlueTile();
        treasureTile = new TreasureTile();
    }

    @After
    public void teardown()
    {
        greenTile = null;
        blueTile = null;
        treasureTile = null;
    }

    /**
     * Test for green tile type getter
     */
    @Test
    public void testGetGreenType()
    {
        //test for green tile
        assertEquals("Asserting green tile type", TileType.GREEN, greenTile.getType());
    }

    /**
     * Test for blue tile type getter
     */
    @Test
    public void testGetBlueType()
    {
        //test for blue tile
        assertEquals("Asserting blue tile type", TileType.BLUE, blueTile.getType());
    }

    /**
     * Test for treasure tile type getter
     */
    @Test
    public void testGetTreasureType()
    {
        //test for treasure tile
        assertEquals("Asserting treasure tile type", TileType.TREASURE, treasureTile.getType());
    }

    /**
     * Test for green and blue tiles html getter
     */
    @Test
    public void testGreenAndBlueGetHtml()
    {
        //test for green tile
        assertEquals("Asserting green tile html", "<td class=\"$class\">$player</td>", greenTile.getHtml());
        //test for blue tile
        assertEquals("Asserting blue tile html", "<td class=\"$class\">$player</td>", blueTile.getHtml());
    }


    /**
     * Test for treasure tile html getter
     */
    @Test
    public void testTreasureGetHtml()
    {
        //test for treasure tile
        assertEquals("Asserting treasure tile html", "<td class=\"$class\"><img class=\"tile-img\" src=\"images/treasure.png\">$player</td>", treasureTile.getHtml());
    }

    /**
     * Test for green tile classname getter
     */
    @Test
    public void testGetGreenClassName()
    {
        //test for green tile
        assertEquals("Asserting green tile class name", "grass-back", greenTile.getClassName());
    }

    /**
     * Test for treasure tile classname getter
     */
    @Test
    public void testGetTreasureClassName()
    {
        //test for green tile
        assertEquals("Asserting treasure tile class name", "grass-back", treasureTile.getClassName());
    }

    /**
     * Test for blue tile classname getter
     */
    @Test
    public void testGetBlueClassName()
    {
        //test for green tile
        assertEquals("Asserting blue tile class name", "water-back", blueTile.getClassName());
    }


}