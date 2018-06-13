package MultiThreadBouncingShapes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class BouncingShapeFrame extends JFrame {
	
	private BouncingShapeCanvas canvas;
	private JPanel controlPanel;
	private JComboBox<String> choiceShape;
	private JComboBox<String> choiceColor;
	
	public BouncingShapeCanvas getCanvas(){
		return this.canvas;
	}
	
	public BouncingShapeFrame(int frameWidth, int frameHeight){
		Dimension dim;
		setSize(frameWidth, frameHeight);
		dim = this.getSize();
		setLayout(new BorderLayout());
		canvas = new BouncingShapeCanvas(this, 5);
		add("Center", canvas);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1,0));
		JButton startButton = new JButton("play/pause");
		controlPanel.add(startButton);
		JButton restartButton = new JButton("restart");
		controlPanel.add(restartButton);
		
		choiceColor = new JComboBox<String>();
		choiceColor.addItem("random");
		choiceColor.addItem("red");
		choiceColor.addItem("blue");
		choiceColor.addItem("yellow");
		choiceColor.setSelectedIndex(0);
		
		choiceShape = new JComboBox<String>();
		choiceShape.addItem("circle");
		choiceShape.addItem("rectangle");
		choiceShape.setSelectedIndex(0);
		
		controlPanel.setSize(dim);
		controlPanel.add(choiceShape);
		controlPanel.add(choiceColor);
		controlPanel.setBackground(Color.BLACK);
		add("South", controlPanel);
		
		startButton.addActionListener(new ButtonHandler(ButtonHandler.PLAY_PAUSE_ANIMATION));
		restartButton.addActionListener(new ButtonHandler(ButtonHandler.RESTART_ANIMATION));
		choiceShape.addItemListener(new ChoiceHandler(0));
		choiceColor.addItemListener(new ChoiceHandler(1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void show_frame(){
		setVisible(true);
	}
	
	protected class ButtonHandler implements ActionListener{
		static final int PLAY_PAUSE_ANIMATION = 1;
		static final int RESTART_ANIMATION = 2;
		protected int cmd;
		
		public ButtonHandler(int cmd){
			this.cmd = cmd;
		}
		
		public void actionPerformed(ActionEvent event){
			switch(cmd){
			case PLAY_PAUSE_ANIMATION: getCanvas().play_pause(); break;
			case RESTART_ANIMATION: getCanvas().restart(choiceShape.getSelectedItem().toString(), choiceColor.getSelectedItem().toString()); break;
			}
		}
	}
		
	protected class ChoiceHandler implements ItemListener{
		
		static final int SHAPE_CHOOSER = 0;
		static final int COLOR_CHOOSER = 1;
		
		protected int cmd;
		
		public ChoiceHandler(int cmd){
			this.cmd = cmd;
		}
		
		public void itemStateChanged(ItemEvent event){
			JComboBox<String> choice = (JComboBox<String>) event.getSource();
			BouncingShapeCanvas canvas = getCanvas();
			switch(cmd){
				case SHAPE_CHOOSER:
					if (choice != null){
						canvas.getAnimatedShapes().forEach(animatedShape -> {
							animatedShape.setThreadToNull();
							int index = canvas.getAnimatedShapes().indexOf(animatedShape);
							canvas.getAnimatedShapes().set(index, animatedShape.copyToAnimatedShape(event.getItem().toString()));
						});
					}
				case COLOR_CHOOSER:
					if (choice != null){
						if ("random".equals(event.getItem())){
							for (int i = 0; i < canvas.getAnimatedShapes().size(); i++){
								canvas.getAnimatedShapes().get(i).setColor(canvas.getRandomColor()); 
							}
						}
						else if ("red".equals(event.getItem())){
							for (int i = 0; i < canvas.getAnimatedShapes().size(); i++){
								canvas.getAnimatedShapes().get(i).setColor(Color.RED); 
							}
						}
						else if ("blue".equals(event.getItem())){
							for (int i = 0; i < canvas.getAnimatedShapes().size(); i++){
								canvas.getAnimatedShapes().get(i).setColor(Color.BLUE); 
							}
						}
						else if ("yellow".equals(event.getItem())){
							for (int i = 0; i < canvas.getAnimatedShapes().size(); i++){
								canvas.getAnimatedShapes().get(i).setColor(Color.YELLOW); 
							}
						}
						if (canvas.getAnimator().getThread() == null){
							canvas.repaint();
						} 
					}
			}
		}
	}
}				