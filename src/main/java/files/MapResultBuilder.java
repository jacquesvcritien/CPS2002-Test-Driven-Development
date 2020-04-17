package files;

import player.Player;

public class MapResultBuilder extends Builder
{
    private Page mapResultsPage;

    @Override
    public Page getPage() {
        if(mapResultsPage == null)
            mapResultsPage = new Page();
        
        return mapResultsPage;
    }

    @Override
    public void buildTitle(int player) {;
    }

    @Override
    public void buildMapView(Player player) {

    }

}
