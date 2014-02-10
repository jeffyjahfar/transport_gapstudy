package check;

import java.io.*; 
import java.util.*;

public class MainRoadCar {
	int xcar, ycar;
	boolean withinframe;
	int pos;

	public MainRoadCar(int pos1, MainRoadCar[] CarArray){
		
		xcar = 10;
		ycar= 135;
		withinframe = true;
		pos=pos1;
		System.out.println("Car Created on mainRoad..");
		addtoqueue(pos,CarArray);
		
	}

	
	public void addtoqueue(int pos, MainRoadCar CarArray[]){
		System.out.println("New Car added to frame at position" + pos);
		//CarArray[pos]=MainRoadCar.this;	
		while(withinframe){
			System.out.println("moving..");
			move(CarArray);
		}
        if(!withinframe){
        	System.out.println("Car is out of frame. calling delete function..");
        	deletefromqueue(CarArray,pos);
        }
        
        pos=pos+1;
	}

	public void deletefromqueue(MainRoadCar CarArray[], int pos){
		System.out.println(" Deleting it from array..");
        if(CarArray[0]==this){
        	int i=0;
        	while(i<pos){
            CarArray[i]=CarArray[i+1];
        	}
        }
        pos=pos-1;

	}

	public void move(MainRoadCar CarArray[]){
		int velocity=5;        //add velocity value here
		xcar+=velocity;
		System.out.println("moved.. ");
		System.out.println("check for withinframe");
		checkwithinframe(CarArray);
		

	}

	public void checkwithinframe(MainRoadCar CarArray[]){
		int frameboarderleftx=0;
		int frameborderrightx=1000;
		if(xcar>=frameboarderleftx && xcar<=frameborderrightx) {
			withinframe=true;
			System.out.println("it is within frame");
			move(CarArray);
		}
		
		else { 
			withinframe=false;
			System.out.println("out of frame..");
			deletefromqueue(CarArray,pos);
		}
	
	}

    public static void main(String args[]){
    	
    	MainRoadCar[] CarArray=null;  		
    	int pos=0;
    	System.out.println("starting the simulation");
    	MainRoadCar Car1= new MainRoadCar(pos,CarArray); 
    	
    	

    }


}