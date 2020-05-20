package map;

/**
 * Class for Safe Maze creator
 */
public class HazardousMapFactory extends MapFactory {

    /**
     * Method to return a hazardous map
     * @return hazardous map
     */
    @Override
    public Map createMap()
    {
        return HazardousMap.getMap();
    }

}
