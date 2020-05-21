package team;

import exceptions.MapNotSetException;
import files.Director;
import game.Game;
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
    //holds random number
    private Random random;

    /**
     * Constructor which sets the random and the starting position
     * @throws MapNotSetException
     */
    public Team(Random random) throws MapNotSetException {
        random = random;
        this.start = Game.getMap().generateStarting(random);
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
     */
    public void setState(Direction state) {
        this.directionState = state;
        notifyAllPlayers();
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
     */
    public void notifyAllPlayers(){
        for (Player player : players) {
            player.update();
        }
    }

    /**
     * Getter for starting position
     * @return starting position
     */
    public Position getStart() {
        return start;
    }
}
