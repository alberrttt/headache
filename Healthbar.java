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
    GreenfootImage main;
    GreenfootImage secondary;
    Player player;
    public Healthbar(Player player)
    {
        // create rect
        this.player = player;
        main = new GreenfootImage(310, 70);
        main.setColor(new greenfoot.Color(0, 255, 0));
        main.fillRect(0, 17, 0, 42);
        drawBorder();
        
        setImage(main);

    }
    public void drawBorder() {
        GreenfootImage border = new GreenfootImage("./images/healthbar_empty.png");
        border.scale(310, 60);
        main.drawImage(border, 0,0 );       
        secondary = border; 
    }
    public void placeInNewScreen() {
        Screen screen = (Screen) getWorld();
        
        setLocation(50+main.getWidth()/2, screen.getHeight() - main.getHeight() - 10);
    }
    public void act()
    {
        // Add your action code here.
    }
    public void update() {
        main.clear();
        main.fillRect(0, 17, (int)(hbPercentage() * 310), 42);
        drawBorder();
    }

    public double hbPercentage() {
        return (double) player.charges / player.maxCharges;
    }
}
