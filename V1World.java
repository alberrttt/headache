import greenfoot.*;

public class V1World extends Screen {
    public V1World(Screen top, Screen bottom, Screen left, Screen right) {
        super();

        this.setAdjacentScreens(top, bottom, left, right);
        init();
    }

    public V1World() {
        super();
        this.addObject(Player.getInstance(), 0, getHeight() / 2);
        Screen.currentScreen = this;
        init();
    }

    public void after() {
    }

    static GreenfootImage[] treeImages = {
            new GreenfootImage("./images/tree0.png"),
            new GreenfootImage("./images/tree1.png"),
            new GreenfootImage("./images/tree2.png"),
            new GreenfootImage("./images/tree3.png"),
    };

    public void init() {
        setBackground("./images/grass.png");

        // Spawn random trees

        Princess princess = new Princess();
        addObject(princess, getWidth() / 2, getHeight() / 2);
        princess.init();
        this.right = new CannonWorld(this, null, null, null);
        // spawn pills
        for (int i = 0; i < 5; i++) {
            Pill.spawnPill(this);
        }
    }

}