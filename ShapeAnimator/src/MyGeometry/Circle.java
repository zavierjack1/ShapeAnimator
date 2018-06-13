package MyGeometry;
import java.awt.*;

import MyGeometry.Point;

public class Circle implements Shape {
	private Point center;
	private Color color;
	private int radius;

	// constructors
	public Circle(Point topLeft_center, int radius, Color color, String cornerCode) {
		switch(cornerCode){
			case "topLeft":
				setCenter(new Point(topLeft_center.getX()-radius, topLeft_center.getY()-radius));
			case "center":
				setCenter(topLeft_center);
		}
		setRadius(radius);
		setColor(color);
	}
	
	public Circle(Point center, int radius, Color color) {
		setCenter(center);
		setRadius(radius);
		setColor(color);
	}
	
	public Circle(Point center, int radius) {
		setCenter(center);
		setRadius(radius);
		setColor(Color.BLACK);
	}

	public Circle(Point center) {
		setCenter(center);
		setRadius(0);
		setColor(Color.BLACK);
	}
	
	public Circle() {
		setCenter(new Point(0,0));
		setRadius(0);
		setColor(Color.BLACK);
	}

	// accessors
	public Point getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	public int getDiameter() {
		return radius*2;
	}
	
	public Point getTopLeft(){
		return new Point(getCenter().getX() - getRadius(), getCenter().getY() - getRadius());
	}
	
	public Color getColor(){
		return color;
	}
	
	//mutators
	public void setCenter(Point center){
		this.center = center;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	// canoical forms methods
	public boolean equals(Circle c) {
		if (c.getCenter() == getCenter() && c.getRadius() == getRadius()) {
			return true;
		} else {
			return false;
		}
	}

	public String toString(){
		return getClass().toString() + " radius: "+getRadius()+ ", center: "+getCenter().getX()+","+getCenter().getY();
	}
	
	//Other
	public boolean isIn(Point p) {
		double distanceFromCenter = getCenter().distanceTo(p);
		if (distanceFromCenter < getRadius()) {
			return true;
		} else {
			return false;
		}
	}

	public void fill(Graphics g) {
		g.setColor(getColor());
		g.fillOval(getTopLeft().getX(), getTopLeft().getY(), getDiameter(), getDiameter());
	}
	
	public void draw(Graphics g){
		g.drawOval(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), getDiameter(), getDiameter());
	}

	/*
	 * Get the distance from the center of two circles. Then get the combined
	 * length of their radii. 
	 * 
	 * If the distanceFromCenters < their combined radii then they overlap
	 * 
	 */
	public boolean overlaps(Circle c) {
		double distanceFromCenters = this.getCenter().distanceTo(c.getCenter());

		if (distanceFromCenters <  this.getRadius() + c.getRadius()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Shape copyToShape(String shape_type){
		Shape shape;
		if (shape_type.equals("rectangle")){
			shape = (Shape) new Rectangle(getTopLeft(), getDiameter(), getDiameter(), getColor());
		}
		else{
			shape = this;
		}
		return shape;
	}
}
