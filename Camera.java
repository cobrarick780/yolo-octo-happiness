package engine;

import java.awt.Graphics;

public class Camera {
	double rotateX,rotateY,rotateZ;
	double px,py,pz;
	final double MAX_DISTANCE = 20000000, FoW_X = Math.PI/6, FoW_Y = Math.PI/6;
	double width, height;
	World w;
	
	
	public Camera(int width, int height, World w) {
		this.width = width;
		this.height = height;
		rotateX = 0;
		rotateY = 0;
		rotateZ = 0;
		px = 0;
		py = 0;
		pz = 0;
		this.w = w;
	}
	int t= 0;
	public void update() {
		rotateX = Math.PI/8.0*Math.sin(t);
		System.out.println(t);
	}
	public void draw(Graphics g) {
		long time = System.currentTimeMillis();
		w.prepDraw();
		for(int x = 0; x < width; x++){
			long time2 = System.currentTimeMillis();
			for(int y = 0; y < height;y++){

				double xAngle = rotateX + (x-width/2)/(width/2)*FoW_X;
				double yAngle = rotateY+ (y-height/2)/(height/2)*FoW_X;
				double dx = Math.cos(xAngle)*Math.sin(yAngle)*Particle.SIZE/10;
				double dy = Math.sin(xAngle)*Particle.SIZE/10;
				double dz = Math.cos(xAngle)*Math.cos(yAngle)*Particle.SIZE/10;
				w.drawPixel(px, py, pz, MAX_DISTANCE, dx, dy, dz, x, y, g);
			}
			long timeP2 = System.currentTimeMillis()- time2;
			System.out.println("Time passed: " + timeP2);
		}
		w.closeDraw();
		long timeP = System.currentTimeMillis()- time;
		double fps = 1.0/timeP*1000;
		System.out.println("FPS: " + fps + " \t\t Time Passed: " + timeP/1000.0);
		t++;
		System.out.println(t);
	}
	
}
