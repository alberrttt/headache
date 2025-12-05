import greenfoot.GreenfootImage;

public class Dead extends Screen {
    public Dead() {
        super();

        GreenfootImage text = new GreenfootImage("You Died", 100, greenfoot.Color.BLACK, new greenfoot.Color(0, 0, 0, 0));
        Button retry = new Button("Retry.png", new OnClick() {
            @Override
            public void execute() {
                Menu world = new Menu();
               greenfoot.Greenfoot.setWorld(world);
            }
        });
        addObject(retry, getWidth() / 2, getHeight() / 2 + 100);
        setBackground(text);
    }
}
