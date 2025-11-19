import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

public class Screen extends World {
    public Screen bottom;
    public Screen top;
    public Screen left;
    public Screen right;

    public Screen() {
        super(1600, 900, 1);
    }
    public void setAdjacentScreens(Screen top, Screen bottom, Screen left, Screen right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
    public void checkActorCollisions(Actor actor) {
        int aX = actor.getX();
        int aY = actor.getY();

        if (aX <= 0 && left != null) {
            left.addObject(actor, left.getWidth() - 1, aY);
            this.removeObject(actor);
            Greenfoot.setWorld(left);
        } else if (aX >= getWidth() - 1 && right != null) {
            this.removeObject(actor);
            actor.setLocation(0, aY);
            right.addObject(actor, 0, aY);

            Greenfoot.setWorld(right);
        } else if (aY <= 0 && top != null) {
            top.addObject(actor, aX, top.getHeight() - 1);
            this.removeObject(actor);
            Greenfoot.setWorld(top);
        } else if (aY >= getHeight() - 1 && bottom != null) {
            bottom.addObject(actor, aX, 0);
            this.removeObject(actor);
            Greenfoot.setWorld(bottom);
        }
    }
}
