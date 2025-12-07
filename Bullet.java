
/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;

public class Bullet extends Actor {
    public double angle;
    static GreenfootImage bulletImg = new GreenfootImage("./images/lazer_bullet.png");
    static {
        bulletImg.scale(20, 20);

    }

    /**
     * Constructor for objects of class Bullet
     */
    public Bullet(double angle) {
        this.angle = angle;
        setImage(bulletImg);
        setRotation((int) (angle * 180 / Math.PI));
        Greenfoot.playSound("./sounds/laser-312360.mp3");
    }

    public void act() {

        move(25);
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
