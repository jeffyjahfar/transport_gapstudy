/**
 * 
 */
package check;

import java.util.Random;
import java.lang.Math;


/**
 * @author jeffy
 *
 */
public class CarGeneration {
	//Timer time;
	long t;
	int MainRoadFlowRate, CrossRoadFlowRate;
	int mainroadm, crossroadm;
	Random randomnumber;
	double MRr,CRr;
	double mean,variance;
	double mainroadt1,mainroadtcum;
	double crossroadt1,crossroadtcum;
	
	public CarGeneration() {
		System.out.println("Car gereation begins..");
		mean=0.5;
		variance=1;
		mainroadtcum=0;
		crossroadtcum=0;
		MainRoadFlowRate = 900;
		CrossRoadFlowRate=600;
		mainroadm= 3600/MainRoadFlowRate;
		crossroadm = 3600/CrossRoadFlowRate;
		randomnumber = new Random();
		MRr=mean+randomnumber.nextGaussian()*variance;
		mainroadt1=mainroadm*(-Math.log(MRr));
		CRr=mean+randomnumber.nextGaussian()*variance;
		crossroadt1=crossroadm*(-Math.log(CRr));
		crossroadtcum+=crossroadt1;
		mainroadtcum += mainroadt1;
		
		//time= new Timer(0,ActionListener);
		//t= time.getDelay();
		
	}
	
	
	public static void main(String[] args) {
		CarGeneration simulation1 = new CarGeneration();
		

	}

}
