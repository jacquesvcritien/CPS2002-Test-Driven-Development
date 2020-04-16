package player;

public class Position {
    private int xCoordinate;
    private int yCoordinate;

    //empty constructor
    public Position(){}

    //constructor
    public Position(int xCoordinate, int yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /*
    Getter for x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /*
    Getter for y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Setter for x coordinate
     * @param xCoordinate the coordinate to set
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Setter for y coordinate
     * @param yCoordinate the coordinate to set
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Overriding equals method to compare coordinates to check position
     * @param object point to check
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Position)) {
            return false;
        }
        return (xCoordinate == ((Position) object).getxCoordinate() && yCoordinate == ((Position) object).getyCoordinate());
    }
}
