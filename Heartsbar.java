import greenfoot.GreenfootImage;
import greenfoot.World;

public class Heartsbar extends Hearts {
    public Heartsbar(Owner owner, int maxHearts, int currentHearts) {
        super(null, maxHearts, currentHearts);
    }

    public void act() {

    }

    @Override
    public void addedToWorld(World w) {
        int myWidth = getImage().getWidth();
        int myHeight = getImage().getHeight();
        setLocation(w.getWidth() / 2 + myWidth / 4, w.getHeight() - myHeight / 2 - 25);
        update();
    }

    @Override
    public void update() {
        int spacing = 5;
        int maxWidth = 480;
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
