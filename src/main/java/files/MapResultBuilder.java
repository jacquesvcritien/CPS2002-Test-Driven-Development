package files;

import player.Player;

public class MapResultBuilder extends Builder
{
    private Page mapResultsPage = new Page();

    @Override
    public Page getPage() {
        return mapResultsPage;
    }

    @Override
    public void buildTitle(int player) {;
    }

    @Override
    public void buildMapView(Player player) {

    }

}
