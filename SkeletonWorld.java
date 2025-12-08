import greenfoot.Greenfoot;

public class SkeletonWorld extends Screen {
    public SkeletonWorld(Screen left) {
        super();
        init();
        this.left = left;
    }

    public SkeletonWorld() {
        super();
        Player p = new Player();
        StatsUI.instance = new StatsUI();
        this.currentScreen = this;
        this.addObject(p, getWidth() / 2, getHeight() / 2);

        init();

    }

    public void init() {
        for (int i = 0; i < 7; i++) {
            spawnSkeleton();
        }
    }

    public void spawnSkeleton() {
        Skeleton skeleton = new Skeleton();

        int x, y;
        boolean validLocation = false;
        while (!validLocation) {
            x = Greenfoot.getRandomNumber(2 * getWidth() / 3) + (getWidth() / 3);
            y = Greenfoot.getRandomNumber(getHeight());
            // Check if outside the 250x250 center area
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int threshold = 125; // 250/2
            if (Math.abs(x - centerX) > threshold || Math.abs(y - centerY) > threshold) {
                validLocation = true;
                this.addObject(skeleton, x, y);
            }
        }
    }
}
