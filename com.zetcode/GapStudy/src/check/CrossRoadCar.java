/**
 * 
 */
package check;


/**
 * @author jeffy
 *
 */
public class CrossRoadCar {

	int xcar, ycar,reflinex,refliney,stopliney,CarsOnRoad;
	int proxX1,proxX2;
	boolean withinframe;
	MainRoadCar CarArray[];
	
	public CrossRoadCar(int NoOfCars) {
		xcar=435;
		ycar=410;
		withinframe=true;
		reflinex=639;
		refliney=185;
		stopliney=215;
		CarsOnRoad=NoOfCars;
		proxX1=410;
		proxX2=560;
		
		System.out.println("Car Created..");
		move();		
	}
	
	public void move(){
		System.out.println("Car starts moving on crossRoad..");
		while(ycar>stopliney){
			ycar=ycar-1;
			
			if(ycar>stopliney-5 && ycar<stopliney+5){
				System.out.println("Car reaches near stopline..");
				crossingroad();
			}
		}
		
	}

	private void crossingroad() {
	if(checkproximity(CarsOnRoad))	{
		circularmotion();
		System.out.println("Circular crossing starts..");
	}
		
	}
	
	private void circularmotion() {
		int radius=75;
		double angle=0;
		while(angle<90){
			xcar=(int) (xcar+radius-(radius*Math.cos(angle)));
			ycar=(int) (ycar-(radius*Math.sin(angle)));
			angle++;
		}		
	System.out.println("Circular crossing over..");	
	}

	public boolean checkproximity(int CarsOnRoad){
		for(int i=0;i<CarsOnRoad;i++){
			
			if(CarArray[i].xcar>proxX1 && CarArray[i].xcar<proxX2){
				System.out.println("A Car in proximity. stopping to wait..");
				startTimer();
						
				}
			}
		return true;	
	}
		
	private void startTimer() {
		// TODO Auto-generated method stub
		System.out.println("timer started..");
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int CarsOnRoad=1;
		CrossRoadCar car1 = new CrossRoadCar(CarsOnRoad);
		

	}

}
