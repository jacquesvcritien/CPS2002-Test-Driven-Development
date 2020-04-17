package player;

import map.Map;
import map.Tile;
import map.TileType;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Player {
    //stores position for player
    private Position position;
    private Position start;
    private Set<Tile> tilesVisited;

    /**
     * Constructor
     * @param random
     */
    public Player(Random random)
    {
        //set position
        generateStarting(random);
        this.position = this.start;
        tilesVisited = new HashSet<Tile>();
        addVisitedTile(this.start);
    }

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
     * Method to generate a starting position
     * @param random
     */
    private void generateStarting(Random random)
    {
        //generate coordinates
        int[] generatedCoordinates;
        //set position
        Position newPosition;

        //do while you find a valid position
        do{
            generatedCoordinates = Map.getMap().getRandomCoordinates(random);
            newPosition = new Position(generatedCoordinates[0], generatedCoordinates[1]);
        }while(Map.getMap().getMapTile(newPosition).getType() != TileType.GREEN);

        this.start = newPosition;

    }

    /**
     * Method to get visited tiles
     * @return set of visited tiles
     */
    public Set<Tile> getTilesVisited() {
        return tilesVisited;
    }

    /**
     * Method to set start position
     * @param start position to set
     * @return true if move was successful, else false
     */
    public boolean setStart(Position start)
    {
        this.start = start;
        return true;
    }

    /**
     * Add a new visited tile
     * @param position position of tile to add to player's visited tiles
     */
    private void addVisitedTile(Position position)
    {
        tilesVisited.add(Map.getMap().getMapTile(position));
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

                Position newPosition = new Position(x+1, y);
                //set new position
                setPosition(newPosition);
                //add tile
                addVisitedTile(newPosition);
            }break;
            case LEFT:
            {
                //check if at the edge
                if(position.getxCoordinate() == 0)
                    return false;
                Position newPosition = new Position(x-1, y);
                //set new position
                setPosition(newPosition);
                //add tile
                addVisitedTile(newPosition);
            }break;
            case UP:
            {
                //check if at the edge
                if(position.getyCoordinate() == map.getSize())
                    return false;
                Position newPosition = new Position(x, y+1);
                //set new position
                setPosition(newPosition);
                //add tile
                addVisitedTile(newPosition);
            }break;
            default:
            {

                //check if at the edge
                if(position.getyCoordinate() == 0)
                    return false;
                Position newPosition = new Position(x, y-1);
                //set new position
                setPosition(newPosition);
                //add tile
                addVisitedTile(newPosition);
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

    /**
     * Getter for start position
     * @return start position of player
     */
    public Position getStart() {
        return start;
    }
}
