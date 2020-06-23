package map;

import java.util.Random;

/**
 * Class for Safe Map creator
 */
public class SafeMapFactory extends MapFactory {

    @Override
    public Map createMap(Random random)
    {
        SafeMap map = SafeMap.getMap();
        map.setProbability(random);
        return map;
    }

}
