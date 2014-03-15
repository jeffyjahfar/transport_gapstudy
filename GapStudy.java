package main;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.text.*;

import javax.swing.*;

public class GapStudy {

static JComboBox cbStarttime;
static JComboBox cbDuration;
static JComboBox cbCountinterval;
static double[] xCord;
static int[] yCord;
static Image[] image;

public CarMotionAnimation CM_Animation; //object of animation class



public StartTimer(); //function to start timer
public StopTimer(); //function to stop timer and calculate interval time



}
// CAR MOTION CLASS FROM SATURATION FLOW PGM

/* *****************************
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
    		// label =new JLabel("Super Fast");
    		//labelTable.put( new Integer( 25 ), label );
    		//slSimSpeed.setLabelTable( labelTable );
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
    		
    		JPanel pane2= new JPanel(//new FlowLayout(FlowLayout.LEFT));
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
        	timeInSec = (Integer.parseInt(lMinutes.getText())*60+Integer.parseInt(lSeconds.getText()));
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
        	}
        	//System.out.println("timeInSec " + timeInSec);
        	//System.out.println("dispTime " + dispTime);
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
    		  greenTimeTask();
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
        bottomPane.add(scrollTab,BorderLayout.CENTER);
        //repaint();
		
	}
************************ */