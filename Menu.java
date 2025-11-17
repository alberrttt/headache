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
        getBackground().drawImage(header, 800 - header.getWidth()/2, 200 - header.getHeight()/2);
        GreenfootImage headart = new GreenfootImage("./images/head.png");
        getBackground().drawImage(headart, 1200 - headart.getWidth()/2,  headart.getHeight());

        Button start = new Button("Play.png");
        addObject(start, 800, 450);
        Button about = new Button("About.png");
        addObject(about, 800, 625);
    }
}
