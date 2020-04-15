package player;

public class Player {
    //stores position for player
    private Position position;

    /**
     *  Method to move player
     * @param direction direction to move
     */
    public void move(Direction direction)
    {

    }

    /**
     * Getter for position
     * @return position of player
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Setter for position
     * @param position psoition to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
