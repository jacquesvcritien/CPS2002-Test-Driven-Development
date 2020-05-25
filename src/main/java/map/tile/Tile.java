package map.tile;

/**
 * This is a class which represents a tile
 */
public abstract class Tile {
    //This is the initial html code for a tile
    private String html = "<td class=\"$class\">$team.player</td>";

    /**
     * Method to get html
     * @return html as string
     */
    public String getHtml()
    {
        return html;
    }

    /**
     * Getetr for class name
     * @return class name
     */
    public abstract String getClassName();

    /**
     * Getter for type
     * @return type
     */
    public abstract TileType getType();
}
