package map;

public class GreenTile implements Tile{

    private TileType type;
    private final String html = "<td class=\"grass-back $visited\"></td>";

    public GreenTile()
    {
        this.type = TileType.GREEN;
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
