package simulation;

public abstract class Collision extends AbstractEvent{
	protected Particle[] ps;
	private int [] initialCollisions;
    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) {
        // TODO implement constructor
    	super(t);
    	this.ps = ps;
    	initialCollisions = new int[ps.length];
    	for(int i = 0; i < ps.length; i++) {
    		initialCollisions[i] = ps[i].collisions();
    	}
    }

    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
    public boolean isValid() {
        // TODO implement his method
    	for(int i = 0; i < ps.length; i++) {
    		if(initialCollisions[i] != ps[i].collisions())
    		{
    			return false;
    		}
    			
    	}
        return true;
    }

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        // TODO implement this method
        return ps;
    }
}
