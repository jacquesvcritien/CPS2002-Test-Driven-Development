package files;

import team.player.Player;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * This is the builder class which is used to build the page
 */
public abstract class Builder {

    //page
    protected Page page;

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
     * Build team.player title
     * @param player to build index for
     */
    public abstract void buildTitle(Player player);

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
