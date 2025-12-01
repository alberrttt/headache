import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healthbar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healthbar extends Actor
{
    /**
     * Act - do whatever the Healthbar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage healthbarEmpty;
    GreenfootImage healthbarColor;
    Player player;
    public Healthbar(Player player)
    {
        // create rect
        this.player = player;
        
        GreenfootImage img = new GreenfootImage("./images/healthbar_empty.png");
        img.scale(300, 70);
        this.healthbarEmpty = img;
        GreenfootImage  rect = new GreenfootImage(1, 40);
        
        rect.setColor(new greenfoot.Color(0, 255, 0));
        rect.fill();
        this.healthbarColor = rect;
        img.drawImage(healthbarColor,0,0);
        
        setImage(img);

    }

    public void placeInNewScreen() {
        Screen screen = (Screen) getWorld();
        
        setLocation(healthbarEmpty.getWidth(), screen.getHeight() - healthbarEmpty.getHeight() - 10);
    }
    public void act()
    {
        // Add your action code here.
    }
    public void update() {
        healthbarColor.scale((int)(3* hbPercentage()), 40);
        healthbarEmpty.drawImage(healthbarColor, 0, 0);
    }
    public double hbPercentage() {
        return (double) player.health / player.maxHealth;
    }
}
