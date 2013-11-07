package view.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import model.Direction;
import model.GenerationStrategy;
import model.Map;
import model.Room;
import view.gui.dialogs.HowToPlayDialog;

/**
 * Is the main GUI for Hunt the Wumpus
 * 
 * @author Christopher Chapline
 * @author Christopher Toepfer
 * @author David Christy
 * @author James Fagan
 */

public class HuntTheWumpusGUI extends JFrame implements ActionListener, KeyListener, Observer {

	//Serialization
	private static final long serialVersionUID = 2671883464712322785L;
	
	//Frame settings
	private final int WIDTH;
	private final int HEIGHT;

	//Game elements
	private GenerationStrategy employedStrategy;
	private Map gameMap;
	private RoomView[][] graphicalView;
	private boolean firingMode = false;

	//GUI Components
	private JMenuBar jmb;
	private JMenu gameSettings, help;
	private JMenuItem startNewGame, quitGame, howToPlay, credits, switchView;
	
	
	//panels
	private JPanel consolePanel;
	private JPanel GuiPanel;
	private JPanel mapPieces;
	
	//Console components
	private JTextArea console;
	private JLabel status;

	
	/**
	 * Starts the game with a custom generation strategy
	 * @param strategy The generation strategy to use
	 */
	public HuntTheWumpusGUI( GenerationStrategy strategy ) {
		//Call superconstructor
		super( "Hunt the Wumpus" );
		
		//Set height and width
		this.WIDTH = strategy.getColumns() * 50;
		this.HEIGHT = strategy.getRows() * 50 + 65;
		
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
		gameMap.addObserver(this);
		Room[][] roomsInMap = this.gameMap.getRooms();
		this.graphicalView = new RoomView[ roomsInMap.length ][ roomsInMap[0].length ];
		firingMode = false;
		
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
		super.setLayout( new FlowLayout( 1, 2, 0) );
		super.setResizable( false );
		super.setLocationRelativeTo( null );
		super.addKeyListener( this );
	}

	/**
	 * Initializes GUI components (ignoring graphical elements)
	 */
	private final void initComponents() {
		//Menu items
		this.howToPlay = new JMenuItem( "How to Play" );
		this.howToPlay.addActionListener( this );
		
		this.startNewGame = new JMenuItem( "New Game");
		this.startNewGame.addActionListener( this );
		
		this.quitGame = new JMenuItem("Quit Game");
		this.quitGame.addActionListener( this );
		
		this.credits = new JMenuItem("Credits");
		this.credits.addActionListener( this );
		
		this.switchView = new JMenuItem("switch (will change)");
		this.switchView.addActionListener( this );
		
		this.mapPieces = new JPanel();
		this.mapPieces.setLayout( new GridLayout( this.employedStrategy.getColumns(), this.employedStrategy.getRows(), 0, 0) );
		
		
		
		//Menus
		this.gameSettings = new JMenu( "Game Settings" );
		
		this.help = new JMenu( "Help" );
		this.help.add( this.howToPlay );
		this.gameSettings.add(this.startNewGame);
		this.gameSettings.add(this.quitGame);
		this.gameSettings.add(this.credits);
		this.gameSettings.add(this.switchView);
		
		//Menu bar
		this.jmb = new JMenuBar();
		this.jmb.add( this.gameSettings );
		this.jmb.add( this.help );
		
		//console
		this.console = new JTextArea();
		this.consolePanel = new JPanel();
		consolePanel.add(console);
		
		//Status
		this.status = new JLabel("Click help from the menu bar to learn how to play!");
	}

	/**
	 * Adds relevant components to the GUI
	 */
	private final void addComponents() {
		super.setJMenuBar( this.jmb );
	
		for( int i = 0; i < this.graphicalView.length; i++ ) {
			for( int j = 0; j < this.graphicalView[0].length; j++ ) {
				mapPieces.add( this.graphicalView[i][j] );
			}
		}
		super.add(status);
		super.add(mapPieces);
	}

	
	@Override public void keyPressed(KeyEvent arg0) {
		//NOTHING
	}

	
	@Override public void keyReleased(KeyEvent arg0) {
//		System.out.println( "Key Released: " + arg0.getKeyChar() );
		int keyCode = arg0.getKeyCode();
		Direction movementDirection = null;
		switch( keyCode ) {
		case KeyEvent.VK_W: movementDirection = Direction.UP; break;
		case KeyEvent.VK_S: movementDirection = Direction.DOWN; break;
		case KeyEvent.VK_A: movementDirection = Direction.LEFT; break;
		case KeyEvent.VK_D: movementDirection = Direction.RIGHT; break;
		case KeyEvent.VK_N: 
			gameMap.resetMap(false);
			firingMode = false;
			break;
		case KeyEvent.VK_F: 
			if(gameMap.playing()) firingMode = true;
			break;	
		default: movementDirection = null;
				firingMode = false;
		}
		
		if(firingMode && movementDirection != null)
			this.gameMap.fireArrow(movementDirection);
		else if( movementDirection != null ) {
			this.gameMap.move( movementDirection );
		}
		update();
	}

	@Override public void keyTyped(KeyEvent arg0) {
		//NOTHING
	}

	@Override public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if( source == this.howToPlay ) {
			HowToPlayDialog htpd = new HowToPlayDialog( this );
		}
		else if (source == this.startNewGame){
			gameMap.resetMap(false);
			update();
		}
		else if (source == this.quitGame){
			System.exit(0);
		}
		else if (source == this.credits){
			JOptionPane.showMessageDialog(credits,
					"All these people did stuff!\nChristopher Chapline\nChristopher Toepfer\nDavid Christy\nJames Fagan");
		}
		else if (source == this.switchView){
			//here is where the code goes for switch between text and GUI
		}
		
	}

	public static void main(String[] args) {
		HuntTheWumpusGUI htwg = new HuntTheWumpusGUI();
		htwg.setVisible( true );
	}

	@Override
	public void update() {
		super.repaint();
		if(!gameMap.playing())
			this.status.setText(gameMap.getStatus()+" Select n to start a new game.");
		else if(firingMode)
			this.status.setText("FIRING MODE: Select a, s, w, or d to fire your arrow, or any other key to exit firing mode.");
		else
			this.status.setText(gameMap.getStatus());
	}

}
