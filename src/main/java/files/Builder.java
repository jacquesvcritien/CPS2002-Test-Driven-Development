package files;

import player.Player;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Builder {

    /**
     * Method to get page
     * @return page
     */
    public abstract Page getPage();

    /**
     * Method to initialise page to template
     */
    public abstract void init() throws IOException, URISyntaxException;

    /**
     * Build player title
     * @param playerIndex index of player
     */
    public abstract void buildTitle(int playerIndex);

    /**
     * Build map view
     * @param player whose data is to be built
     */
    public abstract void buildMapView(Player player);

    /**
     * Build moves
     * @param player whose data is to be built
     */
    public abstract void buildMoves(Player player);

    /**
     * Build winner
     * @param player whose data is to be built
     */
    public abstract void buildWinner(Player player);
}
