package MyGeometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle implements Shape{
	private Point topLeft;
	private Color color;
	private int width,height;
	
	// constructors
	public Rectangle(Point topLeft, int width, int height, Color color) {
		setTopLeft(topLeft);
		setWidth(width);
		setHeight(height);
		setColor(color);
	}
	
	public Rectangle(Point topLeft, int width, int height) {
		setTopLeft(topLeft);
		setWidth(width);
		setHeight(height);
		setColor(Color.BLACK);
	}
	
	//Square built using center
	public Rectangle(Point center, int width_height) {
		center.setX(center.getX()-width_height); 
		center.setY(center.getY()-width_height); 
		setTopLeft(center);
		setWidth(width_height);
		setHeight(width_height);
		setColor(Color.BLACK);
	}
	
	//with color
	public Rectangle(Point center, int width_height, Color color) {
		center.setX(center.getX()-(width_height/2)); 
		center.setY(center.getY()-(width_height/2));
		setTopLeft(center);
		setWidth(width_height);
		setHeight(width_height);
		setColor(color);
	}
	
	public Rectangle(Point topLeft) {
		setTopLeft(topLeft);
		setWidth(0);
		setHeight(0);
		setColor(Color.BLACK);
	}
	
	public Rectangle() {
		setTopLeft(new Point(0, 0));
		setWidth(0);
		setHeight(0);
		setColor(Color.BLACK);
	}

	// accessors
	public Point getTopLeft() {
		return topLeft;
	}

	public Point getTopRight() {
		return new Point(topLeft.getX()+width, topLeft.getY());
	}
	
	public Point getBottomLeft(){
		return new Point(topLeft.getX(), topLeft.getY()+height);
	}
	
	public Point getBottomRight(){
		return new Point(topLeft.getX()+width, topLeft.getY()+height);
	}
	
	public int getLeftLine(){
		return getTopLeft().getX();
	}
	
	public int getRightLine(){
		return getTopRight().getX();
	}
	
	public int getTopLine(){
		return getTopLeft().getY();
	}
	
	public int getBottomLine(){
		return getBottomLeft().getY();
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public Point getCenter(){
		Point center = getTopLeft();
		center.setX(center.getX()+(getWidth()/2));
		center.setY(center.getY()+(getHeight()/2));
		return center;
	}
	
	public Color getColor(){
		return color;
	}
	
	//mutators
	public void setTopLeft(Point topLeft){
		this.topLeft = topLeft;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}

	public void setColor(Color color){
		this.color = color;
	}
	
	// canoical forms methods
	public boolean equals(Rectangle r) {
		if (
			this.getTopLeft() == r.getTopLeft() && 
			this.getWidth() == r.getWidth() && 
			this.getHeight() == r.getHeight()
			) 
		{
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return 0;
	};

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public String toString(){
		return this.getClass().toString() + " width: "+this.getWidth()+ ", height: "+this.getHeight()+", topLeft: "+this.getTopLeft().getX()+","+this.getTopLeft().getY();
	}
	
	//Other
	public boolean isIn(Point p) {
		if (p.getX() > getLeftLine() ||
			p.getX() < getRightLine() ||
			p.getY() > getBottomLine()||
			p.getY() < getTopLine())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void fill(Graphics g) {
		g.setColor(getColor());
		g.fillRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(), getHeight());
	}
	
	public void draw(Graphics g){
		g.drawRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(), getHeight());
	}
	
	public Shape copyToShape(String shape_type){
		Shape shape;
		if (shape_type.equals("circle")){
			shape = (Shape) new Circle(getTopLeft(), getWidth()/2, getColor(), "topLeft");
		}
		else{
			shape = (Shape) this;
		}
		return shape;
	}
}

