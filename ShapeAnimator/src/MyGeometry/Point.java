package MyGeometry;

public class Point {
	//fields
	protected int x,y;
	
	//constructors
	public Point(){
		this.x = 0; this.y = 0;
	}
	
	public Point(int in_x, int in_y){
		this.x = in_x;
		this.y = in_y;
	}
	
	public int distanceTo(Point in_Point) {
		int distanceX = this.x - in_Point.x;
		int distanceY = this.x - in_Point.y;
		
		int distance = (int)Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		return distance;
	}
	
	//accessors
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	//canonical form methods
	/*
	 public boolean equals(Point inPoint){};
	 public in hashCode(){};
	 public Point Clone(){};
	 public String toString(){};
	 */
	
	//mutators
	public void setX (int x){
		this.x = x;
	}
	
	public void setY (int y){
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + getX() + ", y=" + getY() + "]";
	}
}
