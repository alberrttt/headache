import greenfoot.*;
public class SweepingLaser extends Actor {
    public SweepingLaser() {
        // create a red box
        GreenfootImage img = new GreenfootImage(200, 20);
        img.setColor(new greenfoot.Color(255, 0, 0));
        img.fill();
        setImage(img);

    }
    public void rotateOne() {
        setRotation(getRotation() + 1);
    }
}
