import java.io.Console;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

public class Screen extends World {
    public Screen bottom;
    public Screen top;
    public Screen left;
    public Screen right;
    public GreenfootSound walkingSound = new GreenfootSound("./sounds/walking.wav");
    public static Screen currentScreen = null;
    public boolean mayLeave = false;

    public Screen() {
        super(1600, 900, 1);
        addObject(new ActCounter(), 50, 50);
    }

    public void after() {
    }

    public void setAdjacentScreens(Screen top, Screen bottom, Screen left, Screen right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public boolean canMoveOn() {
        return true;
    }

    public void fadeOutScreen() {
        GreenfootImage fade = new GreenfootImage(getWidth(), getHeight());
        fade.setColor(Color.BLACK);
        class Fade extends Actor {
            public Fade() {
                setImage(fade);
            }
        }
        Fade fadeActor = new Fade();
        addObject(fadeActor, getWidth() / 2, getHeight() / 2);

        for (int alpha = 0; alpha <= 255; alpha += 10) {
            fade.clear();

            fade.setColor(new Color(0, 0, 0, alpha));
            fade.fill();
            fadeActor.setImage(fade);
            Greenfoot.delay(1);
        }
        removeObject(fadeActor);
    }

    public void fadeInScreen(Screen newScreen) {
        GreenfootImage fade = new GreenfootImage(getWidth(), getHeight());
        fade.setColor(Color.BLACK);
        fade.fill();
        class Fade extends Actor {
            public Fade() {
                setImage(fade);
            }
        }
        Fade fadeActor = new Fade();
        newScreen.addObject(fadeActor, getWidth() / 2, getHeight() / 2);
        for (int alpha = 255; alpha >= 0; alpha -= 10) {
            fade.clear();

            fade.setColor(new Color(0, 0, 0, alpha));
            fade.fill();
            fadeActor.setImage(fade);
            Greenfoot.delay(1);
        }
        removeObject(fadeActor);
    }

    public void changeScreen(Screen newScreen, int x, int y) {
        Player player = Player.instance;
        player.stopSounds();
        newScreen.addObject(player, x, y);
        fadeOutScreen();
        Greenfoot.setWorld(newScreen);
        fadeInScreen(newScreen);
        currentScreen = newScreen;
        newScreen.after();
        player.afterAdded();
    }

    public boolean checkPlayerCollisions() {
        Player player = Player.instance;
        int aX = player.getX();
        int aY = player.getY();
        if (!mayLeave)
            return false;
        if (!canMoveOn()) {
            return false;
        }
        if (aX <= 0 && left != null) {
            changeScreen(left, left.getWidth() - 10, aY);

            return true;
        } else if (aX >= getWidth() - 1 && right != null) {
            changeScreen(right, 10, aY);
            return true;
        } else if (aY <= 0 && top != null) {
            changeScreen(top, aX, top.getHeight() - 10);
            return true;
        } else if (aY >= getHeight() - 1 && bottom != null) {
            changeScreen(bottom, aX, 10);
            return true;
        }
        return false;
    }
}
