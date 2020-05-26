package map.tile;

/**
 * This is a class which represents a blue tile
 */
public class BlueTile extends Tile{

    private TileType type;
    private String className;

    public BlueTile()
    {
        this.className = "water-back";
        this.type = TileType.BLUE;
    }

    /**
     * Getter for type
     * @return type
     */
    @Override
    public TileType getType() {
        return type;
    }

    /**
     * Getter for class name
     * @return class name
     */
    @Override
    public String getClassName() {
        return className;
    }


}
