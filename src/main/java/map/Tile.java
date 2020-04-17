package map;

public abstract class Tile {
    private String html = "<td class=\"$class\">$player</td>";

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
