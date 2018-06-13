package MultiThreadBouncingShapes;

public class BouncingShapeLauncher {
	public static void main(String[] args){
		BouncingShapeFrame frame = new BouncingShapeFrame(500, 500);
		frame.show_frame();
		frame.getCanvas().restart("circle", "random");
	}
}

