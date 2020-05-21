package team.player;

import exceptions.MapNotSetException;
import map.Map;
import map.Tile;
import map.TileType;
import game.Game;

import java.util.*;

/**
 * Class which represents a position
 */
public class Player {
    //stores position for team.player
    private Position position;
    //stores starting position
    private Position start;
    //stores visited tiles
    private Set<Tile> tilesVisited;
    //stores moves
    private ArrayList<Direction> moves;

    /**
     * Constructor
     * @param random random generator to use
     */
    public Player(Random random) throws MapNotSetException {
        //set position
        generateStarting(random);
        this.position = this.start;
        tilesVisited = new HashSet<>();
        this.moves = new ArrayList<>();
        addVisitedTile(this.start);
    }

    /**
     * Method to set position
     * @param position position to set
     */
    public void setPosition(Position position)
    {
        this.position = position;
    }

    /**
     * Method to generate a starting position
     * @param random random generator to use
     */
    public void generateStarting(Random random) throws MapNotSetException {
        //generate coordinates
        int[] generatedCoordinates;
        //set position
        Position newPosition;
        boolean goodPath;

        //do while you find a valid position
        do{ generatedCoordinates = Game.getMap().getRandomCoordinates(random);
            newPosition = new Position(generatedCoordinates[0], generatedCoordinates[1]);
            //check if it is a good path
            goodPath = Game.getMap().goodPath(Game.getMap().getMapTiles(), newPosition.getyCoordinate(), newPosition.getxCoordinate());
        }while(Game.getMap().getMapTile(newPosition).getType() != TileType.GREEN || !goodPath);

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
     */
    public void setStart(Position start)
    {
        this.start = start;
    }

    /**
     * Add a new visited tile
     * @param position position of tile to add to team.player's visited tiles
     */
    private void addVisitedTile(Position position)
    {
        tilesVisited.add(Game.getMap().getMapTile(position));
    }

    /**
     * Method to get moves
     * @return list of moves
     */
    public ArrayList<Direction> getMoves() {
        return moves;
    }

    /**
     *  Method to move team.player
     * @param direction direction to move
     */
    public boolean move(Direction direction){
        Map map = Game.getMap();
        //get x coordinate
        int x = position.getxCoordinate();
        //get y coordinate
        int y = position.getyCoordinate();

        Position newPosition = new Position();
        newPosition.setxCoordinate(x);
        newPosition.setyCoordinate(y);

        switch (direction)
        {
            case RIGHT:
            {
                //check if at the edge
                if(position.getxCoordinate() == map.getSize()-1)
                {
                    System.out.println("You are on the edge, you cannot move right");
                    return false;
                }

                newPosition.setxCoordinate(x+1);
            }
            break;
            case LEFT:
            {
                //check if at the edge
                if(position.getxCoordinate() == 0)
                {
                    System.out.println("You are on the edge, you cannot move left");
                    return false;
                }

                newPosition.setxCoordinate(x-1);
            }
            break;
            case UP:
            {
                //check if at the edge
                if(position.getyCoordinate() == 0)
                {
                    System.out.println("You are on the edge, you cannot move up");
                    return false;
                }

                newPosition.setyCoordinate(y-1);
            }
            break;
            default:
            {

                //check if at the edge
                if(position.getyCoordinate() == map.getSize()-1)
                {
                    System.out.println("You are on the edge, you cannot move down");
                    return false;
                }

                newPosition.setyCoordinate(y+1);

            }break;
        }

        Tile newTile = map.getMapTile(newPosition);
        TileType type = newTile.getType();

        switch (type)
        {
            case TREASURE:
            {
                //set winner
                Game.setWinner(this);
                //set new position
                setPosition(newPosition);
                //add tile
                addVisitedTile(newPosition);
                //add to moves
                this.moves.add(direction);
            }break;
            case BLUE:
            {
                //add tile
                addVisitedTile(newPosition);
                //sent to start
                setPosition(start);
                //add to moves
                this.moves.add(direction);
            }
            break;
            default:
            {
                //set new position
                setPosition(newPosition);
                //add tile
                addVisitedTile(newPosition);
                //add to moves
                this.moves.add(direction);
            }
        }




        return true;
    }

    /**
     * Getter for position
     * @return position of team.player
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getter for start position
     * @return start position of team.player
     */
    public Position getStart() {
        return start;
    }
}
