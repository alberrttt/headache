import greenfoot.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(1600, 900, 1); 
        Actor player = new Player();
        this.addObject(player, getWidth()/2, getCellSize()/2);
       int medicines = 10;
        for (int i = 0; i < medicines; i ++) {
            Actor pill = new Pill();
            int x = (int)(Math.random()*getWidth());
            int y = (int)(Math.random()*getHeight());
            this.addObject(pill, x, y);
        }
        setBackground("./images/grass.png");
        
    }
    public static void main(String[] args) {
    }
}
