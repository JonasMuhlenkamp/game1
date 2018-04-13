package game1;

import java.awt.Image;
//import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Sprite {

	private Image image;
	private String path;
	
	private int w;
	private int h;
	private int x = 60;
	private int y = 40;
	
	private double dx;
	private double dy;
	
	//Should be around 2-10 for practical purposes
	public final double SPEED_MULTIPLIER = 2; //I recommend speed 100 if you want things to look crazy.  50 is about the fastest it can go without continually overshooting the mouse.

	public Sprite(String filename) {

		path = filename;

		loadImage();
	}

	private void loadImage() {

		//get the image icon and initialize image to it
		ImageIcon ii = new ImageIcon(path);
		image = ii.getImage();

		//get the image width and height (null means no other objects are waiting for the image to load)
		w = image.getWidth(null);
		h = image.getHeight(null);
	}

	public void move() {

		//we move based on the calculations in calc_dx_dy (below)
		x += dx;
		y += dy;   
	}

	public void calc_dx_dy(double mouseX, double mouseY) {

		//if we are hovering over the center of the sprite, we stop moving
		if((mouseX < (x + (w / 2.0) + 5) && mouseX > (x + (w / 2.0) - 5)) && (mouseY < (y + (h / 2.0) + 5) && mouseY > (y + (h / 2.0) - 5))) {		

			dx = 0;
			dy = 0;
			
		} else {

			//otherwise, we calculate the angle from the center of the sprite to the mouse and then calculate dx and dy
			double theta = Math.atan2(mouseY - (y + h / 2.0), mouseX - (x + w / 2.0));
			dx = SPEED_MULTIPLIER * Math.cos(theta);
			dy = SPEED_MULTIPLIER * Math.sin(theta);
			
			if(GameBoard.count % 50 == 0)
				System.out.println("Angle to Mouse: " + theta + "\nX Differential: " + dx + "\nY Differential: " + dy + "\n");
			
		}
	
	}

	//simple methods that return attributes of the sprite
	public int getX() {

		return x;
	}

	public int getY() {

		return y;
	}

	public int getWidth() {

		return w;
	}

	public int getHeight() {

		return h;
	}    

	public Image getImage() {

		return image;
	}

}
