import java.util.Random;


public class Physics {

	private static double gravity = -9.8;
	private static double newX, newY;
	private int randomY1, randomY2, randomX1, randomX2, p1Score = 0, p2Score = 0;
	private boolean hit = false;
	
	//Player 1
    public void applyGravity1(double xCor, double yCor, double power, double angle, double time){ 
          
        double radianAngle = Math.toRadians(angle); 
        double velocityX =  (power*Math.cos(radianAngle)); 
        double velocityY =  (power*Math.sin(radianAngle)); 
          
        newX = xCor + velocityX*time; 
        newY = yCor - (( velocityY*time ) + ( (.5*gravity)*Math.pow(time, 2) ) ); 
            
          
    } 
    
    //Player 2
    public void applyGravity2(double xCor, double yCor, double power, double angle, double time){ 
        
        double radianAngle = Math.toRadians(angle); 
        double velocityX =  (power*Math.cos(radianAngle)); 
        double velocityY =  (power*Math.sin(radianAngle)); 
          
        newX = xCor - velocityX*time; 
        newY = yCor - (   (velocityY*time ) + ( (.5*gravity)*Math.pow(time, 2) ) ); 

          
    }

    
    public boolean detectHit(){ 
    	
    	int x1=385; 
        int x2=385+224; 
        int y1=280; 
        int y2=700; 
        int yGround=700; 
        if (newY >= yGround){ 
            hit = true; 
        } 
        if (newX>=x1 && newX<=x2 && newY>=y1 && newY<=y2){ 
            hit = true; 
        } 
        if (newX>=randomX1 && newX<= randomX1+100 && newY>= (randomY1 + 10) && newY <= randomY1+52){ //if player 1 gets hit, p2 gets a point
        	p2Score++;
        	hit = true;
        }
        if (newX>=randomX2 && newX<= randomX2+100 && newY>= (randomY2 + 10) && newY <= randomY2+52){ //if player 2 gets hit, p1 gets a point
        	p1Score++;
        	hit = true;
        }
          
        return hit; 
    } 
    
	//creates random value to determine placement of the players
	public void randomInt(){

	    Random rand = new Random();

	    randomY1 = rand.nextInt((550 - 350) + 1) + 350;
	    randomY2 = rand.nextInt((550 - 350) + 1) + 350;
	    randomX1 = rand.nextInt((170 - 40) + 1) + 40;
	    randomX2 = rand.nextInt((830 - 700) + 1) + 700;
	    
	}
	
	//reset score
	public void resetScore(){
		p1Score = 0;
		p2Score = 0;
	}
	
	//set hit boolean
	public void setHitFalse(){
		
		hit = false;
		
	}
	
	//Getter methods for the scores
	public int getP1Score(){
		return p1Score;
	}
	public int getP2Score(){
		return p2Score;
	}
	
	
	//Getter methods for the random values
	public int getRandomY1(){	
		int y = randomY1;
		return y;
	}
	public int getRandomY2(){
		int y = randomY2;
		return y;
	}
	public int getRandomX1(){
		int x = randomX1;
		return x;
	}
	public int getRandomX2(){
		int x = randomX2;
		return x;
	}
	
	//Getters for new X and Y
    public double getNewX(){ 
        double x = newX; 
        return x; 
    } 
    public double getNewY(){ 
        double y = newY; 
        return y; 
    }
	
	
}
