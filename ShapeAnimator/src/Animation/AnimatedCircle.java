package Animation;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import MyGeometry.Circle;
import MyGeometry.Point;

public class AnimatedCircle extends Circle implements AnimatedShape{
	//fields
	private int dx;
	private int dy;
	private boolean wallBound;
	private JPanel panelDrawnOn;
	
	private Thread t;
	private int delay;
	
	//constructors canvas
	public AnimatedCircle (int delay, Point topLeft_center, int radius, Color color, int dx, int dy, boolean wallBound, String cornerCode, JPanel panel) {
		super(topLeft_center, radius, color, cornerCode);
		setDX(dx);
		setDY(dy);
		setWallBound(wallBound);
		this.delay = delay;
		panelDrawnOn = panel;
	}
	
	/*the following two constructors are used if we convert one shape to another and want to use the same thread*/
	public AnimatedCircle (Thread t, int delay, Point center, int radius, Color color, int dx, int dy, boolean wallBound, JPanel panel){
		super(center, radius, color);
		setDX(dx);
		setDY(dy);
		setWallBound(wallBound);
		this.t = t;
		this.delay = delay;
		panelDrawnOn = panel;
	}
	
	public AnimatedCircle (Thread t, int delay, Point topLeft_center, int radius, Color color, int dx, int dy, boolean wallBound, String cornerCode, JPanel panel) {
		super(topLeft_center, radius, color, cornerCode);
		setDX(dx);
		setDY(dy);
		setWallBound(wallBound);
		this.t = t;
		this.delay = delay;
		panelDrawnOn = panel;
	}
	
	public AnimatedCircle (int delay, Point center, int radius, Color color, int dx, int dy, boolean wallBound, JPanel panel){
		super(center, radius, color);
		setDX(dx);
		setDY(dy);
		setWallBound(wallBound);
		this.delay = delay;
		panelDrawnOn = panel;
	}
	
	//getters
	public int getDX(){
		return dx;
	}
	
	public int getDY(){
		return dy;		
	}
	
	public boolean getWallBound(){
		return wallBound;
	}
	
	public Thread getThread(){
		return t;
	}
	
	public int getDelay(){
		return delay;
	}
	
	public JPanel getPanelDrawnOn(){
		return panelDrawnOn;
	}
	
	//mutators
	public void setThreadToNull(){
		t = null;
	}
	
	public void setDX(int dx){
		this.dx = dx;
	}
	
	public void setDY(int dy){
		this.dy = dy;
	}
	
	public void setWallBound(boolean wallBound){
		this.wallBound = wallBound;
	}
	
	public void setThread(Thread t){
		this.t = t;
	}
	
	public void setDelay(int delay){
		this.delay = delay;
	}
	
	public void setPanelDrawnOn(JPanel panelDrawnOn){
		this.panelDrawnOn = panelDrawnOn;
	}
	
	//other
	public void move(){
		Dimension d = panelDrawnOn.getSize();
		if (getWallBound()){
			//if the ball hits the west or east wall
			if (getCenter().getX() <= getRadius() || getCenter().getX() + getRadius()>= d.getWidth())
			{
				setDX(-dx);
			}
			//if the ball hits the north or south
			if (getCenter().getY() <= getRadius() || getCenter().getY() + getRadius() >= d.getHeight())
			{	
				setDY(-dy);
			}
		}
		else{
			setDX(-dx);
			setDY(-dy);
		}
		//move the ball by dx and dy
		setCenter(new Point (getCenter().getX()+getDX(), getCenter().getY()+getDY()));
	}
	
	public AnimatedShape copyToAnimatedShape(String shape_type){
		AnimatedShape shape;
		if (shape_type.equals("rectangle")){
			shape = (AnimatedShape) new AnimatedRectangle(getDelay(), getCenter(), getDiameter(), getColor(), getDX(), getDY(), getWallBound(), getPanelDrawnOn());
			shape.start();
		}
		else{
			shape = (AnimatedShape) this;
		}
		return shape;
	}
	
	public void start(){
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
	
	public void run(){
		while (Thread.currentThread() == t)
		{
			try{
				Thread.sleep(10);
			}
			catch (InterruptedException e){}
			move(); //the component being animated for the bouncing balls app is the canvas
		}
		
	}
}
