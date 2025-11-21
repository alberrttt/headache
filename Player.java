import java.util.ArrayList;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT
}
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
    public Direction facing = Direction.LEFT;
    public String a(String x) {
        return "./images/" + x + ".png";
    }
    public static int width = 75;
    public static int height = 150;
    public Healthbar hb;
    public Player(Healthbar hb) {
        this.hb = hb;
        String path = a(idle);
        GreenfootImage img = new GreenfootImage(path);
        img.scale(width, height);
        setImage(img);


    }



    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() {
        int currentX = getX();
        int currentY = getY();
        int mov = 5;

        Direction dir = null;
        if (Greenfoot.isKeyDown("w")) {
            currentY -= mov;
            dir = Direction.UP;
        }
      
        if (Greenfoot.isKeyDown("s")) {


            currentY += mov;
            dir = Direction.DOWN;
        }
          if (Greenfoot.isKeyDown("a")) {
            currentX -= mov;
            dir = Direction.LEFT;
        }
        if (Greenfoot.isKeyDown("d")) {
            currentX += mov;
            dir = Direction.RIGHT;
        }

        boolean moved = Math.abs(currentX-getX()) > 0 || Math.abs(currentY - getY()) > 0;
        int oldX = getX();
        int oldY = getY();
        setLocation(currentX, currentY);
        this.hb.setLocation(currentX, currentY-100);

        boolean blocked = false;
        if (isTouching(Box.class)) {
            // revert move when colliding with a Box
            setLocation(oldX, oldY);
            blocked = true;
        }

        if (moved && !blocked) {

            move(dir);
            ((Screen)getWorld()).checkActorCollisions(this);

        } else {
            String path = a(idle);
            GreenfootImage img = new GreenfootImage(path);
            img.scale(width, height);

            setImage(img);
        }
        facing = dir;

    }
    public void placeInNewScreen() {

    }
    public void move(Direction dir) {
        
        delta += 1;
        if (delta % 5 == 0) {
            String path = a(paths[current]);
            GreenfootImage img = new GreenfootImage(path);
            img.scale(width, height);
            if (dir == Direction.LEFT) {
                img.mirrorHorizontally();
            }
            setImage(img);

            current = (current + 1) % paths.length;
        }
        
        if (isTouching(Pill.class))
        {
            ArrayList<Pill> pills = (ArrayList<Pill>) getIntersectingObjects(Pill.class);
            for (Pill pill : pills) {
                pill.collect();
            }
        }

    }
}
