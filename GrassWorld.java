import greenfoot.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrassWorld extends Screen
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GrassWorld()
    {    
        super();
      
        Player player = new Player();
        this.addObject(player, getWidth()/2, getCellSize()/2);
        int medicines = 10;
        for (int i = 0; i < medicines; i ++) {
            Actor pill = new Pill();
            int x = (int)(Math.random()*getWidth());
            int y = (int)(Math.random()*getHeight());
            this.addObject(pill, x, y);
        }
        setBackground("./images/grass.png");
        Screen right = new V1World(null, null, this, null);
        this.setAdjacentScreens(null, null, null, right);        
    }
    
    public static void main(String[] args) {
    }

}
