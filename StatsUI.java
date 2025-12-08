import greenfoot.*;

public class StatsUI extends Actor {
    static StatsUI instance = new StatsUI();
    Powerbar powerbar;
    Heartsbar hearts;

    public StatsUI() {
        powerbar = new Powerbar();
        hearts = new Heartsbar(null, 1, 0);
    }

    @Override
    public void addedToWorld(World w) {
        int start = w.getHeight() - powerbar.getImage().getHeight() / 2 - 200;
        int leftStart = 50;
        w.addObject(powerbar, powerbar.getImage().getWidth() / 2 + leftStart,
                start);
        w.addObject(hearts, start + hearts.getImage().getWidth() / 2, leftStart);
        update();

    }

    public void update() {
        powerbar.update();
        hearts.update();
    }

}
