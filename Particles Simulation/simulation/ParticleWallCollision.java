package simulation;

public class ParticleWallCollision extends Collision {
	private Wall wall;
	public ParticleWallCollision(Particle p, Wall w, double t) {
		super(t, new Particle[]{p});
		// TODO Auto-generated constructor stub
		wall = w;
	}

	@Override
	public void happen(ParticleEventHandler h) {
		// TODO Auto-generated method stub
		Particle.collide(ps[0], wall);
		h.reactTo(this);
	}

}
