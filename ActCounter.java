import greenfoot.*;

public class ActCounter extends Actor {

    private int actCount = 0;
    private long lastTime = System.currentTimeMillis();

    public ActCounter() {
        setImage(new GreenfootImage("APS: 0", 20, Color.BLACK, Color.WHITE));
    }

    public void act() {
        actCount++;

        long now = System.currentTimeMillis();
        if (now - lastTime >= 10) { // 10 ms passed
            updateDisplay(actCount * (1000 / (now - lastTime))); // Scale to APS
            actCount = 0;
            lastTime = now;
        }
    }

    private void updateDisplay(double aps) {
        setImage(new GreenfootImage("APS: " + aps, 20, Color.BLACK, Color.WHITE));
    }
}
