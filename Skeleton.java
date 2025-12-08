import java.util.List;

import greenfoot.*;

public class Skeleton extends SmoothMover implements Owner {
    static GifImage idle = new GifImage("./images/skele/skele_idle.gif");
    static GifImage walk = new GifImage("./images/skele/skele_walk.gif");
    static GifImage death = new GifImage("./images/skele/skele_death.gif");
    static GifImage attack = new GifImage("./images/skele/skele_attack.gif");

    static {
        GifImage[] gifs = { idle, walk, death, attack };
        for (GifImage gif : gifs) {
            for (GreenfootImage img : gif.getImages()) {
                img.scale(img.getWidth() * 5, img.getHeight() * 5);
            }
        }
    }

    public int health;
    public GifImage id = new GifImage(idle);
    public GifImage wa = new GifImage(walk);
    public GifImage de = new GifImage(death);
    public GifImage at = new GifImage(attack);

    @Override
    public int getYPos() {
        return getY() + 75;
    }

    @Override
    public int getXPos() {
        return getX();
    }

    // Hitbox image for more accurate collision detection
    private GreenfootImage hitboxImage;
    public Hearts hearts;

    @Override
    public void addedToWorld(World world) {
        hearts = new Hearts(this, health, health);
        hearts.update();
        world.addObject(hearts, getX(), getY() + 145);
    }

    public Skeleton() {
        health = 5;
        // Create a centered hitbox of 126x82 pixels
        // Original sprite is 63x41, scaled 5x = 315x205
        // Hitbox is approximately 2x the original size, centered
        hitboxImage = new GreenfootImage(126, 82);
        hitboxImage.setColor(new Color(0, 0, 0, 0)); // Transparent
        hitboxImage.fill();

    }

    /**
     * Override getImage to composite the sprite with hitbox for collision detection
     */
    private GreenfootImage createCompositeImage(GreenfootImage sprite) {
        // Create a new image with the same size as the sprite
        int spriteWidth = sprite.getWidth();
        int spriteHeight = sprite.getHeight();
        GreenfootImage composite = new GreenfootImage(spriteWidth, spriteHeight);

        // Draw the sprite
        composite.drawImage(sprite, 0, 0);

        // Calculate center position for hitbox
        int hitboxX = (spriteWidth - hitboxImage.getWidth()) / 2;
        int hitboxY = (spriteHeight - hitboxImage.getHeight()) / 2;

        // The hitbox is used internally by Greenfoot for collision detection
        // We set the actual collision image to be the smaller hitbox
        return composite;
    }

    /**
     * Custom collision detection using the smaller hitbox
     */
    public boolean isTouchingWithHitbox(Class<?> cls) {
        List<?> objects = getWorld().getObjects(cls);
        int hitboxWidth = 126;
        int hitboxHeight = 82;

        for (Object obj : objects) {
            Actor other = (Actor) obj;
            int dx = Math.abs(getX() - other.getX());
            int dy = Math.abs(getY() - other.getY());

            // Check if within the smaller hitbox bounds
            if (dx < hitboxWidth / 2 && dy < hitboxHeight / 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove touching objects using custom hitbox detection
     */
    public void removeTouchingWithHitbox(Class<?> cls) {
        List<?> objects = getWorld().getObjects(cls);
        int hitboxWidth = 126;
        int hitboxHeight = 82;

        for (Object obj : objects) {
            Actor other = (Actor) obj;
            int dx = Math.abs(getX() - other.getX());
            int dy = Math.abs(getY() - other.getY());

            // Check if within the smaller hitbox bounds
            if (dx < hitboxWidth / 2 && dy < hitboxHeight / 2) {
                getWorld().removeObject(other);
            }
        }
    }

    public boolean aggroed = false;

    private double[] separationVector(double radius) {
        if (getWorld() == null) {
            return new double[] { 0.0, 0.0 };
        }

        List<Skeleton> skeletons = getWorld().getObjects(Skeleton.class);
        double steerX = 0.0;
        double steerY = 0.0;
        double radiusSq = radius * radius;

        for (Skeleton other : skeletons) {
            if (other == this) {
                continue;
            }

            double dx = getX() - other.getX();
            double dy = getY() - other.getY();
            double distSq = dx * dx + dy * dy;

            if (distSq > 0.0001 && distSq < radiusSq) {
                // Push away harder when very close
                steerX += dx / distSq;
                steerY += dy / distSq;
            }
        }

        double mag = Math.sqrt(steerX * steerX + steerY * steerY);
        if (mag == 0.0) {
            return new double[] { 0.0, 0.0 };
        }

        return new double[] { steerX / mag, steerY / mag };
    }

    public void act() {
        Player player = Player.getInstance();
        int playerX = player.getX();
        int playerY = player.getY();
        int myX = getX();
        int myY = getY();
        int speed = 7;
        double hyp = Math.sqrt((playerX - myX) * (playerX - myX) + (playerY - myY) * (playerY - myY));
        if (health <= 0) {
            // Play death animation
            GreenfootImage img = new GreenfootImage(de.getCurrentImage());
            setImage(img);

            if (de.currentIndex + 1 >= de.getImages().size()) {
                Coin c = new Coin();
                getWorld().addObject(c, getX(), getY());
                getWorld().removeObject(hearts);
                getWorld().removeObject(this);

            }
            return;
        }
        if (isTouchingWithHitbox(Bullet.class)) {
            health -= 1;
            aggroed = true;
            hearts.currentHearts = health;
            hearts.update();
            removeTouchingWithHitbox(Bullet.class);
        }
        if (hyp <= 90 || attackFrame > 0) {
            attack();
            return;
        }
        if (hyp > 500 && !aggroed) {
            setImage(id.getCurrentImage());
        } else {
            // we're also aggroed
            // Move towards player, diagonally?
            double angle = Math.atan2(playerY - myY, playerX - myX);
            double jitter = (Math.random() - 0.5) * (Math.PI / 3);
            double x = Math.exp(1 / hyp);

            double[] separation = separationVector(160);
            double separationInfluence = 2.0;
            double jitterX;
            double jitterY;
            // if closer on x axis, add more jitter to y movement
            if (Math.abs(playerX - myX) < Math.abs(playerY - myY)) {
                jitterX = jitter * 0.3;
                jitterY = jitter * 0.7;
            } else {
                jitterX = jitter * 0.7;
                jitterY = jitter * 0.3;
            }

            double dX = (speed * Math.cos(angle + jitter)) * x + separation[0] * separationInfluence;
            double dY = (speed * Math.sin(angle + jitter)) * x + separation[1] * separationInfluence;
            setLocation(myX + dX, myY + dY);
            GreenfootImage img = new GreenfootImage(wa.getCurrentImage());
            if (-Math.PI / 2 <= angle && angle < Math.PI / 2) {

            } else {
                img.mirrorHorizontally();

            }
            setImage(img);

        }
    }

    public int attackFrame = 0;

    public void attack() {
        Player player = Player.getInstance();
        long delta = System.currentTimeMillis() - at.time;

        GreenfootImage img = new GreenfootImage(at.getCurrentImage());
        attackFrame = at.currentIndex + 1;

        if (player.getX() < getX()) {
            img.mirrorHorizontally();
        }
        setImage(img);

        if (attackFrame == 6 && delta >= at.delay[5]) {
            // Check for collision with player
            if (this.getObjectsInRange(180, Player.class).size() > 0) {
                player.takeDamage(2);
                player.slow(50);
            }
        }
        if (attackFrame >= at.getImages().size()) {
            attackFrame = 0;
        }

    }

}
