package main;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public  class GapStudyApplet extends JApplet implements ActionListener,ChangeListener 
{
	
	private URL url,url1;             //to be removed
	private URLConnection urlConn,urlConn1;                //to be removed
	private MotionAnimation animation;
	static JComboBox cbStarttime;
	static JComboBox cbDuration;
	static JComboBox cbCountinterval;
	private String[] vtype,CRvtype;
	boolean[] crossing,crossed;
	double[] CRAngle;
	static double[] xCord;
	static int[] yCord;
	static double[] CRyCord;
	static double[] CRxCord;
	static Image[] image;
	static Image[] CRimage;
	static int maxVeh=1000,CRmaxVeh=1000;
	int[] MRVehiclesOnRoad, CRVehiclesOnRoad;
	static int MRVehiclesOnRoadnum, CRVehiclesOnRoadnum;
	static int velocity=5;
	float[] MRtime,CRtime,MRArrivalTime,CRArrivalTime;
	int MRinitialDelay,CRinitialDelay;
	double[] randomnumberarray;
	int MRm,CRm;
	int MRFlowRate=900,CRFlowRate=600;
	double deltaAngle = 0.1;
	double acceptedGapValue,rejectedGapValue,GapValue;
	double[] AcceptedGapsArray,RejectedGapsArray,cumulativeAcceptedGaps,cumulativeRejectedGaps;
	int CRVehWaiting;
	int gapnumber;
	int checkvehicle;
	String AorR;
	boolean flagred,flaggreen;	
	static int maxLength,CRmaxLength;
	int cycleTime;
	int redTime;
	int greenTime;
	static DefaultButton bNext,ReportBtn,ActualReportBtn;
	static String[][] map,CRmap;
	JPanel content;
	static Vector<Vector<Integer>> vRow;
	private int rows;
	private int columns;
	static Vector<Vector<Integer>>vSubtRow;
	private Vector<Integer> vRowData;	
	
	private static final int IMAGE_WIDTH = 40;
	private static final int IMAGE_HEIGHT = 20; 
	private static final int CR_IMAGE_WIDTH = 20;
	private static final int CR_IMAGE_HEIGHT = 40; 
	private static final int GAP = 15;
	private static final String NO_OF_LANES = "3";
	private int delayInMillisec = 1000;
	int xSize,ySize;   //frame dimension
	Dimension size;    //for window screen component
	Timer timer;
	int imgWidth,imgHeight;   //image dimension
	int CRimgWidth,CRimgHeight; //Cross Road image height and width
	int maxTime;//43,
	int time = 6,time2;
	int detPosition,detPosition2,detPosition1 ; //position of detector
	static int stoplineposition,reflinepositionx,reflinepositiony,checkposition;
	static double randomnumberavg=0.5,randomnumberstddev=0.28;
	int MRvehCounter,CRvehCounter;
	double radius;	
	double angle=0.0;
	int carcount = 14;//TODO: now counter for cars..no. shows the index of tfCell to update.
	int bikecount = 12;
	int totalVolume;
	int maxRowtotal;
	int displayScreen; 
	Font font1,font2;
	ArrayList excludecount;
	DefaultButton bStart,bStop,bPause,bBack,bTimer; 
	CarMotion carmotion, carmotion1; 
	JPanel navigationPane,clockPane,calcPane;
	JSlider slSimSpeed;
	JLabel lStarttime,lDuration,lCountinterval;//label for combobox
	JLabel lColon1,lColon2,lHours,lMinutes,lSeconds,lTimer;//digital clock..!!
	JTextField tfStudentName;
	JLabel  studentNamelabel ;
	JPanel NamePanel,bottom;
	Clock pTimer,pTimer1,gaptimer;
	Timer timerForMRVehicle,timerForCRVehicle,timerForSimulation,timerForMRCarGeneration,timerForCRCarGeneration; //timer to fire event after particular delay for vehicle position updation
	Timer timerForGapCalculation;
	Timer timerForClock; 
	Timer timerForDisplayTime,timerForDisplayTime1;//when stopwatch is stopped the time is displayed for a few seconds using this timer 
	protected double timeCheck;
	private float addTimeCheck;
	private double timeOfTimer,timeOfTimer1;
	static int yTopLane,CRxLane;
	protected static final int  RESOLUTION = 10;
	private static final int XGAP = 30;
	private Random randomNumber;
	private int random;
	private boolean highlight;
	private Vector<String> fullVector;
	static final int AREALENGTH = 100;
	 int count;
	 TablePane table,table1,table2;
	 int noOfRows,noOfColumns;
	 int timeInSec ,dispTime,l, flag;
	 double exp_cycle1_t3,exp_cycle2_t3,exp_cycle3_t3,exp_cycle1_t13,exp_cycle2_t13,exp_cycle3_t13;
	 double ThirdVehicleTime,ThirteenthVehicletime,tut_avg_h ,tut_avg_s , tut_avg_lt,exp_avg_h ,exp_avg_s , exp_avg_lt;
	 double exp_cycle1_h,exp_cycle2_h,exp_cycle3_h,exp_cycle1_s,exp_cycle2_s,exp_cycle3_s,exp_cycle1_lt,exp_cycle2_lt,exp_cycle3_lt;
	 double tut_cycle1_h,tut_cycle2_h,tut_cycle3_h,tut_cycle1_s,tut_cycle2_s,tut_cycle3_s,tut_cycle1_lt,tut_cycle2_lt,tut_cycle3_lt;
	 
	 private DecimalFormat df;
	 //Graphics2D g2d;
	 Ellipse2D.Double circle,circle1;
	 JLabel subTitle;
	 private long startTime3,startTime13,count13Time,count3Time,endTime3,endTime13;
	 private long GapStartTime,GapEndTime;
	 private boolean started3,started13,gapStarted,gapEnded;
	 private long endTime;
	 JScrollPane scrollTab;
	 JPanel bottomPane;
	 String studentName;
	 private double[] cycle1,cycle2,cycle3,average;
	 File Report,ActualReport;
	 Graphics2D g2d;
//	 AffineTransform trans;
	 
	//method to read data into map object
    
	public void init() 
	
	{  
		flag = 0;
		started3 = false;
		started13 = false;
		//System.out.println(" Hello");
		xSize = 1320 ;
		ySize = 625;
		detPosition = 4*(xSize/7)-110;
		
		
		detPosition1 = 4*(xSize/7)+70;
		detPosition2 = 4*(xSize/7)+75;
		
		
		df = new DecimalFormat("#0.00");
		
		noOfRows = 6;
		noOfColumns= 4;
	
		flagred=true;
		flaggreen=false;
		
		GapStartTime = 0;
		GapEndTime= 0;
		gapStarted = false;
		gapEnded = false;
		stoplineposition = ySize/7+80 +2*(IMAGE_HEIGHT +GAP);
		reflinepositionx = xSize-650+ (IMAGE_HEIGHT + GAP);
		checkposition=detPosition+3*(IMAGE_HEIGHT+GAP);
		maxLength = xSize;
		radius = 1.5*IMAGE_HEIGHT+3*GAP;
		totalVolume = 0;
		maxRowtotal = 0;
		displayScreen = 0;
		MRvehCounter = 0;
		CRvehCounter = 0;
		timeOfTimer = 0.0;
		timeOfTimer1 = 0.0;
		yTopLane = ySize/7+103;
		CRxLane = detPosition+40;
		CRVehWaiting =0;
		gapnumber=0;
		checkvehicle=0;
		timeCheck = (double)delayInMillisec/(1000 * RESOLUTION);
		addTimeCheck = (float) timeCheck;
		randomNumber = new Random();
		highlight = false;
		fullVector = new Vector<String>();
		vRow = new Vector<Vector<Integer>>();
		
		font1 = new Font("Thoma",Font.BOLD, 19);
		font2 = new Font("Thoma",Font.BOLD, 15);
		excludecount = new ArrayList();
	//	AffineTransform trans = new AffineTransform();
		
		
		excludecount = new ArrayList();
		try 
		{
			vtype = new String[maxVeh];
	        CRvtype = new String[CRmaxVeh];
			xCord = new double[maxVeh];
			yCord = new int[maxVeh];
			CRxCord = new double[CRmaxVeh];
			CRyCord = new double[CRmaxVeh];
			MRVehiclesOnRoad = new int[maxVeh];
			CRVehiclesOnRoad = new int[CRmaxVeh];
			MRVehiclesOnRoadnum =0;
			CRVehiclesOnRoadnum =0;
			randomnumberarray = new double[maxVeh];
			MRtime = new float[maxVeh];
			CRtime = new float[CRmaxVeh];
			MRArrivalTime = new float[maxVeh];
			CRArrivalTime = new float[CRmaxVeh];
			AcceptedGapsArray= new double[CRmaxVeh];
			RejectedGapsArray=new double[CRmaxVeh];
			cumulativeAcceptedGaps=new double[CRmaxVeh];
			cumulativeRejectedGaps=new double[CRmaxVeh];
			crossing = new boolean[CRmaxVeh];
			crossed = new boolean[CRmaxVeh];
			CRAngle = new double[CRmaxVeh];
			
			MRm= 3600/MRFlowRate;
			CRm= 3600/CRFlowRate;
			
			for(int i=0;i<maxVeh;i++){
				MRArrivalTime[i]=0;
				CRArrivalTime[i]=0;
				MRtime[i]=0;
				CRtime[i]=0;
			}
			
			for(int i=0;i<maxVeh;i++){
				MRVehiclesOnRoad[i]=0;
				CRVehiclesOnRoad[i]=0;
			}
			
			for(int i=0;i<maxVeh;i++){
				AcceptedGapsArray[i]=0.0;
				RejectedGapsArray[i]=0.0;
				cumulativeAcceptedGaps[i]=0.0;
				cumulativeRejectedGaps[i]=0.0;
			}
			
			for(int i=0;i<maxVeh;i++){
				
				randomnumberarray[i]=Math.abs(randomNumber.nextGaussian()*randomnumberstddev+randomnumberavg);
				float roundvalue=Math.round(MRm*(-Math.log(randomnumberarray[i])));
				MRtime[i]=roundvalue;
				if(i!=0)MRArrivalTime[i]=MRArrivalTime[i-1]+MRtime[i];
				else MRArrivalTime[i]=MRtime[i];
				
				roundvalue=Math.round(CRm*(-Math.log(randomnumberarray[i])));
				CRtime[i]=roundvalue;
				if(i!=0)CRArrivalTime[i]=CRArrivalTime[i-1]+CRtime[i];
				else CRArrivalTime[i]=CRtime[i];
				
			}
			
			MRinitialDelay= Math.round(1000*MRArrivalTime[0]);
			CRinitialDelay= Math.round(1000*CRArrivalTime[0]);

			
			for(int i = 0; i < maxVeh; i++)
			{
				//vtype[i] = map[6][i+1].substring(map[6][i+1].lastIndexOf("_")+ 1);//vehicle type data on the fourth row..starts from 2nd column hence i+1.....
				vtype[i] = "Car";
				xCord[i] = -200.0;
				yCord[i]=-200;//Initialize x-coordinate to zero.
							
			}
			
			for(int i = 0; i < CRmaxVeh; i++)
			{
				//vtype[i] = map[6][i+1].substring(map[6][i+1].lastIndexOf("_")+ 1);//vehicle type data on the fourth row..starts from 2nd column hence i+1.....
				CRvtype[i] = "CR";
				CRxCord[i] =-200;
				CRyCord[i] = -200;//Initialize x-coordinate to zero.
				CRAngle[i] =0;
				crossing[i]=false;
				crossed[i] = false;
							
			}
			
			image = new Image[maxVeh];
			CRimage = new Image[CRmaxVeh];
			//load images
			for(int i = 0; i < maxVeh; i++)
			{
				if(vtype[i].equals("Car"))
				{
					//System.out.println(".....No!!");
					image[i] = getImage(getCodeBase(),"images/carTopView"+i%8+".png");
					if (image[i] == null)
	        			System.out.println("image not loaded");
					//CRimage[i] = getImage(getCodeBase(),"images/"+i/3+".png");
					Image temp = image[i];
	        		image[i] = temp.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_FAST);
	        		//Image temp1 = CRimage[i];
	        		//CRimage[i] = temp1.getScaledInstance(IMAGE_HEIGHT, IMAGE_WIDTH, Image.SCALE_FAST);
	        		
	        		//if (CRimage[i] == null)
	        			//System.out.println("Image not loaded");
				}
				
			}
			System.out.println(CRmaxVeh);
			for(int i = 0; i < CRmaxVeh; i++)
			{
				if(CRvtype[i].equals("CR"))
				{
				//	System.out.println(".....YESgjf!!");
					CRimage[i] = getImage(getCodeBase(),"images/"+i%3+".png");
					if (CRimage[i] == null)
	        			System.out.println("Image not loaded");
					
	        		Image temp1 = CRimage[i];
	        		CRimage[i] = temp1.getScaledInstance(IMAGE_HEIGHT, IMAGE_WIDTH, Image.SCALE_FAST);
	        		if(i%3==0)CRvtype[i].replace("CR", "2 Wheeler");
	        		if(i%3==1)CRvtype[i].replace("CR", "3 Wheeler");
	        		if(i%3==2)CRvtype[i].replace("CR", "4 Wheeler");
	        		
				}
				else {
					System.out.println("ERROR!!!");
				}
				
			}
			
			
			carmotion = new CarMotion(image);
			//carmotion1 = new CarMotion(CRimage);
		    carmotion.setBackground(Color.gray);
			
			int tablemin = 0;
			
			ActionListener taskPerformerMRVehicle = new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent evt)
	        	{
	        		if(timerForMRCarGeneration.isRunning() )
	        		{  
	        			//System.out.println(" Timer for vehicle is running");
	        			
	        			//System.out.println(" time :" + time);
	        			//System.out.println(" MAx time :" + maxTime);
	        			
	        			if(timeInSec < maxTime)
	        			{
	        				updateMRVehiclePosition();
	        				//updateTable();
	        			}
	        			else
	        			{
	        				timerForMRCarGeneration.stop();
	        				timerForClock.stop();
	        				time = 3;
	        				//calculateAndSetSpeed();
	        				for (int i = 0; i < xCord.length; i++)
	        					xCord[i] =-200;
	        				if(flag==1)
	        				bNext.setEnabled(true);
	        				bPause.setEnabled(false);
	        				/*bStart.setEnabled(true);
	        				cbStarttime.setEnabled(true);
	        				cbDuration.setEnabled(true);
	        				cbSampleSize.setEnabled(true);*/
	        				//resetTime();
	        				
	        				
	        				repaint();
	        				//TODO:Notify user that simulation has ended
	        			}
	        			
	        		}
	        		
	        	}
	        };
	        
	        ActionListener taskPerformerCRVehicle = new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent evt)
	        	{
	        		if(timerForCRCarGeneration.isRunning() )
	        		{  
	        			//System.out.println(" Timer for vehicle is running");
	        			
	        			//System.out.println(" time :" + time);
	        			//System.out.println(" MAx time :" + maxTime);
	        			
	        			if(timeInSec < maxTime)
	        			{
	        				try {
								updateCRVehiclePosition();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        				//updateTable();
	        			}
	        			else
	        			{
	        				timerForCRVehicle.stop();
	        				timerForClock.stop();
	        				time = 3;
	        				//calculateAndSetSpeed();
	        				for (int i = 0; i < CRxCord.length; i++)
	        					CRxCord[i] = -200;
	        				if(flag==1)
	        				bNext.setEnabled(true);
	        				bPause.setEnabled(false);
	        				/*bStart.setEnabled(true);
	        				cbStarttime.setEnabled(true);
	        				cbDuration.setEnabled(true);
	        				cbSampleSize.setEnabled(true);*/
	        				//resetTime();
	        				
	        				
	        				repaint();
	        				//TODO:Notify user that simulation has ended
	        			}
	        			
	        		}
	        		
	        	}
	        };
	        
	        ActionListener taskPerformerClock = new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent evt)
	        	{
	        		if(timerForClock.isRunning() )
	        		{//	System.out.println("clock timer called");
	        			int sec = Integer.parseInt(lSeconds.getText());
	        			int min = Integer.parseInt(lMinutes.getText());
	        			int hr = Integer.parseInt(lHours.getText());
	        			
	        			//for switching between red and green signals...
	        			timeInSec = min*60 + sec;
	        			
	        		//	updateForSignalSwitch(timeInSec);
	        			displayTime(hr, min, sec);
	        			repaint();
	        		}           			
	        	}

				private void updateForSignalSwitch(int timeInSec) {
					dispTime = 0;
					if(timeInSec%cycleTime == 0)
		        	{
		        		dispTime = 0;
		        	}
		        	else
		        	{
		        		dispTime = timeInSec%cycleTime;
		        	}
					
				}
	        };
	        
	        ActionListener taskPerformerTime = new ActionListener()
	        {
	        	int i = 0;
	        	public void actionPerformed(ActionEvent evt)
	        	{
	        		i++;
	        		
					if(i%2 == 1)
						{
						if(count == 3)
						{
						lTimer.setText(timeOfTimer+" seconds");
						ThirdVehicleTime=(timeOfTimer);
						}
						
						
						if( count == 13)
						{
							lTimer.setText(timeOfTimer+" seconds");
							ThirteenthVehicletime=(timeOfTimer);	
						}
						
						}
						
					else
					{
						lTimer.setText("");
						timerForDisplayTime.stop();
						//pTimer.reset();
					}
					
	        	}
	        };
	        
	        ActionListener taskPerformerTime1 = new ActionListener()
	        {
	        	int i = 0;
	        	public void actionPerformed(ActionEvent evt)
	        	{
	        		i++;
	        		//int s = pTimer.getSeconds();
					//int m = pTimer.getMinutes();
					//int h = pTimer.getHours();
					
					if(i%2 == 1)
						{
						lTimer.setText(timeOfTimer1+" seconds");
						ThirteenthVehicletime=(timeOfTimer1);
						}
						
					else
					{
						lTimer.setText("");
						timerForDisplayTime1.stop();
						//pTimer.reset();
					}
					
	        	}
	        };
	        
	        ActionListener taskMRCarGeneration = new ActionListener()
	        {
	        	
	        	public void actionPerformed(ActionEvent evt)
	        	{		System.out.println("mr cargeneration");
	        			xCord[MRvehCounter]=0;
	        			yCord[MRvehCounter]=yTopLane;
	        			MRVehiclesOnRoad[MRVehiclesOnRoadnum]=MRvehCounter;
	        			MRvehCounter++;
	        			MRVehiclesOnRoadnum++;
	        			int delay= Math.round(1000*MRtime[MRvehCounter]);
	        			timerForMRCarGeneration.setDelay(delay);
	        		
	        		
	        		
					
	        	}
	        };
	        
	        ActionListener taskCRCarGeneration = new ActionListener()
	        {
	        	
	        	public void actionPerformed(ActionEvent evt)
	        	{
	        			CRxCord[CRvehCounter]=CRxLane;
	        			CRyCord[CRvehCounter]=stoplineposition+100;
	        			CRVehiclesOnRoad[CRVehiclesOnRoadnum]=CRvehCounter;
	        			CRvehCounter++;
	        			CRVehiclesOnRoadnum++;
	        			int delay= Math.round(1000*CRtime[CRvehCounter]);
	        			timerForCRCarGeneration.setDelay(delay);
	        			
	        		
					
	        	}
	        };
	        
	        ActionListener taskPerformerSimulation = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(timeInSec>=maxTime){
						//updateCRVehiclePosition();
						//updateMRVehiclePosition();
						timerForCRVehicle.stop();
						timerForMRVehicle.stop();
						timerForClock.stop();
						timerForDisplayTime.stop();
						timerForDisplayTime1.stop();
						repaint();
					//	timerForSimulation.setDelay(1);
					//	drawGraphs();
						bNext.setEnabled(true);
											
					}
					
				/*	else{
						timerForCRCarGeneration.stop();
						timerForMRCarGeneration.stop();
						timerForMRVehicle.stop();
						timerForCRVehicle.stop();
					}*/
				}
			};
			
					
			ActionListener taskPerformerGapCalc = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
				//	while(timerForGapCalculation.isRunning()){
					GapValue+=0.01;
				//	}
				//	System.out.println(GapValue);
				}
			};
			
			
			timerForMRVehicle = new Timer(100 , taskPerformerMRVehicle);//fires event after 100 millisec. for constant refresh rate for updation of vehicle position
			timerForCRVehicle = new Timer(100 , taskPerformerCRVehicle);
			timerForMRCarGeneration = new Timer(MRinitialDelay, taskMRCarGeneration);
			timerForCRCarGeneration = new Timer(CRinitialDelay, taskCRCarGeneration);
			timerForClock = new Timer(1000,taskPerformerClock);
	        timerForDisplayTime = new Timer(4000,taskPerformerTime);
	        timerForDisplayTime1 = new Timer(4000,taskPerformerTime1);
	        timerForSimulation = new Timer(1, taskPerformerSimulation);
	        timerForGapCalculation = new Timer(10,taskPerformerGapCalc);
	        
	        timerForDisplayTime.setInitialDelay(1);
	        timerForDisplayTime1.setInitialDelay(1);
	        
	        
			//for first pane...
			
			
			
			//second pane in calcPane...
			
			//navigationPane...part of maincalcPane having buttons to switch between different panes in calcPane
			
	        //maincalcPane.add(navigationPane,BorderLayout.SOUTH);
	        
	        //JScrollPane scrollPane = new JScrollPane(maincalcPane);
			//Initialize the content pane
			//content = getContentPane();
	        
	        /*content = new JPanel(new BorderLayout());
			content.setBackground(Color.white);
			content.add(carmotion,BorderLayout.CENTER);*/
			
			navigationPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			navigationPane.setOpaque(false);
	        bBack = new DefaultButton("Back");
	        bBack.addActionListener(this);
	        bBack.setEnabled(false);
	        navigationPane.add(bBack);
	        bNext = new DefaultButton("Next");
	        bNext.addActionListener(this);
	        bNext.setEnabled(false);
	        navigationPane.add(bNext);
	        
	        bTimer.addActionListener(this);
			
	        calcPane = new JPanel(new CardLayout());
	        JScrollPane scrollPane = new JScrollPane(carmotion);
	        
	        
	        
	        subTitle = new JLabel(" Enter Values in the Table",JLabel.CENTER);
			subTitle.setOpaque(false);
			subTitle.setFont(font2);
			subTitle.setForeground(Color.BLUE);
	        
			
			bottomPane= new JPanel(new BorderLayout());
			bottomPane.setOpaque(false);
			
	        makeTable();
	        
			
			if(flag==1)
			bottomPane.add(subTitle,BorderLayout.NORTH);
			
			bottomPane.add(navigationPane,BorderLayout.SOUTH);
			
			
			JPanel mainContent = new JPanel(new GridLayout(2,1));
			mainContent.setOpaque(false);
			mainContent.add(scrollPane);
			mainContent.add(bottomPane);
			
			add(mainContent);
	       
			//setContentPane(carmotion);
	        //setContentPane(mainPane);
			//Initialize reportPane
			
			double border = 10;
			
			//evaluationPane.setBorder(BorderFactory.createLineBorder(Color.black));
			
	        //listeners...
			bStart.addActionListener(this);
			bPause.addActionListener(this);
			bStop.addActionListener(this);
			cbStarttime.addActionListener(this);
			cbDuration.addActionListener(this);
			cbCountinterval.addActionListener(this);
			
			
			slSimSpeed.addChangeListener(this);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
	}

	class CarMotion extends JPanel 
    {	
    	
    	
    	public CarMotion(LayoutManager layout) 
    	{
    		super(layout);
    		setLayout(layout);
    		
    	}
    	//constructor 
    	public CarMotion(Image[] image) throws IOException 
    	{   
    		
    		bStart = new DefaultButton("start");
    		bPause = new DefaultButton("pause");
    		bStop = new DefaultButton("Reset");
    		
    		
    		bottom = new JPanel();
    		bottom.setOpaque(false);
    		
    		
    		bTimer = new DefaultButton("Timer");
    		bTimer.setEnabled(false);
    		pTimer = new Clock();
    		pTimer1=new Clock();
    		lTimer = new JLabel();
    		lTimer.setFont(new Font("Courier New", Font.ITALIC, 12));
    		lTimer.setForeground(Color.magenta);
    		
    		//bottom.add(bTimer);
    		if(flag==0)
    		{
    		bottom.add(pTimer);
    		bottom.add(lTimer);
    		}
    		
    		slSimSpeed = new JSlider(0,20,12);
    		slSimSpeed.setOpaque(false);
    		slSimSpeed.setPreferredSize( new Dimension( 300, 45 ) );
    		slSimSpeed.setMajorTickSpacing(5);
    		slSimSpeed.setPaintTicks(true);
    		slSimSpeed.setSnapToTicks(true);
    		
    		Hashtable labelTable = new Hashtable();
    		JLabel label = new JLabel("Very Slow");
    		labelTable.put( new Integer( 0 ), label );
    		label =new JLabel("Slow");
    		labelTable.put( new Integer( 5 ), label );
    		label =new JLabel("Normal");
    		labelTable.put( new Integer( 10 ), label );
    		label =new JLabel("Fast");
    		labelTable.put( new Integer( 15 ), label );
    		label =new JLabel("Very Fast");
    		labelTable.put( new Integer( 20 ), label );
    		slSimSpeed.setLabelTable( labelTable );
    		/*label =new JLabel("Super Fast");
    		labelTable.put( new Integer( 25 ), label );
    		*/slSimSpeed.setLabelTable( labelTable );
    		//slSimSpeed.setFont(new Font("Courier",Font.ITALIC,15));
    		slSimSpeed.setPaintLabels(true);
    		
    		Font titleFont = new Font("ArialBlack",Font.BOLD,39);
    		JLabel title = new JLabel ("<html><font color = blue> Gap Study</font></html> ",JLabel.CENTER);
    		title.setOpaque(false);
    		title.setFont(titleFont);
    		
    		JPanel pane1 = new JPanel(new BorderLayout());
    		pane1.setOpaque(false);
    		
    		studentNamelabel = new JLabel("Enter Student Name :",JLabel.LEFT);
            tfStudentName = new JTextField("",20);
    		tfStudentName.setHorizontalAlignment(JTextField.CENTER);
            
    		NamePanel = new JPanel();
    		NamePanel.setOpaque(false);
    	    
    		NamePanel.add(studentNamelabel);
    		NamePanel.add(tfStudentName);
    		
    		//pane1.add(title,BorderLayout.NORTH);
    		if(flag==1)
    		pane1.add(NamePanel,BorderLayout.NORTH);
    		
    		JPanel pane2= new JPanel(/*new FlowLayout(FlowLayout.LEFT)*/);
    		pane2.setOpaque(false);
    		
    		pane2.add(bStart);
    		pane2.add(bPause);
    		pane2.add(bStop);
    		pane2.add(slSimSpeed);
    		
    		for(int i =0;i<=20;i+=5)
    		{
    			label = (JLabel) labelTable.get( new Integer( i ) );
        		Font font = new Font("Arial",Font.ITALIC,10);
        		label.setFont( font ); 
        		label.setSize( label.getPreferredSize() );
    		}
    		
    		String[] strttime = {"8","9","10"};
    		String[] duration = {"10"};
    		String[] countinterval = {"5","10","15"};
    		lStarttime = new JLabel("Start Time");
    		lDuration = new JLabel("Duration(minutes)");
    		lCountinterval = new JLabel("Count Interval(minutes)");
    		cbStarttime = new JComboBox(strttime);
    		cbDuration = new JComboBox(duration);
    		cbCountinterval = new JComboBox(countinterval);
    		clockPane = new JPanel();
    		Font font1 = new Font("Arial",Font.BOLD,20);
    		lHours = new JLabel((String) cbStarttime.getSelectedItem());
    		if(((String) cbStarttime.getSelectedItem()).length() == 1)
				lHours.setText("0"+(String) cbStarttime.getSelectedItem());
			else
				lHours.setText((String) cbStarttime.getSelectedItem());
    		lHours.setFont( font1 );
    		lMinutes = new JLabel("00");
    		lMinutes.setFont( font1 );
    		lSeconds = new JLabel("00");
    		lSeconds.setFont( font1 );
    		lColon1 = new JLabel(":");
    		lColon1.setFont( font1 );
    		lColon2 = new JLabel(":");
    		lColon2.setFont( font1 );
    		
    		pane2.add(lStarttime);
    		pane2.add(cbStarttime);
    		pane2.add(lDuration);
    		pane2.add(cbDuration);
    		//pane2.add(lCountinterval);
    		//pane2.add(cbCountinterval);
    		clockPane.add(lHours);
    		clockPane.add(lColon1);
    		clockPane.add(lMinutes);
    		clockPane.add(lColon2);
    		clockPane.add(lSeconds);
    		clockPane.setOpaque(true);
    		//clockPane.setBackground(Color.yellow);
    		pane2.add(clockPane);
    		pane2.add(bottom);
    		
    		
    		pane1.add(pane2,BorderLayout.CENTER);
    		add(pane1);
    		
    	}
    	
    	@Override
    	public void paintComponent(Graphics g)
    	{
    		super.paintComponent( g );
    		g2d = (Graphics2D) g;
    		int w = getWidth();
    	    int h = getHeight();
    	    int x = 5;
    	    int y = 7;
                 
    	 
    	    Color color1 = getBackground();
    	    Color color2 = Color.white;
    	    
    	    GradientPaint gp = new GradientPaint( 0, 0, color1, 100, 100, color2,true );
    	    
    	    g2d.setPaint( Color.white );
    	    g2d.fillRect( 0, 0, w, h );
    	 
    	    setOpaque( false );
    	    super.paintComponent( g );
    	    setOpaque( true );
    		
    		Rectangle2D rect = new Rectangle2D.Float(0, ySize/7+60, xSize, 2*(IMAGE_HEIGHT +GAP));
    		
    	    GradientPaint gp1 = new GradientPaint(0, ySize/7+60, Color.darkGray,
    	        xSize, ySize/7+60, Color.darkGray, true);
    	    Rectangle2D rect2 = new Rectangle2D.Float(xSize-650, ySize/7+40, 2*(IMAGE_HEIGHT +GAP), 2*(IMAGE_HEIGHT +GAP+20));
    	    Rectangle2D rect3 = new Rectangle2D.Float(xSize-650, ySize/7+70, 2*(IMAGE_HEIGHT +GAP), 200);
    	    
    	    g2d.setPaint(gp1);
    	    g2d.fill(rect);
    	    
    		Stroke drawingStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    		Stroke stroke = g2d.getStroke();
    		g2d.drawLine(0, ySize/7+60, xSize - 1, ySize/7+60);// change something for perfection
    		//g2d.drawLine(0, ySize/7+20 + 2*(IMAGE_HEIGHT +GAP) , xSize - 1, ySize/7+20 + 2*(IMAGE_HEIGHT +GAP) );
    		g2d.setColor(Color.white);
    		g2d.drawLine(detPosition, ySize/7+60 , detPosition, ySize/7+60 + 2*(IMAGE_HEIGHT +GAP) );//detector position
    		g2d.drawLine(detPosition+3*(IMAGE_HEIGHT+GAP)+10, ySize/7+60 , detPosition+3*(IMAGE_HEIGHT+GAP)+10, ySize/7+60 + 2*(IMAGE_HEIGHT +GAP));//detector position
    		//g2d.setStroke(drawingStroke);
    		g2d.drawLine(0, ySize/7+60 + IMAGE_HEIGHT +GAP, xSize - 1, ySize/7+60 + IMAGE_HEIGHT + GAP);//lane marking
    		//g2d.drawLine(0, ySize/7+20 + 2*(IMAGE_HEIGHT +GAP), xSize - 1, ySize/7+20 + 2*(IMAGE_HEIGHT + GAP));//lane marking
    		
    		
    		
    		
    		g2d.setStroke(stroke);
    		g2d.setPaint(gp1);
    		g2d.fill(rect2);
    		
    		g2d.setPaint(gp1);
    		g2d.fill(rect3);
    		g2d.setStroke(stroke);
    		
    		g2d.setColor(Color.white);
    		//g2d.setStroke(drawingStroke);
    		//g2d.drawLine(xSize-200+ 2*(IMAGE_HEIGHT + GAP), ySize/7, xSize-200+ 2*(IMAGE_HEIGHT + GAP), ySize/7+20 + 3*(IMAGE_HEIGHT + GAP+14));//lane marking
    		g2d.drawLine(xSize-650+ (IMAGE_HEIGHT + GAP), ySize/7+40, xSize-650+ (IMAGE_HEIGHT + GAP), ySize/7+260+ 3*(IMAGE_HEIGHT + GAP+14));//lane marking
    		g2d.setStroke(stroke);
    	//	stoplineposition = ySize/7+80 +2*(IMAGE_HEIGHT +GAP);
    	//	reflinepositionx = xSize-650+ (IMAGE_HEIGHT + GAP);
    	//	checkposition=detPosition+3*(IMAGE_HEIGHT+GAP)+10;
    		g2d.drawLine(detPosition, ySize/7+80 +2*(IMAGE_HEIGHT +GAP), xSize-650+2*(IMAGE_HEIGHT+GAP)+20,ySize/7+80 +2*(IMAGE_HEIGHT +GAP));
    		
    		//g.drawString(Integer.toString(xSize/2),25 ,25 );
    		//System.out.println( " det pos :" + detPosition);
    		//System.out.println(" x size : " + xSize);
    	}
    	public void paint(Graphics g) 
        {
    		
        	super.paint(g);
        	
        	/*-------------
        	 * timeInSec = (Integer.parseInt(lMinutes.getText())*60+Integer.parseInt(lSeconds.getText()));
        	dispTime = 0;
        	
        	l = noOfColumns;
        	g2d = (Graphics2D) g;
        	if(timeInSec%cycleTime == 0)
        	{
        		dispTime = 0;
        		//count=0;
        	}
        	else
        	{
        	dispTime += (timeInSec%cycleTime);
        	}-------------------*/
        	//System.out.println("timeInSec " + timeInSec);
        	//System.out.println("dispTime " + dispTime);
        	
        	Graphics2D g2d = (Graphics2D) g;
        	// Draw images 
        	for(int i = 0;i < maxVeh; i++)
        	{        		
        		g2d.drawImage(image[i], (int)xCord[i]+XGAP, yCord[i], this);//draw the vehicles at the positions calculated..
        		        	
        	}
        	
        	for(int i = 0;i < CRmaxVeh; i++)
        	{
        		
        /*		if((int)CRyCord[i] == yTopLane-2*XGAP){
        		//	System.out.println("90 rotation called");
        			AffineTransform transform = new AffineTransform();
        			transform.rotate(Math.toRadians(90));
        		//	g2d.setTransform(transform);
        			g2d.drawImage(CRimage[i],transform,this);
        		//	g2d.setTransform(null);
        		} 
        		*/
        		   	
    			
    			g2d.drawImage(CRimage[i], (int)CRxCord[i]+XGAP,(int)CRyCord[i], this);//draw the vehicles at the positions calculated..
    			 
        	/*	else{
        		if(CRyCord[i]==yTopLane-2*XGAP){
        			System.out.println("drawing after cross");
        			g2d.drawImage(CRimage[i], (int)CRxCord[i]+XGAP,(int)CRyCord[i], this);//draw the vehicles at the positions calculated..
        		}
        		else { System.out.println("drawing cross");
        			g2d.drawImage(CRimage[i],trans, this);
        			g2d.drawImage(CRimage[i], (int)CRxCord[i]+XGAP,(int)CRyCord[i], this);
        			
        		}
        		}	*/
        	}
        	
        	// For Red :Light
        	Ellipse2D.Double circle =new Ellipse2D.Double(detPosition, stoplineposition, 10,10);
        	g2d.setPaint(Color.gray);
    		g2d.setColor(Color.gray);
    		g2d.fill(circle);
    		if(flagred)
        	{
        		g2d.setPaint(Color.red);
        		g2d.setColor(Color.red);
        		g2d.fill(circle);
        		excludecount.clear();
        		count=0;
        		pTimer.stop();
        		started3 = false;
        		pTimer.reset();
        		
        		
        		//ThirteenthVehicletime=0.0;
        		//ThirdVehicleTime=0.0;
        		//System.out.println("count red : "+count);
        	}
        	
    		// For Green Light 
    		if(flaggreen)
    		{   
    			g2d.setPaint(Color.green);
        		g2d.setColor(Color.green);
        		g2d.fill(circle);
    		//  greenTimeTask();
    		}
    	}
    }
	
	
	
	public void makeTable()
	{   
		
		table = new TablePane(new GridLayout(),noOfRows,noOfColumns);
		
        if(flag==0)
        setTutTableValues();
        
        else if (flag==1)
        setExpTableValues(table);
        
        //JPanel tabPane = new JPanel(new BorderLayout());
       // tabPane.setOpaque(false);
        //tabPane.add(table,BorderLayout.CENTER);
        calcPane.add(table,"First Table");
        scrollTab=new JScrollPane(calcPane);
        scrollTab.setPreferredSize(new Dimension(1000, 150));
        bottomPane.add(scrollTab,BorderLayout.CENTER);
        //repaint();
		
	}
	
		
	public void greenTimeTask()
	{  
         
		Ellipse2D.Double circle1 =new Ellipse2D.Double(detPosition, stoplineposition, 10,10);
		
		g2d.setPaint(Color.green);
		g2d.setColor(Color.green);
		g2d.fill(circle1);
		
		
		pTimer.start();
		
		
		
		if(!started3)
		{
			startTime3  = System.currentTimeMillis();
			started3 = true;
		}
		
	
		
		for(int i = 0;i < maxVeh; i++)
    	{  
			
			if(xCord[i] >detPosition  && !excludecount.contains(Integer.toString(i)) )
			{
			   count++;
			   excludecount.add(Integer.toString(i));
			   //System.out.println(" count grn : " + count);
			  
			}
			
			if(count == 3  && !highlight &&   xCord[i] > detPosition && pTimer.isRunning())
			{   				
				endTime3 = System.currentTimeMillis();
				long delay3 = (long) ((endTime3 - startTime3)/((double)delayInMillisec/1000));
				timeOfTimer = (((double)delay3/1000));
				//timerForDisplayTime.start();
				
				
				//System.out.println( " Third vehicle time : " + (ThirdVehicleTime));
				highlight = true;
				
				
				if(flag==0)
				{
				
				g2d.setColor(Color.yellow);
				g2d.draw3DRect((int)xCord[i]+XGAP, (int)yCord[i], IMAGE_WIDTH, IMAGE_HEIGHT, true);
				g2d.drawString(" Third Vehicle", detPosition, ySize/7+73);
				
				}
				
				{
				if(timeInSec < cycleTime)
				 { 
				    
					if(flag==0)
					{
						
					table.tfCell[l+1].setText(Double.toString(ThirdVehicleTime));
					table.tfCell[l+1].setOpaque(true);
					table.tfCell[l+1].setBackground(Color.yellow);
					}
					
					else if ( flag==1)
					{
						exp_cycle1_t3 = ThirdVehicleTime;
						//System.out.println(" exp_cycle1_t3:"+exp_cycle1_t3 );
					}
					
				 }
				
				else if (timeInSec > cycleTime && timeInSec < (cycleTime*2))
				{
					if(flag==0)
					{
					
						
					table.tfCell[l+2].setText(Double.toString(ThirdVehicleTime));
					table.tfCell[l+2].setOpaque(true);
					table.tfCell[l+2].setBackground(Color.yellow);
					}
					else if ( flag==1)
					{
						exp_cycle2_t3 = ThirdVehicleTime;
						//System.out.println(" exp_cycle2_t3:"+exp_cycle2_t3 );
					}
					
					
					
				}
				
				else if (timeInSec > (cycleTime*2) && timeInSec < (cycleTime*3))
				{
					
					if(flag==0)
					{
						
						
					table.tfCell[l+3].setText(Double.toString(ThirdVehicleTime));
					table.tfCell[l+3].setOpaque(true);
					table.tfCell[l+3].setBackground(Color.yellow);
					}
					
					else if ( flag==1)
					{
						exp_cycle3_t3 = ThirdVehicleTime;
						//System.out.println(" cycle3_t1:"+exp_cycle3_t3 );
					}
					
				}
				
			}
				
				
			}
			
			df = new DecimalFormat("#0.00");
			if( count==13 && !highlight && xCord[i] >detPosition && pTimer.isRunning() )
			{   
				
			
				endTime13 = System.currentTimeMillis();
				long delay13 = (long) ((endTime13 - startTime3)/((double)delayInMillisec/1000));
				timeOfTimer = (((double)delay13/1000));
				timerForDisplayTime.start();
				
				
				//System.out.println( " Thirteenth  Vehicle  time : " + ThirteenthVehicletime);
				
				highlight = true;
				
				
				if(flag==0)
				{
				
				g2d.setColor(Color.yellow);
				g2d.draw3DRect((int)xCord[i]+XGAP, (int)yCord[i], IMAGE_WIDTH, IMAGE_HEIGHT, true);
				g2d.drawString(" Thirteenth Vehicle", detPosition1, ySize/7+73);
				
				}
				
				if(timeInSec < cycleTime)
				 { 
				    
					
				if(flag==0)
				{   
					table.tfCell[l*2+1].setText((Double.toString(ThirteenthVehicletime)));
					table.tfCell[l*2+1].setOpaque(true);
					table.tfCell[l*2+1].setBackground(Color.yellow);
					
					tut_cycle1_h = (Double.parseDouble(table.tfCell[l*2+1].getText())-Double.parseDouble(table.tfCell[l+1].getText()))/(13-3);
					tut_cycle1_s = 3600 / tut_cycle1_h ;
					tut_cycle1_lt = Double.parseDouble(table.tfCell[l+1].getText())-(3*tut_cycle1_h);
					table.tfCell[l*3+1].setText(df.format(tut_cycle1_h));
					table.tfCell[l*4+1].setText(df.format(tut_cycle1_s));
					table.tfCell[l*5+1].setText(df.format(tut_cycle1_lt));
					//System.out.println( " 1");
					//System.out.println( " tut_cycle1_h :" + tut_cycle1_h);
					//System.out.println( " tut_cycle1_s :" + tut_cycle1_s );
					//System.out.println( " tut_cycle1_l : " + tut_cycle1_lt);
				}
				
					else if ( flag==1)
					{
						exp_cycle1_t13 = ThirteenthVehicletime;
						exp_cycle1_h=  (exp_cycle1_t13-exp_cycle1_t3)/(13-3);
						exp_cycle1_s= 3600/exp_cycle1_h;
						exp_cycle1_lt= exp_cycle1_t3-(3* exp_cycle1_h);
						
						System.out.println("  exp_cycle1_t3 : " +exp_cycle1_t3);
						System.out.println("  exp_cycle1_t13 :" +exp_cycle1_t13 );
						System.out.println( " exp_cycle1_h  :" +exp_cycle1_h );
						System.out.println( " exp_cycle1_s  :" +exp_cycle1_s );
						System.out.println( " exp_cycle1_lt :"+exp_cycle1_lt);
					}
				}
				
				else if (timeInSec > cycleTime && timeInSec < (cycleTime*2))
				{
					
					if(flag==0)
					{
					table.tfCell[l*2+2].setText(Double.toString(ThirteenthVehicletime));
					table.tfCell[l*2+2].setOpaque(true);
					table.tfCell[l*2+2].setBackground(Color.yellow);
					
					tut_cycle2_h = (Double.parseDouble(table.tfCell[l*2+2].getText())-Double.parseDouble(table.tfCell[l+2].getText()))/(13-3);
					tut_cycle2_s = 3600 / tut_cycle2_h ;
					tut_cycle2_lt = Double.parseDouble(table.tfCell[l+2].getText())-(3*tut_cycle2_h);
   					table.tfCell[l*3+2].setText(df.format(tut_cycle2_h));
   					table.tfCell[l*4+2].setText(df.format(tut_cycle2_s));
   					table.tfCell[l*5+2].setText(df.format(tut_cycle2_lt));
   					
   					System.out.println( " 2");
   					System.out.println( " tut_cycle2_h :" + tut_cycle2_h);
   					System.out.println( " tut_cycle2_s :" + tut_cycle2_s );
   					System.out.println( " tut_cycle2_l : " + tut_cycle2_lt);
   					
					}
					else if ( flag==1)
					{
						exp_cycle2_t13 = ThirteenthVehicletime;
						
						exp_cycle2_h= (exp_cycle2_t13-exp_cycle2_t3)/(13-3);
						exp_cycle2_s= 3600/exp_cycle2_h;
						exp_cycle2_lt= exp_cycle2_t3-(3* exp_cycle2_h);
						
						System.out.println(" exp_cycle2_t3 :"+exp_cycle2_t3 );
						System.out.println(" exp_cycle2_t13 :"+exp_cycle2_t13 );
						System.out.println( " exp_cycle2_h  :" +exp_cycle2_h );
						System.out.println( "  exp_cycle2_s :" +exp_cycle2_s );
						System.out.println( "  exp_cycle2_lt :" + exp_cycle2_lt);
					}
				}
				
				else if (timeInSec > (cycleTime*2) && timeInSec < (cycleTime*3))
				{
					
					if(flag==0)
					{
					table.tfCell[l*2+3].setText(Double.toString(ThirteenthVehicletime));
					table.tfCell[l*2+3].setOpaque(true);
					table.tfCell[l*2+3].setBackground(Color.yellow);
					
					tut_cycle3_h = (Double.parseDouble(table.tfCell[l*2+3].getText())-Double.parseDouble(table.tfCell[l+3].getText()))/(13-3);
					tut_cycle3_s = 3600 / tut_cycle3_h ;
					tut_cycle3_lt = Double.parseDouble(table.tfCell[l+3].getText())-(3*tut_cycle3_h);
   					table.tfCell[l*3+3].setText(df.format(tut_cycle3_h));
   					table.tfCell[l*4+3].setText(df.format(tut_cycle3_s));
   					table.tfCell[l*5+3].setText(df.format(tut_cycle3_lt));
   					
   					
   					tut_avg_h = (tut_cycle1_h + tut_cycle2_h + tut_cycle3_h)/3 ; 
   					tut_avg_s = (tut_cycle1_s + tut_cycle2_s + tut_cycle3_s)/3;
   					tut_avg_lt = (tut_cycle1_lt +  tut_cycle2_lt + tut_cycle3_lt)/3 ;
   					
   					table.tfCell[l*3+4].setText(df.format(tut_avg_h));
   					table.tfCell[l*4+4].setText(df.format(tut_avg_s));
   					table.tfCell[l*5+4].setText(df.format(tut_avg_lt));
   					
   					System.out.println( " 3");
   					System.out.println( " tut_cycle3_h :" + tut_cycle3_h);
   					System.out.println( " tut_cycle3_s :" + tut_cycle3_s );
   					System.out.println( " tut_cycle3_lt : " + tut_cycle3_lt);
					}
					
					else if ( flag==1)
					{
						exp_cycle3_t13 = ThirteenthVehicletime;
						exp_cycle3_h= (exp_cycle3_t13-exp_cycle3_t3)/(13-3);
						exp_cycle3_s= 3600/exp_cycle3_h;
						exp_cycle3_lt= exp_cycle3_t3-(3* exp_cycle3_h);
						
						
						exp_avg_h= (exp_cycle1_h + exp_cycle2_h + exp_cycle3_h)/3;
						exp_avg_s= (exp_cycle1_s + exp_cycle2_s + exp_cycle3_s)/3;
						exp_avg_lt= (exp_cycle1_lt + exp_cycle2_lt + exp_cycle3_lt)/3;
						
						
						System.out.println(" exp_cycle1_t3:"+exp_cycle3_t3 );
						System.out.println(" exp_cycle1_t13:"+exp_cycle3_t13 );
						System.out.println(" exp_cycle3_s:"+exp_cycle3_s );
						System.out.println(" exp_cycle3_lt:"+exp_cycle3_lt );
						
					}
					
				}
				
				
				
				
			}
			
			if(highlight)
			{
				g2d.setColor(Color.yellow);
				g2d.draw3DRect((int)xCord[i]+XGAP, (int)yCord[i], IMAGE_WIDTH, IMAGE_HEIGHT, true);
			}
			highlight=false;
			
        
		
	}    
		
	
	}
	
	
	
	
	public void readData(URLConnection urlConn) throws IOException
	{
		ArrayList line = new ArrayList();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		String s = null;
		while ((s = br.readLine()) != null)
			line.add(s);
		map = new String[line.size()][];
		for (int i = 0; i < map.length; i++) 
		{
			s = (String) line.get(i);
			StringTokenizer st = new StringTokenizer(s, ",");
			String[] arr = new String[st.countTokens()];
			for (int j = 0; j < arr.length; j++)
			{
				String token = st.nextToken();
				if(token != "")
				{
					arr[j] = token;
				}
				else
				{
					arr[j] = "0";
				}
				//System.out.println("row::"+i+" value"+ arr[j]);
			}
			map[i] = arr;
		}
		
		//maxTime = Integer.parseInt(map[0][1]);
				
		
	//	cycleTime = (int) Double.parseDouble(map[3][1]);
	//	redTime = (int) Double.parseDouble(map[4][1]);
	//	greenTime = (int) Double.parseDouble(map[5][1]);
		//System.out.println(maxTime+" "+maxVeh+" "+maxLength+" "+cycleTime+" "+redTime);
		
		
	}
	
	public void CRreadData(URLConnection urlConn1) throws IOException
	{
		ArrayList line = new ArrayList();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn1.getInputStream()));
		String s = null;
		while ((s = br.readLine()) != null)
			line.add(s);
		CRmap = new String[line.size()][];
		for (int i = 0; i < CRmap.length; i++) 
		{
			s = (String) line.get(i);
			StringTokenizer st = new StringTokenizer(s, ",");
			String[] arr = new String[st.countTokens()];
			for (int j = 0; j < arr.length; j++)
			{
				String token = st.nextToken();
				if(token != "")
				{
					arr[j] = token;
				}
				else
				{
					arr[j] = "0";
				}
				//System.out.println("row::"+i+" value"+ arr[j]);
			}
			CRmap[i] = arr;
		}
		
		//maxTime = Integer.parseInt(map[0][1]);
		
		CRmaxVeh = Integer.parseInt(CRmap[1][1]);
		CRmaxLength = (int) Double.parseDouble(CRmap[2][1]);
		//CRcycleTime = (int) Double.parseDouble(CRmap[3][1]);
		//CRredTime = (int) Double.parseDouble(CRmap[4][1]);
		//greenTime = (int) Double.parseDouble(CRmap[5][1]);
		//System.out.println(maxTime+" "+maxVeh+" "+maxLength+" "+cycleTime+" "+redTime);
		
		
	}
	
	
	public void setTutTableValues()
	{
		table.lCell[0].setText("Serial No");
		table.lCell[1].setText("Vehicle Type");
		table.lCell[2].setText("Gap (sec)");
		table.lCell[3].setText("Accept/Reject");
		//table.lCell[4].setText("Average");
		
		
		table.tfCell[noOfColumns+0].setText("1");
		table.tfCell[noOfColumns+4].setText("2");
		table.tfCell[noOfColumns+8].setText("3");
		table.tfCell[noOfColumns+12].setText("4");
		table.tfCell[noOfColumns+16].setText("5");
		
		String[] vehicletypes = {"2","3","4"};
		table.tfCell[noOfColumns+1].setEditable(true);
		table.tfCell[noOfColumns+5].setEditable(true);
		table.tfCell[noOfColumns+9].setEditable(true);
		table.tfCell[noOfColumns+13].setEditable(true);
		table.tfCell[noOfColumns+17].setEditable(true);
		
	//	table.tfCell[noOfColumns+1].setText(AcceptedGapsArray[i]);
		
	/*	if (table.tfCell[noOfColumns+1].getText()!="2" ||table.tfCell[noOfColumns+1].getText()!="3" ||table.tfCell[noOfColumns+1].getText()!="4"){
			table.tfCell[noOfColumns+1].setText("value not allowed");
			table.tfCell[noOfColumns+1].setText(" ");
			table.tfCell[noOfColumns+1].setEditable(true);
		
		}*/


		
	//	table.tfCell[noOfColumns+1].setText(JComboBox(vehicletypes));
	//	table.tfCell[noOfColumns+5] = new JComboBox(vehicletypes);
		
		//table.tfCell[noOfColumns+4].setText("-");
	//	table.tfCell[noOfColumns*2+4].setText("-");
		
		
	}
	
	
	public void setExpTableValues(TablePane table)
	{   
		int l = noOfColumns+1;
		
		table.lCell[0].setText("Serial No");
		table.lCell[1].setText("Vehicle Type");
		table.lCell[2].setText("Gap (sec)");
		table.lCell[3].setText("Accept/Reject");
		//table.lCell[4].setText("Average");
		
		table.tfCell[noOfColumns+0].setText("1");
		table.tfCell[noOfColumns+4].setText("2");
		table.tfCell[noOfColumns+8].setText("3");
		table.tfCell[noOfColumns+12].setText("4");
		table.tfCell[noOfColumns+16].setText("5");
		
		for (int i =noOfColumns;i< noOfColumns*noOfRows;i++)
		{
			table.tfCell[i].setEditable(true);
		}
		
		
		for(int i = noOfColumns,j=0;i < noOfRows*noOfColumns;i += noOfColumns,j++)
		{
			table.tfCell[i].setEditable(false);
		}
		
		table.tfCell[noOfColumns+3].setEditable(false);
		table.tfCell[noOfColumns*2+3].setEditable(false);
	//	table.tfCell[noOfColumns+3].setText("-");
	//	table.tfCell[noOfColumns*2+3].setText("-");
		
	}
	
	
	
	public void displayTime(int hr, int min, int sec) 
	{
		
		
		if(sec >= 59)
		{
			sec = 00;
			min++;
			lSeconds.setText("00");
			if(min < 10)
				lMinutes.setText("0"+Integer.toString(min));
			else
				lMinutes.setText(Integer.toString(min));
		}
		else if(min > 59)
		{
			sec = min = 00;
			hr++;
			lSeconds.setText("00");
			lMinutes.setText("00");
			if(hr < 10)
				lHours.setText("0"+Integer.toString(hr));
			else
				lHours.setText(Integer.toString(hr));
		}
		else
		{
			sec++;
			if(sec < 10)
				lSeconds.setText("0"+Integer.toString(sec));
			else
				lSeconds.setText(Integer.toString(sec));
		}
	}
	
	
	public void resetTime() 
	{
		lSeconds.setText("00");
		lMinutes.setText("00");
		if(((String) cbStarttime.getSelectedItem()).length() == 1)
			lHours.setText("0"+(String) cbStarttime.getSelectedItem());
		else
			lHours.setText((String) cbStarttime.getSelectedItem());
	}
	
	
	
	public void updateMRVehiclePosition() 
	{ 
	//	System.out.println(" update mr vehicle called");
	//	if(timeCheck >= 1 || time == 6)
	//		time ++;
		
		if(timeInSec<maxTime){
			for (int i = 0; i < MRVehiclesOnRoadnum; i++){
			 if(xCord[MRVehiclesOnRoad[i]]<maxLength){
				xCord[MRVehiclesOnRoad[i]]+=velocity;
				yCord[MRVehiclesOnRoad[i]]=yTopLane;
				if(checkvehicle==MRVehiclesOnRoad[i]){
					if(xCord[MRVehiclesOnRoad[i]]>=checkposition){ 
						checkvehicle++;
						GapEndTime = System.currentTimeMillis();
						GapValue=(double) ((double)GapEndTime-(double)GapStartTime)/1000;
						if(!crossing[CRVehWaiting] && !crossed[CRVehWaiting]){
							System.out.println(GapValue);
							rejectedGapValue=GapValue;
							AorR = "R";
							RejectedGapsArray[CRVehWaiting]=acceptedGapValue;
						}
						else{System.out.println(GapValue);
						acceptedGapValue=GapValue;
						AorR = "A";
						AcceptedGapsArray[CRVehWaiting]=acceptedGapValue;
							
						}
						GapStartTime = GapEndTime;
					}
				}
			 }
				
			 else {
				 xCord[MRVehiclesOnRoad[i]]=-200;
				 yCord[MRVehiclesOnRoad[i]]=-200;
				 for(int j=0;j<MRVehiclesOnRoadnum-1;j++){					 
					 MRVehiclesOnRoad[j]=MRVehiclesOnRoad[j+1];	
					 
				 }
				 MRVehiclesOnRoadnum--;
				
			 }
			}
			
		}
		else timerForSimulation.stop();
		
		repaint();
		//updateTable();
	/*	if(timeCheck == 1)
			timeCheck = 0;
		else if (timeCheck > 1)
			timeCheck -= 1 ;
		timeCheck += addTimeCheck;*/
	}
	
	public void updateCRVehiclePosition() throws FileNotFoundException, IOException 
	{ 
		//System.out.println(" update cr vehicle called");
		//if(timeCheck >= 1 || time == 6)
		//	time ++;
		
		if(timeInSec<maxTime){
			for (int i = 0; i < CRVehiclesOnRoadnum; i++){
				
				if (CRyCord[CRVehiclesOnRoad[i]] < stoplineposition && CRyCord[CRVehiclesOnRoad[i]] > yTopLane -2*XGAP) {
					if(!crossing[CRVehiclesOnRoad[i]]){
						flaggreen=true;
						flagred=false;
						repaint();
						GapStartTime = System.currentTimeMillis();
						CRVehWaiting = CRVehiclesOnRoad[i];
						crossing[CRVehiclesOnRoad[i]] = CrossCheck();
					}
					
				}
				
				if (crossing[CRVehiclesOnRoad[i]]) {
					angle = CRAngle[CRVehiclesOnRoad[i]];
					
					if(angle>=1.57){
						//angle=0;
						CRyCord[CRVehiclesOnRoad[i]] =yTopLane-2*XGAP;
						crossing[CRVehiclesOnRoad[i]] = false;
						crossed[CRVehiclesOnRoad[i]] = true;
					//	timerForGapCalculation.stop();
						flaggreen=false;
						flagred=true;
						repaint();
						
					//	setTutTableValues();
						
					//	time_to_breakout=true;
					//	System.out.println("car croosing ended bey..");
					}
					else {						
						deltaAngle=0.1*velocity/5;
						CRxCord[CRVehiclesOnRoad[i]] += - radius*cos(angle - deltaAngle) + radius*cos(angle);
						CRyCord[CRVehiclesOnRoad[i]] += radius*sin(angle-deltaAngle) - radius*sin(angle);
				//		BufferedImage old= CRimage[i];
				//	AffineTransform trans = new AffineTransform();
				//		trans.translate((int)CRxCord[CRVehiclesOnRoad[i]],(int)CRyCord[CRVehiclesOnRoad[i]]);
				//	trans.translate((int)- radius*cos(angle - deltaAngle) + radius*cos(angle),(int)radius*sin(angle-deltaAngle) - radius*sin(angle));
				//	trans.rotate(angle);
				//	g2d.setTransform(trans);
				//	g2d.drawImage(CRimage[CRVehiclesOnRoad[i]],trans, this);   
					//	Image temp = CRimage[CRVehiclesOnRoad[i]];
		/*				BufferedImage old = ImageIO.read(new FileInputStream("images/"+i%3+".png"));
						BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
						g2d = (Graphics2D) image.getGraphics();
						g2d.rotate(Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);
						g2d.translate((image.getWidth() - old.getWidth()) / 2, (image.getHeight() - old.getHeight()) / 2);
						g2d.drawImage(old, 0, 0, old.getWidth(), old.getHeight(), null);                */
				//		g2d.drawImage(CRimage[CRVehiclesOnRoad[i]],trans, this);                  
					//	g2d.rotate(angle);			
					//	System.out.println("ding");
				//	g2d.drawImage(CRimage[CRVehiclesOnRoad[i]], (int)CRxCord[CRVehiclesOnRoad[i]],(int)CRyCord[CRVehiclesOnRoad[i]], this);//draw the vehicles at the positions calculated..
					//	g2d.drawRenderedImage(CRimage[i], trans);
					//	System.out.println("ding");
						                                        
						CRAngle[CRVehiclesOnRoad[i]]+=deltaAngle;
						
						//numCRVehWaiting--;
						//angle[CRVehiclesOnRoad[i]]
					}
					
					
				}
			 
				if(CRyCord[CRVehiclesOnRoad[i]]>=stoplineposition){
					if(i!=0){
						if( CRyCord[CRVehiclesOnRoad[i-1]]-(CRyCord[CRVehiclesOnRoad[i]]-velocity)>=IMAGE_WIDTH/2+XGAP){
							CRyCord[CRVehiclesOnRoad[i]]-=velocity;
							CRxCord[CRVehiclesOnRoad[i]]=CRxLane;						
						}					
					}
					else{
						CRyCord[CRVehiclesOnRoad[i]]-=velocity;
						CRxCord[CRVehiclesOnRoad[i]]=CRxLane;
						}
				
					
					CRAngle[CRVehiclesOnRoad[i]] = 0;
			     }
				
				/*
				if(CRyCord[CRVehiclesOnRoad[i]] < stoplineposition && CRyCord[CRVehiclesOnRoad[i]] > yTopLane -2*XGAP){
						System.out.println("car crossing");
						gapnumber++;
						//numCRVehWaiting++;
						if((CRyCord[CRVehiclesOnRoad[i]] < stoplineposition) && (CRyCord[CRVehiclesOnRoad[i]] >= stoplineposition - 5)){
							acceptedGapValue=0;
							GapValue=0;
							rejectedGapValue=0;
							timerForGapCalculation.setDelay(10);
							flaggreen=true;
							flagred=false;
												
						}
						angle=0;
						boolean time_to_breakout = false;
						if(CrossCheck()){
							timerForGapCalculation.start();		
							while(!time_to_breakout){
								
								CRxCord[CRVehiclesOnRoad[i]] -=(radius-radius*cos(angle));
								CRyCord[CRVehiclesOnRoad[i]] -=radius*sin(angle);
						//		BufferedImage old= CRimage[i];
								AffineTransform trans = new AffineTransform();
						//		trans.translate((int)CRxCord[CRVehiclesOnRoad[i]],(int)CRyCord[CRVehiclesOnRoad[i]]);
								trans.translate((int)radius-radius*cos(angle),(int)radius*sin(angle));
								trans.rotate(angle);
							//	Image temp = CRimage[CRVehiclesOnRoad[i]];
								BufferedImage old = ImageIO.read(new FileInputStream("images/"+i%3+".png"));
								BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
								g2d = (Graphics2D) image.getGraphics();
								g2d.rotate(Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);
								g2d.translate((image.getWidth() - old.getWidth()) / 2, (image.getHeight() - old.getHeight()) / 2);
								g2d.drawImage(old, 0, 0, old.getWidth(), old.getHeight(), null);                
								g2d.drawImage(CRimage[CRVehiclesOnRoad[i]],trans, this);                  
							//	g2d.rotate(angle);			
							//	System.out.println("ding");
				//				g2d.drawImage(CRimage[CRVehiclesOnRoad[i]], (int)CRxCord[CRVehiclesOnRoad[i]],(int)CRyCord[CRVehiclesOnRoad[i]], this);//draw the vehicles at the positions calculated..
							//	g2d.drawRenderedImage(CRimage[i], trans);
							//	System.out.println("ding");
								for(int k=0;k<60000;k++){
									for(int p=0;p<60000;p++){
										for(int d=0;d<60000;d++);
									};
								};                                               
								angle+=0.55;
								if(angle>=1.57){
									angle=0;
									CRyCord[CRVehiclesOnRoad[i]] =yTopLane-2*XGAP;
									timerForGapCalculation.stop();
									flaggreen=false;
									flagred=true;
									System.out.println(GapValue);
									acceptedGapValue=GapValue;
									AorR = "A";
									AcceptedGapsArray[i]=acceptedGapValue;
								//	setTutTableValues();
									
									time_to_breakout=true;
								//	System.out.println("car croosing ended bey..");
								}
								//numCRVehWaiting--;
								
							}
						}
						else{ timerForGapCalculation.start();
							while(xCord[checkvehicle]<=detPosition);
							timerForGapCalculation.stop();
							flaggreen=false;
							flagred=true;
							rejectedGapValue = GapValue;
							AorR = "R";
							RejectedGapsArray[i]=rejectedGapValue;
							}
						
					//	CRCarCrossing(CRVehiclesOnRoad[i]);
					}
	*/				
	
					if(CRxCord[CRVehiclesOnRoad[i]]<=-10){
							 
							CRxCord[CRVehiclesOnRoad[i]]=-200;
							 for(int j=0;j<CRVehiclesOnRoadnum-1;j++){
								 CRVehiclesOnRoad[j]=CRVehiclesOnRoad[j+1];								 
							 }
							 CRVehiclesOnRoadnum--;
					}
					
					if(CRyCord[CRVehiclesOnRoad[i]]==yTopLane-2*XGAP){
						// System.out.println("i m on mainroad now");
						CRxCord[CRVehiclesOnRoad[i]]-=velocity;
						// CRxCord[CRVehiclesOnRoad[i]]-=0;	
						CRAngle[CRVehiclesOnRoad[i]]=1.57;
					}					
				
			}
		}
		else timerForSimulation.stop();
		repaint();
		//updateTable();
		if(timeCheck == 1)
			timeCheck = 0;
		else if (timeCheck > 1)
			timeCheck -= 1 ;
		timeCheck += addTimeCheck;
	}
	
/*	public void CRCarCrossing(int carnumber){
		System.out.println("car crossing called");
		//gaptimer.start();
		while(angle<=1.57){
			CRxCord[carnumber] -=radius-radius*sin(angle);
			CRyCord[carnumber] -=radius*cos(angle);
			AffineTransform trans = new AffineTransform();
			trans.translate((int)CRxCord[carnumber]+XGAP,(int)CRyCord[carnumber]);
		//	trans.translate((int)radius-radius*cos(angle),(int)radius*sin(angle));
			trans.rotate(6.28-angle);
			g2d.drawImage(CRimage[carnumber], (int)CRxCord[carnumber]+XGAP,(int)CRyCord[carnumber], this);//draw the vehicles at the positions calculated..
			angle--;}
		//gaptimer.stop();
		//float totaltime=gaptimer.getSeconds()+gaptimer.getMinutes()*60+gaptimer.getHours()*3600;
	//	float timeinsec = totaltime/1000;
		System.out.println("car crossing ends");
	} */

	public boolean CrossCheck(){
	boolean	flag=false;
	/* for(int i=0;i<MRVehiclesOnRoadnum;i++){
		if(xCord[MRVehiclesOnRoad[i]]<detPosition){
			double timeAvailable= (detPosition-xCord[MRVehiclesOnRoad[i]])/velocity;
			if(timeAvailable>=5){
				flag=true;
				
			}
		}
	}*/
	if((reflinepositionx-xCord[checkvehicle])>=150){
		flag=true;
	//	GapValue=0;
	//	timerForGapCalculation.start();
		
	}
		 return flag;
	//return true;
	}
	
	public void gapcheckfunction(int n){
		if(!(xCord[n]<checkposition)){
		//	timerForGapCalculation.stop();
		}
	}
	
	 public void actionPerformed(ActionEvent e) 
	    {
			if(e.getSource()== bStart)
	    	{   	System.out.println("start button clicked");
				    maxTime = Integer.parseInt((String) cbDuration.getSelectedItem()) * 60;
					// timer.setInitialDelay(100);
					timerForMRVehicle.start();
					timerForCRVehicle.start();
					timerForClock.start();
					timerForCRCarGeneration.start();
					timerForMRCarGeneration.start();
					timerForSimulation.start();
					
					System.out.println("timers started");
					//displayTime();
					
					bStart.setEnabled(false);
					cbStarttime.setEnabled(false);
					cbDuration.setEnabled(false);
				
	    	}	
				
				
	    	
			if(e.getSource()== bPause)
			{
				//System.out.println("pause");
				try
				{
					timerForMRVehicle.stop();
					timerForCRVehicle.stop();
					timerForClock.stop();
					timerForSimulation.stop();
					if(pTimer.isRunning())
					pTimer.stop();
					if(pTimer1.isRunning())
						pTimer1.stop();
					repaint();
				}
				catch(Exception exception)
				{
					JOptionPane.showMessageDialog(null, "Simulation not yet started.!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				bStart.setEnabled(true);
			}
			if(e.getSource()== bStop)
			{
				try
				{
					timerForMRCarGeneration.stop();
					timerForCRCarGeneration.stop();
					timerForCRVehicle.stop();
					timerForMRVehicle.stop();
					timerForSimulation.stop();
					timerForClock.stop();
					time = 6;
					for (int i = 0; i < maxVeh; i++){
						xCord[i] = -200;
						yCord[i] = -200;
					}	
					for (int i = 0; i < CRmaxVeh; i++){
						CRxCord[i] = -200.0;
						CRyCord[i] = -200.0;
					}	
					repaint();
				}
				catch(Exception exception)
				{
					JOptionPane.showMessageDialog(null, "Simulation not yet started.!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				bStart.setEnabled(true);
				bPause.setEnabled(true);
				cbStarttime.setEnabled(true);
				cbDuration.setEnabled(true);
				tfStudentName.setEditable(true);
				tfStudentName.setText("");
				
				resetTime();
				//resetTable();
				//if(flag==0)
					resetTable();
					//if(flag==1)
					//resetExpTable();
				bNext.setEnabled(false);
				bBack.setEnabled(false);
				//reset to first pane in cardLayout
				CardLayout cl1 = (CardLayout)(calcPane.getLayout());
		        cl1.first(calcPane);
		        displayScreen = 0;
				//calcPane.remove(table);
				//makeTable();
				
				
		        //reset timer to normal speed.
		        delayInMillisec = 1000;
		        timeCheck = (double)1/RESOLUTION;
		        addTimeCheck = (float)1/RESOLUTION;
		        //timer.setDelay(delayInMillisec);
		        pTimer.setDelay(delayInMillisec);
		        pTimer.reset();
		        pTimer1.setDelay(delayInMillisec);
		        pTimer1.reset();
		        lTimer.setText("");
		        slSimSpeed.setValue(10);
		        //lastHighlightedVehicle = 0;
		        fullVector.removeAllElements();
		        highlight = false;
			
			
			}
			if(e.getSource()== cbStarttime)
			{
				//System.out.println("...HELLOOOOOOOOO....");
				if(((String) cbStarttime.getSelectedItem()).length() == 1)
					lHours.setText("0"+(String) cbStarttime.getSelectedItem());
				else
					lHours.setText((String) cbStarttime.getSelectedItem());
				
				repaint();
			}
			if(e.getSource()== cbDuration)
			{				
				repaint();
			}
			if(e.getSource()== cbCountinterval)
			{
				
				repaint();
			}
			
			
			if(e.getSource()== bNext)
			{   
				
				
					
					  studentName = (String)(tfStudentName.getText()).trim();
						
						if(studentName.isEmpty() == false )
			        	{
			        		 int l = studentName.length();
			        		 for ( int i =0 ; i< l;i++)
			        		 { 
			        		if ( ( ((int)studentName.charAt(i) > 64) && ((int)studentName.charAt(i) < 91))	||  (((int)studentName.charAt(i) > 96) && ((int)studentName.charAt(i) < 123)) || (int)studentName.charAt(i) == 32 )
			        		continue;
			        		else
			        		
			        		{
			        			JOptionPane.showMessageDialog(null, "Enter valid Name ", "ERROR", JOptionPane.ERROR_MESSAGE);
				        		return;
			        		}
			       		  }
			        	}
			        	else 
			        	{
			        		JOptionPane.showMessageDialog(null, "Enter your Name ", "ERROR", JOptionPane.ERROR_MESSAGE);
			        		return;
			        	}
						
						 
						try
			        	{
							getTableValues(); 
			        	}
			        	catch(Exception ae)
			        	{
			        		System.out.println("NFE "+ae.getMessage());
			        		JOptionPane.showMessageDialog(null, "Please fill numeric values in all cells...", "ERROR", JOptionPane.ERROR_MESSAGE);
			        		return;
			        	}
			        	
			        	
			        	
					addPane1();
					
			      
				
				
				tfStudentName.setEditable(false);
				bNext.setEnabled(false);
				
				subTitle.setText(" Actual Values are in Braces");
				CardLayout cl1 = (CardLayout)(calcPane.getLayout());
		        cl1.next(calcPane);
		        displayScreen += 1;
		        bBack.setEnabled(true);
		       
		        
		         
			}
			if(e.getSource()== bBack)
			{
				CardLayout cl1 = (CardLayout)(calcPane.getLayout());
		        cl1.previous(calcPane);
		        displayScreen -= 1;
		        bNext.setEnabled(true);
		        if(displayScreen == 0)
		        {
		        bBack.setEnabled(false);
		        }
		        
		        
		        subTitle.setText(" Enter values");
			}
			
			if(e.getSource() == ReportBtn)
			{
				JFileChooser chooser = new JFileChooser();
		        chooser.setCurrentDirectory( new File( "./") );
		        	chooser.setFileFilter(new pdfFilefilter());
		        	int actionDialog = chooser.showSaveDialog(null);
		        	if(actionDialog == JFileChooser.APPROVE_OPTION )
		        	{  
			
		        		 Report = new File( chooser.getSelectedFile( ) + ".pdf" );
		        		 if(Report == null)
		        		 return;
		        		if(Report.exists())
		        		{
		        		 actionDialog = JOptionPane.showConfirmDialog(null,"Replace existing file?");
		        		 // may need to check for cancel option as well
		        		 if (actionDialog == JOptionPane.NO_OPTION)
		        		 return;
		        			  }
		        		}
				
				try {
					createPDFFileOfExpReport();
				} catch (DocumentException ae) {
					// TODO Auto-generated catch block
					ae.printStackTrace();
				} catch (IOException ue) {
					// TODO Auto-generated catch block
					ue.printStackTrace();
				}
			}
			
			if(e.getSource() == ActualReportBtn)
			{
				JFileChooser chooser = new JFileChooser();
		        chooser.setCurrentDirectory( new File( "./") );
		        	chooser.setFileFilter(new pdfFilefilter());
		        	int actionDialog = chooser.showSaveDialog(null);
		        	if(actionDialog == JFileChooser.APPROVE_OPTION )
		        	{  
			
		        		 ActualReport = new File( chooser.getSelectedFile( ) + ".pdf" );
		        		 if(ActualReport == null)
		        		 return;
		        		if(ActualReport.exists())
		        		{
		        		 actionDialog = JOptionPane.showConfirmDialog(null,"Replace existing file?");
		        		 // may need to check for cancel option as well
		        		 if (actionDialog == JOptionPane.NO_OPTION)
		        		 return;
		        			  }
		        		}
				
				try {
					createPDFFileOfActualReport();
				} catch (DocumentException ae) {
					// TODO Auto-generated catch block
					ae.printStackTrace();
				} catch (IOException ue) {
					// TODO Auto-generated catch block
					ue.printStackTrace();
				}
			}
			
			
	    }
	 
	 public void getTableValues()
	 { 
		 
		 cycle1 = new double[5];
		 cycle2 = new double[5];
		 cycle3 = new double[5];
		 average = new double[5];
		 
		 
		 
		 
		 for(int i = noOfColumns+1,j=0; i < (noOfRows)*noOfColumns ; i += noOfColumns,j++)
	 
		{
			cycle1[j] = Double.parseDouble((table.tfCell[i].getText()).trim());
			
		}
		 
		 for(int i = noOfColumns+2,j=0; i < (noOfRows-1)*noOfColumns  ; i += noOfColumns,j++)
			 
			{
				cycle2[j] = Double.parseDouble(table.tfCell[i].getText().trim());
				
			}
		 
		 for(int i = noOfColumns+3,j=0; i < (noOfRows-1)*noOfColumns  ; i += noOfColumns,j++)
			 
			{
				cycle3[j] = Double.parseDouble(table.tfCell[i].getText().trim());
				
			}
		 
	/*	 for(int i = noOfColumns*3+4,j=0; i < (noOfRows-1)*noOfColumns   ; i += noOfColumns,j++)
			 
			{
				average[j] = Double.parseDouble(table.tfCell[i].getText().trim());
				
			} */
		 
			 
		 
	 }
	 
	 public void resetTable()
	 { 
		 
		 if(flag==1)
			 subTitle.setText("Enter Table Values");
		 for(int i =noOfColumns ; i<noOfRows*noOfColumns ; i++)
		 {
			table.tfCell[i].setText(""); 
			table.tfCell[i].setBackground(Color.white);
			
		 }
		
		 setTutTableValues();
	 }
	 
	 
	 public void resetExpTable()
	 {    
		 
		 
		 
		 
		 if(displayScreen == 0)
		 {
			resetTable(); 
		 }
		 
		 if(displayScreen == 1)
		 {
		 for(int i =noOfColumns ; i<noOfRows*noOfColumns ; i++)
		 {
			table2.tfCell[i].setText(""); 
			table2.tfCell[i].setBackground(Color.white);
			
		 } 
		 setExpTableValues(table2);
		 }
		 
	 }
	 
	 
	 

  public void addPane1()
  {
	  
	  try
		{
			if(calcPane.getComponent(1) != null )
			{
				calcPane.remove(table2);
			}
			addTable();
		}
	  catch(Exception e)
	  {
		  addTable();
		  return;
	  }
	  
  }
  
  
  public void addTable()
  {
	  
	  table2 = new TablePane(new GridLayout(),noOfRows,noOfColumns);
	  setExpTableValues(table2);
	  setExpTableContents();
	  
	  calcPane.add(table2,"second table");
	  
  }

          public void setExpTableContents()
  
          {
	         l= noOfColumns;
             
             table2.tfCell[l+1].setText(table.tfCell[l+1].getText() +"("+ df.format(exp_cycle1_t3)+")");
             table2.tfCell[l+1].setToolTipText(df.format(exp_cycle1_t3));
             table2.tfCell[l+2].setText(table.tfCell[l+2].getText() +"("+ df.format(exp_cycle2_t3)+")");
             table2.tfCell[l+2].setToolTipText(df.format(exp_cycle2_t3));
             table2.tfCell[l+3].setText(table.tfCell[l+3].getText() +"("+ df.format(exp_cycle3_t3)+")");
             table2.tfCell[l+3].setToolTipText(df.format(exp_cycle3_t3));
             
             
             
             table2.tfCell[l+6].setText(table.tfCell[l+6].getText() +"("+ df.format(exp_cycle1_t13)+")");
             table2.tfCell[l+6].setToolTipText(df.format(exp_cycle1_t13));
             table2.tfCell[l+7].setText(table.tfCell[l+7].getText() +"("+ df.format(exp_cycle2_t13)+")");
             table2.tfCell[l+7].setToolTipText(df.format(exp_cycle2_t13));
             table2.tfCell[l+8].setText(table.tfCell[l+8].getText() +"("+ df.format(exp_cycle3_t13)+")");
             table2.tfCell[l+8].setToolTipText(df.format(exp_cycle3_t13));
             
             
             
             table2.tfCell[l+11].setText(table.tfCell[l+11].getText() +"("+ df.format(exp_cycle1_h)+")");
             table2.tfCell[l+11].setToolTipText(df.format(exp_cycle1_h));
             table2.tfCell[l+12].setText(table.tfCell[l+12].getText() + "("+df.format(exp_cycle2_h)+")");
             table2.tfCell[l+12].setToolTipText(df.format(exp_cycle2_h));
             table2.tfCell[l+13].setText(table.tfCell[l+13].getText() +"("+ df.format(exp_cycle3_h)+")");
             table2.tfCell[l+13].setToolTipText(df.format(exp_cycle3_h));
             table2.tfCell[l+14].setText(table.tfCell[l+14].getText() +"("+ df.format(exp_avg_h)+")");
             table2.tfCell[l+14].setToolTipText(df.format(exp_avg_h));
             
             
             
             table2.tfCell[l+16].setText(table.tfCell[l+16].getText() +"("+ df.format(exp_cycle1_s)+")");
             table2.tfCell[l+16].setToolTipText(df.format(exp_cycle1_s));
             table2.tfCell[l+17].setText(table.tfCell[l+17].getText() +"("+ df.format(exp_cycle2_s)+")");
             table2.tfCell[l+17].setToolTipText(df.format(exp_cycle2_s));
             table2.tfCell[l+18].setText(table.tfCell[l+18].getText() +"("+ df.format(exp_cycle3_s)+")");
             table2.tfCell[l+18].setToolTipText(df.format(exp_cycle3_s));
             table2.tfCell[l+19].setText(table.tfCell[l+19].getText() +"("+ df.format(exp_avg_s)+")");
             table2.tfCell[l+19].setToolTipText(df.format(exp_avg_s));
             
             
             
             table2.tfCell[l+21].setText(table.tfCell[l+21].getText() +"("+ df.format(exp_cycle1_lt)+")");
             table2.tfCell[l+21].setToolTipText(df.format(exp_cycle1_lt));
             table2.tfCell[l+22].setText(table.tfCell[l+22].getText() +"("+ df.format(exp_cycle2_lt)+")");
             table2.tfCell[l+22].setToolTipText(df.format(exp_cycle2_lt));
             table2.tfCell[l+23].setText(table.tfCell[l+23].getText() +"("+ df.format(exp_cycle3_lt)+")");
             table2.tfCell[l+23].setToolTipText(df.format(exp_cycle3_lt));
             table2.tfCell[l+24].setText(table.tfCell[l+24].getText() +"("+ df.format(exp_avg_lt)+")");
             table2.tfCell[l+24].setToolTipText(df.format(exp_avg_lt)); 
              
	    }


	@Override
	public void stateChanged(ChangeEvent e) 
    {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int slSimSpeed = (int)source.getValue();
            if (slSimSpeed == 0) 
            {
          /*      delayInMillisec = 4000;
                pTimer.setDelay(delayInMillisec);
                pTimer1.setDelay(delayInMillisec);
                timerForClock.setDelay(delayInMillisec );
            	addTimeCheck = (float) (0.25 * ((float)1/RESOLUTION));
            	//System.out.println(addTimeCheck);   */
            	velocity = 2;
            } 
            else if (slSimSpeed == 5)
            {
         /*   	delayInMillisec = 2000;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = (float) (0.50 * ((float)1/RESOLUTION));
            	//System.out.println(addTimeCheck);    */
            	velocity = 3;
            }
            else if (slSimSpeed == 10)
            {
        /*    	delayInMillisec = 1000;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = 1 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);  */
            	velocity = 5;
            }
            else if (slSimSpeed == 15)
            {
          /*  	delayInMillisec = 500;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = 2 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck); */
            	velocity = 10;
            }
            else if (slSimSpeed == 20)
            {
        /*    	delayInMillisec = 240;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = 4 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);    */
            	velocity = 20;
            }
            else if (slSimSpeed == 25)
            {
       /*     	delayInMillisec = 100;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	timeCheck = 1;
            	addTimeCheck = 1; */
            	velocity = 30;
            }
        }
    }
	 
	private void createPDFFileOfExpReport() throws DocumentException, IOException
	{
		
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try
		{
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(Report));
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			
		}
        document.open();
        document.add(new Paragraph("         Gap Study Experiment",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 0, 0)))); 
          
        document.add(new Paragraph(" Report of "+tfStudentName.getText()+" ",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 0, 0)))); 
        document.add( new Paragraph(" "));
        
        document.add(new Paragraph("Submitted Values",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD))); 
        document.add( new Paragraph(" "));
        
        PdfPTable t1 = new PdfPTable(noOfColumns);
        t1.setWidthPercentage(100);
        
        
        for(int j = 0; j < noOfColumns*(noOfRows);j++)
		{
			t1.addCell(table.tfCell[j].getText());
		}
		
         document.add(t1); 
         
         
		 document.close();
        
       }
	
	
	private void createPDFFileOfActualReport() throws DocumentException, IOException
	{
		
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try
		{
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(ActualReport));
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
        document.open();
        document.add(new Paragraph("               Gap Study Experiment",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 0, 0)))); 
          
        document.add(new Paragraph(" Report of "+tfStudentName.getText()+" ",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 0, 0)))); 
        document.add( new Paragraph(" "));
        
        document.add(new Paragraph(" Actual Values in braces.",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD))); 
        document.add( new Paragraph(" "));
        
        PdfPTable t1 = new PdfPTable(noOfColumns );
        t1.setWidthPercentage(100);
        
        for(int j = 0; j < noOfColumns*(noOfRows);j++)
		{   
        	if(table.tfCell[j].getToolTipText()!= null)
			t1.addCell(table.tfCell[j].getText() + "(" +table2.tfCell[j].getToolTipText()+")" );
        	else 
        	t1.addCell(table.tfCell[j].getText());
			
		}  
         document.add(t1); 
         document.close();
        
       }
	
	}


