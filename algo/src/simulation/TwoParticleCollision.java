
package simulation;

public class TwoParticleCollision extends Collision {
	
	public Particle particleOne;
	public Particle particleTwo;
	
	public TwoParticleCollision(Particle p1, Particle p2, double t)
        {   
        super(t, new Particle[] {p1, p2});  //not sure about that
        this.time = t;
	this.particleOne = p1;
	this.particleTwo = p2;  
	}

	@Override
	public void happen(ParticleEventHandler h) 
	{
	Particle.collide(particleOne, particleTwo);
	h.reactTo(this);	
	}
	
}
