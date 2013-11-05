package view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Room;

/**
 * EXPERIMENTAL PROOF OF CONCEPT CODE
 * @author Christopher
 *
 */
public class RoomView extends JPanel {

	private static final long serialVersionUID = -8556412318385780750L;
	private final String fileSep = System.getProperty( "file.separator");
	private final String baseDir = System.getProperty("user.dir");
	private final String imageLoadLocation = baseDir + fileSep + "res" + fileSep;
	private Room room;
	
	public RoomView( Room room ) {
		super();
		super.setPreferredSize( new Dimension( 50, 50 ) );
		super.setVisible( true );
		this.room = room;
		
		System.out.println( this.imageLoadLocation );
	}
	
	/**
	 * Called in repaint()
	 */
	@Override public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//If the room is visible, load and draw images
		if( this.room.isVisible() ) {
			try {
				//Draw ground background
				BufferedImage groundBackground = ImageIO.read( new File( imageLoadLocation + "Ground.png" ) );
				g2.drawImage( groundBackground, 0, 0, null );
				
				//Get requisite effect
				String fileName = null;
				switch( this.room.getCondition() ) {
				case SLIME: fileName = "Slime.png"; break;
				case BLOOD: fileName = "Blood.png"; break;
				case PIT: fileName = "SlimePit.png"; break;
				case WUMPUS: fileName = "Wumpus.png"; break;
				case GOOP: fileName = "Goop.png"; break;
				default: break;
				}
				
				//If there is a relevant effect, this will != null and an image will be loaded and drawn
				if( fileName != null ) {
					BufferedImage effect = ImageIO.read( new File( imageLoadLocation + fileName ) );
					g2.drawImage( effect, 0, 0, null );
				}
				
				//If the hunter is in the room, draw the hunter
				if( this.room.hasHunter() ) {
					BufferedImage effect = ImageIO.read( new File( imageLoadLocation + "TheHunter.png" ) );
					g2.drawImage( effect, 0, 0, null );
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Otherwise, fill the square with black
		else {
			Color prev = g.getColor();
			g.setColor(Color.black);
		    g.fillRect( 0, 0, 50, 50 );
		    g.setColor( prev );
		}
	}
}
