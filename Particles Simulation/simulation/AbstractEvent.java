package simulation;

public abstract class AbstractEvent implements Event {
	private final double time;

    /**
     * Constructor for AbstractEvent.
     */
    public AbstractEvent(double time) {
        // TODO implement the constructor 
    	this.time = time;
    }

    /**
     * Returns the time (in ticks) at which this event will occur.
     */
    @Override
    public double time() {
        // TODO implement this method
        return time;
    }

    /**
     * Compares this object with the specified Event.
     */
    @Override
    public int compareTo(Event that) {
    	if(time > that.time())
    		return 1;
    	else if(time < that.time())
    		return -1;
        // TODO implement this method
        return 0;
    }

}
