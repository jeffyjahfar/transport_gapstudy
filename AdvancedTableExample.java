import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

class AdvancedTableExample
		extends 	JFrame
 {
	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	JTable		table;
	private	JScrollPane scrollPane;

	private	String		columnNames[];
	private	String		dataValues[][];


	// Constructor of main frame
	public AdvancedTableExample()
	{
		// Set the frame characteristics
		setTitle( "Advanced Table Application" );
		setSize( 300, 200 );
		setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create columns
		CreateColumns();
		CreateData();

		// Create a new table instance
		table = new JTable( dataValues, columnNames );

		// Configure some of JTable's paramters
		table.setShowHorizontalLines( false );
		table.setRowSelectionAllowed( true );
		table.setColumnSelectionAllowed( true );

		// Change the selection colour
		table.setSelectionForeground( Color.white );
		table.setSelectionBackground( Color.red );

		// Add the table to a scrolling pane
		scrollPane = table.createScrollPaneForTable( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );
	}

	public void CreateColumns()
	{
		// Create column string labels
		columnNames = new String[4];

		columnNames[0]="Serial No.";
		columnNames[1]="Vehicle Tyoe";
		columnNames[2]="Gap (sec)";
		columnNames[3]="Accept/Reject";


	}

	public void CreateData()
	{
		// Create data for each element
		dataValues = new String[100][8];

		for( int iY = 0; iY < 100; iY++ )
		{
			for( int iX = 0; iX < 8; iX++ )
			{
				dataValues[iY][iX] = "" + iX + "," + iY;
			}
		}
	}


	// Main entry point for this example
	public static void main( String args[] )
	{
		// Create an instance of the test application
		AdvancedTableExample mainFrame	= new AdvancedTableExample();
		mainFrame.setVisible( true );
	}

}