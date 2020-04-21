
import exceptions.MapNotSetException;
import map.Map;
import map.Tile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class MapTest {

    Map map;
    Random randomMocked;
    @Before
    public void setup()
    {
        map = Map.getMap();
        randomMocked = Mockito.mock(Random.class);
    }

    @After
    public void teardown()
    {
       map = null;
    }

    /**
     * Test for size getter
     */
    @Test
    public void testGetSize() throws MapNotSetException {
        int mapSize =5;
        map.setSize(mapSize, new Random());
        //test for get map size
        assertEquals("Asserting map size getter", 5, map.getSize());
    }

    /**
     * Test for size setter
     */
    @Test
    public void testSetSize() throws MapNotSetException {
        int mapSize =7;
        map.setSize(mapSize, new Random());
        assertEquals("Asserting map size setter", 7, map.getSize());
    }

    /**
     * Test for tiles getter
     */
    @Test
    public void testGetTiles() throws MapNotSetException {
        map.setSize(5, new Random());
        Tile[][] tiles = map.getMapTiles();

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
        Mockito.when(randomMocked.nextInt(mapSize)).thenReturn(1,1,1,1,2,2,2,3,4,2,1,0,1,2);
        map.setSize(mapSize, randomMocked);

        Tile[][] mapTiles = map.getMapTiles();
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
        map.reset();
        map.generate(new Random());
    }





}