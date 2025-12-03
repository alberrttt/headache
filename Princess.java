import greenfoot.*;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
public class Princess extends Boss {
    SweepingLaser laser = new SweepingLaser();
    public int delta = 0;
    public Princess() {
        super("./images/princess.png");
        this.health = 5;

    }
    public void init() {
    }
    public void onHit() {
        flashRed();
        Greenfoot.delay(2);
        resetImg();
        
        
    }
    public void act() {
        delta += 1;
        if (delta % 200 == 0) {
            castLaser();
        }
        updateLaser();
        if (delta % 300 == 0) {
            getWorld().removeObject(laser);
        }
    }
    public void updateLaser() {
        // setLocation will set the center of the laser
        // thus it should be the midpoint between the princess and the player
        
    }
    public void castLaser() {
        getWorld().addObject(laser, getX(), getY());
    }
}
