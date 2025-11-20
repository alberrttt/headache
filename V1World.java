public class V1World extends Screen {
    public V1World(Screen top, Screen bottom, Screen left, Screen right) {
        super();
        setBackground("./images/v1.png");
        this.setAdjacentScreens(top, bottom, left, right);      

        prepare1();
    }

    public V1World() {
        super();
        setBackground("./images/v1.png");
        prepare1();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare1()
    {
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
        Box box = new Box();
        addObject(box,160,449);
        box.setLocation(154,452);
        box.setLocation(202,461);
        box.setLocation(185,454);
        Box box6 = new Box();
        addObject(box6,267,455);
        box6.setLocation(275,455);
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Box box = new Box();
        addObject(box,352,453);
        Box box2 = new Box();
        addObject(box2,414,452);
        Box box3 = new Box();
        addObject(box3,483,456);
        box3.setLocation(488,452);
        Box box4 = new Box();
        addObject(box4,112,623);
        Box box5 = new Box();
        addObject(box5,195,628);
        box5.setLocation(195,625);
        Box box6 = new Box();
        addObject(box6,284,628);
        box6.setLocation(289,628);
        Box box7 = new Box();
        addObject(box7,366,626);
        box7.setLocation(373,629);
        Box box8 = new Box();
        addObject(box8,441,630);
        box8.setLocation(447,628);
        Box box9 = new Box();
        addObject(box9,514,623);
        box9.setLocation(520,627);
        Box box10 = new Box();
        addObject(box10,115,547);
        Box box11 = new Box();
        addObject(box11,514,535);
    }
}