package map.tile;

/**
 * This is a class which represents a green tile
 */
public class GreenTile extends Tile{

    private TileType type;
    private String className;

    public GreenTile()
    {
        this.className = "grass-back";
        this.type = TileType.GREEN;
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
