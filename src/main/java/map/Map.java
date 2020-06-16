package map;

import exceptions.MapNotSetException;
import game.Game;
import map.tile.Tile;
import map.tile.TileType;
import team.player.Position;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class interface for Map for factory
 */
public abstract class Map {
    //size of map
    protected int size =0;
    //holds map tiles
    protected Tile[][] mapTiles;

    /**
     * Setter for map size
     * @param size size to set
     * @throws MapNotSetException if the map is not set
     */
    public void setSize(int size, Random random) throws MapNotSetException {
        this.size = size;
        this.mapTiles = new Tile[size][size];
        generate(random);
    }

    /**
     * Setter for map tiles - FOR TESTING
     * @param mapTiles map tiles to set
     */
    public void setMapTiles(Tile[][] mapTiles) {
        this.mapTiles = mapTiles;
    }

    /**
     * Method to generate map
     * @param random random number
     * @throws MapNotSetException if the map is ot set
     */
    public abstract void generate(Random random) throws MapNotSetException;

    /**
     * Method to set probability
     * @param random random number ot set probability
     */
    public abstract void setProbability(Random random);

    /**
     * Getter for map type
     * @return type of map
     */
    public abstract MapType getMapType();

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
    public int[] getRandomCoordinates(Random random) throws MapNotSetException {
        if(this.size ==0)
            throw new MapNotSetException("Map size is not set");

        int[] toReturn = new int[2];
        toReturn[0] = random.nextInt(this.size);
        toReturn[1] = random.nextInt(this.size);
        return toReturn;
    }

    /**
     * Method which checks if a position would be good for a treasure tile
     * @param y coordinate of treasure
     * @param x coordinate of treasure
     * @return true if can be placed as treasure
     */
    public boolean goodPositionForTreasure(Tile[][] mapTiles, int y, int x)
    {
        //available options;
        int available = 0;
        //if a blue tile
        if(mapTiles[y][x] != null)
            return false;

        //if on top is not the edge and is empty
        if((y-1) > -1 && mapTiles[y-1][x] == null)
            available++;

        //if below is not the edge and is empty
        if((y+1) < (mapTiles.length) && mapTiles[y+1][x] == null)
            available++;

        //if left is not the edge and is empty
        if((x-1) > -1 && mapTiles[y][x-1] == null)
            available++;

        //if right is not the edge and is empty
        if((x+1) < (mapTiles.length) && mapTiles[y][x+1] == null)
            available++;

        return available > 0;

    }

    /**
     * Method used just for testing
     */
    public void reset()
    {
        this.size = 0;
        this.mapTiles = null;
    }

    /**
     * Method to check path
     * @param tiles matrix of tiles
     * @param startY starting y position
     * @param startX starting x position
     * @return true if good
     */
    public boolean goodPath(Tile[][] tiles, int startY, int startX)
    {
        ArrayList<Tile> visited = new ArrayList<>();

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
    public boolean goodPath(Tile[][] tiles, int startY, int startX, ArrayList<Tile> visited)
    {

        if((startX < 0) || (startY < 0) ||  (startX > tiles.length-1) ||  (startY > tiles.length-1))
            return false;

        if(tiles[startY][startX].getType() == TileType.BLUE || visited.contains(tiles[startY][startX]))
            return false;

        visited.add(tiles[startY][startX]);

        if(tiles[startY][startX].getType() == TileType.TREASURE)
            return true;

        return goodPath(tiles, startY + 1, startX, visited) ||
                goodPath(tiles, startY - 1, startX, visited) ||
                goodPath(tiles, startY, startX + 1, visited) ||
                goodPath(tiles, startY, startX - 1, visited);
    }

    /**
     * Method to generate a starting position
     *
     * @param random random generator to use
     * @return starting position
     */
    public static Position generateStarting(Random random) throws MapNotSetException {
        //generate coordinates
        int[] generatedCoordinates;
        //set position
        Position newPosition;
        boolean goodPath;

        //do while you find a valid position
        do {
            generatedCoordinates = Game.getMap().getRandomCoordinates(random);
            newPosition = new Position(generatedCoordinates[0], generatedCoordinates[1]);
            //check if it is a good path
            goodPath = Game.getMap().goodPath(Game.getMap().getMapTiles(), newPosition.getyCoordinate(), newPosition.getxCoordinate());
        } while (Game.getMap().getMapTile(newPosition).getType() != TileType.GREEN || !goodPath);

        return newPosition;

    }

}
