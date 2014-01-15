package main;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;


public class GapStudyUI extends JFrame {

    public GapStudyUI() {
	   
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
    	
    	/* nextbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
       }
    }); */
    	String text = "OBSERVATIONS";
    	JLabel label = new JLabel(text);
    	label.setFont(new Font("Georgia", Font.PLAIN, 14));
    	label.setForeground(new Color(0, 0, 0));
    	
    	
    	panel.add(nextbutton);
    	panel.add(finishbutton);
    	panel.add(label);
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
                GapStudyUI ex = new GapStudyUI();
                
                ex.setVisible(true);
            }
        });
    }
}