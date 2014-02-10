/**
 * 
 */
package main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author jeffy
 *
 */
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
