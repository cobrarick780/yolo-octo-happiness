package engine;

import java.awt.Color;
import java.awt.Graphics;

public class World {
	Cube[] c = new Cube[0];
	public final static int LENGTH = 16;
	double x,y,z, size;
	Particle[][][] world = new Particle[LENGTH][LENGTH][LENGTH];//x,y,z
	public World(){
		size = world.length*Particle.SIZE;
		x = -size/2;
		y = -size/2;
		z = 0;
		generateWorld();
		for(int i = 0; i < c.length;i++)
			c[i] = new Cube();
	}
	
	private void generateWorld() {
		for(int i = 0; i < LENGTH; i++){
			for(int j = 0; j < LENGTH; j++){
				//world[i][j][0] = new Particle((int)(Math.random()*255),0,0);
				world[i][j][LENGTH - 1] = new Particle(0,0,255-(int)(Math.random()*150));
				world[0][j][i] = new Particle(255-(int)(Math.random()*150),0,0);
				world[LENGTH-1][j][i] = new Particle(255-(int)(Math.random()*150),0,0);
				world[i][0][j] = new Particle(0,255-(int)(Math.random()*150),0);
				world[i][LENGTH-1][j] = new Particle(0,255-(int)(Math.random()*150),0);
			}
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void prepDraw(){
		for(int i = 0; i < c.length;i++){
			c[i].update();
			c[i].add(world);
		}
	}
	
	public void drawPixel(double cx, double cy,double cz, double maxD, double dx, double dy, double dz, int px, int py, Graphics g){
		Particle p = null;
		double x=cx-this.x, y = cy-this.y, z= cz-this.z;
		double dist = Math.sqrt(x*x + y*y + z*z), travel = 0;
		while(travel < maxD && !((x < 0&&dx < 0)||(x>size && dx > 0)||(y < 0&&dy < 0)||(y>size && dy > 0)||(z < 0&&dz < 0)||(z>size && dz > 0))){//not going away from the block

			if(x > 0 && x < size && y > 0 && y< size && z > 0 && z < size){
				int bx = (int)(x/Particle.SIZE);
				int by = (int)(y/Particle.SIZE);
				int bz = (int)(z/Particle.SIZE);
				if(world[bx][by][bz] != null){
					p = world[bx][by][bz];
					break;
				}
			}
			x+=dx;
			y += dy;
			z += dz;
			travel += dist;
		}
		if(p != null){
			g.setColor(new Color(p.R, p.G, p.B));
			g.fillRect(px, py, 1, 1);
		}
	}
	
	public void closeDraw(){
		for(int i = 0; i < c.length;i++)
			c[i].remove(world);
	}
}
