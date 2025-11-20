import greenfoot.*;

/**
 * Box - simple obstacle actor
 */
public class Box extends Actor {
    public Box() {
        // create a simple filled rectangle as the box image
        GreenfootImage img = new GreenfootImage(48, 48);
        img.setColor(new greenfoot.Color(100, 100, 100));
        img.fill();
        setImage(img);
    }
}
