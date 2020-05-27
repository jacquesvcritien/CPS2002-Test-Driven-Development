package team;


import team.player.Direction;
import team.player.Observer;
import team.player.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a class which represents a team and it acts as a subject in
 * the observer design pattern
 */
public abstract class Subject {
    //this holds the players
    protected List<Observer> players = new ArrayList<>();
    //this variable holds the direction as a state
    protected Direction directionState;

    /**
     * Method to get state
     * @return team's direction
     */
    public Direction getDirectionState()
    {
        return directionState;
    }


    /**
     * Method to attach a player to the team
     * @param observer player to attach
     */
    public void attach(Observer observer){
        players.add(observer);
    }

    /**
     * Method to notify all players and update them
     * @return if state is possible
     */
    public boolean notifyAllPlayers(){
        for (Observer player : players) {
            //if the state is not correct, return false immediately
            if(!player.update())
                return false;
        }
        return true;
    }

    /**
     * Getter for players
     * @return players
     */
    public List<Observer> getPlayers() {
        return players;
    }

}
