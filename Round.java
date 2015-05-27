import java.util.LinkedList;
import java.util.Queue;
 
public class Round {
    
	//adding the Queue
	private static Queue<Double> power = new LinkedList<Double>();
	private static Queue<Double> angle = new LinkedList<Double>();
	
    
    //Adds to queue of choice
    public void addToPower(double x){
    	
    	power.offer(x);
    	
    }
    public void addToAngle(double x){
    	
    	angle.offer(x);
    	
    }
    
    //retrieves head of the queue
    public double getFromPower(){
    	
    	return power.poll();
    	
    }
    public double getFromAngle(){
    	
    	return angle.poll();
    	
    }
    
    //takes all elements out of the queue
    public void resetQueue(){
    	
    	while(power.poll()!=null){
    		power.poll();
    	}
    	while(angle.poll()!=null){
    		angle.poll();
    	}
    }
    
}
    
   