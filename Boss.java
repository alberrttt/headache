import greenfoot.*;

public class Boss extends Actor {
    public int health;
    public int flashFrames = 0;

    public void act() {
        if (isTouching(Bullet.class)) {
            removeTouching(Bullet.class);
            flashFrames = 5;
            onHit();
        }
        if (flashFrames > 0) {
            flashFrames--;
            setImage(getFlashedRedImg());
        } else {
            resetImg();
        }

        if (health <= 0) {
            Thread deathThread = new Thread(() -> {
                onDeath();
                getWorld().removeObject(this);

            });
            deathThread.start();
        }
    }

    public void init() {
    }

    public void onDeath() {
    }

    public void onHit() {
    }

    public GreenfootImage getBossImage() {
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }

    public Boss() {
        super();
        resetImg();

    }

    public void resetImg() {
        GreenfootImage original = getBossImage();
        original.scale(original.getWidth(), original.getHeight());
        setImage(original);
    }

    public void fade() {
        GreenfootImage img = getImage();
        for (int i = 255; i >= 0; i -= 10) {
            for (int w = 0; w < img.getWidth(); w++) {
                for (int h = 0; h < img.getHeight(); h++) {

                    Color rgb = img.getColorAt(w, h);

                    int red = rgb.getRed();
                    int green = rgb.getGreen();
                    int blue = rgb.getBlue();
                    int alpha = rgb.getAlpha();
                    alpha = Math.min(alpha, i);

                    Color newColor = new Color(red, green, blue, alpha);
                    img.setColorAt(w, h, newColor);

                }
            }
            Greenfoot.delay(1);
        }
    }

    GreenfootImage flashedRedImg = null;

    public GreenfootImage getFlashedRedImg() {
        if (flashedRedImg != null)
            return flashedRedImg;
        GreenfootImage img = new GreenfootImage(getBossImage());
        for (int w = 0; w < img.getWidth(); w++) {
            for (int h = 0; h < img.getHeight(); h++) {

                Color rgb = img.getColorAt(w, h);

                int red = rgb.getRed();
                int green = rgb.getGreen();
                int blue = rgb.getBlue();
                int alpha = rgb.getAlpha();
                red = Math.min(255, red + 100);

                Color newColor = new Color(red, green, blue, alpha);
                img.setColorAt(w, h, newColor);

            }
        }
        flashedRedImg = img;
        return flashedRedImg;
    }
}
