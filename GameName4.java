// Kathy Zeng
// 5/3/21
// GameName4.java
// Desciption: My game is called Geography Quiz with 10 questions.
// Source cited: EmptyBorder (Java Platform SE 7 ). (2020, June 24).
// Retrieved April 21, 2021, from https://docs.oracle.com/javase/7/docs
// api/javax/swing/border/EmptyBorder.html
// URL address: https://docs.oracle.com/javase/7/docs
// api/javax/swing/border/EmptyBorder.html

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameName4
{
	public static void main(String[] args) 
	{
		GameName4 gn4 = new GameName4();
		gn4.run();
	}

	// Creates a frame 600 by 600.
	public void run() 
	{
		JFrame frame = new JFrame ("GameName4");
		frame.setSize(600, 600);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		GameName4Panel gn4p = new GameName4Panel();
		frame.getContentPane().add(gn4p);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}

class GameName4Panel extends JPanel
{
	private CardLayout cards; // Use it to pass to the all the classes and sets the layout.
	private StartPage sp; //  Instantiate the start page and add it.
	private Instructions is; // Instantiate the instructions page and add it.
	private GamePanel gp; // Instantiate the game page and add it.
	private DifficultyPanel dp; // Instantiate the difficulty page and add it. 
	private PlayGamePanel pgp; // Instantiate the playgame page and add it.
	private GameResultsPanel grp; // Instantiate the gameresults page and add it.
	private GameData gd; // Passes in to all classes.
	
	public GameName4Panel()
	{
		cards = new CardLayout();
		gd.grabQuestionFromFile();
		setLayout(cards);
		sp = new StartPage(gd, this, cards);
		add(sp, "StartPage");
		is = new Instructions(gd, this, cards);
		add(is, "InstructionsPage");
		gp = new GamePanel(gd, this, cards);
		add(gp, "GamePage");
		dp = new DifficultyPanel(gd, this, cards);
		add(dp, "SelectADifficulty");
		pgp = new PlayGamePanel(gd, this, cards);
		add(pgp, "PlayGamePanel");
		grp = new GameResultsPanel(gd, this, cards);
		add(grp, "GameResultsPanel");
	}
}

// This is a class for a start panel with a null layout. Welcome
// message is above instructions, play game, and exit buttons.
// These handler classes are use to make them responsive. 
class StartPage extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName4Panel gameName; // Pass in as a parameter.
	private String worldPic; // Naming an image.
	private Image image; // Gets an image file.
	private GameData gd;
	
	public StartPage(GameData gdIn, GameName4Panel gameNam4, CardLayout cardsIn)
	{
		gd = gdIn;
		gameName = gameNam4;
		cards = cardsIn;
		worldPic = new String("countriesoftheworld.png");
		image = null;
		setLayout(null);
		
		getAImage();
		JLabel welcome = new JLabel("Welcome to Geography Quiz", JLabel.CENTER);
		welcome.setBounds(5, 5, 200, 200);
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setVerticalAlignment(JLabel.CENTER);
		add(welcome);

		JButton instructions = new JButton("Instructions");
		instructions.setBounds(200, 120, 300, 120);
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setVerticalAlignment(JLabel.CENTER);
		add(instructions);

		JButton playGame = new JButton("Play Game");
		playGame.setBounds(200, 280, 300, 120);
		playGame.setHorizontalAlignment(JLabel.CENTER);
		playGame.setVerticalAlignment(JLabel.CENTER);
		add(playGame);

		JButton exit = new JButton("Exit");
		exit.setBounds(200, 400, 300, 120);
		exit.setHorizontalAlignment(JLabel.CENTER);
		exit.setVerticalAlignment(JLabel.CENTER);
		add(exit);

		StartHandler sh = new StartHandler();
		instructions.addActionListener(sh);

		PlayHandler ph = new PlayHandler();
		playGame.addActionListener(ph);

		ExitHandler eh = new ExitHandler();
		exit.addActionListener(eh);
	}

	// Loads an image which is a world map.
	public void getAImage()
	{
		try
		{
			image = ImageIO.read(new File(worldPic) );
		}
		catch(IOException e)
		{
			System.err.println("\n\n\n" + worldPic + "can't be found.\n\n\n");
			e.printStackTrace();
		}
	}

	// Draws an image.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 600, 600, this);
	}
	
	class StartHandler implements ActionListener
	{
		// Goes to Instructions page.
		public void actionPerformed(ActionEvent evt)
		{
			cards.show(gameName, "InstructionsPage");
		}
	}

	class PlayHandler implements ActionListener
	{
		// Goes to game page by pressing play game button or start page
		// by pressing go back button.
		public void actionPerformed(ActionEvent evt)
		{
			
			String command = evt.getActionCommand(); 
			if(command.equals("Play Game") )
				cards.show(gameName, "GamePage");
			else if(command.equals("Go Back") )
				cards.show(gameName, "StartPage");
		}
	}

	class ExitHandler implements ActionListener
	{
		
		// Exits the game.
		public void actionPerformed(ActionEvent evt)
		{
			System.exit(1);
		}
	}
}

// Instructions' class has a border layout. A label is in north and
// a text area takes a remaining space with instructions.  
class Instructions extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName4Panel gameName; // Pass in as a parameter.
	pirvate GameData gd;
	
	public Instructions(GameData gdIn, GameName4Panel gameNameIn, CardLayout cardsIn)
	{
		gd = gdIn;
		cards = new CardLayout();
		gameName = gameNameIn;
		cards = cardsIn;
		setLayout(new BorderLayout() );
		
		JLabel ins = new JLabel("Read Instructions");
		add(ins, BorderLayout.NORTH);
		JTextArea ta = new JTextArea("Geography Quiz: There is 10 questions"
			+ " per game that a user's goal is to get all answers right\n"
			+ "in order to get 10 falling balls after game been finished."
			+ " A user needs to type a right answer, if \n not, a right answer"
			+ " is going to shown or a user click on a hint button.");
		add(ta, BorderLayout.CENTER);
		JButton back = new JButton("Go Back");
		add(back, BorderLayout.SOUTH);
		InstructHandler ih = new InstructHandler();
		back.addActionListener(ih);
	}

	class InstructHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent evt)
		{
			// When a user clicks on "Go Back" button, takes back to
			// start page.
			String command = evt.getActionCommand();
			if(command.equals("Go Back") )
				cards.show(gameName, "StartPage");
		}
	}
}


class GamePanel extends JPanel
{
	private JButton back;
	private CardLayout cards; // Pass in as a parameter.
	private GameName4Panel gameName; // Pass in as a parameter.
	private DifficultyPanel dp;
	private PlayGamePanel pgp;
	private GameResultsPanel gr;
	private GameData gd;
	
	public GamePanel(GameData gdIn, GameName4Panel gameNameIn, CardLayout cardsIn)
	{
		gd = gdIn;
		gameName = gameNameIn;
		cards = cardsIn;
		setLayout(new BorderLayout() );
		JLabel category = new JLabel("Select at least one category");
		add(category, BorderLayout.NORTH);
		SelectPanel sp = new SelectPanel();
		add(sp, BorderLayout.CENTER);
		back = new JButton("Go Back");
		add(back, BorderLayout.SOUTH);
		JButton ok = new JButton("ok");
		add(ok, BorderLayout.SOUTH);
		GameHandler gh = new GameHandler();
		back.addActionListener(gh);
		ok.addActionListener(gh);
	}

	// A set of checkboxes are use for specific geographic categories.
	class SelectPanel extends JPanel
	{
		public SelectPanel()
		{
			setLayout(new GridLayout(3, 2) );
			JCheckBox select1 = new JCheckBox("cities");
			add(select1);
			JCheckBox select2 = new JCheckBox("capital cities");
			add(select2);
			JCheckBox select3 = new JCheckBox("beaches");
			add(select3);
			JCheckBox select4 = new JCheckBox("population");
			add(select4);
			JCheckBox select5 = new JCheckBox("landmarks");
			add(select5);
			JCheckBox select6 = new JCheckBox("parks");
			add(select6);
		}
	}

	class GameHandler implements ActionListener
	{
		// When a user clicks on "Go Back" button, takes to the start
		// page. When a user clicks on ok button, takes to a difficulty
		// panel.
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Go Back") )
				cards.show(gameName, "StartPage");
			else if(command.equals("ok") )
				cards.show(gameName, "SelectADifficulty");
		}
	}
}

// Creates a difficulty panel with a grid layout.
class DifficultyPanel extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName4Panel gameName; // Pass in as a parameter.
	private ButtonGroup bg;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private Timer timer;
	private int initialTime;
	private GameData gd;

	public DifficultyPanel(GameData gdIn, GameName4Panel gm4pIn, CardLayout cardsIn)
	{
		gd = gdIn;
		gameName = gm4pIn;
		cards = cardsIn;
		bg = new ButtonGroup();
		easy = new JRadioButton("Easy");
		medium = new JRadioButton("Medium");
		hard = new JRadioButton("Hard");
		
		setLayout(new BorderLayout() );
		JLabel select = new JLabel("Difficulty: ");
		add(select, BorderLayout.NORTH);

		SelectDifficulty sd = new SelectDifficulty();
		add(sd, BorderLayout.CENTER);
		JButton confirm = new JButton("ok");
		add(confirm, BorderLayout.SOUTH);
		PlayGameHandler pgh = new PlayGameHandler();
		confirm.addActionListener(pgh);

		TimerHandler th = new TimerHandler();
		timer = new Timer(120, th);
		initialTime = 120;
		
	}

	class SelectDifficulty extends JPanel
	{
		public SelectDifficulty()
		{
			setLayout(new GridLayout(3, 1) );
			add(easy);
			add(medium);
			add(hard);
			DifficultyHandler dh = new DifficultyHandler();
			easy.addActionListener(dh);
			medium.addActionListener(dh);
			hard.addActionListener(dh);
		}	
	}

	class DifficultyHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String cmd = evt.getActionCommand();
			if(cmd.equals("Easy") )
				initialTime = 120;
			else if(cmd.equals("Medium") )
				initialTime = 90;
			else if(cmd.equals("Hard") )
				initialTime = 60;
		}
	}
	
	class PlayGameHandler implements ActionListener
	{
		// When a user clicks on ok button takes directly to the next panel.
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("ok") )
				cards.show(gameName, "PlayGamePanel");
			
		}
	}

	// Timer should be in text field and use boolean variables. 
	class TimerHandler implements ActionListener
	{
		// Timer varies on difficulty
		public void actionPerformed(ActionEvent evt)
		{
			String cmd = evt.getActionCommand();
			repaint();
			// For 2 minutes for easy
			if(cmd.equals("ok") && cmd.equals("Easy") )
			{
				initialTime = 120;
				cards.show(gameName, "PlayGamePanel");
				
			}

			// For 1.5 minutes for medium
			else if(cmd.equals("ok") && cmd.equals("Medium") )
			{
				initialTime = 90;
				cards.show(gameName, "PlayGamePanel");
			}

			// For 1 minute for hard
			else if(cmd.equals("ok") && cmd.equals("Hard") )
			{
				initialTime = 60;
				cards.show(gameName, "PlayGamePanel");
			}
		}
	}
}

// Array of random scores
class PlayGamePanel extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName4Panel gm4p; // Pass in as a parameter.
	private JButton quit; 
	private Timer timer;
	private int initialTime;
	private int seconds;
	private String message;
	private JTextField answer; // Type answer as textfield
	private JButton start;
	private long begin; // Begins the timer.
	private int[] answerSet; 
	private Image image; // Loads arrays of images
	private int elapsedSeconds;
	private GameData gd;
	
	/// Put an initial value for a timer.
	public PlayGamePanel(GameData gdIn, GameName4Panel gm4pIn, CardLayout cardsIn)
	{
		gd = gdIn;
		elapsedSeconds = 0;
		
		gm4p = gm4pIn;
		cards = cardsIn;
		setLayout(new BorderLayout() );
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		LabelInNorth lin = new LabelInNorth();
		TimerInEast tie = new TimerInEast();
		questions();
		//loadImage();
		GameButtonsSouthAndTextField gbsatf = new GameButtonsSouthAndTextField();
		PanelInCenter pic = new PanelInCenter();
		WestPanel wp = new WestPanel();
		quit = new JButton("Quit");
		// Put all the panels together
		add(lin, BorderLayout.NORTH);
		add(tie, BorderLayout.EAST);
		add(gbsatf, BorderLayout.SOUTH);
		add(pic, BorderLayout.CENTER);
		add(wp, BorderLayout.WEST);

		QuitHandler qh = new QuitHandler();
		quit.addActionListener(qh);
		add(quit, BorderLayout.WEST);
		TimerHandler th = new TimerHandler();
		initialTime = 120;
		timer = new Timer(120, th);
		// timer.start();
		timer.addActionListener(th);
		answer = new JTextField("", 25);
		answerSet = new int[10];
	}

	// Puts a label on top of a game panel and start button is use for a 
	// timer object when user clicks on it.
	class LabelInNorth extends JPanel
	{
		public LabelInNorth()
		{
			JLabel guess = new JLabel("Guess a location");
			add(guess, BorderLayout.NORTH);
			start = new JButton("Start");
			StartGameButtonHandler sgbh = new StartGameButtonHandler();
			start.addActionListener(sgbh);
			add(start, BorderLayout.WEST);
			
		}
	}

	// Starts the timer. originTime - currentTime
	class StartGameButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String cmd = evt.getActionCommand();
			if(cmd.equals("Start") )
			{
				begin = System.currentTimeMillis();
				timer.start();
			}
		}
	}
	
	// Creates the label for a timer.
	class TimerInEast extends JPanel
	{
		public TimerInEast()
		{
			setLayout(new BorderLayout(1, 20) );
			JLabel timer = new JLabel("Timer: ");
			add(timer, BorderLayout.NORTH);
		}

		public void paintComponent(Graphics g)
		{
			boolean running = false;
			running = true;

			int displayMinutes = 0;
			int displaySeconds = 0;

			displayMinutes = elapsedSeconds/60;
			displaySeconds = elapsedSeconds%60;

			g.drawString (displayMinutes + " minutes " + 
				String.format("%d", displaySeconds) + " seconds" , 20, 140 );
			/*
			int tenthSec = 0;
			double secondsDisplay = 0.0;
			int elapsedMinutes = 0;
			// elapsedMinutes - time passed
			secondsDecimal = (int)time + tenthSec / 10;
			secondsDisplay = secondsDecimal % 60; 	
			elapsedMinutes = (int)secondsDecimal / 60;
			
			g.drawString (elapsedMinutes + " minutes " + 
				String.format("%.2f", secondsDisplay) + " seconds" , 20, 140 );
			secondsDisplay--; */
			//repaint();
		}
	}

	// Stores in 10 questions with images. Continents are randomly generated.
	/// Use 2D array for images.
	public void questions()
	{
		String[][] names = new String[2][5];
		int[] num = new int[10];
		int counter = 0;
		String categoryNam = new String("beaches");
		String continentName = new String("");
		int numContinent = 1;
		numContinent = (int)(Math.random() * 5) + 1;
		if(numContinent == 1)
			continentName = "North America";
		else if(numContinent == 2)
			continentName = "South America";
		else if(numContinent == 3)
			continentName = "Europe";
		else if(numContinent == 4)
			continentName = "Asia";
		else if(numContinent == 5)
			continentName = "Oceania";
		else if(numContinent == 6)
			continentName = "Africa";

		for(int numQuestions = 0; numQuestions < 10; numQuestions++)
		{
			num[numQuestions] += 1;
		}
		counter++;
		Image[] images = new Image[num.length];
		//images = new Image[names.length];
		for(int i = 0; i < names.length; i++)
		{
			//images[i] = loadImages(names[i]);	// finish this line
			if(numContinent == 1)
			{
				if(categoryNam.equals("cities") )
				{
					names = new String[][] {{"sf.jpg", "ny.jpg", "nfalls.jpg", "la.jpg","sd.jpg"},
						{"nvalley.jpg", "lv.jpg", "tij.jpg", "ch.jpg", "sea.jpg"}};
				}
				else if(categoryNam.equals("capital cities") )
				{
					names = new String[][] {{"wdc.jpg", "ott.jpg", "mcity.jpg", "hav.jpg", "sanjose.jpg"},
						{"te.jpg", "bel.jpg", "pap.jpg", "san.jpg", "pc.jpg"}};
				}
				
				else if(categoryNam.equals("beaches") )
				{
					names = new String[][] {{"bbeach.jpg", "bsur.jpg", "cmay.jpg", "hbeach.jpg", "lbeach.jpg"},
						 {"mbeach,jpg", "mibeach.jpg", "monbeach.jpg", "scbeach.jpg", "higgs.jpg"}};
				}
				else if(categoryNam.equals("population") )
				{
					names = new String[][] {{"ca.jpg", "hawaii.jpg", "texas.jpg", "or.jpg", "wash.jpg"},
						{"nymap.jpg", "wyom.jpg", "colo.jpg", "al.jpg", "can.jpg"}};
				}
				else if(categoryNam.equals("landmarks") )
				{
					names = new String[][] {{"gatewayarch.jpg", "mtrushmore.jpg", "sliberty.jpg", "whouse.jpg", "gbridge.jpg"},
						{"hdam.jpg", "lbell.jpg", "aisland.jpg", "dworld.jpg", "cgate.jpg"}};
				}
				else if(categoryNam.equals("parks") )
				{
					names = new String[][] {{"ynp.jpg", "yellownp.jpg", "znp.jpg", "gcnp.jpg", "gtnp.jpg"},
						{"snp.jpg", "dvnp.jpg", "mtrainer.jpg", "lcnp.jpg", "dtnp.jpg"}};
				}
				System.out.println(categoryNam);
			 }
			else if(numContinent == 2)
			{
				if(categoryNam.equals("cities") )
				{
					names = new String [][] {{"rio.jpg", "ush.jpg", "sanpedro.jpg", "elarg.jpg", "salta.jpg"},
						{"santafe.jpg", "cali.jpg", "santacruz.jpg", "boavista.jpg", "surce.jpg"}};
				}
				else if(categoryNam.equals("capital cities") )
				{
					names = new String[][] {{"bas.jpg", "ba.jpg", "lima.jpg", "sanchile.jpg", "monte.jpg"},
						{"bog.jpg", "quito.jpg", "cayenne.jpg", "caracas.jpg", "gtown.jpg"}};
				}
				else if(categoryNam.equals("beaches") )
				{
					names = new String[][] {{"copacabana.jpg", "lopesbeach.jpg", "tortuga.jpg", "playa.jpg", "ica.jpg"},
						{"valparaíso.jpg", "concha.jpg", "sanandrés.jpg", "prainha.jpg", "itamambuca.jpg"}};
				}
				else if(categoryNam.equals("population") )
				{
					names = new String[][] {{"brazil.jpg", "chile.jpg", "bolivia.jpg", "peru.jpg", "columbia.jpg"},
						{"venezuela.jpg", "ecuador.jpg", "guyana.jpg", "suriname.jpg", "paraguay.jpg"}}; 
				}
				else if(categoryNam.equals("landmarks") )
				{
					names = new String[][] {{"sloaf.jpg", "mp.jpg", "if.jpg", "elmorro.jpg", "casapueblo.jpg"},
						{"hp.jpg", "petrohué.jpg", "tierradelfuego.jpg", "tutelarfigures.jpg", "lrcemetery.jpg"}};
				}
				else if(categoryNam.equals("parks") )
				{
					names = new String[][] {{"lppark.jpg", "rnpark.jpg", "tpark.jpg", "itatiaia.png", "cajas.jpg"},
						{"cotopaxi.jpg", "machalilla.jpg", "desertwithwater.jpg", "huascaran.jpg", "andeannp.jpg"}};
				}
			}
			//~ else if(numContinent == 3)
			//~ {
				//~ if(categoryNam.equals("cities") )
				//~ {
					//~ names[i] = {"ven.jpg", "stpeter.jpg", "barcelona.jpg",
						//~ "naples.jpg", "bath.jpg", "lyb.jpg", "cork.jpg",
						//~ "graz.jpg", "munich.jpg", "krakow.jpg"};
				//~ }
				//~ else if(categoryNam.equals("capital cities") )
				//~ {
					//~ names[i] = {"dublin.jpg", "london.jpg", "paris.jpg",
						//~ "vienna.jpg", "athens.jpg", "sofia.jpg", "rome.jpg",
						//~ "moscow.jpg", "minsk.jpg", "helsinki.jpg"};
				//~ }
				//~ else if(categoryNam.equals("beaches") )
				//~ {
					//~ names[i] = {"myrtos.jpg", "fibeach.jpg", "kbeach.jpg",
						//~ "hbeachnorway.jpg", "westbeach.jpg", "bbay.jpg",
						//~ "for.jpg", "costabeach.jpg", "sanbeach.jpg", "smal.jpg"};
				//~ }
				//~ else if(categoryNam.equals("population") )
				//~ {
					//~ names[i] = {"sland.jpg", "germany.jpg", "uk.jpg",
						//~ "ireland.jpg", "finland.jpg", "norway.jpg",
						//~ "austria.jpg", "russia.jpg", "bulgaria.jpg", "greece.jpg"};
				//~ }
				//~ else if(categoryNam.equals("landmarks") )
				//~ {
					//~ names[i] = {"sh.jpg", "ncastle.jpg", "bpalace.jpg",
						//~ "alps.jpg", "lmeseum.jpg", "col.jpg", "bigben.jpg",
						//~ "tbridge.jpg", "atom.jpg", "etower.jpg"};
				//~ }
				//~ else if(categoryNam.equals("parks") )
				//~ {
					//~ names[i] = {"balloonpark.jpg", "bnp.jpg", "enp.jpg",
						 //~ "knp.jpg", "tnp.jpg", "cnp.jpg", "gnp.jpg", "pcnp.jpg",
						 //~ "cnkscotland.jpg", "cairngorms.jpg"};
				//~ }

			//~ }

			//~ if(numContinent == 4)
			//~ {
				//~ if(categoryNam.equals("cities") )
				//~ {
					//~ names[i] = {"shanghai.jpg", "dubai.jpg", "mumbai.jpg",
						//~ "istanbul.jpg", "lhasa.jpg", "hk.jpg", "macau.jpg",
						//~ "kashgar.jpg", "singapore.jpg", "sr.jpg"};
				//~ }
				//~ else if(categoryNam.equals("capital cities") )
				//~ {
					//~ names[i] = {"beijing.jpg", "tokyo.jpg", "astana.jpg",
						//~ "seoul.jpg", "kualalumpur.jpg", "pyongyang.jpg",
						//~ "abudhabi.jpg", "ankara.jpg", "kathmandu.jpg", "bangkok.jpg"};
				//~ }
				//~ else if(categoryNam.equals("beaches") )
				//~ {
					//~ names[i] = {"goa.jpg", "phuket.jpg", "pianemo.jpg",
						//~ "pinkbeach.jpg", "matakingisland.jpg", "halongbay.jpg",
						//~ "kelingking.jpg", "baisao.jpg", "pompom.jpg",
						//~ "whitebeach.jpg"};
				//~ }
				//~ else if(categoryNam.equals("population") )
				//~ {
					//~ names[i] = {"india.jpg", "china.jpg", "southkorea.jpg",
						//~ "thailand.jpg", "northkorea.jpg", "burma.jpg",
						//~ "nepal.jpg", "arab.jpg", "turkey.jpg", "kazakhstan.jpg"};
				//~ }
				//~ else if(categoryNam.equals("landmarks") )
				//~ {
					//~ names[i] = {"greatwall.jpg", "tajmahal.jpg", "wuhantower.jpg",
						//~ "mteverest.jpg", "fuji.jpg", "gobi.jpg", "mtd.jpg",
						//~ "angkorwat.jpg", "petra.jpg", "fbcity.jpg"};
				//~ }
				//~ else if(categoryNam.equals("parks") )
				//~ {
					//~ names[i] = {"skidubai.jpg", "hkdisney.jpg", "olypark.jpg",
						//~ "waterpark.jpg", "waterpark2.jpg", "waterpark3.jpg",
						//~ "fujipark.jpg", "cutepark.jpg", "animalpark.jpg",
						//~ "inbeach.jpg"};
				//~ }
			/*else if(numContinent == 5)
			{
				if(categoryNam.equals("cities") )
				{
					names[i] = {"sydney.jpg", "gc.jpg", "akland.jpg", "rot.jpg",
						"cchurch.jpg", "dune.jpg", "qtown.jpg", "mel.jpg",
						"perth.jpg", "pmoresby.jpg"};
				}
				else if(categoryNam.equals("capital cities") )
				{
					names[i] = {"canberra.jpg", "wellington.jpg", "noumea.jpg",
						"alofi.jpg", "papeete.jpg", "suva.jpg", "pagopago.jpg",
						"adamstown.jpg", "nuku'alofa.jpg", "majuro.jpg"};
				}
				
				else if(categoryNam.equals("beaches") )
				{
					names[i] = {"bondibeach.jpg", "fmbeach.jpg", "nmbeach.jpg",
							"spbeach.jpg", "manlybeach.jpg", "whitehavenbeach.jpg",
							"rbay.jpg", "seventyfivebeach.jpg", "moobeach.jpg",
							"cbeach.jpg"};
				}
				
				else if(categoryNam.equals("population") )
				{
					names[i] = {"aust.jpg", "nz.jpg", "fiji.jpg", "niue.jpg",
						"newcal.jpg", "tah.jpg", "pisland.jpg", "guam.jpg",
						"masisland.jpg", "tonga.jpg"};
				}
				else if(categoryNam.equals("landmarks") )
				{
					names[i] = {"gbreef.jpg", "uluru.jpg", "sohouse.jpg",
						"pinklake.jpg", "lava.jpg", "jellylake.jpg", "hut.jpg",
						"bblagoon.jpg", "mountain.jpg", "fisland.jpg"};
				}
				else if(categoryNam.equals("parks") )
				{
					names[i] = {"mtcook.jpg", "bmount.jpg", "cbay.jpg",
						"cnationalpark.jpg", "dtnpark.jpg", "fnp.jpg",
						"fnpark.jpg", "kakadu.jpg", "nambung.jpg", "rockpark.jpg"};
				}

			}
			else if(numContinent == 6)
			{
				if(categoryNam.equals("cities") )
				{
					names[i] = {"casa.jpg", "marr.jpg", "johnaburg.jpg",
						"cot.jpg", "brazzaville.jpg", "bamako.jpg", "kumasi.jpg",
						"mombasa.jpg", "nouakchott.jpg", "bujumbura.jpg"};
				}
				else if(categoryNam.equals("capital cities") )
				{
					names[i] = {"cairo.jpg", "rabat.jpg", "lagos.jpg",
						"deer.jpg", "vic.jpg", "tunis.jpg", "juba.jpg",
						"lome.jpg", "nairobi.jpg", "tripoli.jpg"};
				}
				else if(categoryNam.equals("beaches") )
				{
					names[i] = {"misland.jpg", "qa.jpg", "anse.jpg", "lamua.jpg",
						"t-a-b.jpg", "portjohn.jpg", "lisland.jpg", "marie.jpg",
						"currbeach.jpg", "skcoast.jpg"};
				}
				else if(categoryNam.equals("population") )
				{
					names[i] = {"egypt.jpg", "morocco.jpg", "safrica.jpg",
						"tanzania.jpg", "nigeria.jpg", "chad.jpg", "mali.jpg",
						"niger.jpg", "cameroon.jpg", "congo.jpg"};
				}
				else if(categoryNam.equals("landmarks") )
				{
					names[i] = {"mtkili.jpg", "baobab.jpg", "pyramid.jpg",
						"tmountain.jpg", "tim.jpg", "cocodemer.jpg", "vicfalls.jpg",
						"namib.jpg", "nriver.jpg", "delta.jpg"};
				}
				else if(categoryNam.equals("parks") )
				{
					names[i] = {"serengeti.jpg", "etosha.jpg", "kruger.jpg",
						"laken.jpg", "virunga.jpg", "ngorongoro.jpg", "amboseli.jpg",
						"hwange.jpg", "lakemanyara.jpg", "namib-n.jpg"};
				}
			}*/
			System.out.println(names[i][i]);
			images[i] = loadImages(names[i][i]);
		} 
	}
	
	public Image loadImages(String pictName)
	{
		image = null;
		try
		{
			image = ImageIO.read(new File(pictName) );
		}
		catch(IOException e)
		{
			System.err.println("\n\n\n" + pictName + "can't be found.\n\n\n");
			e.printStackTrace();
		}
		return image;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 150, 120, 300, 300, this);
		repaint();
	}
	
	// Instantiates hint and enter button and textfield to let a user
	// type an answer.
	class GameButtonsSouthAndTextField extends JPanel
	{
		public GameButtonsSouthAndTextField()
		{
			JButton hint = new JButton("hint");
			add(hint, BorderLayout.WEST);
			JTextField answer = new JTextField("", 25);
			add(answer, BorderLayout.CENTER);
			JButton enter = new JButton("Enter");
			add(enter, BorderLayout.EAST);
			EnterHandler eh = new EnterHandler();
			enter.addActionListener(eh);
			
		}

		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
		
			if(command.equals("Enter"))
			{
				answerSet[data.getCorrectAnswer()].setBackground(Color.GREEN);
				for(int i = 0; i < answerSet.length; i++)
				{
					if(answerSet[i].isSelected())
					{
						if(i != data.getCorrectAnswer())
						{
							answerSet[i].setBackground(Color.RED);
						}
						else
						{
							data.addOneToCorrectCount();
						}
					}
				}
				resetQuestion();
			}
		}
		public void resetQuestion ( )
		{
			data.grabQuestionFromFile();
			questionArea.setText(data.getQuestion());
			for(int i = 0; i < answerSet.length; i++)
			{
				answerSet[i].setEnabled(true);
				answerSet[i].setBackground(new Color(230, 230, 230));
			}
		}
	}

	// Question's label is located in a center top panel.
	class PanelInCenter extends JPanel
	{
		public PanelInCenter()
		{
			JLabel question = new JLabel("Question: " /* + questions*/);
			add(question); 
		}
	}

	// Quit button is located in WestPanel.
	class WestPanel extends JPanel
	{
		public WestPanel()
		{
			quit = new JButton("Quit");
			add(quit, BorderLayout.NORTH);
		}
	}

	// Makes quit button works.
	class QuitHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String cmd = evt.getActionCommand();
			if(cmd.equals("Quit") )
				cards.show(gm4p, "StartPage");
		}
	}

	// Creates a timer for each difficulty level that a user clicks and use
	// stop method. 
	class TimerHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			elapsedSeconds++;
			repaint();
			if(elapsedSeconds == 30 && command.equals("Easy") )
				timer.stop();
			else if(elapsedSeconds == 20 && command.equals("Medium"))
				timer.stop();
			else if(elapsedSeconds == 10 && command.equals("Hard") )
				timer.stop();
		}
	}
}

// D & I labels and buttons in the constructer. 
class GameResultsPanel extends JPanel
{
	private GameName4Panel gm4p; // Pass in as a parameter.
	private CardLayout cards; // Pass in as a parameter.
	private int scores;
	private Scanner inFile;
	private String scoreFile;
	private String value;
	private PrintWriter pw;
	private String updateScoreFile;
	private String [] answerSet;
	private int correctAnswer;
	private boolean [] chosenQuestions;
	private int questionCount;
	private int correctCount, lastGameCorrectCount;
	private boolean hasBeenAdded;
	private GameData gd;

	/// Need File IO to store in scores.
	// Add all the components.
	// 5 points for each answer
	public GameResultsPanel(GameData gdIn, GameName4Panel gm4pIn, CardLayout cardsIn)
	{
		gd = gdIn;
		gm4p = gm4pIn;
		cards = cardsIn;
		scores = 1;
		setLayout(new GridLayout(5, 1) );
		scores++;
		JLabel numScores = new JLabel("Number of scores earned: " + scores);
		add(numScores);
		JLabel correctAns = new JLabel("Correct Answer: ");
		add(correctAns);
		JLabel wrongAns = new JLabel("Wrong Answer: ");
		add(wrongAns);
		JButton playAgain = new JButton("Play Again");
		add(playAgain);
		JButton exit = new JButton("Exit");
		add(exit);
		inFile = null;
		scoreFile = new String("scoresandquestions.txt");
		openFile();
		pw = null;
		value = "";
		updateScoreFile = new String("updateScores.txt");
		createFile();
		createScores();
		hasBeenAdded = false;
		// Add these buttons to an instance of a handler class to
		// make them responsive.
		GameResultsHandler grh = new GameResultsHandler();
		playAgain.addActionListener(grh);
		exit.addActionListener(grh);

		resetAll();
	}

	public void resetAll()
	{
		int correctCount;
		answerSet = new String[1];
		question = "";
		for(int i = 0; i < answerSet.length; i++)
		{
			answerSet[i] = "";
		}
		int correctAnswer = -1;
		boolean chosenQuestions = new boolean[10];
	}
	
	// Opens a file.
	public void openFile()
	{
		File score = new File(scoreFile);
		try
		{
			inFile = new Scanner(score);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("\n\n\n" + scoreFile + " can't be opened.\n\n\n");
			System.exit(1);
		}
	}

	// Appends/creates a file.
	public void createFile()
	{
		File updateScore = new File(updateScoreFile);
		try
		{
			pw = new PrintWriter( new FileWriter(updateScore, true) );
		}

		catch(IOException e)
		{
			System.err.println("\n\n\n" + updateScoreFile + " can't be created.\n\n\n");
			System.exit(2);
		}
	}

	// Appends scores after every game been played, closes a printwriter,
	// and file.  
	public void createScores()
	{
		String result = "";
		boolean hasBeenAdded = false;
		/*while(inFile.hasNext()) 
		{
			String line = inFile.nextLine();
			if(!hasBeenAdded && Integer.parseInt("" + line.charAt(line.indexOf("/") - 1)) <= lastGameCorrectCount)
			{
				result += lastGameCorrectCount + "/50\n";
				hasBeenAdded = true;
			}
			result += line + "\n";
		}*/

		//inFile.close();

		while(inFile.hasNext() )
		{
			value = inFile.next();
			pw.append(value);
		}
		inFile.close();
		pw.close();
	}
	
	// When a user presses play again button, it takes to game page.
	// When a user presses exit button, it exits the game.
	class GameResultsHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String commands = evt.getActionCommand();
			if(commands.equals("Play Again") )
				cards.show(gm4p, "GamePage");
			else if(commands.equals("Exit") )
				System.exit(2);
		}
	}
}
