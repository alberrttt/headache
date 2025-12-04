import greenfoot.*;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
public class Princess extends Boss {
    SweepingLaser laser = new SweepingLaser();
    public int delta = 0;
    public boolean alive = true;
    public boolean laserCasted = false;
    public Princess() {
        super("./images/princess.png");
        this.health = 5;

    }
    public void init() {
    }
    @Override
    public void onDeath() {
        alive = false;
        // Greenfoot.playSound("./sounds/princess_defeated.mp3");
        Screen world = (Screen) getWorld();
            world.removeObject(laser);
        // world.player.canMoveOn = true;
    }
    @Override
    public void onHit() {
        flashRed();
        Greenfoot.delay(2);
        resetImg();
        System.out.println("Princess hit! Health: " + this.health);
        
    }
    public void act() {
        if (!alive)
            return;

        super.act();
        if (delta == 30) {
            castLaser();
            laserCasted = true;
        }
        delta += 1;

        if (laserCasted)
            updateLaser();
        if (delta  == 80) {
            getWorld().removeObject(laser);
            laserCasted = false;
            delta = 0;
        }

    }
    public void updateLaser() {
        // setLocation will set the center of the laser
        // thus it should be the midpoint between the princess and the player
        World world = getWorld();
        if (world == null)
            return;
        
        laser.setRotation(Math.toRadians(laser.getRotation()+1),getX(), getY());
        
    }
    public void castLaser() {
        World world = getWorld();
        world.addObject(laser, 0,0);
        Player plr = getWorld().getObjects(Player.class).get(0);
        double angle = Math.atan2(plr.getY() - getY(), plr.getX() - getX());
        laser.setRotation(angle-Math.PI/6, getX(), getY());
    }
}
