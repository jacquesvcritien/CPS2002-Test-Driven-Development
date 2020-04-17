package map;

public class BlueTile implements Tile{

    private TileType type;
    private final String html = "<td class=\"water-back $visited\"></td>";

    public BlueTile()
    {
        this.type = TileType.BLUE;
    }

    @Override
    public String getHtml() {
        return html;
    }

    @Override
    public TileType getType() {
        return type;
    }
}
