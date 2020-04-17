package files;

import map.Map;
import map.Tile;
import map.TileType;
import player.Direction;
import player.Player;
import player.Position;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;

public class MapResultBuilder extends Builder
{
    private Page mapResultsPage;
    private final String filename = "game.html";
    private final String playerPosHTML = "<img class=\"tile-img\" src=\"images/user.png\">";

    @Override
    public Page getPage() {
        if(mapResultsPage == null)
            mapResultsPage = new Page();

        return mapResultsPage;
    }

    @Override
    public void init() throws IOException, URISyntaxException {
        mapResultsPage = new Page();
        mapResultsPage.setHTML(Helper.readResourcesFileAsString(filename));
    }

    @Override
    public void buildTitle(int player) {
        String newHtml = mapResultsPage.getHTML().replace("$index", String.valueOf(player));
        mapResultsPage.setHTML(newHtml);
    }

    @Override
    public void buildMapView(Player player) {
        //get map
        Map map = Map.getMap();
        //get map size
        int size = map.getSize();
        //get players visited tile
        Set<Tile> visited = player.getTilesVisited();

        StringBuilder html = new StringBuilder();
        //get map tiles
        Tile[][] mapTiles=  map.getMapTiles();

        //player position
        Position playerPosition = player.getPosition();


        //iterate through tiles
        for(int i=0; i < size; i++)
        {
            html.append("<tr>");
            for(int j=0; j < size; j++)
            {
                //get current tile in loop
                Tile currentTile = mapTiles[i][j];
                //get tile html
                String tileHtml = currentTile.getHtml();

                //if its not visited
                if(!visited.contains(currentTile))
                {
                    //set class to not-visited
                    tileHtml = tileHtml.replace("$class", "not-visited");

                    //if its a treasure, hide treasure image
                    if (currentTile.getType() == TileType.TREASURE)
                        tileHtml = tileHtml.replace("<img class=\"tile-img\" src=\"images/treasure.png\">", "");

                }
                else
                    tileHtml = tileHtml.replace("$class", currentTile.getClassName());

                //if player position, add position html, else remove '$player'
                tileHtml = (i == playerPosition.getxCoordinate() && j == playerPosition.getyCoordinate()) ?
                            tileHtml.replace("$player", playerPosHTML) : tileHtml.replace("$player", "");

                //add html for tile
                html.append(tileHtml);
            }

            //close row
            html.append("</tr>");
        }

        //replace placeholder
        String mapResult = mapResultsPage.getHTML().replace("$mapResult", html.toString());
        mapResultsPage.setHTML(mapResult);
    }

    @Override
    public void buildMoves(Player player) {

        ArrayList<Direction> playerMoves = player.getMoves();
        StringBuilder html = new StringBuilder();
        for (int i=0; i < playerMoves.size(); i++)
        {
            Direction direction = playerMoves.get(i);
            html.append("<p>" + direction + "</p>");
        }

        String moves = mapResultsPage.getHTML().replace("$moves", html.toString());
        mapResultsPage.setHTML(moves);

    }

}
