
import map.Map;
import map.Tile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    Map map;
    @Before
    public void setup()
    {
        map = new Map(5);
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
        Tile[][] tiles = map.getMapTiles();

        int actualSize =0;
        for(int i=0; i < tiles.length; i++)
            actualSize += tiles[i].length;
        
        assertEquals("Asserting map size setter", 25, actualSize);
    }





}