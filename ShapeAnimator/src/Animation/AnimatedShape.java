package Animation;

import java.awt.Dimension;

import MyGeometry.Shape;

public interface AnimatedShape extends Shape, Runnable{
	public void move();
	public int getDX();
	public int getDY();
	public void setThreadToNull();
	public boolean getWallBound();
	public AnimatedShape copyToAnimatedShape(String movingShape_type);
	public void start();
	public void run();
}
