import greenfoot.*;

public class StatsUI implements AfterAdded {
    static StatsUI instance = new StatsUI();
    Powerbar powerbar;
    Hearts hearts;

    public StatsUI() {
        powerbar = new Powerbar();
        hearts = new Hearts();
    }

    @Override
    public void afterAdded() {
        Screen world = Screen.currentScreen;
        update();
        int start = world.getHeight() - powerbar.getImage().getHeight() / 2 - 200;
        int leftStart = 50;
        world.addObject(powerbar, powerbar.getImage().getWidth() / 2 + leftStart,
                start);
        world.addObject(hearts, start + hearts.getImage().getWidth() / 2, leftStart);
    }

    public void update() {
        powerbar.update();
        hearts.update();
    }

}
