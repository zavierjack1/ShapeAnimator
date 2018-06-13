package MultiThreadBouncingShapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.JPanel;

import Animation.AnimatedCircle;
import Animation.AnimatedRectangle;
import Animation.AnimatedShape;
import Animation.Animator;
import MyGeometry.Point;

public class BouncingShapeCanvas extends JPanel {
	
	private final int SHAPE_CIRCLE = 0;
	private final int SHAPE_RECTANGLE = 1;
	private int current_shape;
	private Random generator = new Random();
	private ArrayList<AnimatedShape> animatedShapes = new ArrayList<AnimatedShape>();
	private boolean isPlaying;
	private int delay;
	private Animator animator;
	
	public BouncingShapeCanvas(BouncingShapeFrame frame, int delay){
		super(true);//double buffered
		this.delay = delay;
		animator = new Animator(this, delay);
	}
	
	//getters
	public ArrayList<AnimatedShape> getAnimatedShapes(){
		return animatedShapes;
	}
	
	public Point getRandomPoint(){
		int randomXPos = (int)getSize().getWidth()/2;
		int randomYPos = (int)getSize().getHeight()/2;
		return new Point(randomXPos, randomYPos);
	}
	
	public Point getRandomPointInDimension(Dimension dim){
		int randomXPos = (int)dim.getWidth()/2;
		int randomYPos = (int)dim.getHeight()/2;
		
		return new Point(randomXPos, randomYPos);
	}
	
	public Color getRandomColor(){
		int randomColor1 = generator.nextInt(255);
		int randomColor2 = generator.nextInt(255);
		int randomColor3 = generator.nextInt(255);
		
		return new Color(randomColor1, randomColor2, randomColor3);
	}
	
	public boolean getIsPlaying(){
		return this.isPlaying;
	}
	
	public Dimension getDimension(){
		return this.getSize();
	}
	
	public Animator getAnimator(){
		return this.animator;
	}
	
	public int getDelay(){
		return this.delay;
	}
	
	//setters
	public void setIsPlaying(boolean isPlaying){
		this.isPlaying = isPlaying;
	}
	
	public void play_pause(){
		if (getAnimator().getThread() == null){
			setIsPlaying(true);
			
			getAnimatedShapes().forEach(animatedShape -> {
				animatedShape.start();
			});
			
			getAnimator().start();
		}
		else{
			setIsPlaying(false);
			getAnimatedShapes().forEach(animatedShape -> {
				animatedShape.setThreadToNull();
			});
			getAnimator().stop();
		}
	}
	
	public void stop(){
		setIsPlaying(false);
		getAnimator().stop();
	}

	public void restart(String shapeSelected, String colorSelected){
		//stop the animator
		getAnimator().stop();
		
		//null all of the current threads
		getAnimatedShapes().forEach(animatedShape -> {
			animatedShape.setThreadToNull();
		});
		
		//clear the shapes array
		getAnimatedShapes().clear();
		
		//shape selector
		if (shapeSelected.equals("circle")){
			current_shape = SHAPE_CIRCLE;
		}
		else if(shapeSelected.equals("rectangle")){
			current_shape = SHAPE_RECTANGLE;
		}
		
		//color selector
		Color color;
		if(!colorSelected.equals("random")){
			if (colorSelected.equals("red")){
				color = Color.RED;
			}
			else if (colorSelected.equals("blue")){
				color = Color.BLUE;
			}
			else if (colorSelected.equals("yellow")){
				color = Color.YELLOW;
			}
			else{
				color = Color.BLACK;
			}
		}
		else{
			color = Color.BLACK;
		}
		
		//create array of random count of shapes
		for (int i = 0; i < generator.nextInt(10000); i++){
			if (colorSelected.equals("random")){
				 color = getRandomColor();
			}
			
			Point randomPos = getRandomPoint();
			
			int vectorX = 5 - generator.nextInt(10);
			int vectorY = 5 - generator.nextInt(10);
			
			//if both dx and dy are 0, regen dx and dy 
			while (vectorX == 0 && vectorY == 0){
				//nextInt grabs a random number from 0 to 20. we do 10-(random to 20) so we can get a range from -10 to 10
				vectorX = 5 - generator.nextInt(10);
				vectorY = 5 - generator.nextInt(10);
			}
			//create the runnable array of shapes
			switch(current_shape){
				case SHAPE_CIRCLE:
					int randomRadius = generator.nextInt(100);
					getAnimatedShapes().add(
							new AnimatedCircle(
								getDelay()*2,
								randomPos,
								randomRadius,
								color,
								vectorX,
								vectorY, 
								true, 
								this
								)
							);
					break;
				case SHAPE_RECTANGLE:
					int randomWidth = generator.nextInt(100);
					int randomHeight = generator.nextInt(100);
					getAnimatedShapes().add(
							new AnimatedRectangle(
								getDelay()*2,
								randomPos,
								randomWidth,
								randomHeight,
								color,
								vectorX,
								vectorY, 
								true, 
								this
								)
							);
					break;
			}
		}
		
		//start each of the threads in the array
		//null all of the current threads
		getAnimatedShapes().forEach(animatedShape -> {
			animatedShape.start();
		});
		
		setIsPlaying(true);
		getAnimator().start();
		
	}
	
	public void paint(Graphics g){
		//draw the background
		g.setColor(Color.black);
		g.fillRect(0, 0, getSize().width, getSize().height);
		
		//null all of the current threads
		getAnimatedShapes().forEach(animatedShape -> {
			animatedShape.fill(g);
		});
	}
}
