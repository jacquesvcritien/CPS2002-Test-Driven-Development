
import exceptions.MapNotSetException;
import map.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;

public class MapFactoryTest {

    /**
     * Test for get map safe
     */
    @Test
    public void testGetSafeMap(){
        MapType mapType = MapType.SAFE;
        Map map = MapFactory.getMap(mapType, new Random());
        assertEquals("Asserting map type", mapType, map.getMapType());
    }

    /**
     * Test for get map hazardous
     */
    @Test
    public void testGetHazardousMap(){
        MapType mapType = MapType.HAZARDOUS;
        Map map = MapFactory.getMap(mapType, new Random());
        assertEquals("Asserting map type", mapType, map.getMapType());
    }

}