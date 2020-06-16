
import exceptions.MapNotSetException;
import map.*;
import map.tile.BlueTile;
import map.tile.GreenTile;
import map.tile.Tile;
import map.tile.TreasureTile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;

public class SafeMapTest {

    SafeMap safeMap;
    Random randomMocked;
    @Before
    public void setup()
    {
        safeMap = SafeMap.getMap();
        randomMocked = Mockito.mock(Random.class);
    }

    @After
    public void teardown()
    {
       safeMap = null;
    }

    /**
     * Test for size getter
     */
    @Test
    public void testGetSize() throws MapNotSetException {
        int mapSize =5;
        safeMap.setSize(mapSize, new Random());
        //test for get map size
        assertEquals("Asserting map size getter", 5, safeMap.getSize());
    }

    /**
     * Test for size setter
     */
    @Test
    public void testSetSize() throws MapNotSetException {
        int mapSize =7;
        safeMap.setSize(mapSize, new Random());
        assertEquals("Asserting map size setter", 7, safeMap.getSize());
    }

    /**
     * Test for tiles getter
     */
    @Test
    public void testGetTiles() throws MapNotSetException {
        safeMap.setSize(5, new Random());
        Tile[][] tiles = safeMap.getMapTiles();

        int actualSize =0;
        for(int i=0; i < tiles.length; i++)
            actualSize += tiles[i].length;

        assertEquals("Asserting map size setter", 25, actualSize);
    }

    /**
     * Test for generate
     */
    @Test
    public void testGenerate() throws MapNotSetException {
        int mapSize =5;
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(0,0,0,0,1,0,0,1,1,2,2,1,2,2,2,3,4,2,1,0,1,2);
        safeMap.setSize(mapSize, randomMocked);

        Tile[][] mapTiles = safeMap.getMapTiles();
        int counter = 0;
        for(int i=0; i < mapTiles.length; i++)
            for (int j=0; j < mapTiles[i].length; j++)
                counter++;

        Assert.assertEquals("Asserting tiles sizes", mapSize*mapSize, counter);

    }

    /**
     * Test for generate when not set
     */
    @Test(expected = MapNotSetException.class)
    public void testGenerateNotSet() throws MapNotSetException {
        safeMap.reset();

        safeMap.generate(new Random());
    }

    /**
     * Test for good path when there is a path
     */
    @Test
    public void testGoodPathExists(){
        Tile[][] tiles =
                {
                {new GreenTile(), new BlueTile(), new GreenTile(), new GreenTile(), new GreenTile()},
                {new GreenTile(), new GreenTile(), new GreenTile(), new GreenTile(), new BlueTile()},
                {new GreenTile(), new GreenTile(), new TreasureTile(), new GreenTile(), new GreenTile()},
                {new GreenTile(), new GreenTile(), new GreenTile(), new GreenTile(), new BlueTile()},
                {new GreenTile(), new BlueTile(), new GreenTile(), new GreenTile(), new GreenTile()}
                };

        int startY =0;
        int startX = 0;

        assertTrue("Asserting there is a path", SafeMap.getMap().goodPath(tiles, startY, startX));
    }

    /**
     * Test for good position for treasure method when to check is not empty
     */
    @Test
    public void testGoodPositionForTreasure(){
        Tile[][] tiles =
                {
                        {null, new BlueTile(), null, null, null},
                        {null, null, null, null, new BlueTile()},
                        {null, null, null, null, null},
                        {null, null, null, null, new BlueTile()},
                        {null, new BlueTile(), null, null, null}
                };

        int startY =0;
        int startX = 1;

        assertFalse("Asserting it is not a good position", SafeMap.getMap().goodPositionForTreasure(tiles, startY, startX));
    }

    /**
     * Test for good position for treasure method when position is not good
     */
    @Test
    public void testGoodPositionForTreasureBadPosition(){
        Tile[][] tiles =
                {
                        {null, new BlueTile(), null, null, null},
                        {new BlueTile(), null, null, null, new BlueTile()},
                        {null, null, null, null, null},
                        {null, null, null, null, new BlueTile()},
                        {null, new BlueTile(), null, null, null}
                };

        int startY =0;
        int startX = 0;

        assertFalse("Asserting it is not a good position", SafeMap.getMap().goodPositionForTreasure(tiles, startY, startX));
    }

    /**
     * Test for good position for treasure method when position is not good
     */
    @Test
    public void testGoodPositionForTreasureBadPosition2(){
        Tile[][] tiles =
                {
                        {null, new BlueTile(), null, null, null},
                        {null, null, null, null, new BlueTile()},
                        {null, null, null, null, null},
                        {null, null, null, null, new BlueTile()},
                        {null, new BlueTile(), null, new BlueTile(), null}
                };

        int startY =4;
        int startX = 4;

        assertFalse("Asserting it is not a good position", SafeMap.getMap().goodPositionForTreasure(tiles, startY, startX));
    }
    
    

    /**
     * Test for good path when there is no path
     */
    @Test
    public void testGoodPathNoPath(){
        Tile[][] tiles =
                {
                    {new GreenTile(), new BlueTile(), new GreenTile(), new GreenTile(), new GreenTile()},
                    {new BlueTile(), new BlueTile(), new GreenTile(), new GreenTile(), new BlueTile()},
                    {new GreenTile(), new GreenTile(), new TreasureTile(), new GreenTile(), new GreenTile()},
                    {new GreenTile(), new GreenTile(), new GreenTile(), new GreenTile(), new BlueTile()},
                    {new GreenTile(), new BlueTile(), new GreenTile(), new GreenTile(), new GreenTile()}
                };

        int startY =0;
        int startX = 0;

        Assert.assertFalse("Asserting there is no path", SafeMap.getMap().goodPath(tiles, startY, startX));
    }

    /**
     * Test for get random coordinates when size is 0
     * */
    @Test (expected = MapNotSetException.class)
    public void testGetRandomCoordinatesSize0() throws MapNotSetException {
        safeMap.reset();
        safeMap.getRandomCoordinates(new Random());

    }





}