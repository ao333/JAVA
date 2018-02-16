package simulation;

public class ParticleWallCollision extends Collision {
	
	public Particle particle;
	public Wall wall;
	
	public ParticleWallCollision(Particle par, Wall w, double t ) 
	{        
	super(t, new Particle[] {par});  //not sure about that
	this.time = t;
	this.particle = par;
	this.wall = w; 
	}

	@Override
	public void happen(ParticleEventHandler h) 
	{
	Particle.collide(this.particle, this.wall);
	h.reactTo(this);
	}

}
