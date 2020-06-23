package map;

import java.util.Random;

/**
 * Class for Map factory
 */
public abstract class MapFactory {

    /**
     * Factory method
     * @param mapType type of map
     * @param random Random number generator for map blue tiles probability
     * @return a map
     */
    public static Map getMap(MapType mapType, Random random)
    {

        MapFactory creator;

        //get type of creator needed
        switch (mapType)
        {
            case SAFE: creator = new SafeMapFactory();break;
            default: creator = new HazardousMapFactory();break;
        }

        //create and return map
        return creator.createMap(random);
    }

    /**
     * Abstract method to create the map
     * @param random Random number generator for map blue tiles probability
     * @return new map
     */
    public abstract Map createMap(Random random);

}
