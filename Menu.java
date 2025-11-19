import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World 
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1);    
        // make the header, it should not be background its header.png and position it at 800,150, scale it up to 2x
        GreenfootImage header = new GreenfootImage("./images/header.png");
        int leftAlign = 800 - header.getWidth()/2;
        getBackground().drawImage(header, leftAlign, 200 - header.getHeight()/2);
        GreenfootImage headart = new GreenfootImage("./images/head.png");
        getBackground().drawImage(headart, 1600 - headart.getWidth(),  900 - headart.getHeight());
        class Callback implements OnClick {
            public void execute() {
                Greenfoot.setWorld(new GrassWorld());
            }

        }
        Button start = new Button("Play.png", new Callback());
        
        addObject(start, leftAlign + start.getWidth()/2 , 450);

        class ExitCallback implements OnClick {
            public void execute() {
                Greenfoot.stop();
            }

        }

        Button about = new Button("Exit.png", new ExitCallback());
        addObject(about, leftAlign + about.getWidth()/2, 560);
    }
}
