import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Road extends JPanel{

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(25,25,1000, 50);
		g.draw3DRect(75,75, 30, 15, true);
		g.draw3DRect(105, 75, 30, 15, true);
		Button button1,button2;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("ROAD UNDER GAP STUDY");
		frame.setSize(1100, 500);
		frame.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
			        System.exit(0);}			
		});
		Container contentPane = frame.getContentPane();
        contentPane.add(new Road());
		frame.show();
	}
}
