package team.player;

import team.Subject;

/**
 * Observer class
 */
public abstract class Observer {
    //subject
    protected Subject team;

    /**
     * Method used to update all observers
     * @return if the update was successful
     */
    public abstract boolean update();

    /**
     * Getter for team
     * @return team
     */
    public Subject getTeam() {
        return team;
    }
}