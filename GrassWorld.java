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
        this.addObject(player, getWidth()/2, getHeight()/2);
        player.placeInNewScreen();
        int medicines = 10;
        for (int i = 0; i < medicines; i ++) {
            Actor pill = new Pill(player);
            // make them spawn in the area that is not 250x250 from center
            int x, y;
            boolean validLocation = false;
            while (!validLocation) {
                x = Greenfoot.getRandomNumber(getWidth());
                y = Greenfoot.getRandomNumber(getHeight());
                // Check if outside the 250x250 center area
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int threshold = 125; // 250/2
                if (Math.abs(x - centerX) > threshold || Math.abs(y - centerY) > threshold) {
                    validLocation = true;
                    this.addObject(pill, x, y);
                }
            }
        }
        setBackground("./images/grass.png");
        
        Screen right = new V1World(player, null, null, this, null);
        this.player = player;
        this.setAdjacentScreens(null, null, null, right);        
    }
    
    public static void main(String[] args) {
    }

}
