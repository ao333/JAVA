package simulation;

public abstract class AbstractEvent implements Event {

    private double time;  //answer given in function below lol

    public AbstractEvent(double time) {           //constructer which is called by Tick and sets time to
        this.time = time;
    }

 
    @Override
    public double time() {
        return this.time;
    }

   
    
    @Override
    public int compareTo(Event that) {
    	if (this.time < that.time()) 
	{
    		return -1;
    	} 
	else if (this.time > that.time()) 
	{
    		return 1;
    	} 
	else 
	{
    		return 0;
    	}
    }
}
