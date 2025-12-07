import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class CannonBall extends Actor {
    public static GreenfootImage image = new GreenfootImage("./images/fireball.png");

    static {
        image.rotate(225);
        image.scale(40, 40);
        // // draw circle
        // GreenfootImage img = new GreenfootImage(20, 20);
        // img.setColor(greenfoot.Color.BLACK);
        // img.fillOval(0, 0, 20, 20);
        // image = img;
    }
    public double angle;

    public CannonBall(double angle) {
        this.angle = angle;
        setImage(image);
        setRotation((int) (angle * 180 / Math.PI));
        // https://www.youtube.com/watch?v=WdFxHb9wpW8
        GreenfootSound sound = new GreenfootSound("./sounds/cannon.mp3");
        sound.setVolume(50);

        sound.play();
    }

    public void act() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
            return;
        }
        move(5 + (int) (Math.random() * 4));
        if (isTouching(Player.class)) {
            Player plr = getWorld().getObjects(Player.class).get(0);
            plr.takeDamage(1);
            getWorld().removeObject(this);
        }
    }

}
