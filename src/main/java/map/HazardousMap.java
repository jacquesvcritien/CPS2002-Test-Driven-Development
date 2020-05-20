package map;

import exceptions.MapNotSetException;

import java.util.Random;

/**
 * Class for safe map
 */
public class HazardousMap extends Map {

    //probability for blue tiles
    private final double probability = 0.35;
    //probability for blue tiles
    public final MapType mapType = MapType.HAZARDOUS;
    //for singleton
    private static HazardousMap hazardousMap = new HazardousMap();


    /**
     * method to return map instance
     */
    public static HazardousMap getMap()
    {
        return hazardousMap;
    }

    /**
     * Map generator
     */
    public void generate(Random random) throws MapNotSetException {
        if(this.size ==0)
            throw new MapNotSetException("Map size should be set before generating map");

        //array to hold coordinates
        int[] generatedCoordinates = new int[2];

        //get treasure coordinates
        int[] treasureCoordinates = getRandomCoordinates(random);

        //set water tiles
        //get number of blue tiles
        int blueTilesAmt = (int)(probability * (this.size * this.size));
        //counter for blue tiles added
        int blueTilesAdded = 1;
        while(blueTilesAdded < blueTilesAmt){
            //get new coordinates
            generatedCoordinates = getRandomCoordinates(random);

            //if not treasure coordinates
            if(generatedCoordinates[0] != treasureCoordinates[0] || generatedCoordinates[1] != treasureCoordinates[1])
            {
                //set tile to blue
                mapTiles[generatedCoordinates[0]][generatedCoordinates[1]] = new BlueTile();
                //increment tiles added counter
                blueTilesAdded++;
            }

        }

        boolean goodPositionForTreasure;
        //set treasure coordinates
        do {
            goodPositionForTreasure = goodPositionForTreasure(mapTiles, treasureCoordinates[0],treasureCoordinates[1]);

            if(goodPositionForTreasure)
                mapTiles[treasureCoordinates[0]][treasureCoordinates[1]] = new TreasureTile();
            else
                treasureCoordinates = getRandomCoordinates(random);

        }while(!goodPositionForTreasure);

        //set green tiles
        for(int i=0; i < this.size; i++)
            for(int j=0; j < this.size; j++)
                //if does not exist
                if(mapTiles[i][j] == null)
                    mapTiles[i][j] = new GreenTile();

    }

    /**
     * Getter for map type
     * @return map type
     */
    @Override
    public MapType getMapType() {
        return mapType;
    }


}
