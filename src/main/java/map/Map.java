package map;

import exceptions.MapNotSetException;
import player.Position;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private static Map map = new Map();
    private int size;
    private Tile[][] mapTiles;
    private final double blueProbability = 0.2;

    /**
     * method to return map instance
     */
    public static Map getMap()
    {
        return map;
    }

    /**
     * Setter for map size
     * @param size size to set
     */
    public boolean setSize(int size, Random random) throws MapNotSetException {
        this.size = size;
        this.mapTiles = new Tile[size][size];
        generate(random);
        return true;
    }

    /**
     * Getter for map size
     * @return size of map
     */
    public int getSize() {
        return size;
    }

    /**
     * Get map tiles
     * @return a multi-dimentional array
     */
    public Tile[][] getMapTiles() {
        return mapTiles;
    }

    /**
     * Get map tile of position
     * @param position position of tile
     */
    public Tile getMapTile(Position position)
    {
        return this.mapTiles[position.getyCoordinate()][position.getxCoordinate()];
    }

    /**
     * Method to get random coordinates
     * @return random coordinates
     */
    public int[] getRandomCoordinates(Random random)
    {
        int[] toReturn = new int[2];
        toReturn[0] = random.nextInt(this.size);
        toReturn[1] = random.nextInt(this.size);
        return toReturn;
    }

    /**
     * Map generator
     */
    public void generate(Random random) throws MapNotSetException {
        if(this.size ==0)
            throw new MapNotSetException("Map size should be set before generating map");

        //array to hold coordinates
        int[] generatedCoordinates = new int[2];

        //set treasure tile

        generatedCoordinates = getRandomCoordinates(random);
        mapTiles[generatedCoordinates[0]][generatedCoordinates[1]] = new TreasureTile();

        //set water tiles
        //get number of blue tiles
        int blueTilesAmt = (int)(blueProbability * (this.size * this.size));
        //counter for blue tiles added
        int blueTilesAdded = 1;
        while(blueTilesAdded < blueTilesAmt){
            //get new coordinates
            generatedCoordinates = getRandomCoordinates(random);

            //if not set
            if(mapTiles[generatedCoordinates[0]][generatedCoordinates[1]] == null)
            {
                //set tile to blue
                mapTiles[generatedCoordinates[0]][generatedCoordinates[1]] = new BlueTile();
                //increment tiles added counter
                blueTilesAdded++;
            }

        }

        //set green tiles
        for(int i=0; i < this.size; i++)
            for(int j=0; j < this.size; j++)
                //if does not exist
                if(mapTiles[i][j] == null)
                    mapTiles[i][j] = new GreenTile();

    }

    /**
     * Method used just for testing
     */
    public void reset()
    {
        map.size = 0;
        map.mapTiles = null;
    }

    /**
     * Method to check path
     * @param tiles matrix of tiles
     * @param startY starting y position
     * @param startX starting x position
     * @return true if good
     */
    public static boolean goodPath(Tile[][] tiles, int startY, int startX)
    {
        ArrayList<Tile> visited = new ArrayList<Tile>();

        return goodPath(tiles, startY, startX, visited);

    }

    /**
     * Method to check path
     * @param tiles matrix of tiles
     * @param startY starting y position
     * @param startX starting x position
     * @param visited visited nodes
     * @return true if good
     */
    public static boolean goodPath(Tile[][] tiles, int startY, int startX, ArrayList<Tile> visited)
    {

        if((startX < 0) || (startY < 0) ||  (startX > tiles.length-1) ||  (startY > tiles.length-1))
            return false;

        if(tiles[startY][startX].getType() == TileType.BLUE || visited.contains(tiles[startY][startX]))
            return false;

        visited.add(tiles[startY][startX]);

        if(tiles[startY][startX].getType() == TileType.TREASURE)
            return true;

        if(goodPath(tiles, startY+1, startX, visited) ||
                goodPath(tiles, startY-1, startX, visited) ||
                goodPath(tiles, startY, startX+1, visited) ||
                goodPath(tiles, startY, startX-1, visited))
            return true;

        return false;
    }
}
