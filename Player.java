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
        setLocation(currentX, currentY);

        if (moved) {
            move(dir);
            
        } else {
            String path = a(idle);
            GreenfootImage img = new GreenfootImage(path);
            img.scale(100, 200);

            setImage(img);
        }
        facing = dir;

    }

    public void move(Direction dir) {

        delta += 1;
        if (delta % 5 == 0) {
            String path = a(paths[current]);
            GreenfootImage img = new GreenfootImage(path);
            img.scale(100, 200);
            if (dir == Direction.LEFT) {
                img.mirrorHorizontally();
            }
            setImage(img);

            current = (current + 1) % paths.length;
        }
        ((Screen)getWorld()).checkActorCollisions(this);
        
        if (isTouching(Pill.class))
        {
            ArrayList<Pill> pills = (ArrayList<Pill>) getIntersectingObjects(Pill.class);
            for (Pill pill : pills) {
                pill.collect();
            }
        }
    }
}
