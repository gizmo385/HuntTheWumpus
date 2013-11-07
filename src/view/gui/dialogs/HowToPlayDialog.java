package View.gui.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Tells the user how to play Hunt the Wumpus
 * @author Christopher
 *
 */
public class HowToPlayDialog extends JDialog implements MouseListener {

	//Serialization
	private static final long serialVersionUID = -4591491816066369022L;

	//Final variables
	private final String baseDir = System.getProperty( "user.dir" );
	private final String fileSep = System.getProperty( "file.separator" );
	private final String imageFolder = baseDir + fileSep + "res" + fileSep;
	private final int WIDTH = 640;
	private final int HEIGHT = 450;

	public HowToPlayDialog( JFrame parent ) {
		//Frame settings
		super( parent, "How to Play", true );
		super.addMouseListener( this );
		super.setUndecorated( true ); //Remove titlebar and window decoration
		super.setSize( new Dimension( WIDTH, HEIGHT ) );
		super.setResizable( false );
		super.setLocationRelativeTo( parent );
		super.setLayout( new FlowLayout() );
		super.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		super.setVisible( true );
	}


	@Override public void paint( Graphics g ) {
		super.paint( g );

		Graphics2D g2 = (Graphics2D)g;

		BufferedImage exitBar = null;
		BufferedImage howToPlay = null;

		try {
			//Load the images
			exitBar = ImageIO.read( new File( imageFolder + "exitBar.png" ) );
			howToPlay = ImageIO.read( new File( imageFolder + "howToPlay.png" ) );
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		//Draw the images on the screen
		if( exitBar != null && howToPlay != null ) {
			g2.drawImage( exitBar, null, 0, 0 );
			g2.drawImage( howToPlay, null, 0, 50 );
		}
	}

	@Override public void mousePressed( MouseEvent me ) {
		//Get the mouse location
		int xCoord = me.getX();
		int yCoord = me.getY();

		//Print the mouse location (debugging purposes)
		System.out.println( "(" + xCoord +  ", " + yCoord + ")" );

		//Test that the click is within the confines of the button
		if( xCoord <= WIDTH && xCoord >= WIDTH - 50 && yCoord >= 0 && yCoord <= HEIGHT + 50 ) {
			dispose(); //Close the dialog
		}
	}
	
	/* Unimplemented Listener Methods */
	@Override public void mouseEntered(  MouseEvent me ) { }
	@Override public void mouseExited( MouseEvent me ) { }
	@Override public void mouseReleased( MouseEvent me ) { }
	@Override public void mouseClicked( MouseEvent me ) { }
	
	public static void main(String[] args) {
		HowToPlayDialog htpd = new HowToPlayDialog( null );
	}

}
