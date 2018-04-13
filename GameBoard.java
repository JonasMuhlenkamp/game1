package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener {

	private Sprite spaceShip;
	private final int DELAY = 10; 

	public static int count = 0;
	
	public GameBoard() {

		initBoard();
	}

	//This method is only called once, when the GameBoard is constructed over in GameInterface
	private void initBoard() {

//		addMouseListener(new MAdapter());
//		addMouseMotionListener(new MAdapter());
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);

		spaceShip = new Sprite("I:\\spaceship.png");
		
		Timer timer = new Timer(DELAY, this);	
		timer.start();
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		super.paintComponent(g2d);

		g2d.drawImage(spaceShip.getImage(), spaceShip.getX(), 
				spaceShip.getY(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(mouseOnComponent()) {
			count++;
			step();
		}
				
	}
	
	private void step() {

		spaceShip.calc_dx_dy(getMouseX(), getMouseY());		
		spaceShip.move();

		//This repaint looks ugly because it is adjusted for all speeds of the sprite
		repaint(spaceShip.getX() - (int)(spaceShip.SPEED_MULTIPLIER * 1.5), spaceShip.getY() - (int)(spaceShip.SPEED_MULTIPLIER * 1.5), 
				spaceShip.getWidth() + (int)(spaceShip.SPEED_MULTIPLIER * 3), spaceShip.getHeight() + (int)(spaceShip.SPEED_MULTIPLIER * 3));    
	}    

	//Gets the mouse position for use in sprite movement calculations
	private double getMouseX() {

		PointerInfo mousePointer = MouseInfo.getPointerInfo();
		Point mousePoint = mousePointer.getLocation();

		return mousePoint.getX() - this.getLocationOnScreen().getX();
	}

	private double getMouseY() {

		PointerInfo mousePointer = MouseInfo.getPointerInfo();
		Point mousePoint = mousePointer.getLocation();

		return mousePoint.getY() - this.getLocationOnScreen().getY();
	}

	public boolean mouseOnComponent() {
	
		if((this.getX() < getMouseX() && getMouseX() < this.getX() + this.getWidth()) && (this.getY() < getMouseY() && getMouseY() < this.getY() + this.getHeight())) {
			
			return true;
			
		} else {
			
			return false;
		}
		
	}
	

//	private class MAdapter extends MouseAdapter {
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//						
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//
//		}
//
//		@Override
//		public void mouseMoved(MouseEvent e) {
//		
//		}
//
//		@Override
//		public void mouseDragged(MouseEvent e) {
//
//		}
//		
//	}

}