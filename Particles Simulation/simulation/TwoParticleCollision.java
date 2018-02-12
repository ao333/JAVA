package simulation;

public class TwoParticleCollision extends Collision{

	public TwoParticleCollision(Particle p1, Particle p2, double t) {
		super(t, new Particle[]{p1,p2});
		// TODO Auto-generated constructor stub
	}

	@Override
	public void happen(ParticleEventHandler h) {
		// TODO Auto-generated method stub
		Particle.collide(ps[0], ps[1]);
		h.reactTo(this);
		
	}

}
