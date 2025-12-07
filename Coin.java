import greenfoot.*;

public class Coin extends Actor {
    public Coin() {
        GreenfootImage img = new GreenfootImage("./images/coin.png");
        img.scale(100, 100);
        setImage(img);
    }

    public void collect() {
        getWorld().removeObject(this);
        Player.instance.incCharges(10);
        Player.instance.incHealth(2);
        StatsUI.instance.update();
        Greenfoot.playSound("./sounds/collect_item.mp3");
    }

    public void act() {
        if (isTouching(Bullet.class)) {
            collect();
        }
    }
}
