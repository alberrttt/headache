import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Actor {
    static final String[] paths = { "R2", "R3", "R4" };
    public int current = 0;
    public int delta = 0;

    static final String idle = "R1";

    public String a(String x) {
        return "./images/" + x + ".png";
    }

    public Player() {

        String path = a(idle);
        GreenfootImage img = new GreenfootImage(path);
        setImage(img);
        img.scale(100, 200);

    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() {

        int currentX = getX();
        int currentY = getY();
        int mov = 5;
        // Add your action code here.
        if (Greenfoot.isKeyDown("w")) {
            currentY -= mov;
            move();
        }
        if (Greenfoot.isKeyDown("a")) {
            currentX -= mov;
            move();
        }
        if (Greenfoot.isKeyDown("s")) {
            currentY += mov;
            move();
        }
        if (Greenfoot.isKeyDown("d")) {
            currentX += mov;
            move();
        }
        setLocation(currentX, currentY);

    }

    public void move() {
        delta += 1;
        if (delta % 5 == 0) {
            String path = a(paths[current]);
            GreenfootImage img = new GreenfootImage(path);
            img.scale(100, 200);

            setImage(img);
            current = (current + 1) % paths.length;
        }

        if (isTouching(Pill.class))
        {
            removeTouching(Pill.class); 
        }
    }
}
