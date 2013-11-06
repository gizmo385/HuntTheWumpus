package view.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Direction;
import model.GenerationStrategy;
import model.Map;
import model.Room;

public class HuntTheWumpusGUI extends JFrame implements ActionListener, KeyListener {

	//Serialization
	private static final long serialVersionUID = 2671883464712322785L;
	
	//Frame settings
	private final int WIDTH;
	private final int HEIGHT;

	//Game elements
	private GenerationStrategy employedStrategy;
	private Map gameMap;
	private RoomView[][] graphicalView;

	//GUI Components
	private JMenuBar jmb;
	private JMenu gameSettings, help;
	private JMenuItem startNewGame, quitGame, howToPlay, credits;

	/**
	 * Starts the game with a custom generation strategy
	 * @param strategy The generation strategy to use
	 */
	public HuntTheWumpusGUI( GenerationStrategy strategy ) {
		//Call superconstructor
		super( "Hunt the Wumpus" );
		
		//Set height and width
		this.WIDTH = strategy.getColumns() * 50;
		this.HEIGHT = strategy.getRows() * 50;
		
		//Call initialization methods
		initGameElements( strategy );
		initFrame();
		initComponents();
		addComponents();
	}
	
	/**
	 * Starts the game with the default generation strategy
	 */
	public HuntTheWumpusGUI() {
		this( new GenerationStrategy( 3, 1, 3, 15, 15 ) );
	}

	/**
	 * Initializes GUI elements
	 * @param strategy The GenerationStrategy used to generate gameplay
	 */
	private final void initGameElements( GenerationStrategy strategy ) {
		//Initialize game map
		this.employedStrategy = strategy;
		this.gameMap = new Map( this.employedStrategy );
		Room[][] roomsInMap = this.gameMap.getRooms();
		this.graphicalView = new RoomView[ roomsInMap.length ][ roomsInMap[0].length ];
		
		//Initialize JPanels
		for( int i = 0; i < this.graphicalView.length; i++ ) {
			for( int j = 0; j < this.graphicalView[0].length; j++ ) {
				this.graphicalView[i][j] = new RoomView( roomsInMap[i][j] );
			}
		}
	}

	/**
	 * Initializes frame settings
	 */
	private final void initFrame() {
		super.setSize( this.WIDTH, this.HEIGHT );
		super.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		super.setLayout( new GridLayout( this.employedStrategy.getColumns(), this.employedStrategy.getRows(), 0, 0 ) );
		super.setResizable( false );
		super.setLocationRelativeTo( null );
		super.addKeyListener( this );
	}

	/**
	 * Initializes GUI components (ignoring graphical elements)
	 */
	private final void initComponents() {
		
	}

	/**
	 * Adds relelvent components to the GUI
	 */
	private final void addComponents() {
		for( int i = 0; i < this.graphicalView.length; i++ ) {
			for( int j = 0; j < this.graphicalView[0].length; j++ ) {
				super.add( this.graphicalView[i][j] );
			}
		}
	}

	
	@Override public void keyPressed(KeyEvent arg0) {
		//NOTHING
	}

	
	@Override public void keyReleased(KeyEvent arg0) {
		System.out.println( "Key Released: " + arg0.getKeyChar() );
		int keyCode = arg0.getKeyCode();
		Direction movementDirection = null;
		switch( keyCode ) {
		case KeyEvent.VK_W: movementDirection = Direction.UP; break;
		case KeyEvent.VK_S: movementDirection = Direction.DOWN; break;
		case KeyEvent.VK_A: movementDirection = Direction.LEFT; break;
		case KeyEvent.VK_D: movementDirection = Direction.RIGHT; break;
		default: movementDirection = null;
		}
		
		if( movementDirection != null ) {
			this.gameMap.move( movementDirection );
			super.repaint();
		}
	}

	@Override public void keyTyped(KeyEvent arg0) {
		//NOTHING
	}

	@Override public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		HuntTheWumpusGUI htwg = new HuntTheWumpusGUI();
		htwg.setVisible( true );
	}

}
