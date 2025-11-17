import greenfoot.*;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class Button extends Actor {
    private GreenfootImage buttonImage;

    public Button(String path) {
        this.buttonImage = new GreenfootImage(path);
        setImage(buttonImage);        
    }
    public void act() {
        // Check for mouse interaction
        if (Greenfoot.mouseClicked(this)) {
            // This is where you would put the code that runs when the button is clicked.
            // For example, you could switch to a different world:
            // Greenfoot.setWorld(new AnotherWorld());
            
            // Or you could print a message to the console:
            System.out.println("Button clicked!");
        }   
    }
}