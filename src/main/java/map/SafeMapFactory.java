package map;

/**
 * Class for Safe Maze creator
 */
public class SafeMapFactory extends MapFactory {

    @Override
    public Map createMap()
    {
        return SafeMap.getMap();
    }

}
