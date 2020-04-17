package files;

import player.Player;

public abstract class Builder {

    /**
     * Method to get page
     * @return page
     */
    public abstract Page getPage();

    /**
     * Build player title
     * @param playerIndex index of player
     */
    protected abstract void buildTitle(int playerIndex);

    /**
     * Build map view
     * @param player whose data is to be built
     */
    protected abstract void buildMapView(Player player);
}
