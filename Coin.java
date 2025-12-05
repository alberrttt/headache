import greenfoot.*;

public class Coin extends Actor {
    public Coin() {
        GreenfootImage img = new GreenfootImage("./images/coin.png");
        img.scale(100, 100);
        setImage(img);
    }
    public void collect() {
        getWorld().removeObject(this);
        Player.instance.charges += 10;
        Player.instance.coins += 1;
        Player.instance.hb.update();
        Greenfoot.playSound("./sounds/collect_item.mp3");
    }
}
