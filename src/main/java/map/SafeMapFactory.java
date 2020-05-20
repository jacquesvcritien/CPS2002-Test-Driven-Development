package map;

/**
 * Class for Safe Map creator
 */
public class SafeMapFactory extends MapFactory {

    @Override
    public Map createMap()
    {
        return SafeMap.getMap();
    }

}
