import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import svu.csc213.Dialog;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

    private static final double SPACE = 5.0; //the space between the bricks

    private Ball ball;
    private Paddle paddle;

    private int row; //number of brick rows
    private int col; //number of brick columns
    private int numBricksInRow;
    private int remainingBricks;

    private Color[] rowColors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE};
    @Override
    public void init(){
        ball = new Ball(getWidth() / 2, 350, 10, this.getGCanvas());
        ball.setFilled(true);
        ball.setFillColor(Color.pink);
        add(ball);

        paddle = new Paddle(230, 430, 100, 10);
        paddle.setFilled(true);
        paddle.setFillColor(Color.CYAN);
        add(paddle);

        numBricksInRow = (int) (getWidth()/(Brick.WIDTH + SPACE));
        remainingBricks = numBricksInRow * 10;

        for(row = 0; row < 10; row++){
            for(col = 0; col < numBricksInRow; col++){
                Brick brick = new Brick(10 + col * (Brick.WIDTH + SPACE), 4 * Brick.HEIGHT + row * (Brick.HEIGHT + SPACE), rowColors[row], row);

                add(brick);
            }
        }
    }

    @Override
    public void run(){
        addMouseListeners();
        waitForClick();
        gameLoop();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        if((e.getX() < getWidth() - paddle.getWidth() / 2) && (e.getX() > paddle.getWidth() / 2)){
            paddle.setLocation(e.getX() - paddle.getWidth() / 2, getHeight() - 60 - paddle.getHeight());
        }
    }

    private void gameLoop(){
        while(true){
            ball.handleMove();
            handleCollision();

            if(ball.lost) {
                lose();
            }

            pause(5);
        }
    }

    private void handleCollision() {
        GObject obj = null;

        //check the top right corner for collision
        if(obj == null){
            obj = this.getElementAt(ball.getX() + ball.getWidth(), ball.getY());
        }

        //check the top left corner for collision
        if(obj == null){
            obj = this.getElementAt(ball.getX(), ball.getY());
        }

        //check the bottom right corner for collision
        if(obj == null){
            obj = this.getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight());
        }

        //check the bottom left corner for collision
        if(obj == null){
            obj = this.getElementAt(ball.getX(), ball.getY() + ball.getHeight());
        }

        if (obj != null) {

            //we hit the paddle
            if(obj instanceof Paddle){

                if(ball.getX() < (paddle.getX() + (paddle.getWidth() * .4))){ //change number for bigger edges
                    //if left edge of paddle, bounce left
                    ball.bounceLeft();
                } else if(ball.getX() > (paddle.getX() + (paddle.getWidth() * .8))) {
                    //if right edge of paddle, bounce right
                    ball.bounceRight();
                } else {
                    //bounce the ball
                    ball.bounce();
                }
            }

            //we hit a brick
            System.out.println("Brick");
            //take a hitpoint
            if(obj instanceof Brick){
                ((Brick) obj).takeDamage();

                //determine if we broke the brick
                if(((Brick) obj).getHitpoints() <= 0) {

                    //if we did, remove the the brick
                    this.remove(obj);
                    remainingBricks--;
                    if(remainingBricks <= 0){
                        win();
                    }

                }

                ball.bounce();
            }

            //if out of hitpoints, delete
            //bounce the ball

        }
    }

    private void win(){
        Dialog.showMessage("You Won!");
        System.exit(0);
    }

    private void lose(){
        //lose a life
        if(ball.lost){
        }
        //check if we lost all 3 lives
        //tell the player they actually lost
        //close the game if we did
        System.exit(0);
        //reset

    }

    private void reset(){
        ball.setLocation(getWidth() / 2, 350);
        paddle.setLocation(230, 430);
        //tell the ball it isnt lost anymore
        waitForClick();
    }

    public static void main(String[] args) {
        new Breakout().start();
    }
}