package team;

import exceptions.MapNotSetException;
import map.Map;
import team.player.Direction;
import team.player.Observer;
import team.player.Player;
import team.player.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    /**
     * This is a class which represents a team and it acts as a subject in
     * the observer design pattern
     */
    public class Team extends Subject {
        //this holds the starting position o the team
        private Position start;
        //holds current player index for turn
        int nextPlayerTurn;
        //holds team's id
        int id;
        //this variable holds the direction as a state
        protected Direction state;

        /**
         * Constructor which sets the random and the starting position
         * @param id id
         * @param random random number
         * @throws MapNotSetException thrown when map is not set and generate starting is called
         */
        public Team(Random random, int id) throws MapNotSetException {
            //holds random number
            this.id = id;
            this.start = Map.generateStarting(random);
        }
        /**
         * Method to get state
         * @return team's direction
         */
        public int getNextPlayerTurn() {
            return ((Player)observers.get(nextPlayerTurn)).getId();
        }

        /**
         * Method to set state (direction)
         * @param state direction to set as state
         * @return if state is possible
         */
        public boolean setState(Direction state) {
            this.state = state;
            //if move was successful, increment next player turn counter and return true
            if(notifyAllObservers())
            {
                //check if next index is out of bounds
                if(nextPlayerTurn == observers.size()-1)
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
         * Getter for starting position
         * @return starting position
         */
        public Position getStart() {
            return start;
        }

        /**
         * Getter for team's id
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Method to get state
         * @return team's direction
         */
        public Direction getState()
        {
            return state;
        }
    }
