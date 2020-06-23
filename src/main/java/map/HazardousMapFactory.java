package map;

import java.util.Random;

/**
 * Factory class for hazardous map
 */
public class HazardousMapFactory extends MapFactory {

    /**
     * Method to return a hazardous map
     * @param random Random number generator for map blue tiles probability
     * @return hazardous map
     */
    @Override
    public Map createMap(Random random)
    {
        HazardousMap map = HazardousMap.getMap();
        map.setProbability(random);
        return map;
    }

}
