package simulation;

public class ParticleWallCollision extends Collision {

	private Wall w;
	public ParticleWallCollision(Particle p, Wall w, double t) {
		super(t, new Particle[]{p});
		this.w = w;
	}

	@Override
	public void happen(ParticleEventHandler h) {
		Particle.collide(ps[0], w);
		h.reactTo(this);
	}
}
