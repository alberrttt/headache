import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healthbar here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Powerbar extends Actor implements AfterAdded {
    /**
     * Act - do whatever the Healthbar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage main;
    GreenfootImage secondary;

    public Powerbar() {
        // create rect
        main = new GreenfootImage(248, 55);
        main.setColor(new greenfoot.Color(118, 237, 255));
        main.fillRect(0, 17, 0, 42);
        drawBorder();

        setImage(main);

    }

    public void drawBorder() {
        GreenfootImage border = new GreenfootImage("./images/healthbar_empty.png");
        border.scale(248, 56);
        main.drawImage(border, 0, 0);
        secondary = border;
    }

    @Override
    public void afterAdded() {
        Screen screen = (Screen) getWorld();

        setLocation(50 + main.getWidth() / 2, screen.getHeight() - main.getHeight() - 10);
    }

    public void act() {
        // Add your action code here.
        Player player = Player.getInstance();
        setLocation(player.getX(), player.getY() + 100);

    }

    public void update() {
        main.clear();
        main.fillRect(0, 17, (int) (hbPercentage() * 248), 42);
        drawBorder();
    }

    public double hbPercentage() {
        Player player = Player.instance;
        return (double) player.charges / player.maxCharges;
    }
}
