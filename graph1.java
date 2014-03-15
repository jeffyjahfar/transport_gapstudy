import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
 
public class graph1 extends JPanel {
    int[] data = {
        2,3,5,8,13,21,34,55,89
    };
    final int PAD = 20;
    double prevx,prevy,newx,newy;
    
    protected void paintComponent(Graphics g) {
        prevx=0;
    prevy=0;
    newx=0;newy=0;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        double xInc = (double)(w - 2*PAD)/(data.length-1);
        double scale = (double)(h - 2*PAD)/getMax();
        prevx=PAD;
        prevy=h-PAD;
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*data[i];
            newx=x;
            newy=y;
            g2.draw(new Line2D.Double(prevx, prevy, newx, newy));

            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            prevx=newx;
            prevy=newy;
        }
    }
 
    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
 
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new graph1());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}