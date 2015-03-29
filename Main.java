package engine;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main extends JFrame{
	static InputHandler ih;
	static Main m;
	static Painter painter;
	public static final int WIDTH = 1024, HEIGHT = 768;
	
	public static void main(String[] args){
		new Main().setVisible(true);
	}
	
	private Main(){
		super("3D World Test");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		m = this;
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		setFocusable(true);
		setLocation(30, 30);
		
		ih = new InputHandler();
		painter = new Painter(WIDTH,HEIGHT);
		
		addMouseListener(ih);
		addMouseWheelListener(ih);
		addMouseMotionListener(ih);
		addFocusListener(ih);
		addKeyListener(ih);
		setContentPane(painter);
		
		Thread t = new Thread(painter);
		t.start();
	}
	
	public static Image getImage(String name){
		try{
			return new ImageIcon(ImageIO.read(m.getClass().getResource("pictures/" + name + ".png"))).getImage();
		}catch(IOException e){
			return null;
		}
	}
}
