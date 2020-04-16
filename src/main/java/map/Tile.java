package map;

public interface Tile {
    /**
     * Method to get html
     * @return html as string
     */
    public abstract String getHtml();

    /**
     * Method to get type
     * @return type of tile
     */
    public TileType getType();
}
