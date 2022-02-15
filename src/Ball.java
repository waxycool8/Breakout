import acm.graphics.GOval;
import acm.graphics.GCanvas;

public class Ball extends GOval {

    private double deltaX = 1; //the horizontal amount to move each frame
    private double deltaY = -1; //the vertical amount to move each frame
    private GCanvas screen; //the screen the ball is on
    public boolean lost; //whether or not the ball has fallen off the screen

    public Ball(double size, GCanvas screen){
        this(100,100, size, screen);
    }

    public Ball(double x, double y, double size, GCanvas screen){
        super(x, y, size, size);
        setFilled(true);
        this.screen = screen;
    }

    public void handleMove(){
        move(deltaX, -deltaY);

        //What if I hit the top of the screen?
        if(getY() <= 0){
            deltaY = -Math.abs(deltaY);
        }
        //What if I hit the bottom of the screen?
        if(getY() >= screen.getHeight() - getHeight()){
            lost = true;
        }
        //What if I hit the left edge of the screen?
        if(getX() <= 0){
            deltaX = Math.abs(deltaX);
        }
        //What if I hit the right edge of the screen?
        if(getX() >= screen.getWidth() - getWidth()){
            deltaX = -Math.abs(deltaX); //Possible change
        }
    }

    public void bounce(){
        deltaY = -deltaY;
    }

    public void bounceLeft(){
        deltaY = -deltaY;
        deltaX = -Math.abs(deltaX);
    }

    public void bounceRight(){
        deltaY = -deltaY;
        deltaX = -Math.abs(deltaX);
    }

}
