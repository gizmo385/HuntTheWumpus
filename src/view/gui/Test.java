package view.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Condition;
import model.Room;

/**
 * EXPERIMENTAL PROOF OF CONCEPT CODE
 * @author Christopher
 *
 */
public class Test extends JFrame {

	private static final long serialVersionUID = -7349214588455573920L;
	private Room r = new Room();
	private RoomView rv;
	
	public Test() {
		super("Test");
		super.setSize( 250, 100 );
		super.setLayout( new GridLayout( 1, 2, 0, 0) );
		super.setLocationRelativeTo( null );
		super.setResizable( false );
		super.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		/*
		 * CHANGE THIS TO CHANGE THE CONDITION
		 */
		r.setCondition( Condition.PIT );
		rv = new RoomView( r );
		super.add( rv );
		JButton jb = new JButton( "Visible" );
		jb.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				r.setVisible( ! r.isVisible() );
				rv.repaint();
			}
		});
		super.add( jb );
		super.setVisible( true );
	}
	
	public static void main( String[] args ) {
		Test t = new Test();
	}

}
