package main;

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
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
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

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;*/


public  class GapStudyApplet extends JApplet implements ActionListener,ChangeListener 
{
	
	private URL url;
	private URLConnection urlConn;
	private MotionAnimation animation;
	static JComboBox cbStarttime;
	static JComboBox cbDuration;
	static JComboBox cbCountinterval;
	private String[] vtype;
	
	static double[] xCord;
	static int[] yCord;
	static Image[] image;
	static int maxVeh;
	static int maxLength;
	int cycleTime;
	int redTime;
	int greenTime;
	static DefaultButton bNext,ReportBtn,ActualReportBtn;
	static String[][] map;
	JPanel content;
	static Vector<Vector<Integer>> vRow;
	private int rows;
	private int columns;
	static Vector<Vector<Integer>>vSubtRow;
	private Vector<Integer> vRowData;
	
	
	
	
	
	private static final int IMAGE_WIDTH = 40;
	private static final int IMAGE_HEIGHT = 20; 
	private static final int GAP = 15;
	private static final String NO_OF_LANES = "3";
	private int delayInMillisec = 1000;
	int xSize,ySize;   //frame dimension
	Dimension size;    //for window screen component
	Timer timer;
	int imgWidth,imgHeight;   //image dimension
	int maxTime;//43,
	int time = 6,time2;
	int detPosition,detPosition2,detPosition1 ; //position of detector
	int[] vehCounter;
	int carcount = 14;//TODO: now counter for cars..no. shows the index of tfCell to update.
	int bikecount = 12;
	int totalVolume;
	int maxRowtotal;
	int displayScreen; 
	Font font1,font2;
	ArrayList excludecount;
	DefaultButton bStart,bStop,bPause,bBack,bTimer; 
	CarMotion carmotion; 
	JPanel navigationPane,clockPane,calcPane;
	JSlider slSimSpeed;
	JLabel lStarttime,lDuration,lCountinterval;//label for combobox
	JLabel lColon1,lColon2,lHours,lMinutes,lSeconds,lTimer;//digital clock..!!
	JTextField tfStudentName;
	JLabel  studentNamelabel ;
	JPanel NamePanel,bottom;
	Clock pTimer,pTimer1;
	Timer timerForVehicle; //timer to fire event after particular delay for vehicle position updation
	Timer timerForClock; 
	Timer timerForDisplayTime,timerForDisplayTime1;//when stopwatch is stopped the time is displayed for a few seconds using this timer 
	protected double timeCheck;
	private float addTimeCheck;
	private double timeOfTimer,timeOfTimer1;
	int yTopLane;
	protected static final int  RESOLUTION = 10;
	private static final int XGAP = 30;
	private Random randomNumber;
	private int random;
	private boolean highlight;
	private Vector<String> fullVector;
	static final int AREALENGTH = 100;
	 int count;
	 TablePaneGS table,table1,table2;
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
	 private boolean started3,started13;
	 private long endTime;
	 JScrollPane scrollTab;
	 JPanel bottomPane;
	 String studentName;
	 private double[] cycle1,cycle2,cycle3,average;
	 File Report,ActualReport;
	 
	 
	//method to read data into map object
    
	public void init() 
	
	{  
		flag = 0;
		started3 = false;
		started13 = false;
		//System.out.println(" Hello");
		xSize = 1320 ;
		ySize = 625;
		detPosition = 4*(xSize/7)+60+130;
		
		
		detPosition1 = 4*(xSize/7)+70;
		detPosition2 = 4*(xSize/7)+75;
		
		
		df = new DecimalFormat("#0.00");
		
		noOfRows = 6;
		noOfColumns= 5;
	
		
		totalVolume = 0;
		maxRowtotal = 0;
		displayScreen = 0;
		vehCounter = new int[140];
		timeOfTimer = 0.0;
		timeOfTimer1 = 0.0;
		yTopLane = ySize/7+73;
		
		timeCheck = (double)delayInMillisec/(1000 * RESOLUTION);
		addTimeCheck = (float) timeCheck;
		randomNumber = new Random();
		highlight = false;
		fullVector = new Vector<String>();
		vRow = new Vector<Vector<Integer>>();
		
		font1 = new Font("Thoma",Font.BOLD, 19);
		font2 = new Font("Thoma",Font.BOLD, 15);
		excludecount = new ArrayList();
		
		for(int i = 0 ; i < 140; i++)
		{
			vehCounter[i] = 0;
		}
		excludecount = new ArrayList();
		try 
		{
			//As it is an applet, to read a file from server the following method is used.
			//It is not possible to directly read file by an applet.
			url = new URL(getCodeBase().toString() + "/dump_900_0_graph_1.csv");
	        urlConn = url.openConnection();
	        urlConn.setDoInput(true);
	        urlConn.setUseCaches(false);
	        
	        
	        readData(urlConn);
	        
	        /*detPosition1=(int) (((float)(maxLength - AREALENGTH)/2) * (xSize/maxLength));
			detPosition2=(int) (xSize - (((float)(maxLength - AREALENGTH)/2) * (xSize/maxLength)));
			*/
	        vtype = new String[maxVeh];
			xCord = new double[maxVeh];
			yCord = new int[maxVeh];
			
			for(int i = 0; i < maxVeh; i++)
			{
				//vtype[i] = map[6][i+1].substring(map[6][i+1].lastIndexOf("_")+ 1);//vehicle type data on the fourth row..starts from 2nd column hence i+1.....
				vtype[i] = "Car";
				xCord[i] = 0.0;//Initialize x-coordinate to zero.
				
			}
			image = new Image[maxVeh];
			//load images
			for(int i = 0; i < maxVeh; i++)
			{
				if(vtype[i].equals("Car"))
				{
					//System.out.println(".....YES!!");
					image[i] = getImage(getCodeBase(),"images/carTopView"+i%8+".png");
					Image temp = image[i];
	        		image[i] = temp.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_FAST);
	        		if (image[i] == null)
	        			System.out.println("Image not loaded");
				}
				if(vtype[i].equals("Bike"))
				{
					//System.out.println(".....YES..bike!!");
					image[i] = getImage(getCodeBase(),"images/bikeTopView"+i%2+".jpg");
					Image temp = image[i];
	        		image[i] = temp.getScaledInstance(IMAGE_WIDTH - 20, IMAGE_HEIGHT - 10, Image.SCALE_FAST);
	        		if (image[i] == null)
	        			System.out.println("Image not loaded");
				}
			}
			
			
			carmotion = new CarMotion(image);
		    carmotion.setBackground(Color.gray);
			
			int tablemin = 0;
			
			
			
			ActionListener taskPerformerVehicle = new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent evt)
	        	{
	        		if(timerForVehicle.isRunning() )
	        		{  
	        			//System.out.println(" Timer for vehicle is running");
	        			
	        			//System.out.println(" time :" + time);
	        			//System.out.println(" MAx time :" + maxTime);
	        			
	        			if(time < maxTime)
	        			{
	        				updateVehiclePosition();
	        				//updateTable();
	        			}
	        			else
	        			{
	        				timerForVehicle.stop();
	        				timerForClock.stop();
	        				time = 3;
	        				//calculateAndSetSpeed();
	        				for (int i = 0; i < xCord.length; i++)
	        					xCord[i] = 0;
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
	        		{
	        			int sec = Integer.parseInt(lSeconds.getText());
	        			int min = Integer.parseInt(lMinutes.getText());
	        			int hr = Integer.parseInt(lHours.getText());
	        			
	        			//for switching between red and green signals...
	        			timeInSec = min*60 + sec;
	        			
	        			updateForSignalSwitch(timeInSec);
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
			
			
			timerForVehicle = new Timer(100 , taskPerformerVehicle);//fires event after 100 millisec. for constant refresh rate for updation of vehicle position
	        timerForClock = new Timer(delayInMillisec,taskPerformerClock);
	        timerForDisplayTime = new Timer(4000,taskPerformerTime);
	        timerForDisplayTime1 = new Timer(4000,taskPerformerTime1);
	        
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
    		JLabel title = new JLabel ("<html><font color = blue> Volume Study </font></html> ",JLabel.CENTER);
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
    		Graphics2D g2d = (Graphics2D) g;
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
    	    Rectangle2D rect2 = new Rectangle2D.Float(xSize-350, ySize/7+40, 2*(IMAGE_HEIGHT +GAP), 2*(IMAGE_HEIGHT +GAP+20));
    	    Rectangle2D rect3 = new Rectangle2D.Float(xSize-350, ySize/7+70, 2*(IMAGE_HEIGHT +GAP), 2*(IMAGE_HEIGHT +GAP)- 15);
    	    
    	    g2d.setPaint(gp1);
    	    g2d.fill(rect);
    	    
    		Stroke drawingStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    		Stroke stroke = g2d.getStroke();
    		g2d.drawLine(0, ySize/7+60, xSize - 1, ySize/7+60);// change something for perfection
    		//g2d.drawLine(0, ySize/7+20 + 2*(IMAGE_HEIGHT +GAP) , xSize - 1, ySize/7+20 + 2*(IMAGE_HEIGHT +GAP) );
    		g2d.setColor(Color.white);
    		g2d.drawLine(detPosition, ySize/7+60 , detPosition, ySize/7+60 + 2*(IMAGE_HEIGHT +GAP) );//detector position
    		//g2d.setStroke(drawingStroke);
    		g2d.drawLine(0, ySize/7+60 + IMAGE_HEIGHT +GAP, xSize - 1, ySize/7+60 + IMAGE_HEIGHT + GAP);//lane marking
    		//g2d.drawLine(0, ySize/7+20 + 2*(IMAGE_HEIGHT +GAP), xSize - 1, ySize/7+20 + 2*(IMAGE_HEIGHT + GAP));//lane marking
    		
    		
    		
    		g2d.setStroke(stroke);
    		g2d.setPaint(gp1);
    		g2d.fill(rect2);
    		g2d.setColor(Color.white);
    		//g2d.setStroke(drawingStroke);
    		//g2d.drawLine(xSize-200+ 2*(IMAGE_HEIGHT + GAP), ySize/7, xSize-200+ 2*(IMAGE_HEIGHT + GAP), ySize/7+20 + 3*(IMAGE_HEIGHT + GAP+14));//lane marking
    		g2d.drawLine(xSize-350+ (IMAGE_HEIGHT + GAP), ySize/7+40, xSize-350+ (IMAGE_HEIGHT + GAP), ySize/7+60 + 3*(IMAGE_HEIGHT + GAP+14));//lane marking
    		g2d.setStroke(stroke);
    		
    		g2d.setPaint(gp1);
    		g2d.fill(rect3);
    		g2d.setStroke(stroke);
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
        		if((int)xCord[i] > 0)
        		{   
        			yCord[i] = yTopLane ;
        			g2d.drawImage(image[i], (int)xCord[i]+XGAP, yCord[i], this);//draw the vehicles at the positions calculated..
        		}
        	}
        	
        	// For Red :Light
        	Ellipse2D.Double circle =new Ellipse2D.Double(detPosition, ySize/7+40, 10,10);
        	g2d.setPaint(Color.gray);
    		g2d.setColor(Color.gray);
    		g2d.fill(circle);
    		if(dispTime <= redTime)
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
    		if(redTime < dispTime
    				&& dispTime < cycleTime)
    		{   
    		
    		}
    	}
    }
	
	
	
	public void makeTable()
	{   
		
		table = new TablePaneGS(new GridLayout(),noOfRows,noOfColumns);
        if(flag==0)
        setTutTableValues();
        
        else if (flag==1)
        setExpTableValues(table);
        
        //JPanel tabPane = new JPanel(new BorderLayout());
       // tabPane.setOpaque(false);
        //tabPane.add(table,BorderLayout.CENTER);
        calcPane.add(table,"First Table");
        scrollTab=new JScrollPane(calcPane);
        bottomPane.add(scrollTab,BorderLayout.CENTER);
        //repaint();
		
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
		maxVeh = Integer.parseInt(map[1][1]);
		maxLength = (int) Double.parseDouble(map[2][1]);
		cycleTime = (int) Double.parseDouble(map[3][1]);
		redTime = (int) Double.parseDouble(map[4][1]);
		greenTime = (int) Double.parseDouble(map[5][1]);
		//System.out.println(maxTime+" "+maxVeh+" "+maxLength+" "+cycleTime+" "+redTime);
		
		
	}
	
	
	public void setTutTableValues()
	{
		table.lCell[0].setText("Cycle No.");
		table.lCell[1].setText("1");
		table.lCell[2].setText("2");
		table.lCell[3].setText("3");
		table.lCell[4].setText("Average");
		
		
		table.tfCell[noOfColumns+0].setText("T3");
		table.tfCell[noOfColumns+5].setText("T13");
		table.tfCell[noOfColumns+10].setText("Saturation HeadWay (Sec/Veh)");
		table.tfCell[noOfColumns+15].setText("Saturation Flow (Veh/Hr)");
		table.tfCell[noOfColumns+20].setText("LossTime (Sec)");
		
		table.tfCell[noOfColumns+4].setText("-");
		table.tfCell[noOfColumns*2+4].setText("-");
		
		
	}
	
	
	public void setExpTableValues(TablePaneGS table)
	{   
		int l = noOfColumns+1;
		
		table.lCell[0].setText("Cycle No.");
		table.lCell[1].setText("1");
		table.lCell[2].setText("2");
		table.lCell[3].setText("3");
		table.lCell[4].setText("Average");
		
		table.tfCell[noOfColumns+0].setText("T3");
		table.tfCell[noOfColumns+5].setText("T13");
		table.tfCell[noOfColumns+10].setText("Saturation HeadWay (Sec/Veh)");
		table.tfCell[noOfColumns+15].setText("Saturation Flow (Veh/Hr)");
		table.tfCell[noOfColumns+20].setText("LossTime (Sec)");
		
		for (int i =noOfColumns;i< noOfColumns*noOfRows;i++)
		{
			table.tfCell[i].setEditable(true);
		}
		
		
		for(int i = noOfColumns,j=0;i < noOfRows*noOfColumns;i += noOfColumns,j++)
		{
			table.tfCell[i].setEditable(false);
		}
		
		table.tfCell[noOfColumns+4].setEditable(false);
		table.tfCell[noOfColumns*2+4].setEditable(false);
		table.tfCell[noOfColumns+4].setText("-");
		table.tfCell[noOfColumns*2+4].setText("-");
		
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
	
	
	
	public void updateVehiclePosition() 
	{ 
		//System.out.println(" update vehicle called");
		if(timeCheck >= 1 || time == 6)
			time ++;

		for (int i = 0; i < maxVeh; i++)
		{
			if(timeCheck < 1 && time != 7)
			{
				if((Double.parseDouble(map[time+1][i+1]) > 0) && (Double.parseDouble(map[time][i+1]) > 0))
					xCord[i] += ((Double.parseDouble(map[time+1][i+1])-Double.parseDouble(map[time][i+1]))/((double)1/addTimeCheck) * (xSize/maxLength));//....interpolate the value
			}
			else
			{
				xCord[i] = Double.parseDouble(map[time][i+1]) * (xSize/maxLength) - IMAGE_WIDTH;
				//System.out.println(" TIME :"+time +" , xCord: "+i+": "+xCord[i]);
			}
			
		}
		
		repaint();
		//updateTable();
		if(timeCheck == 1)
			timeCheck = 0;
		else if (timeCheck > 1)
			timeCheck -= 1 ;
		timeCheck += addTimeCheck;
	}

	
	
	
	 public void actionPerformed(ActionEvent e) 
	    {
			if(e.getSource()== bStart)
	    	{   
				    maxTime = Integer.parseInt((String) cbDuration.getSelectedItem()) * 60;
					//timer.setInitialDelay(100);
					timerForVehicle.start();
					timerForClock.start();
					
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
					timerForVehicle.stop();
					timerForClock.stop();
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
					timerForVehicle.stop();
					timerForClock.stop();
					time = 6;
					for (int i = 0; i < xCord.length; i++)
						xCord[i] = 0;
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
			
		/*	if(e.getSource() == ReportBtn)
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
			}*/
			
			
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
		 
		 for(int i = noOfColumns*3+4,j=0; i < (noOfRows-1)*noOfColumns   ; i += noOfColumns,j++)
			 
			{
				average[j] = Double.parseDouble(table.tfCell[i].getText().trim());
				
			}
		 
			 
		 
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
	  
	  table2 = new TablePaneGS(new GridLayout(),noOfRows,noOfColumns);
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
                delayInMillisec = 4000;
                pTimer.setDelay(delayInMillisec);
                pTimer1.setDelay(delayInMillisec);
                timerForClock.setDelay(delayInMillisec );
            	addTimeCheck = (float) (0.25 * ((float)1/RESOLUTION));
            	//System.out.println(addTimeCheck);
            } 
            else if (slSimSpeed == 5)
            {
            	delayInMillisec = 2000;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = (float) (0.50 * ((float)1/RESOLUTION));
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 10)
            {
            	delayInMillisec = 1000;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = 1 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 15)
            {
            	delayInMillisec = 500;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = 2 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 20)
            {
            	delayInMillisec = 240;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	addTimeCheck = 4 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 25)
            {
            	delayInMillisec = 100;
            	timerForClock.setDelay(delayInMillisec);
            	pTimer.setDelay(delayInMillisec);
            	 pTimer1.setDelay(delayInMillisec);
            	timeCheck = 1;
            	addTimeCheck = 1;
            }
        }
    }
	 
/*	private void createPDFFileOfExpReport() throws DocumentException, IOException
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
        document.add(new Paragraph("               Saturation Flow Experiment",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 0, 0)))); 
          
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
        document.add(new Paragraph("               Saturation Flow Experiment",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 0, 0)))); 
          
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
        
       }*/
	
public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GapStudyApplet ex = new GapStudyApplet();
                
                ex.setVisible(true);
            }
        });
    }
}



////////////////////////////////////////////////////////////

// TABLE PANE GS

////////////////////////////////////////////////////////////

class TablePaneGS extends JPanel
{
	  	int i=0;
		JTextField[] tfCell;
		JLabel[] lCell;
		Font font1,font2;
		Border border;
		public int Rows;
		public int Columns;
		
		public TablePaneGS( GridLayout gridLayout,int Rows,int Columns)
		{
			
		border = BorderFactory.createLineBorder(Color.gray);
		setLayout(new GridLayout(Rows,Columns));	
		tfCell = new JTextField[(Rows)*(Columns)];
		lCell = new JLabel[Columns];
		font1 = new Font("Monotype Corsiva",Font.BOLD,17);
		font2 = new Font("Courier",Font.BOLD,14);
		
		
		
		for(int i = 0;i < Columns; i++)
		{
			lCell[i] = new JLabel();
			lCell[i].setOpaque(false);
			lCell[i].setFont(font1);
			lCell[i].setForeground(Color.BLACK);
        	lCell[i].setHorizontalAlignment(JLabel.CENTER);
        	lCell[i].setBorder(border);
        	add(lCell[i]);
		}
		
		
		for(int i =Columns;i<(Rows)*(Columns);i++)
		{
			tfCell[i] = new JTextField(5);
			//tfCell[i].setText("0");
			tfCell[i].setEditable(false);
			tfCell[i].setOpaque(false);
			tfCell[i].setHorizontalAlignment(JTextField.CENTER);
			tfCell[i].setFont( font2 );
			tfCell[i].setBorder(border);
			add(tfCell[i]);
		}
	
		
		}
		
		public void main(String[] args) {
			int NoOfRows=5;
			int NoOfColumns =4;
			TablePaneGS table1 = new TablePaneGS(new GridLayout(), NoOfRows, NoOfColumns);
			
		}
		
		
}


////////////////////////////////////////////////

// MOTION ANIMATION

////////////////////////////////////////////////

class MotionAnimation extends JPanel implements ChangeListener, ActionListener 
{
	public static final String NO_OF_LANES = "3";
	public static final int XGAP = 0;
	public static final int IMAGE_WIDTH = 70;
	public static final int IMAGE_HEIGHT = 40;
	public static final int GAP = 15;
	public static final int AREALENGTH = 100;
	protected static final int  RESOLUTION = 10;
	static final int INITIAL_TIME = 3;//the row in the file read where time starts
	DefaultButton bStart, bPause, bReset;
	private JSlider slSimSpeed;
	private JLabel lStarttime;
	private JLabel lDuration;
	private JLabel lCountinterval;

	JLabel lHours;
	JLabel lMinutes;
	JLabel lSeconds;
	private JLabel lColon1;
	private JLabel lColon2;
	private static int xSize;
	private static int ySize;
	private int detPosition1;
	private int detPosition2;
	private Timer timerForVehicle;
	Timer timerForClock;
	
	int delayInMillisec = 1000;
	
	int time = INITIAL_TIME;
	private int maxTime;
	private double timeCheck;
	private float addTimeCheck;
	private Vector<Integer> vTotal;
	private Vector<Double> vDensity;
	private double avgDensity;
	//constructor 
	public MotionAnimation() throws IOException 
	{
		xSize = 800;
		ySize = 625;
		
		bStart = new DefaultButton("start");
		bPause = new DefaultButton("pause");
		bReset = new DefaultButton("reset");
		
		
		
		slSimSpeed = new JSlider(0,25,12);
		slSimSpeed.setOpaque(false);
		//slSimSpeed.setBackground(Color.lightGray);
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
		label =new JLabel("Super Fast");
		labelTable.put( new Integer( 25 ), label );
		slSimSpeed.setLabelTable( labelTable );
		//slSimSpeed.setFont(new Font("Courier",Font.ITALIC,15));
		slSimSpeed.setPaintLabels(true);
		Font titleFont = new Font("ArialBlack",Font.BOLD,39);
		JLabel titleLabel = new JLabel("<html> <i> <font color = blue> Gap Study </font></i></html>         ",JLabel.CENTER);
		
		JPanel panel=new JPanel(new BorderLayout());
		panel.setOpaque(false);
		
		panel.add(titleLabel,BorderLayout.NORTH);
		
		Font font1 = new Font("Thoma",Font.BOLD, 15);
		titleLabel.setFont(titleFont);
		
		JPanel pane2=new JPanel();
		pane2.setOpaque(false);
		
		
		pane2.add(bStart);
		pane2.add(bPause);
		pane2.add(bReset);
		pane2.add(slSimSpeed);
		
		for(int i =0;i<=25;i+=5)
		{
			label = (JLabel) labelTable.get( new Integer( i ) );
    		Font font = new Font("Arial",Font.ITALIC,10);
    		label.setFont( font ); 
    		label.setSize( label.getPreferredSize() );
		}
		
		
		/*lResolution = new JLabel("Resolution");
		tfResolution = new JTextField("1",5);
		tfResolution.setEditable(false);
		
		add(lResolution);
		add(tfResolution);*/
		String[] strttime = {"8","9","10"};
		String[] duration = {"20","30","60"};
		String[] countinterval = {"5","10","15"};
		lStarttime = new JLabel("Start Time");
		lDuration = new JLabel("Duration(min)");
		lCountinterval = new JLabel("Interval(min)");
		GapStudyApplet.cbStarttime = new JComboBox(strttime);
		GapStudyApplet.cbDuration = new JComboBox(duration);
		GapStudyApplet.cbCountinterval = new JComboBox(countinterval);
		lHours = new JLabel((String) GapStudyApplet.cbStarttime.getSelectedItem());
		if(((String) GapStudyApplet.cbStarttime.getSelectedItem()).length() == 1)
			lHours.setText("0"+(String) GapStudyApplet.cbStarttime.getSelectedItem());
		else
			lHours.setText((String) GapStudyApplet.cbStarttime.getSelectedItem());
		lMinutes = new JLabel("00");
		lSeconds = new JLabel("00");
		lColon1 = new JLabel(":");
		lColon2 = new JLabel(":");
		
		pane2.add(lStarttime);
		pane2.add(GapStudyApplet.cbStarttime);
		pane2.add(lDuration);
		pane2.add(GapStudyApplet.cbDuration);
		pane2.add(lCountinterval);
		pane2.add(GapStudyApplet.cbCountinterval);
		pane2.add(lHours);
		pane2.add(lColon1);
		pane2.add(lMinutes);
		pane2.add(lColon2);
		pane2.add(lSeconds);
		panel.add(pane2,BorderLayout.CENTER);
		add(panel);
		
		timeCheck = (double)delayInMillisec/(1000 * RESOLUTION);
		addTimeCheck = (float) timeCheck;
		
		ActionListener taskPerformerVehicle = new ActionListener()
        {

			public void actionPerformed(ActionEvent evt)
        	{
        		if(timerForVehicle.isRunning() )
        		{
        			if(time < maxTime)
        			{
        				updateVehiclePosition();
        			}
        			else
        			{
        				timerForVehicle.stop();
        				timerForClock.stop();
        				time = INITIAL_TIME;
        				
        				calculateTotal();
        				calculateDensity();
        				calculateAvgDensity();
        				
        				for (int i = 0; i < GapStudyApplet.xCord.length; i++)
        					GapStudyApplet.xCord[i] = 0;
        				GapStudyApplet.bNext.setEnabled(true);
        				
        				bPause.setEnabled(false);

        				resetTime();
        				repaint();
        			}
        		}
        	}

			private void updateVehiclePosition() 
			{
				if(timeCheck >= 1 || time == INITIAL_TIME)
					time ++;

				for (int i = 0; i < GapStudyApplet.maxVeh; i++)
				{
					if(timeCheck < 1 && time != INITIAL_TIME+1)
					{
						if((Double.parseDouble(GapStudyApplet.map[time+1][i+1]) > 0) && (Double.parseDouble(GapStudyApplet.map[time][i+1]) > 0))
							GapStudyApplet.xCord[i] += ((Double.parseDouble(GapStudyApplet.map[time+1][i+1])-Double.parseDouble(GapStudyApplet.map[time][i+1]))/((double)1/addTimeCheck) * (xSize/GapStudyApplet.maxLength));//....interpolate the value
							//GapStudyApplet.xCord[i] += i+4 ;//....interpolate the value
						
					
					
					}
					else
					{
						GapStudyApplet.xCord[i] = Double.parseDouble(GapStudyApplet.map[time][i+1]) * (xSize/GapStudyApplet.maxLength) - IMAGE_WIDTH;
						 // GapStudyApplet.xCord[i] = i;
						

						
						
						//System.out.println(" TIME :"+time +" , xCord: "+i+": "+xCord[i]);
					}
				}
				
				//calculate y-position of the vehicle so that they do not overlap.
				for (int i = 0; i < GapStudyApplet.xCord.length; i++)
				{
					if(time == INITIAL_TIME+1)
						GapStudyApplet.yCord[i] = ySize/4+50 + IMAGE_HEIGHT/4 ;
					
					
					
					/*if(i < GapStudyApplet.maxVeh-1 && (int)GapStudyApplet.xCord[i] - (int)GapStudyApplet.xCord[i+1]<= (IMAGE_WIDTH + GAP) && GapStudyApplet.xCord[i] > 0 && GapStudyApplet.xCord[i+1] > 0 && GapStudyApplet.yCord[i] == GapStudyApplet.yCord[i+1])
					{
						GapStudyApplet.yCord[i+1] = GapStudyApplet.yCord[i+1] + IMAGE_HEIGHT + GAP;
					
					}
					*/
					
					
					
					/*if(i < GapStudyApplet.maxVeh-2 && (int)GapStudyApplet.xCord[i] - (int)GapStudyApplet.xCord[i+2]<= (IMAGE_WIDTH + GAP) && GapStudyApplet.xCord[i] > 0 && GapStudyApplet.xCord[i+2] > 0 && GapStudyApplet.yCord[i] == GapStudyApplet.yCord[i+2])
					{
						GapStudyApplet.yCord[i+2] = GapStudyApplet.yCord[i+2] + IMAGE_HEIGHT + GAP;
					}*/
					
					
					/*
*/
					
					//System.out.println("time :"+time +"timecheck :"+timeCheck +" , xCord:yCord "+i+": "+xCord[i]+":"+ yCord[i]);
				}
				repaint();
				if(timeCheck == 1)
					timeCheck = 0;
				else if (timeCheck > 1)
					timeCheck -= 1 ;
				timeCheck += addTimeCheck;
			}

			
        };
        
       
        
        
        timerForVehicle = new Timer(100 , taskPerformerVehicle);//fires event after 100 millisec. for constant refresh rate for updation of vehicle position
        
        //timerForDisplayTime.setRepeats(false);
        
        
        
        //System.out.println("det pos 1 :"+detPosition1);
        //System.out.println("det pos 2 :"+detPosition2);
		
        

        //listeners...
		bStart.addActionListener(this);
		bPause.addActionListener(this);


		
		slSimSpeed.addChangeListener(this);
	}
	
	
	protected void calculateAvgDensity() 
	{
		double sumDensity = 0;
		for(int i = 0; i < vDensity.size();i++)
		{
			sumDensity += (double)vDensity.get(i);
		}
		avgDensity = sumDensity/vDensity.size();
	}
	
	protected void calculateDensity() 
	{
		// TODO Auto-generated method stub
		vDensity = new Vector<Double>();
		for(int i = 0; i < vTotal.size();i++)
		{
			double density = (double)vTotal.get(i)/((double)AREALENGTH/1000)/Double.parseDouble(NO_OF_LANES);
			vDensity.add(density);
		}
	}

	protected void calculateTotal() 
	{
		
		vTotal = new Vector<Integer>();
		int rowtotal = 0;
		for(int i = 0; i < GapStudyApplet.vRow.size();i++)
		{
			rowtotal = 0;
			Vector<Integer> vData = GapStudyApplet.vRow.get(i);
			for(int j = 0;j < vData.size();j++)
			{
				rowtotal += vData.get(j);
			}
			vTotal.add(rowtotal);
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
	
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int w = getWidth();
	    int h = getHeight();
	    
	    xSize = getWidth();
	    ySize= getHeight();
	 
	    setDetPosition1((int) (((float)(GapStudyApplet.maxLength - AREALENGTH)/2) * (xSize/GapStudyApplet.maxLength)));
        setDetPosition2((int) (xSize - (((float)(GapStudyApplet.maxLength - AREALENGTH)/2) * (xSize/GapStudyApplet.maxLength))));
	    
        //System.out.println(getDetPosition2()-getDetPosition1());
        //System.out.println(AREALENGTH*(xSize/GapStudyApplet.maxLength));
        
	    Color color1 = getBackground();
	    Color color2 = Color.white;
	 
	    GradientPaint gp = new GradientPaint( 0, 0, color1, 100, 100, color2,true );
	 
	    g2d.setPaint( Color.white );
	    g2d.fillRect( 0, 0, w, h );
	 
	    
		
		Rectangle2D rect = new Rectangle2D.Float(XGAP, ySize/4+50, xSize + XGAP, Integer.parseInt(NO_OF_LANES)*(IMAGE_HEIGHT +GAP));
	    GradientPaint gp1 = new GradientPaint(XGAP, ySize/4+50, Color.darkGray,
	        xSize + XGAP, ySize/4+50, Color.darkGray, true);
	    g2d.setPaint(gp1);
	    g2d.fill(rect);
		Stroke drawingStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		Stroke stroke = g2d.getStroke();
		//g2d.drawRect(XGAP, ySize/11, xSize + XGAP, 3*(IMAGE_HEIGHT +GAP));
		//g2d.drawLine(XGAP, ySize/11, xSize + XGAP - 1, ySize/11);
		//g2d.drawLine(XGAP, ySize/11 + 3*(IMAGE_HEIGHT +GAP) , xSize + XGAP - 1, ySize/11 + 3*(IMAGE_HEIGHT +GAP));
		g2d.setColor(Color.white);
		g2d.drawLine(getDetPosition1()+XGAP, ySize/4+50 , getDetPosition1()+XGAP, ySize/4+50 + 3*(IMAGE_HEIGHT +GAP));//detector position
		g2d.drawLine(getDetPosition2()+XGAP, ySize/4 +50, getDetPosition2()+XGAP, ySize/4+50 + 3*(IMAGE_HEIGHT +GAP));//detector position
		//g2d.setColor(Color.white);
		g2d.setStroke(drawingStroke);
		
		g2d.drawLine(XGAP, ySize/4+50 + IMAGE_HEIGHT +GAP, xSize+ XGAP , ySize/4+50 + IMAGE_HEIGHT + GAP);//lane marking
		g2d.drawLine(XGAP, ySize/4+50 + 2*(IMAGE_HEIGHT +GAP), xSize+XGAP , ySize/4+50 + 2*(IMAGE_HEIGHT + GAP));//lane marking
		g2d.setStroke(stroke);
		g2d.setColor(Color.red);
		g2d.drawString(Integer.toString(AREALENGTH)+" metres", (xSize + XGAP)/2, ySize/2+50);
		//g.drawString(Integer.toString(xSize/2),25 ,25 );
		setOpaque( false );
	    super.paintComponent( g );
	    setOpaque( true );
    }
	
	public void paint(Graphics g) 
    {
    	super.paint(g);
    	//imgHeight = image[0].getHeight(this);
    	//System.out.println("inside paint");
    	Graphics2D g2d = (Graphics2D) g;
    	for(int i = 0;i < GapStudyApplet.maxVeh; i++)
    	{
    		if((int)GapStudyApplet.xCord[i] > 0)
    			g2d.drawImage(GapStudyApplet.image[i], (int)GapStudyApplet.xCord[i], GapStudyApplet.yCord[i], this);//draw the vehicles at the positions calculated..
    			
    	}

    	//g2d.drawString(Integer.toString(imgHeight), 0, 10);
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource()== bStart)
    	{
			startSimulation();
    	}
		if(e.getSource()== bPause)
		{
			//System.out.println("pause");
			try
			{
				stopTimers();
			}
			catch(Exception exception)
			{
				JOptionPane.showMessageDialog(null, "Simulation not yet started.!!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			bStart.setEnabled(true);
		}
		
		/*
		
		*/
	}

	/**
	 * 
	 */
	public void startSimulation() 
	{
		maxTime = Integer.parseInt((String) GapStudyApplet.cbDuration.getSelectedItem()) * 60;
		//timer.setInitialDelay(100);
		timerForVehicle.start();
		timerForClock.start();
		
		displayTime();
		
		bStart.setEnabled(false);
		GapStudyApplet.cbStarttime.setEnabled(false);
		GapStudyApplet.cbDuration.setEnabled(false);
		GapStudyApplet.cbCountinterval.setEnabled(false);
	}

	/**
	 * 
	 */
	public void stopTimers() 
	{
		timerForVehicle.stop();
		timerForClock.stop();
		repaint();
	}

	@Override
	public void stateChanged(ChangeEvent e) 
	{
		// TODO Auto-generated method stub
		JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) 
        {
            int slSimSpeed = (int)source.getValue();
            if (slSimSpeed == 0) 
            {
                delayInMillisec = 4000;
                timerForClock.setDelay(delayInMillisec );
            	addTimeCheck = (float) (0.25 * ((float)1/RESOLUTION));
            	//System.out.println(addTimeCheck);
            } 
            else if (slSimSpeed == 5)
            {
            	delayInMillisec = 2000;
            	timerForClock.setDelay(delayInMillisec);
            	addTimeCheck = (float) (0.50 * ((float)1/RESOLUTION));
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 10)
            {
            	delayInMillisec = 1000;
            	timerForClock.setDelay(delayInMillisec);
            	addTimeCheck = 1 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 15)
            {
            	delayInMillisec = 500;
            	timerForClock.setDelay(delayInMillisec);
            	addTimeCheck = 2 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 20)
            {
            	delayInMillisec = 240;
            	timerForClock.setDelay(delayInMillisec);
            	addTimeCheck = 4 * ((float)1/RESOLUTION);
            	//System.out.println(addTimeCheck);
            }
            else if (slSimSpeed == 25)
            {
            	delayInMillisec = 100;
            	timerForClock.setDelay(delayInMillisec);
            	timeCheck = 1;
            	addTimeCheck = 1;
            }
        }
	}
	
	public void displayTime() 
	{
		// TODO Auto-generated method stub
		int sec = Integer.parseInt(lSeconds.getText());
		int min = Integer.parseInt(lMinutes.getText());
		int hr = Integer.parseInt(lHours.getText());
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
		// TODO Auto-generated method stub
		lSeconds.setText("00");
		lMinutes.setText("00");
		if(((String) GapStudyApplet.cbStarttime.getSelectedItem()).length() == 1)
			lHours.setText("0"+(String) GapStudyApplet.cbStarttime.getSelectedItem());
		else
			lHours.setText((String) GapStudyApplet.cbStarttime.getSelectedItem());
	}
	
	public void resetTimerParameters()
	{
		delayInMillisec = 1000;
	    timeCheck = (double)1/RESOLUTION;
	    addTimeCheck = (float)1/RESOLUTION;
	    //timer.setDelay(delayInMillisec);
	    slSimSpeed.setValue(10);
	}

	/**
	 * @param detPosition1 the detPosition1 to set
	 */
	public void setDetPosition1(int detPosition1) 
	{
		this.detPosition1 = detPosition1;
	}

	/**
	 * @return the detPosition1
	 */
	public int getDetPosition1() 
	{
		return detPosition1;
	}

	/**
	 * @param detPosition2 the detPosition2 to set
	 */
	public void setDetPosition2(int detPosition2) 
	{
		this.detPosition2 = detPosition2;
	}

	/**
	 * @return the detPosition2
	 */
	public int getDetPosition2() 
	{
		return detPosition2;
	}

	public Vector<Integer> getVectorTotal() 
	{
		return vTotal;
	}
	
	public Vector<Double> getVectorDensity() 
	{
		return vDensity;
	}
	
	public double getAvgDensity()
	{
		return avgDensity;
	}
	
	public void resetVectors()
	{
		vTotal.removeAllElements();
		vDensity.removeAllElements();
	}
}



/////////////////////////////////////////////////////////////////////////

// CLOCK

/////////////////////////////////////////////////////////////////////////

class Clock extends JPanel 
{
	private int hours = 0;

	private int minutes = 0;

	private int seconds = 0;

	private int millis = 0;
	
	private int delay = 1000;

	private static final int spacing = 10;

	private static final float twoPi = (float)(2.0 * Math.PI);

	private static final float threePi = (float)(3.0 * Math.PI);

	// Angles for the trigonometric functions are measured in radians.

	// The following in the number of radians per sec or min.

	private static final float radPerSecMin = (float)(Math.PI / 30.0);

	private int size; // height and width of clock face

	private int centerX; // x coord of middle of clock

	private int centerY; // y coord of middle of clock

	private BufferedImage clockImage;

	private javax.swing.Timer t;



//==================================================== Clock constructor
	public Clock() 
	{
		this.setPreferredSize(new Dimension(100,100));

		//this.setBackground(Color.white);

		//this.setForeground(Color.black);

		t = new javax.swing.Timer(delay,new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//System.out.println("action per");
				if(t.isRunning())
				{
					//System.out.println("action performed...!!!!!");
					update();
				}
				
			}
		});
		t.setInitialDelay(0);
	}//end constructor



//=============================================================== update

// Replace the default update so that the plain background

// doesn't get drawn.
	public void update() 
	{
		seconds++;
		if(seconds > 59)
		{
			seconds = 0;
			minutes++;
		}
		if(minutes > 59)
		{
			minutes = 0;
			hours ++;
		}
		this.repaint();
		//System.out.println("update...!!!!!");
		
	}//end update



//================================================================ start

	public void start() 
	{
		t.start(); // start the timer
		//System.out.println("timer started...!!!!!");

	}//end start


//================================================================= stop

	public void stop() 
	{
		t.stop(); // start the timer

	}//end stop

	//================================================================= isRunning

	public boolean isRunning() 
	{
		return t.isRunning(); 

	}//end isRunning
	
//================================================================= setDelay

	public void setDelay(int delayInMillisec) 
	{
		delay = delayInMillisec;
		t.setDelay(delay); // set delay for the timer

	}//end setDelay
	
//======================================================= paintComponent

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); // paint background, borders

		Graphics2D g2 = (Graphics2D)g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,

		RenderingHints.VALUE_ANTIALIAS_ON);

		// The panel may have been resized, get current dimensions

		int w = getWidth();

		int h = getHeight();

		size = ((w<h) ? w : h) - 2*spacing;

		centerX = size/2 + spacing;

		centerY = size/2 + spacing;

		// Create the clock face background image if this is the first time,

		// or if the size of the panel has changed

		if (clockImage == null	|| clockImage.getWidth() != w		|| clockImage.getHeight() != h) 
		{
			clockImage = (BufferedImage)(this.createImage(w, h));

			// now get a graphics context from this image

			Graphics2D gc = clockImage.createGraphics();

			gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,

			RenderingHints.VALUE_ANTIALIAS_ON);

			drawClockFace(gc);
		}
		
		// Now get the time and draw the hands.
		/*
		Calendar now = Calendar.getInstance();

		hours = now.get(Calendar.HOUR);

		minutes = now.get(Calendar.MINUTE);

		seconds = now.get(Calendar.SECOND);

		millis = now.get(Calendar.MILLISECOND);*/

		// Draw the clock face from the precomputed image

		g2.drawImage(clockImage, null, 0, 0);

		
	}//end paintComponent

//======================================================paint
	public void paint(Graphics g)
	{
		super.paint(g);
		// Draw the clock hands
		drawClockHands(g);
	}
	
//======================================================= drawClockHands

	private void drawClockHands(Graphics g) 
	{
		int secondRadius = size/2;

		int minuteRadius = secondRadius * 3/4;

		int hourRadius = secondRadius/2;

		// second hand

		float fseconds = seconds + (float)millis/1000;

		float secondAngle = threePi - (radPerSecMin * fseconds);

		drawRadius(g, centerX, centerY, secondAngle, 0, secondRadius);

		// minute hand

		float fminutes = (float)(minutes + fseconds/60.0);

		float minuteAngle = threePi - (radPerSecMin * fminutes);

		drawRadius(g, centerX, centerY, minuteAngle, 0, minuteRadius);

		// hour hand

		float fhours = (float)(hours + fminutes/60.0);

		float hourAngle = threePi - (5 * radPerSecMin * fhours);

		drawRadius(g, centerX, centerY, hourAngle, 0, hourRadius);
	}//end drawClockHands


//======================================================== drawClockFace

	private void drawClockFace(Graphics g) 
	{
		// clock face

		g.setColor(Color.cyan);

		g.fillOval(spacing, spacing, size, size);

		g.setColor(Color.blue);

		g.drawOval(spacing, spacing, size, size);

		// tic marks

		for (int sec = 0; sec<60; sec++) 
		{
			int ticStart;
			if (sec%5 == 0) 
			{
				ticStart = size/2-10;
			} 
			else 
			{
				ticStart = size/2-5;
			}
			
			drawRadius(g, centerX, centerY, radPerSecMin*sec, ticStart , size/2);

		}

	}//end method drawClockFace


//=========================================================== drawRadius

	private void drawRadius(Graphics g, int x, int y, double angle,int minRadius, int maxRadius) 
	{
		float sine = (float)Math.sin(angle);

		float cosine = (float)Math.cos(angle);

		int dxmin = (int)(minRadius * sine);

		int dymin = (int)(minRadius * cosine);

		int dxmax = (int)(maxRadius * sine);

		int dymax = (int)(maxRadius * cosine);

		g.drawLine(x+dxmin, y+dymin, x+dxmax, y+dymax);

	}//end drawRadius

//=============================================================getSeconds
	
	public int getMilliseconds()
	{
		return millis;		
	}//end getMilliseconds
	
//=============================================================getSeconds
	
	public int getSeconds()
	{
		return seconds;		
	}//end getSeconds
	
//==============================================================getMinutes
	
	public int getMinutes()
	{
		return minutes;
	}//end getMinutes
	
//==============================================================getHours
	
	public int getHours()
	{
		return hours;
	}//end getHours
	
//==============================================================reset
	
	public void reset()
	{
		if(t.isRunning())
			t.stop();
		hours = minutes = seconds = millis = 0;
		repaint();
	}//end reset
	
}

///////////////////////////////////////////////////////////////////////////////

// DEFAULT BUTTON

///////////////////////////////////////////////////////////////////////////////

class DefaultButton extends JButton 
{
	/**
	 * 
	 */
	private static boolean REPAINT_SHADOW = true;
	/*private Color startColor = new Color(255, 255, 255);
	private Color endColor = new Color(0,0,0);
	private Color rollOverColor = new Color(205, 92, 92);*/
	
	
	
	private Color startColor = Color.blue;
	private Color endColor = Color.gray;
	private Color rollOverColor = Color.blue;
	
	
	
	//private Color rollOverColor = new Color(255, 143, 89);//light orange
	//private Color rollOverColor = new Color(46, 139, 87);//spring green
	//private Color pressedColor = new Color(204, 67, 0);;//dark orange
	private Color pressedColor = new Color(204, 67, 0);
	private int outerRoundRectSize = 10;
	private int innerRoundRectSize = 8;
	private GradientPaint GP;

	
	public DefaultButton(Icon arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
	public DefaultButton(String text) 
	{
		super();
		setText(text);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFont(new Font("Thoma", Font.BOLD, 12));
		setForeground(Color.WHITE);
		setFocusable(false);
	}
	
	
	public DefaultButton(String text, Color startColor, Color endColor,Color rollOverColor, Color pressedColor) 
	{
		super();
		setText(text);
		setFont(new Font("Thoma", Font.BOLD, 12));
		this.startColor = startColor;
		this.endColor = endColor;
		this.rollOverColor = rollOverColor;
		this.pressedColor = pressedColor;
		setForeground(Color.WHITE);
		setFocusable(false);
		setContentAreaFilled(false);
		setBorderPainted(false);

	}

	/**
	 * @param arg0
	 */
	public DefaultButton(Action arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DefaultButton(String arg0, Icon arg1) 
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	/***
	 * override paintComponent...
	 */
	public void paintComponent(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		int h = getHeight();
		int w = getWidth();
		ButtonModel model = getModel();
		if (!model.isEnabled()) 
		{
			setForeground(Color.GRAY);
			GP = new GradientPaint(0, 0, new Color(192,192,192), 0, h, new Color(192,192,192),true);
		}
		else
		{
			setForeground(Color.WHITE);
			if (model.isRollover()) 
			{
				GP = new GradientPaint(0, 0, startColor, 0, h/2, rollOverColor,true);
	
			} 
			else 
			{
				GP = new GradientPaint(0, 0, startColor, 0, h/2, endColor, true);
			}
		}
		g2d.setPaint(GP);
		GradientPaint p1;
		GradientPaint p2;
		if (model.isPressed()) 
		{
			GP = new GradientPaint(0, 0, startColor, 0, h/2, pressedColor, true);
			g2d.setPaint(GP);
			p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,new Color(100, 100, 100));
			p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,new Color(255, 255, 255, 100));
		}
		else 
		{
			p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,new Color(0, 0, 0));
			p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,h - 3, new Color(0, 0, 0, 50));
			GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
		}
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,h - 1, outerRoundRectSize, outerRoundRectSize);
		Shape clip = g2d.getClip();
		g2d.clip(r2d);
		g2d.fillRect(0, 0, w, h);
		g2d.setClip(clip);
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,outerRoundRectSize);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,innerRoundRectSize);
		g2d.dispose();
		
		setOpaque(false);
		super.paintComponent(g);
		setOpaque(true);
	}

}


/////////////////////////////////////////////////////////////////////////////

