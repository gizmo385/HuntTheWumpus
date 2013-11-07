package view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Hunter;

public class animateHunter extends JPanel implements Observer {
	private static final long serialVersionUID = -6605416678021140770L;
	private final String fileSep = System.getProperty( "file.separator");
    private final String baseDir = System.getProperty("user.dir");
    private final String imageLoadLocation = baseDir + fileSep + "res" + fileSep;
    public final int HUNTER_START_X = 0;
    public final int HUNTER_START_Y = 0;
    public final int HUNTER_WALK_DISTANCE = 100;
    private int hunterFrameCounter = 0;
    private int hunterTickCounter = 0;
    private int hunterXValue = 0;
    private int hunterYValue = 0;
    private Hunter hunter;
    
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		String fileName = null;
		fileName = "Hunter.png";
		if(fileName != null) {
		try {
			BufferedImage hunter = ImageIO.read( new File( imageLoadLocation + fileName ) );
			gr.drawImage(hunter, hunterXValue, hunterYValue, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void updateAnimations() {
		moveHunter();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	private void moveHunter() {
		
		hunterXValue = hunter.getXCoordinate();
		hunterYValue = hunter.getYCoordinate();
		//hunter.
	}

}
