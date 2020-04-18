package files;

import player.Player;

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
     * @param players players for whom html will be created
     */
    public void construct(Player[] players) {

        for(int i=0; i < players.length; i++)
        {
            builder.buildTitle(i+1);
            builder.buildMapView(players[i]);
            builder.buildMoves(players[i]);
        }
    }

}
