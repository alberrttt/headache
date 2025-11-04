import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
       public void act()
    {
            int currentX = getX();
            int currentY = getY();
            int mov = 5;
            // Add your action code here.
            if (Greenfoot.isKeyDown("w")) {
                currentY -= mov;
            }
            if (Greenfoot.isKeyDown("a")) {
                currentX -= mov;
            }
            if (Greenfoot.isKeyDown("s")) {
                currentY += mov;
            }
            if (Greenfoot.isKeyDown("d")) {
                currentX += mov;
            }
            setLocation(currentX, currentY);

    }
}
