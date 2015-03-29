package engine;

public class Cube {
	int x, y, z;
	int R=0,G=255,B=255;
	
	public Cube(){
		x = (int) (Math.random()*(World.LENGTH-1));
		y = (int) (Math.random()*(World.LENGTH-1));
		z = (int) (Math.random()*(World.LENGTH-1));
	}
	
	public Particle[][][] add(Particle[][][] w){
		w[x][y][z] = new Particle(R,G,B);
		return w;
	}
	
	public Particle[][][] remove(Particle[][][] w){
		w[x][y][z] = null;
		return w;
	}
	
	public void update(){
		x += (int)(Math.random()*3)-1;
		if(x < 0) x = 0;
		else if (x >= World.LENGTH) x = (World.LENGTH-1);
		y += (int)(Math.random()*3)-1;
		if(y < 0) y = 0;
		else if (y >= World.LENGTH) y = (World.LENGTH-1);
		z += (int)(Math.random()*3)-1;
		if(z < 0)z= 0;
		else if (z >= World.LENGTH) z = (World.LENGTH-1);
	}
	
}
