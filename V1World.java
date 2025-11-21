public class V1World extends Screen {
    public V1World(Screen top, Screen bottom, Screen left, Screen right) {
        super();
        
        this.setAdjacentScreens(top, bottom, left, right);      
        init();
    }

    public V1World() {
        super();
        init();
    }
    public void init() {
        setBackground("./images/grass.png");
    }
 
}