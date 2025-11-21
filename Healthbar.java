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
    public Healthbar()
    {
        // create rect
        GreenfootImage img = new GreenfootImage(200, 30);
        img.setColor(Color.RED);
        img.fillRect(0, 0, 200, 30);
        setImage(img);

    }
    public void act()
    {
        // Add your action code here.
    }
}
