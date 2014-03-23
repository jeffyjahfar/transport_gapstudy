package main;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class TablePane extends JPanel 
{
	  	int i=0;
		JTextField[] tfCell;
		JLabel[] lCell;
		Font font1,font2;
		Border border;
		public int Rows;
		public int Columns;
		
		public TablePane( GridLayout gridLayout,int Rows,int Columns)
		{
			
		border = BorderFactory.createLineBorder(Color.gray);
		setLayout(new GridLayout(Rows,Columns));	
		tfCell = new JTextField[(Rows)*(Columns)];
		lCell = new JLabel[Columns];
		font1 = new Font("Courier",Font.BOLD,14);
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
		
		
}



