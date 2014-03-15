import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FinalTable {

    public static void main( String[] str ) {
        String[] colName = new String[] { "Gap time range","Gap","Cumulative no. of accepted gaps<t","Cumulative no. of rejected gaps<t" };
        Object[][] products = new Object[][] {{" "," "," "," "},{" "," "," "," "},{" "," "," "," "},{" "," "," "," "}};

        JTable table = new JTable( products, colName );


        JFrame frame = new JFrame( "Screen 1 Table" );

        // create scroll pane for wrapping the table and add
        // it to the frame
        frame.add( new JScrollPane( table ) );
        frame.pack();
        frame.setVisible( true );       
    }

}