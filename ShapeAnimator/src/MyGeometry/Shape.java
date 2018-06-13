package MyGeometry;

import java.awt.Graphics;
import java.awt.Color;

public interface Shape {
	public String toString();
	public Point getTopLeft();
	public boolean isIn(Point point);
	public void setColor(Color color);
	public Color getColor();
	public void fill(Graphics g);
	public void draw(Graphics g);
	public Shape copyToShape(String shape_type);
}
