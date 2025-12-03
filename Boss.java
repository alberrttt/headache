import greenfoot.*;

public class Boss extends Actor {
    public int health;
    
    public void act() {
        if (isTouching(Bullet.class)) {
            removeTouching(Bullet.class);
            health--;
            onHit();
        }
        if (health <= 0) {
            getWorld().removeObject(this);
        }
    }
    public void init() {}
    public void onHit() {}
    public Boss(String img) {
        super();
        resetImg(); 
        
    }
    public void resetImg() {
        GreenfootImage original = new GreenfootImage("./images/princess.png");
        original.scale(original.getWidth(), original.getHeight());
        setImage(original);
    }
    public void flashRed() {
        GreenfootImage img = getImage();
        for (int w = 0; w < img.getWidth(); w++) {
            for (int h = 0; h < img.getHeight(); h++) {
                
                Color rgb = img.getColorAt(w, h);
              
                int red = rgb.getRed();
                int green = rgb.getGreen();
                int blue = rgb.getBlue();
                int alpha = rgb.getAlpha();
                red = Math.min(255, red + 60);
                
                Color newColor = new Color(red, green, blue, alpha);
                img.setColorAt(w, h, newColor);
                
            }
        }
    }

}
