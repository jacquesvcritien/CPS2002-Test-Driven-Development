
import exceptions.MapNotSetException;
import map.Map;
import map.Tile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void testGetSize()
    {
        map.setSize(5);
        //test for get map size
        assertEquals("Asserting map size getter", 5, map.getSize());
    }

    /**
     * Test for size setter
     */
    @Test
    public void testSetSize()
    {
        //set size
        map.setSize(7);
        assertEquals("Asserting map size setter", 7, map.getSize());
    }

    /**
     * Test for tiles getter
     */
    @Test
    public void testGetTiles()
    {
        map.setSize(5);
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
        map.setSize(5);
        Mockito.when(randomMocked.nextInt(map.getSize())).thenReturn(1,1,1,1,2,2,2,3,4,2,1,0,1,2);
        map.generate(randomMocked);

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