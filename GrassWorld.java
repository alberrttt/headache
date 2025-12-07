import greenfoot.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class GrassWorld extends Screen {

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    static GreenfootImage[] treeImages = {
            new GreenfootImage("./images/tree0.png"),
            new GreenfootImage("./images/tree1.png"),
            new GreenfootImage("./images/tree2.png"),
            new GreenfootImage("./images/tree3.png"),
    };
    static {
        for (GreenfootImage img : treeImages) {
            img.scale(400, 400);
        }
    }

    public GrassWorld() {
        super();
        this.mayLeave = true;
        Player player = new Player();
        this.addObject(player, getWidth() / 2, getHeight() / 2);
        Screen.currentScreen = this;

        int medicines = 10;
        for (int i = 0; i < medicines; i++) {
            // make them spawn in the area that is not 250x250 from center
            spawnPill();
        }
        player.afterAdded();

        setBackground("./images/grass.png");
        getBackground().drawImage(treeImages[0], 200, 50);
        Screen right = new V1World(null, null, this, null);
        this.setAdjacentScreens(null, null, null, right);
    }

    public void spawnPill() {
        Actor pill = new Pill();

        int x, y;
        boolean validLocation = false;
        while (!validLocation) {
            x = Greenfoot.getRandomNumber(getWidth());
            y = Greenfoot.getRandomNumber(getHeight());
            // Check if outside the 250x250 center area
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int threshold = 125; // 250/2
            if (Math.abs(x - centerX) > threshold || Math.abs(y - centerY) > threshold) {
                validLocation = true;
                this.addObject(pill, x, y);
            }
        }
    }

    public static void main(String[] args) {
    }

}
