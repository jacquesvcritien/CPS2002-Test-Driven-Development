package files;

import game.Game;
import map.Map;
import map.tile.Tile;
import map.tile.TileType;
import team.player.Direction;
import team.player.Player;
import team.player.Position;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

/**
 * This is the actual class which builds the page showing the map for the team.player, in fact, it extends the builder class
 */
public class MapResultBuilder extends Builder
{
    
    @Override
    public Page getPage() {
        if(page == null)
            page = new Page();

        return page;
    }

    @Override
    public void init() throws IOException, URISyntaxException {
        page = new Page();
        String filename = "game.html";
        page.setHTML(Helper.readResourcesFileAsString(filename));
    }

    @Override
    public void buildTitle(Player player) {
        String newHtml = page.getHTML().replace("$index", String.valueOf(player.getId()));
        //if player does not have a team, then the game mode is collaborative and if so fill team index, else remove it
        newHtml = (player.getTeam() != null) ? newHtml.replace("$teamTitle", "<div class=\"table-title\">Team "+player.getTeam().getIndex()+"</div>") : newHtml.replace("$teamTitle", "");

        page.setHTML(newHtml);
    }

    @Override
    public void buildMapView(Player player) {
        //get map
        Map map = Game.getMap();
        //get map size
        int size = map.getSize();
        //get players visited tile
        Set<Tile> visited = player.getTilesVisited();

        StringBuilder html = new StringBuilder();
        //get map tiles
        Tile[][] mapTiles=  map.getMapTiles();

        //team.player position
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

                //if team.player position, add position html, else remove '$team.player'
                String playerPosHTML = "<img class=\"tile-img\" src=\"images/user.png\">";
                tileHtml = (j == playerPosition.getxCoordinate() && i == playerPosition.getyCoordinate()) ?
                        tileHtml.replace("$team.player", playerPosHTML) : tileHtml.replace("$team.player", "");

                //add html for tile
                html.append(tileHtml);
            }

            //close row
            html.append("</tr>");
        }

        //replace placeholder
        String mapResult = page.getHTML().replace("$mapResult", html.toString());
        page.setHTML(mapResult);
    }

    @Override
    public void buildMoves(Player player) {

        //get moves
        ArrayList<Direction> playerMoves = player.getMoves();
        StringBuilder html = new StringBuilder();

        //add moves
        for (Direction direction : playerMoves) {
            html.append("<p>").append(direction).append("</p>");
        }

        String moves = page.getHTML().replace("$moves", html.toString());
        page.setHTML(moves);

    }

    @Override
    public void buildWinner(Player player) {

        final String winnerHTML = "<h2 class=\"winner\">Congratulations, You found the treasure!</h2>";
        //if the team.player is a winner
        if(Game.isAWinner(player))
        {
            String winner = page.getHTML().replace("$winner-class", "winner-bg");
            winner = winner.replace("$winner", winnerHTML);
            page.setHTML(winner);
        }
        else
        {
            String winner = page.getHTML().replace("$winner-class", "");
            winner = winner.replace("$winner", "");
            page.setHTML(winner);
        }

    }

}
