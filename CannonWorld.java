import greenfoot.GreenfootImage;

public class CannonWorld extends Screen {
    public CannonWorld(Screen left, Screen right, Screen top, Screen bottom) {

        super();
        this.setAdjacentScreens(top, bottom, left, right);
        init();
    }

    public CannonWorld() {
        super();
        init();
        Player p = Player.getInstance();
        this.addObject(p, 0, getHeight() / 2);
        Screen.currentScreen = this;
        p.afterAdded();
    }

    public void init() {
        setBackground("./images/cannon_bg.png");
        placeCannoneersOn(150);
        placeCannoneersOn(getHeight() - 150);
    }

    public void placeCannoneersOn(int yPos) {
        Cannoneer sample = new Cannoneer();
        GreenfootImage img = sample.getImage();

        int cannoneerCount = 2;
        int padding = 50;
        int y = img.getHeight() / 2 + padding + yPos;
        int gap = getWidth() / (cannoneerCount + 1);

        for (int i = 0; i < cannoneerCount; i++) {
            int x = gap * (i + 1);
            Cannoneer cannoneer = new Cannoneer();
            this.addObject(cannoneer, x, yPos);
        }

    }
}
