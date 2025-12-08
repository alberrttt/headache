import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ObjectInputFilter.Status;
import java.nio.Buffer;
import java.util.ArrayList;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT
}

/**
 * Write a description of class Player here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Actor implements Owner {
    static Player instance = null;

    static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public int current = 0;
    public int delta = 0;
    static final String idle = "R1";

    @Override
    public int getYPos() {
        return getY() - 100;
    }

    @Override
    public int getXPos() {
        return getX();
    }

    public String a(String x) {
        return "./images/" + x + ".png";
    }

    public static int width = 75;
    public static int height = 150;
    static final String[] paths = { "R2", "R3", "R4" };
    static final GreenfootImage[] runFramesRight = new GreenfootImage[paths.length];
    static final GreenfootImage[] runFramesLeft = new GreenfootImage[paths.length];

    static final GreenfootImage idleImg = new GreenfootImage("./images/" + idle + ".png");
    static {
        for (int i = 0; i < paths.length; i++) {
            String path = "./images/" + paths[i] + ".png";
            GreenfootImage img = new GreenfootImage(path);
            img.scale(Player.width, Player.height);
            runFramesRight[i] = img;
        }
        for (int i = 0; i < paths.length; i++) {
            String path = "./images/" + paths[i] + ".png";
            GreenfootImage img = new GreenfootImage(path);
            img.mirrorHorizontally();
            img.scale(Player.width, Player.height);
            runFramesLeft[i] = img;
        }
    }
    public double initialCharges = 20.0;

    public double charges = initialCharges;
    public double maxCharges = 20.0;
    public double defeatedBosses = 0;
    public int health = 5;
    public int maxHealth = 10;

    public void incHealth(int amt) {
        this.health += amt;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        StatsUI.instance.hearts.currentHearts = this.health;
        StatsUI.instance.hearts.update();
    }

    public int coins = 0;
    static Powerbar pb = StatsUI.instance.powerbar;

    public void stopSounds() {
        Screen.currentScreen.walkingSound.stop();
    }

    public Player() {
        String path = a(idle);
        GreenfootImage img = new GreenfootImage(path);
        img.scale(width, height);
        setImage(img);
        health = 10;
        maxHealth = 10;
        charges = initialCharges;
        instance = this;
    }

    public void incCharges(double amt) {
        this.charges += amt;
        if (this.charges > this.maxCharges) {
            this.charges = this.maxCharges;
        }
        pb.update();
    }

    public void shoot() {
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m == null) {
            return;
        }
        if (charges > 0) {
            charges -= 1;
            StatsUI.instance.powerbar.update();
        } else {
            return;
        }
        int x = m.getX();
        int y = m.getY();
        double angle = Math.atan2(y - getY() + 50, x - getX());
        Bullet b = new Bullet(angle);
        getWorld().addObject(b, getX(), getY() - 25);
        Greenfoot.playSound("./sounds/laser-312360.mp3");

    }

    public int deathScreenTimer = -1;

    public void kill() {
        deathScreenTimer = 100;
        stopSounds();
        Greenfoot.playSound("./sounds/dead.mp3");
        Dead dead = new Dead();
        Greenfoot.setWorld(dead);
    }

    public void checkCollisions() {
        if (isTouching(Coin.class)) {
            ArrayList<Coin> coins = (ArrayList<Coin>) getIntersectingObjects(Coin.class);
            for (Coin coin : coins) {
                coin.collect();
            }
        }
        if (isTouching(Pill.class)) {
            ArrayList<Pill> pills = (ArrayList<Pill>) getIntersectingObjects(Pill.class);
            for (Pill pill : pills) {
                pill.collect();
            }
        }
        if (isTouching(SweepingLaser.class)) {

            SweepingLaser laser = (SweepingLaser) getIntersectingObjects(SweepingLaser.class).get(0);
            if (!laser.laserHit) {
                laser.laserHit = true;
                takeDamage(1);

            }
        }
    }

    public int slowFrames = 0;

    public void slow(int frames) {
        slowFrames = frames;

    }

    public void takeDamage(int amt) {
        health -= amt;
        StatsUI.instance.hearts.currentHearts = health;
        StatsUI.instance.hearts.update();
        if (health <= 0) {
            kill();
        }
    }

    public void dash(Direction dir) {
        int dashDistance = 100;
        if (slowFrames > 0) {
            dashDistance = 50;
        }
        if (dir == Direction.UP) {
            setLocation(getX(), getY() - dashDistance);
        } else if (dir == Direction.DOWN) {
            setLocation(getX(), getY() + dashDistance);
        } else if (dir == Direction.LEFT) {
            setLocation(getX() - dashDistance, getY());
        } else if (dir == Direction.RIGHT) {
            setLocation(getX() + dashDistance, getY());
        }

    }

    public int dashCooldown = 0;

    public Direction doMovement() {
        Direction dir = null;

        int currentX = getX();
        int currentY = getY();
        int mov = 5;
        if (slowFrames > 0) {
            mov = 2;
            slowFrames -= 1;
        }
        if (Greenfoot.mousePressed(null)) {
            shoot();
        }
        if (Greenfoot.isKeyDown("w")) {
            currentY -= mov;
            dir = Direction.UP;
        }
        if (Greenfoot.isKeyDown("s")) {

            currentY += mov;
            dir = Direction.DOWN;
        }
        if (Greenfoot.isKeyDown("a")) {
            currentX -= mov;
            dir = Direction.LEFT;
        }
        if (Greenfoot.isKeyDown("d")) {
            currentX += mov;
            dir = Direction.RIGHT;
        }

        boolean moved = Math.abs(currentX - getX()) > 0 || Math.abs(currentY - getY()) > 0;
        setLocation(currentX, currentY);
        if (Greenfoot.isKeyDown("q") && dashCooldown <= 0 && dir != null) {
            if (charges >= 2) {
                dash(dir);
                charges -= 2;
                StatsUI.instance.powerbar.update();
                dashCooldown = 20;
            }

        } else {
            dashCooldown -= 1;
        }
        GreenfootSound walking = Screen.currentScreen.walkingSound;
        if (moved) {
            if (!walking.isPlaying())
                try {
                    walking.play();
                } catch (Exception e) {
                    // TODO: handle exception
                }
        } else {
            walking.stop();
        }
        return dir;
    }

    int count = 0;

    public void act() {
        if (health <= 0) {

            return;
        }
        if (count >= 200) {
            count = 0;
            incCharges(1);
        } else {
            count++;
        }
        Direction dir = doMovement();

        if (dir != null) {

            movementAnimation(dir);

        } else {
            resetImg();
        }
        checkCollisions();
        if (getWorld() != null) {
            Screen.currentScreen.checkPlayerCollisions();
        }

    }

    public void resetImg() {
        idleImg.scale(width, height);

        setImage(idleImg);
    }

    @Override
    public void addedToWorld(World w) {
        // @todo
        // hb.placeInNewScreen();
        StatsUI.instance.hearts.maxHearts = maxHealth;
        StatsUI.instance.hearts.currentHearts = health;
        w.addObject(StatsUI.instance, 0, 0);
    }

    Direction lastDir = Direction.RIGHT;

    public void movementAnimation(Direction dir) {

        delta += 1;
        if (delta % 5 == 0) {
            GreenfootImage img = runFramesRight[current];
            if (dir == Direction.LEFT) {
                img = runFramesLeft[current];
            } else if (dir == Direction.RIGHT) {
                img = runFramesRight[current];
            }

            lastDir = dir;
            setImage(img);

            current = (current + 1) % runFramesRight.length;
        }

    }
}
