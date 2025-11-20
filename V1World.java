 public class V1World extends Screen {
    public V1World(Screen top, Screen bottom, Screen left, Screen right) {
        super();
        setBackground("./images/v1.png");
        this.setAdjacentScreens(top, bottom, left, right);      
 
        Box box1 = new Box();
        addObject(box1,492,245);
        box1.setLocation(504,261);
        Box box2 = new Box();
        addObject(box2,108,453);
        Box box3 = new Box();
        addObject(box3,685,588);
        Box box4 = new Box();
        addObject(box4,436,844);
        Box box5 = new Box();
        addObject(box5,1240,878);
    }
}