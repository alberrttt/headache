import greenfoot.Actor;
import greenfoot.GreenfootImage;

interface Owner {
    public int getYPos();

    public int getXPos();
}

public class Hearts extends Actor {
    static GreenfootImage heartImg = new GreenfootImage("images/heart.png");

    public int maxHearts;
    public int currentHearts;
    public Owner owner;
    public GreenfootImage img = new GreenfootImage(heartImg);

    public Hearts(Owner owner, int maxHearts, int currentHearts) {
        this.owner = owner;
        this.maxHearts = maxHearts;
        // Create a composite image with three hearts in a row
        update();
    }

    public void act() {
        if (owner == null) {
            return;
        }
        setLocation(owner.getXPos(), owner.getYPos());

    }

    public void update() {
        int spacing = 5;
        int maxWidth = 240;
        GreenfootImage composite = new GreenfootImage(
                maxWidth, // width for 5 hearts + spacing
                heartImg.getHeight());

        double factor = maxWidth / (double) ((heartImg.getWidth() + spacing) * maxHearts - spacing);
        heartImg.scale((int) (heartImg.getWidth() * factor), (int) (heartImg.getHeight() * factor));
        // Draw hearts based on current health
        for (int i = 0; i < currentHearts; i++) {
            composite.drawImage(heartImg, i * (heartImg.getWidth() + spacing), 0);
        }

        setImage(composite);
    }

}
