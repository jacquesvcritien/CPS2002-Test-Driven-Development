package map;

public class TreasureTile extends Tile{

    private TileType type;
    private String className;
    private final String html = "<td class=\"$class\"><img class=\"tile-img\" src=\"images/treasure.png\">$player</td>";

    public TreasureTile()
    {
        this.className = "grass-back";
        this.type = TileType.TREASURE;
    }

    /**
     * Getter for html
     * @return
     */
    @Override
    public String getHtml() {
        return html;
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
     * Getetr for class name
     * @return class name
     */
    @Override
    public String getClassName() {
        return className;
    }
}
