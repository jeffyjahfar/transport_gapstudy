import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ResultTable {

    public static void main( String[] str ) {
        String[] colName = new String[] { "Vehicle Type","Critical Gap" };
        Object[][] products = new Object[][] {};

        JTable table = new JTable( products, colName );


        JFrame frame = new JFrame( "Screen 3 Table" );

        // create scroll pane for wrapping the table and add
        // it to the frame
        frame.add( new JScrollPane( table ) );
        frame.pack();
        frame.setVisible( true );       
    }

}