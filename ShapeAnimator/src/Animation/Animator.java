package Animation;
import java.awt.Component;

public class Animator implements Runnable{
	
	protected Component comp; //the component to be animated
	protected int delay;
	protected Thread animationThread;
	
	//Constructors
	public Animator(Component comp){
		this.comp = comp;
	}
	
	public Animator(Component comp, int delay){
		this.comp = comp;
		this.delay = delay;
	}
	
	//setters
	final public void setDelay(int delay){
		this.delay = delay;
	}
	
	//getters
	final public int getDelay(){
		return delay;
	}
	
	final public Thread getThread(){
		return animationThread;
	}
	
	public void start(){
		animationThread = new Thread(this);
		animationThread.start();
	}
	
	public void stop(){
		animationThread = null;
	}
	
	public void run(){
		while (Thread.currentThread() == animationThread)
		{
			try{
				Thread.sleep(delay);
			}
			catch (InterruptedException e){}
			comp.repaint();
		}
	}
}
