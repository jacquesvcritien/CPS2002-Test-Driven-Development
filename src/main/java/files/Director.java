package files;

import player.Player;

import java.io.IOException;
import java.net.URISyntaxException;

public class Director {

    Builder builder;

    /**
     * Constructor
     * @param builder builder to use
     */
    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * Method to construct html by calling builders
     * @param player player for whom html will be created
     */
    public void construct(Player player, int index) throws IOException, URISyntaxException {
        builder.init();
        builder.buildTitle(index);
        builder.buildMapView(player);
        builder.buildMoves(player);
    }

}
