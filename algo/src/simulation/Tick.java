package simulation;

public class Tick extends AbstractEvent {                //created Tick class that extends Abstract event

	public Tick(double time) {
		super(time);                                     //constructor that calls constructor of parent class, see abstract event  
	}

	@Override
	public void happen(ParticleEventHandler h){
		h.reactTo(this);                                 //reacts to the tick
	}

	@Override
	public boolean isValid() {
		return true;
	}
	
	

}