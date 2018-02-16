package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    private MinPriorityQueue<Event> queue;
    private double time;
    
    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
        // TODO implement constructor
    	model = m;
    	screen = new ParticlesView(name,  m);
    	queue = new MinPriorityQueue<>();
    	queue.add(new Tick(1));
    	Iterable<Collision> iterator = m.predictAllCollisions(0);
    	for(Collision C : iterator) {
    		queue.add(C);
    	}
    	time = 0;
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
        
        // TODO complete implementing this method
        while(!queue.isEmpty()) {
        	Event current_event;
        	while((current_event = queue.remove())!=null && !current_event.isValid());
        	if(current_event != null) {
        		model.moveParticles(current_event.time() - time);
        	}
        	time = current_event.time();
        	current_event.happen(this);
        }
    }

	@Override
	public void reactTo(Tick tick) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(FRAME_INTERVAL_MILLIS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screen.update();
		queue.add(new Tick(tick.time() + 1));
	}

	@Override
	public void reactTo(Collision c) {
		// TODO Auto-generated method stub
		 Particle[] ps = c.getParticles();
		 for(int i = 0; i < ps.length; i++) {
			 Iterable<Collision> iterator1 = model.predictCollisions(ps[i], time);
			 for(Collision cc : iterator1) {
				 queue.add(cc);
			 }
		 }
	}

}
