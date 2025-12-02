/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;
public class Bullet  extends Actor
{
    public double angle;

    /**
     * Constructor for objects of class Bullet
     */
    public Bullet(double angle)
    {
        this.angle = angle;
        String path = "./images/lazer_bullet.png";
        GreenfootImage img = new GreenfootImage(path);
        img.scale(20, 20);
        setImage(img);
        setRotation((int)(angle*180/Math.PI));
    }

    public void act() {

        move(15);
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
