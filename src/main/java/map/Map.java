package map;

public class Map {
    private int size;
    Tile[][] mapTiles;

    /**
     * Constructor for map
     * @param size map size
     */
    public Map(int size)
    {
        this.size = size;
        this.mapTiles = new Tile[size][size];
    }

    /**
     * Setter for map size
     * @param size size to set
     */
    public boolean setSize(int size) {
        this.size = size;
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
