package Animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import MyGeometry.Rectangle;
import MyGeometry.Point;

public class AnimatedRectangle extends Rectangle implements AnimatedShape {
	//fields
	private int dx;
	private int dy;
	private boolean wallBound;
	private JPanel panelDrawnOn;
	
	private Thread t;
	private int delay;

	//constructors
	public AnimatedRectangle (int delay, Point topLeft, int width, int height, Color color, int dx, int dy, boolean wallBound, JPanel panel){
		super(topLeft, width, height, color);
		setDX(dx);
		setDY(dy);
		setWallBound(wallBound);
		this.delay = delay;
		panelDrawnOn = panel;
	}
	
	//square
	public AnimatedRectangle (int delay, Point center, int width_height, Color color, int dx, int dy, boolean wallBound, JPanel panel){
		super(center, width_height, color);
		setDX(dx);
		setDY(dy);
		setWallBound(wallBound);
		this.delay = delay;
		panelDrawnOn = panel;
	}
	
	/*the following constructor is used if we convert one shape to another and want to use the same thread*/
	public AnimatedRectangle (Thread t, int delay, Point topLeft, int width, int height, Color color, int dx, int dy, boolean wallBound, JPanel panel){
		super(topLeft, width, height, color);
		setDX(dx);
		setDY(dy);
		setWallBound(wallBound);
		this.t = t;
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
			if (getLeftLine() <= 0 || getRightLine() >= d.getWidth())
			{
				setDX(-dx);
			}
			//if the ball hits the north or south
			if (getTopLine() <= 0 || getBottomLine() >= d.getHeight())
			{	
				setDY(-dy);
			}
		}
		else{
			setDX(-dx);
			setDY(-dy);
		}
		setTopLeft(new Point (getTopLeft().getX()+getDX(), getTopLeft().getY()+getDY()));
	}
	
	
	public void move(Graphics g){
		setTopLeft( new Point (getTopLeft().getX()+getDX(), getTopLeft().getY()+getDY()));
		fill(g);
	}
	
	public void move(Dimension d, Graphics g){
		if (getWallBound()){
			//if the ball hits the west or east wall
			if (getLeftLine() <= 0 || getRightLine() >= d.getWidth())
			{
				setDX(-dx);
			}
			//if the ball hits the north or south
			if (getTopLine() <= 0 || getBottomLine() >= d.getHeight())
			{	
				setDY(-dy);
			}

			//move the ball by dx and dy
			setTopLeft(new Point (getTopLeft().getX()+getDX(), getTopLeft().getY()+getDY()));
		}
		else{
			move(g);
		}
		fill(g);
	}

	public AnimatedShape copyToAnimatedShape(String shape_type){
		AnimatedShape shape;
		if (shape_type.equals("circle")){
			shape = (AnimatedShape) new AnimatedCircle(getDelay(), getCenter(), getWidth()/2, getColor(), getDX(), getDY(), getWallBound(), "center", getPanelDrawnOn());
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
