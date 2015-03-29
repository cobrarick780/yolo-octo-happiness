package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class Painter extends JComponent implements Runnable{
	private boolean running;
	public static final Font FONT = new Font("Arial", Font.PLAIN, 22);
	private Image background;
	private static World w;
	private static Camera c;
	public static int WIDTH,HEIGHT;
	private boolean painting = false;
	
	public Painter(int width, int height){
		w = new World();
		WIDTH = width;
		HEIGHT = height;
		c = new Camera(WIDTH,HEIGHT,w);
	}
	public void run() {
		running = true;
		while(running){
			repaint();
			while(painting){
			try{
				Thread.sleep(10);
			}catch(Exception e){}
			}
			c.update();
			w.update();
			try{
				Thread.sleep(100);
			}catch(Exception e){}
		}
	}

	public void stop() {
		running = false;
	}
	
	public void paint(Graphics g){
		painting= true;
		//g.drawImage(background,0,0,null);
		g.setColor(Color.black);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		c.draw(g);
		painting= false;
		
	}
	public static void playerAction(int action) {
		
	}
}
