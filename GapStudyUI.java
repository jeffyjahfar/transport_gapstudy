import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Hashtable;
import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import check.CrossRoadCar;

public class GapStudyUI extends JFrame implements ActionListener,ChangeListener{
        
    public GapStudyUI() {
       
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        
        panel.setLayout(null);
        
        DrawPanel dpnl = new DrawPanel();

        add(dpnl);
               
        pack();
        
        setTitle("Gap Study at Uncontrolled Intersection");
        setSize(1320,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
            
    }    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GapStudyUI ex = new GapStudyUI();
                
                ex.setVisible(true);
            }
        });
    }

    @Override
    public void stateChanged(ChangeEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}

class DrawPanel extends JPanel {
    Timer timerForVehicle;
    Timer timerforsimulation; //timer to fire event after particular delay for vehicle position updation
    Timer timerForClock; 
    Timer timerForDisplayTime,timerForDisplayTime1;//when stopwatch is stopped the time is displayed for a few seconds using this timer 
    JButton startbutton, pausebutton, stopbutton;
    private static final int IMAGE_WIDTH = 40;
    private static final int IMAGE_HEIGHT = 20;
    
    long t;
    int MainRoadFlowRate, CrossRoadFlowRate;
    int mainroadm, crossroadm;
    Random randomnumber;
    double MRr,CRr;
    double mean,variance;
    double mainroadt1,mainroadtcum;
    double crossroadt1,crossroadtcum;
    int NoOfCars;
    int delay;
    long period;
    Graphics2D g2d;
    private void doDrawing(Graphics g) {

        g2d = (Graphics2D) g;
        //timerforsimulation = new Timer();


        g2d.setColor(Color.black);

        float[] dash1 = {2f, 0f, 2f};
        float[] dash2 = {1f, 1f, 1f};
        float[] dash3 = {4f, 0f, 2f};
        float[] dash4 = {4f, 4f, 1f};

        //g2d.drawLine(20, 40, 250, 40);

        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);

        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f);

        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);

        BasicStroke bs4 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash4, 2f);

        g2d.setColor(new Color(212, 212, 212));
        g2d.drawRect(10, 100, 1280, 100);
        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(10, 100, 1280, 100);

        g2d.setColor(new Color(212, 212, 212));
        g2d.drawRect(600, 200, 100, 200);
        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(600, 200, 100, 200);

        g2d.setStroke(bs2);
        g2d.setColor(new Color(255, 255, 255));
        g2d.drawLine(10, 150, 1280, 150);

        g2d.setStroke(bs4);
        g2d.setColor(new Color(255, 255, 255));
        g2d.drawLine(600, 210, 700, 210);

        g2d.setStroke(bs2);
        g2d.setColor(new Color(255, 255, 255));
        g2d.drawLine(650, 150, 650, 400);

        startbutton = new JButton("Start");
        startbutton.setBounds(20,20, 80, 30);
        stopbutton = new JButton("Stop");
        stopbutton.setBounds(120,20, 80, 30); 
        pausebutton = new JButton("Pause");
        pausebutton.setBounds(220,20, 80, 30);   

        Ellipse2D.Double circle =new Ellipse2D.Double(580, 210, 15,15);
            g2d.setPaint(Color.red);
            g2d.setColor(Color.red);
            g2d.fill(circle);   

        add(startbutton);
        add(pausebutton); 
        add(stopbutton);



        JSlider slSimSpeed = new JSlider(0,20,12);
            slSimSpeed.setOpaque(true);
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
            slSimSpeed.setPaintLabels(true);
            add(slSimSpeed);

        Image img = new ImageIcon("image1.png").getImage();
        Image temp = img;
        img = temp.getScaledInstance(IMAGE_WIDTH , IMAGE_HEIGHT , Image.SCALE_FAST);
        g2d.drawImage(img, 10, 175, null);
        

         // startbutton.addActionListener((ActionListener)this);
           // pausebutton.addActionListener((ActionListener)this);
         //   stopbutton.addActionListener((ActionListener)this);

         //   slSimSpeed.addChangeListener((ChangeListener)this); 


            startbutton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the start button");
                startsimulation();
            }
        });      

            pausebutton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the pause button");
            }
        });      

            stopbutton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the stop button");
            }
        });      
    }       
    
    public void startsimulation(){
        delay = 1000;
        period = 1000;
        System.out.println("Car gereation begins..");
        mean=0.5;
        variance=1;
        mainroadtcum=0;
        crossroadtcum=0;
        MainRoadFlowRate = 900;
        CrossRoadFlowRate=600;
        mainroadm= 3600/MainRoadFlowRate;
        crossroadm = 3600/CrossRoadFlowRate;
        NoOfCars=0;
        randomnumber = new Random();

        carmove();
    /*  timerforsimulation.scheduleAtFixedRate(new TimerTask(){
            public void run(){
            cargeneration();
            }
        }, delay, period);*/
        
        
        
    }
    
    public void CrossRoadCar(){
        System.out.println("cross road car to be generated..");
    }
    
    public void MainRoadCar(){
        System.out.println("main road car to be generated..");

    }
    
  /*  public void cargeneration(){
        while(timerforsimulation){          
            
            MRr=mean+randomnumber.nextGaussian()*variance;
            mainroadt1=mainroadm*(-Math.log(MRr));
            CRr=mean+randomnumber.nextGaussian()*variance;
            crossroadt1=crossroadm*(-Math.log(CRr));
            crossroadtcum+=crossroadt1;
            mainroadtcum += mainroadt1;
            
            if(timerforsimulation.getDelay()==crossroadt1){
                CrossRoadCar();
            }
            
            if(timerforsimulation.getDelay()==crossroadt1){
                MainRoadCar();
            }
            }
    }*/

    public void carmove(){
        System.out.println("rectangles to be generated");
        g2d.setColor(new Color(255, 255, 255));
        g2d.drawRect(10, 175, 20, 10);
        g2d.setColor(new Color(97, 47, 50));
        g2d.fillRect(10, 175, 20, 10);
        int i=0;
        while(i<2000){i=i+1;}
    }
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
        setOpaque(true);
    }
}



