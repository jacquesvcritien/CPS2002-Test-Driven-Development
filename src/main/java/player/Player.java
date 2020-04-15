package player;

public class Player {
    //stores position for player
    private Position position;

    /**
     * Method to set position
     * @param position position to set
     * @return true if move was successful, else false
     */
    public boolean setPosition(Position position)
    {
        this.position = position;
        return true;
    }

    /**
     *  Method to move player
     * @param direction direction to move
     * @throws NullPointerException just for code coverage
     */
    public void move(Direction direction){
        //get x coordinate
        int x = position.getxCoordinate();
        //get y coordinate
        int y = position.getyCoordinate();

        switch (direction)
        {
            case RIGHT: setPosition(new Position(x+1, y));break;
            case LEFT: setPosition(new Position(x-1, y));break;
            case UP: setPosition(new Position(x, y+1));break;
            default: setPosition(new Position(x, y-1));break;
        }
    }

    /**
     * Getter for position
     * @return position of player
     */
    public Position getPosition() {
        return position;
    }
}
