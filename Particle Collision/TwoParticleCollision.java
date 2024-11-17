package simulation;

public class TwoParticleCollision extends Collision{

	public TwoParticleCollision(Particle p1, Particle p2, double t) {
		super(t, new Particle[]{p1,p2});
	}

	@Override
	public void happen(ParticleEventHandler h) {
		Particle.collide(ps[0], ps[1]);
		h.reactTo(this);
	}

}
