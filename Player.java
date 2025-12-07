import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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
public class Player extends Actor implements AfterAdded {
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
    public double charges = 20.0;
    public double maxCharges = 20.0;
    public double defeatedBosses = 0;
    public int health = 3;
    public int maxHealth = 5;

    public void incHealth(int amt) {
        this.health += amt;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
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
        Screen screen = (Screen) getWorld();
        screen.addObject(b, getX(), getY() - 50);

    }

    public void kill() {
        Dead dead = new Dead();
        Greenfoot.setWorld(dead);
        stopSounds();
        Greenfoot.playSound("./sounds/dead.mp3");
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

    public void takeDamage(int amt) {
        health -= amt;
        StatsUI.instance.hearts.update();
        if (health <= 0) {
            kill();
        }
    }

    public Direction doMovement() {
        Direction dir = null;
        int currentX = getX();
        int currentY = getY();
        int mov = 5;
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

    public void act() {

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
    public void afterAdded() {
        // @todo
        // hb.placeInNewScreen();

        StatsUI.instance.afterAdded();
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
