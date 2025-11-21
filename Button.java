import greenfoot.*;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class Button extends Actor {
    private GreenfootImage buttonImage;
    private OnClick onClick;

    public Button(String path, OnClick onClick) {
        this.buttonImage = new GreenfootImage(path);
        this.onClick = onClick;
        setImage(buttonImage);        
    }
    
    public void act() {
        // Check for mouse interaction
        if (Greenfoot.mouseClicked(this)) {
            // This is where you would put the code that runs when the button is clicked.
            // For example, you could switch to a different world:
            // Greenfoot.setWorld(new AnotherWorld());
            onClick.execute();
            // Or you could print a message to the console:

        }   
    }
    public int getWidth() {
        return buttonImage.getWidth();
    }   
    public int getHeight() {
        return buttonImage.getHeight();
    }
}
interface OnClick {
    void execute();
}

