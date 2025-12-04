import greenfoot.   *;
public class V1World extends Screen {
    public V1World(Player plr, Screen top, Screen bottom, Screen left, Screen right) {
        super();
        this.player = plr;
        
        this.setAdjacentScreens(top, bottom, left, right);      
        init();
    }
    

    public V1World() {
        super();
        this.player = new Player();
        this.addObject(player, getWidth()/2, getHeight()/2);
        player.placeInNewScreen();
        init();
    }
    public void after() {
        player.placeInNewScreen();

    }
    public void init() {
        setBackground("./images/grass.png");

        Princess princess = new Princess();
        addObject(princess, getWidth() / 2, getHeight() / 2);
        princess.init();

    }
 
}