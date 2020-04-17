package files;

public abstract class Builder {

    /**
     * Method to get page
     * @return page
     */
    public abstract Page getPage();

    /**
     * Build player title
     */
    protected abstract void buildTitle(int playerIndex);

    /**
     * Build map view
     */
    protected abstract void buildMapView();
}
