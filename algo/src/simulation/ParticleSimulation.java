package simulation;
import utils.List;
import utils.MinPriorityQueue;


import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    
    private double time;
    private MinPriorityQueue<Event> eventQ;

    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) 
   {
        this.model = m;
		this.time = 0.0;
		Tick tick = new Tick(1.0); //josh - this was set for time 0, not as specified in notes?
	    screen = new ParticlesView(name, m);
    	eventQ = new MinPriorityQueue<Event>();
    	
    	eventQ.add(tick);
    	
    	//create queue of all predicted collisions
    /* TO DO*/
    	
        Iterable<Collision> predictedCollisions = new List<Collision>();
    	predictedCollisions = m.predictAllCollisions(time);
    
    	//add predicted collisions to queue
    	for (Collision c : predictedCollisions){
    		eventQ.add(c);
    	}
    	
  	}
   
    /**
     * Runs the simulation.
     */
    @Override
    public void run() {
        try {
            SwingUtilities.invokeAndWait(screen);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        while(eventQ.size() > 0) 
		{
		//remove priority event from queue and process
		   /* TO DO*/
	        
	        //remove event from queue
	        Event nextEvent = eventQ.remove();
	        
	        if (nextEvent.isValid()==true){
	        	double dt = nextEvent.time() - time;
	        	time = nextEvent.time(); //update time clock
	        	model.moveParticles(dt); //move all particles for time passed
	        	
	            nextEvent.happen(this);//tell event to happen and pass this simulation as ParticleEventHandler
	
	            // reactTo(this) is already called in happen function	
	        }
	        	
	 	}
    }

	
    @Override
    public void reactTo(Tick tick)
    {
    	//pause
	     try {
			Thread.sleep(FRAME_INTERVAL_MILLIS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     screen.update(); //update display
	     
	     //add tick
	     Tick newTick = new Tick(tick.time() + 1);
	     eventQ.add(newTick);
    }

    @Override
    public void reactTo(Collision col)
    {
    //create new predicted collisions and add to queue
     /* TO DO*/
    	
    	//get particles involved in collision
    	Particle[] collidedParticles = col.getParticles();
    	
    	//for each collided particle, get new predicted collisions and add to the event queue
    	for (int x = 0; x < collidedParticles.length; x++){
    		
    		Iterable<Collision> newCollisions = model.predictCollisions(collidedParticles[x], time);
    		
    		for (Collision c : newCollisions){
        		eventQ.add(c);
        	}
    	}
    	
    }

}
