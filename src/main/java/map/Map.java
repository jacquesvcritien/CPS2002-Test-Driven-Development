package map;

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
    public boolean setSize(int size) {
        this.size = size;
        this.mapTiles = new Tile[size][size];
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
     * Method to get random coordinate
     * @return random coordinate
     */
    public int getRandomCoordinate()
    {
        return  (int)(Math.random() * this.size);
    }

    /**
     * Map generator
     */
    public void generate() {
        //variables to hold x and y coordinates
        int x,y;

        //set treasure tile
        x = getRandomCoordinate();
        y = getRandomCoordinate();
        mapTiles[x][y] = new TreasureTile();

        //set water tiles
        //get number of blue tiles
        int blueTilesAmt = (int) blueProbability * (this.size * this.size);
        //counter for blue tiles added
        int blueTilesAdded = 0;
        do {
            //get new coordinates
            x = getRandomCoordinate();
            y = getRandomCoordinate();

            //if not set
            if(mapTiles[x][y] == null)
            {
                //set tile to blue
                mapTiles[x][y] = new BlueTile();
                //increment tiles added counter
                blueTilesAdded++;
            }

        }while(blueTilesAdded < blueTilesAmt);

        //set green tiles
        for(int i=0; i < this.size; i++)
            for(int j=0; j < this.size; j++)
                //if does not exist
                if(mapTiles[i][j] == null)
                    mapTiles[i][j] = new GreenTile();
                
    }
}
