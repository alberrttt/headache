public class CannonWorld extends Screen {
    public CannonWorld(Screen left, Screen right, Screen top, Screen bottom) {

        super(Player.instance);
        this.setAdjacentScreens(top, bottom, left, right);
        setBackground("./images/cannon_bg.png");
        
        
    }
}
