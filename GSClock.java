import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.Calendar;

import javax.swing.JPanel;

public class GSClock extends JPanel {
	
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	private int millis = 0;	
	private int delay = 1000;
	
	private int spacing=10;
	
	private static final float twoPi = (float)(2.0 * Math.PI);

	private static final float threePi = (float)(3.0 * Math.PI);

	// Angles for the trigonometric functions are measured in radians.

	// The following in the number of radians per sec or min.

	private static final float radPerSecMin = (float)(Math.PI / 30.0);
	
	private int size;
	private int xcoord;
	private int ycoord;
	
	private BufferedImage clockImage;
	private javax.swing.Timer t;

	// **********constructor class GSClock ***************
	public GSClock() 
	{
		this.setPreferredSize(new Dimension(100,100));

		this.setBackground(Color.white);

		this.setForeground(Color.black);

		t = new javax.swing.Timer(delay,new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("action per");
				if(t.isRunning())
				{
					System.out.println("action performed...!!!!!");
					update();
				}
				
			}
		});
		t.setInitialDelay(0);
	}//end constructor
	
	// ************* function update ***************

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
	
	// ****** function to start timer **************	
	public void start() 
	{
		t.start(); // start the timer
		System.out.println("timer started...!!!!!");

	}//end start
	
	// ********** function to stop timer ************

	public void stop() 
	{
		t.stop(); // stop the timer
		System.out.println("timer stopped...!!");

	}//end stop

	// ***********function isRunning************

	public boolean isRunning() 
	{
		return t.isRunning(); 

	}//end isRunning
	
	// ************ function setDelay ***************

	public void setDelay(int delayInMillisec) 
	{
		delay = delayInMillisec;
		t.setDelay(delay); // set delay for the timer

	}//end setDelay
	
	// *************** function to paint components *********
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); // paint background, borders
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		// The panel may have been resized, get current dimensions
		int w = getWidth();
		int h = getHeight();
		size = ((w<h) ? w : h) - 2*spacing;

		xcoord = size/2 + spacing;

		ycoord = size/2 + spacing;

		// Create the clock face background image if this is the first time,

		// or if the size of the panel has changed

		if (clockImage == null	|| clockImage.getWidth() != w	|| clockImage.getHeight() != h) 
		{
			clockImage = (BufferedImage)(this.createImage(w, h));

			// now get a graphics context from this image

			Graphics2D gc = clockImage.createGraphics();

			gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

			drawClockFace(gc);
		}
		
		// Now get the time and draw the hands.
		
		Calendar now = Calendar.getInstance();

		hours = now.get(Calendar.HOUR);

		minutes = now.get(Calendar.MINUTE);

		seconds = now.get(Calendar.SECOND);

		millis = now.get(Calendar.MILLISECOND);

		// Draw the clock face from the precomputed image

		g2.drawImage(clockImage, null, 0, 0);

		
	}//end paintComponent
	
	// *************** paint ***************
	public void paint(Graphics g)
	{
		super.paint(g);
		// Draw the clock hands
		drawClockHands(g);
	}
	
	// ******************* drawClockHands ***********

	private void drawClockHands(Graphics g) 
	{
		int secondRadius = size/2;

		int minuteRadius = secondRadius * 3/4;

		int hourRadius = secondRadius/2;

		//second hand
		float fseconds = seconds + (float)millis/1000;

		float secondAngle = threePi - (radPerSecMin * fseconds);

		drawRadius(g, xcoord, ycoord, secondAngle, 0, secondRadius);

		// minute hand

		float fminutes = (float)(minutes + fseconds/60.0);

		float minuteAngle = threePi - (radPerSecMin * fminutes);

		drawRadius(g, xcoord, ycoord, minuteAngle, 0, minuteRadius);

		// hour hand

		float fhours = (float)(hours + fminutes/60.0);

		float hourAngle = threePi - (5 * radPerSecMin * fhours);

		drawRadius(g, xcoord, ycoord, hourAngle, 0, hourRadius);
	}//end drawClockHands


// ************ drawClockFace *********************

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
			
			drawRadius(g, xcoord, ycoord, radPerSecMin*sec, ticStart , size/2);

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
	
	public void main(String args[])
	{ GSClock sampleclock = new GSClock();
	sampleclock.start();
	sampleclock.stop();
	}
	
}
	
	
	
	
	
	
	
	



  
