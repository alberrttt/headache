
import greenfoot.GreenfootImage;

public class CannonWorld extends Screen {
    public CannonWorld(Screen left, Screen right, Screen top, Screen bottom) {

        super();
        SkeletonWorld world = new SkeletonWorld(this);

        this.setAdjacentScreens(top, bottom, left, world);
        init();
    }

    public CannonWorld() {
        super();
        SkeletonWorld world = new SkeletonWorld(this);
        this.right = world;
        init();

        Player p = new Player();
        this.addObject(p, 0, getHeight() / 2);
        Screen.currentScreen = this;
    }

    public void init() {
        setBackground("./images/cannon_bg.png");
        placeCannoneersOn(150);
        placeCannoneersOn(getHeight() - 150);
        for (int i = 0; i < 5; i++) {
            Pill.spawnPill(this, 500);
        }
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
