package team;

import exceptions.MapNotSetException;
import map.Map;
import team.player.Direction;
import team.player.Player;
import team.player.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a class which represents a team and it acts as a subject in
 * the observer design pattern
 */
public class Team {
    //this holds the players
    private List<Player> players = new ArrayList<>();
    //this variable holds the direction as a state
    private Direction directionState;
    //this holds the starting position o the team
    private Position start;
    //holds current player index for turn
    int nextPlayerTurn;
    //holds team's index
    int index;

    /**
     * Constructor which sets the random and the starting position
     * @param index index
     * @param random random number
     * @throws MapNotSetException thrown when map is not set and generate starting is called
     */
    public Team(Random random, int index) throws MapNotSetException {
        //holds random number
        this.index = index;
        this.start = Map.generateStarting(random);
    }
    /**
     * Method to get state
     * @return team's direction
     */
    public Direction getDirectionState()
    {
        return directionState;
    }

    /**
     * Method to set state (direction)
     * @param state direction to set as state
     * @return if state is possible
     */
    public boolean setState(Direction state) {
        this.directionState = state;
        //if move was successful, increment next player turn counter and return true
        if(notifyAllPlayers())
        {
            //check if next index is out of bounds
            if(nextPlayerTurn == players.size()-1)
                nextPlayerTurn = 0;
            else
                nextPlayerTurn++;

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Getter for next player turn
     * @return next player's index
     */
    public int getNextPlayerTurn() {
        return players.get(nextPlayerTurn).getId();
    }

    /**
     * Getter for players
     * @return players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Method to attach a player to the team
     * @param observer player to attach
     */
    public void attach(Player observer){
        players.add(observer);
    }

    /**
     * Method to notify all players and update them
     * @return if state is possible
     */
    public boolean notifyAllPlayers(){
        for (Player player : players) {
            //if the state is not correct, return false immediately
            if(!player.update())
                return false;
        }
        return true;
    }

    /**
     * Getter for starting position
     * @return starting position
     */
    public Position getStart() {
        return start;
    }

    /**
     * Getter for index
     * @return index
     */
    public int getIndex() {
        return index;
    }
}
