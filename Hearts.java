import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Hearts extends Actor {
    static GreenfootImage heartImg = new GreenfootImage("images/heart.png");
    static {
        heartImg.scale(30, 30);
    }

    public Hearts() {
        // Create a composite image with three hearts in a row
        update();
    }

    public void act() {
        Player player = Player.getInstance();

        setLocation(player.getX(), player.getY() + 145);
    }

    public void update() {
        int spacing = 5;

        int health = Player.getInstance().health;
        GreenfootImage composite = new GreenfootImage(
                (heartImg.getWidth() + spacing) * 5, // width for 5 hearts + spacing
                heartImg.getHeight());

        // Draw hearts based on current health
        for (int i = 0; i < health; i++) {
            composite.drawImage(heartImg, i * (heartImg.getWidth() + spacing), 0);
        }

        setImage(composite);
    }

}
