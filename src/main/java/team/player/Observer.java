package team.player;

import team.Team;

/**
 * Observer class
 */
public abstract class Observer {
    //subject
    protected Team team;

    /**
     * Method used to update all observers
     * @return if the update was successful
     */
    public abstract boolean update();
}