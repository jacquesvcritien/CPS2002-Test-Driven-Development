package team.player;

import exceptions.MapNotSetException;
import game.GameMode;
import map.Map;
import map.Tile;
import map.TileType;
import game.Game;
import team.Team;

import java.util.*;

/**
 * Class which represents a position
 */
public class Player extends Observer{
    //stores position for team.player
    private Position position;
    //stores starting position
    private Position start;
    //stores visited tiles
    private Set<Tile> tilesVisited;
    //stores moves
    private ArrayList<Direction> moves;
    //stores team
    private Team team;
    //stores id
    private int id;

    /**
     * Constructor with id
     * @param id id to set
     */
    public Player(int id){
        this.id = id;
    }

    /**
     * Constructor
     *
     * @param random random generator to use
     * @param id id
     */
    public Player(Random random, int id) throws MapNotSetException {
        //set position
        this.start = Map.generateStarting(random);
        this.position = this.start;
        //init tiles visited
        tilesVisited = new HashSet<>();
        //init moves
        this.moves = new ArrayList<>();
        //add starting tile to tiles visited
        addVisitedTile(this.start);
        //set index
        this.id = id;
    }

    /**
     * This method prepares the player by setting the teams starting position
     * @param teamStart team's starting position
     */
    public void setup(Position teamStart, Team team)
    {
        //set team
        this.team = team;
        //set position
        this.start = teamStart;
        this.position = this.start;
        //init tiles visited
        tilesVisited = new HashSet<>();
        //init moves
        this.moves = new ArrayList<>();
        //add starting tile to tiles visited
        addVisitedTile(this.start);
    }

    /**
     * Method to set position
     *
     * @param position position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method to get visited tiles
     *
     * @return set of visited tiles
     */
    public Set<Tile> getTilesVisited() {
        return tilesVisited;
    }

    /**
     * Method to set start position
     *
     * @param start position to set
     */
    public void setStart(Position start) {
        this.start = start;
    }

    /**
     * Add a new visited tile
     *
     * @param position position of tile to add to team.player's visited tiles
     */
    private void addVisitedTile(Position position) {
        tilesVisited.add(Game.getMap().getMapTile(position));
    }

    /**
     * Method to get moves
     *
     * @return list of moves
     */
    public ArrayList<Direction> getMoves() {
        return moves;
    }

    /**
     * Method to update player
     * @return if the move is successful
     */
    @Override
    public boolean update()
    {
        //get direction and move to that direction
        return move(this.team.getDirectionState());
    }

    /**
     * Method to move player
     * @param direction direction to move
     * @return if the move is successful
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

                //if collaborative, set winning team
                if(Game.getGameMode() == GameMode.COLLABORATIVE)
                    Game.setWinningTeam(team);

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

    /**
     * Getter for team
     * @return team
     */
    public Team getTeam() {
        return team;
    }

}
