package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class GapStudyUI1 extends JFrame {
	
	Graphics2D g,g2d;
	int xSize,ySize;
	int detPosition,detPosition2,detPosition1 ; //position of detector
	private static final int IMAGE_WIDTH = 40;
	private static final int IMAGE_HEIGHT = 20; 
	private static final int GAP = 15;
	private static final String NO_OF_LANES = "3";
	private int delayInMillisec = 1000;
    public GapStudyUI1() {
	   
    	initUI();
            
    }
    
    private void initUI(){
    	JPanel panel = new JPanel();
    	getContentPane().add(panel);
    	
    	panel.setLayout(null);
    	JButton nextbutton = new JButton("Next");
    	nextbutton.setBounds(700,600, 80, 30);
    	JButton finishbutton = new JButton("Finish");
    	finishbutton.setBounds(800,600, 80, 30);
    	xSize= 1320;
    	ySize = 625;
    	
    	detPosition = 4*(xSize/7)+60+130;
		
		
		detPosition1 = 4*(xSize/7)+70;
		detPosition2 = 4*(xSize/7)+75;
    	/* nextbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
       }
    }); */
    	
    	int w = getWidth();
	    int h = getHeight();
	    int x = 5;
	    int y = 7;
             
	 
	    Color color1 = getBackground();
	    Color color2 = Color.white;
	    
	    GradientPaint gp = new GradientPaint( 0, 0, color1, 100, 100, color2,true );
	    
	    g2d.setPaint( Color.white );
	    g2d.fillRect( 0, 0, w, h );
	 
	    setOpacity(100);
		
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
    	    	
    	panel.add(nextbutton);
    	panel.add(finishbutton);
    	
    	//panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	add(panel);
    	pack();
    	
    	 setTitle("Gap Study at Uncontrolled Intersection");
         setSize(1000,700);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(EXIT_ON_CLOSE);  
    }
    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GapStudyUI1 ex = new GapStudyUI1();
                
                ex.setVisible(true);
            }
        });
    }
}