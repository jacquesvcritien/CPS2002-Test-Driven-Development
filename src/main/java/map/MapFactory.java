package map;

/**
 * Class for Map factory
 */
public abstract class MapFactory {

    /**
     * Factory method
     * @param mapType type of map
     * @return a map
     */
    public static Map getMap(MapType mapType)
    {

        MapFactory creator;

        //get type of creator needed
        switch (mapType)
        {
            case SAFE: creator = new SafeMapFactory();break;
            default: creator = new HazardousMapFactory();break;
        }

        //create and return map
        return creator.createMap();
    }

    /**
     * Abstract method to create the map
     * @return new map
     */
    public abstract Map createMap();

}
