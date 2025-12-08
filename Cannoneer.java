import greenfoot.GreenfootImage;
import greenfoot.sound.Sound;
import greenfoot.sound.SoundFactory;

public class Cannoneer extends Boss {
    public boolean alive = true;

    static int remaining = 4;

    @Override
    public GreenfootImage getBossImage() {
        return new GreenfootImage("./images/cannoneer.png");
    }

    public Cannoneer() {
        super();
        this.health = 10;

    }

    public int delta = 0;

    @Override
    public void act() {
        if (!alive)
            return;
        Player plr = getWorld().getObjects(Player.class).get(0);
        double angle = Math.atan2(plr.getY() - getY(), plr.getX() - getX());
        setRotation((int) (Math.toDegrees(angle)) - 90);
        super.act();
        if (delta >= (50 + (int) (Math.random() * 500))) {
            delta = 0;

            CannonBall ball = new CannonBall(angle * (1 - Math.random() / 7));
            getWorld().addObject(ball, getX(), getY());
        }
        delta++;

    }

    public void init() {
    }

    @Override
    public void onDeath() {
        alive = false;
        // Greenfoot.playSound("./sounds/princess_defeated.mp3");
        Screen world = (Screen) getWorld();
        // world.player.canMoveOn = true;
        Sound sound = SoundFactory.getInstance().createSound("./sounds/yay.mp3", false);
        if (sound != null) {
            sound.play();
        }
        fade();
        Player.instance.defeatedBosses += 1;
        Coin coin = new Coin();
        world.addObject(coin, getX(), getY());
        Player.instance.incCharges(5);
        remaining--;
        if (remaining <= 0) {
            world.mayLeave = true;
        }
    }

    public int flashFrames = 0;

    @Override
    public void onHit() {
        health -= 1;
        flashFrames = 1;

    }
}
