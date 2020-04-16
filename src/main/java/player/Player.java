package player;

import map.Map;

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
     */
    public boolean move(Direction direction){
        Map map = Map.getMap();
        //get x coordinate
        int x = position.getxCoordinate();
        //get y coordinate
        int y = position.getyCoordinate();

        switch (direction)
        {
            case RIGHT:
            {
                //check if at the edge
                if(position.getxCoordinate() == map.getSize())
                    return false;
                setPosition(new Position(x+1, y));
            }break;
            case LEFT:
            {
                //check if at the edge
                if(position.getxCoordinate() == 0)
                    return false;
                setPosition(new Position(x-1, y));
            }break;
            case UP:
            {
                //check if at the edge
                if(position.getyCoordinate() == map.getSize())
                    return false;
                setPosition(new Position(x, y+1));
            }break;
            default:
            {

                //check if at the edge
                if(position.getyCoordinate() == 0)
                    return false;
                setPosition(new Position(x, y-1));
            }break;
        }

        return true;
    }

    /**
     * Getter for position
     * @return position of player
     */
    public Position getPosition() {
        return position;
    }
}
