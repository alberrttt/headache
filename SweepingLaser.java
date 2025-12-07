import greenfoot.*;

public class SweepingLaser extends Actor {
    public boolean laserCasted = false;
    public boolean laserHit = false;

    public SweepingLaser() {
        // create a red box
        GreenfootImage img = new GreenfootImage(900, 5);
        img.setColor(new greenfoot.Color(255, 0, 0));
        img.fill();
        setImage(img);

    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }

    public void rotateOne() {

        setRotation(getRotation() + 1);
    }

    /**
     * Sets the rotation of the laser and updates its position accordingly.
     * 
     * @param angle The angle in degrees to set the rotation to. in radians
     */
    public void setRotation(double angle, int aboutX, int aboutY) {
        int lX = aboutX + (int) (Math.cos(angle) * getWidth() / 2);
        int lY = aboutY + (int) (Math.sin(angle) * getWidth() / 2);
        setLocation(lX, lY);
        super.setRotation((int) (Math.toDegrees(angle)));
    }
}
