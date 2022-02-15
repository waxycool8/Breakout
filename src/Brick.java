import acm.graphics.GRect;
import java.awt.Color;

public class Brick extends GRect {

    public static final int WIDTH = 44;
    public static final int HEIGHT = 20;

    private int hitpoints;
    private int maxHitpoints;
    private Color color;

    public Brick(double x, double y, Color color, int row){
        super(x, y, WIDTH, HEIGHT);
        this.setFillColor(color);
        this.setFilled(true);
        this.color = color;
        pause(25);

        switch(row){
            case 0:
            case 1:
                maxHitpoints = 5;
                break;
            case 2:
            case 3:
                maxHitpoints = 4;
                break;
            case 4:
            case 5:
                maxHitpoints = 3;
                break;
            case 6:
            case 7:
                maxHitpoints = 2;
                break;
            case 8:
            case 9:
                maxHitpoints = 1;
                break;
        }

        //TODO: give bricks different hitpoints based on row

        hitpoints = maxHitpoints;
    }

    public int getHitpoints(){
        return hitpoints;
    }

    public void takeDamage(){
        hitpoints--;
        changeColor();
    }

    private void changeColor() {
        this.setFillColor(this.getFillColor().darker());
    }

    public void resetHitpoints(){
        hitpoints = maxHitpoints;
        setFillColor(color);
    }
}
