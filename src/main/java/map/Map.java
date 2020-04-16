package map;

public class Map {
    private static Map map = new Map();
    private int size;
    private Tile[][] mapTiles;

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
}
