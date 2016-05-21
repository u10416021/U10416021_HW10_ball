//U10416021 張馨容
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.security.SecureRandom;
public class BallPane extends Pane {
	
	double [] radius = new double[20];
	double [] x = new double[20];
	double [] y = new double[20];
	double [] dx = new double[20];
	double [] dy = new double[20];
	Circle [] circle = new Circle[20];

  	private Timeline animation;
  	//constructor
  	public BallPane() {
  		//create a secure random
  		SecureRandom r = new SecureRandom();
  		//use for loop to get its radius,dx,dy,color and x-y coordinate.Then add circle
  		for (int i=0;i<20;i++){
  			int xrandom = r.nextInt(250) + 1;
			int yrandom = r.nextInt(150) + 1;
  			radius[i] = 8;
  			x[i] = xrandom;
  			y[i] = yrandom;
  			dx[i] = 1;
  			dy[i] = 1;
  			circle[i] = new Circle(x[i], y[i], radius[i],new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(),1));
  			getChildren().add(circle[i]);
  		}
    	// Create an animation for moving the ball
    	animation = new Timeline(new KeyFrame(Duration.millis(3), e -> moveBall()));
    	animation.setCycleCount(Timeline.INDEFINITE);
    	animation.play(); // Start animation
  }
  //play animatiom
  public void play() {
    animation.play();
  }
  //pause 
  public void pause() {
    animation.pause();
  }
  //increase speed
  public void increaseSpeed() {
    animation.setRate(animation.getRate() + 1.0);
  }
  //decrease speed
  public void decreaseSpeed() {
    animation.setRate(
      animation.getRate() > 0 ? animation.getRate() - 1.0 : animation.getRate() );
  }
  //rate
  public DoubleProperty rateProperty() {
    return animation.rateProperty();
  }
  //move ball according to whether the ball hit the border
  protected void moveBall() {
    for(int i = 0;i < 20;i++){
    	if (x[i] < radius[i] || x[i] > getWidth() - radius[i]) {
      		dx[i] *= -1; // Change ball move direction
    	}
    	
    	x[i] += dx[i];
    	circle[i].setCenterX(x[i]);
    	if (y[i] < radius[i] || y[i] > getHeight() - radius[i]) {
      		dy[i] *= -1; // Change ball move direction
    	}
    	y[i] += dy[i];
    	circle[i].setCenterY(y[i]);
    }
    
  }
}