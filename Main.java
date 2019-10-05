import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {

	// frame
	JFrame frame = new JFrame();
	JFrame d_frame = new JFrame();  // description frame

	// panel 
	JPanel controlPanel = new JPanel();
	GamePanel gamePanel;
	JPanel coverPanel;
	DescriptionPanel descriptionPanel;

	// button
	JButton start = new JButton("Start");
	JButton end = new JButton("End");
	JButton description = new JButton("Description");

	// layeredPane
	JLayeredPane lPane = new JLayeredPane();

	// label
	JLabel coffeeLabel = new JLabel();

	// Listener
	//ClockListener clockListener;

	// Timer
	Timer goAnime = new Timer(-1, new TimerListener());

	// Random
	Random random = new Random();

	int gamePanelWidth, gamePanelHeight;
	/*int y0=750;
	int x= (int) (Math.random()*700+1), y=0;
	int x1= (int) (Math.random()*700+1);*/

	private final int INIT_POS_X = 500;
	private final int INIT_POS_Y = 500;

	private int X =INIT_POS_X;
	private final int MOV_POS_X = 10;

	private int score = 0;
	private final int CS_MARGIN = 100;  		// coffee, stone
	private final int B_MARGIN = 180;  		// bigstone
	private final int NEW_INTERVAL= 2;	// coffee, stone 나타나는 주기
	private final int BIG_ATTACKER_INTERVAL= 10;	// 큰 공격자가 나타나는 주기 (토글 방식)
	private final int SPEED = 70;			// animation speed
	private final int STEPS = 10;			// 객체들이 움직일 수 있는 픽셀 수
	private final int WIDTH = 1000;
	private final int HEIGHT = 1000;
	private final int COFFEE_INTERVAL = 2;
	private final int STONE_INTERVAL = 2;
	private final int BIG_STONE_INTERVAL = 10;


	private final String BACKGROUND = "background.png";
	private final String PLAY_BACKGROUND = "playing_background.jpg";
	private final String PLAY_CH = "Penguin.png";
	private final String COFFEE1 = "coffee1.png";
	private final String COFFEE2 = "coffee2.png";
	private final String COFFEE3 = "coffee3.png";
	private final String STONE = "stone.png";
	private final String BIGSTONE = "bigstone.jpg";
	private final String D_IMAGE = "stone.png";

	
	private String[] item_list = {COFFEE1, COFFEE2, COFFEE3, STONE};
	

	private final String START_SOUND = "backgroundSound.wav";  
	//private AudioClip backgroundSound;
	ArrayList<Direction_CS> fallingList;
	//private AudioClip boomSound;
	static String playerName;
	//DirectionListener KeyListener;


	// 버튼 토글을 위한 비트 연산에 사용될 상수들
	//private final int START = 1;
	//private final int DESCRIPTION = 2;
	//private final int END = 4;


	public static void main(String[] args)
	{
		playerName = JOptionPane.showInputDialog("이름을 입력해주세요: ");
		new Main().go();
	}


	public void go()
	{
		frame.setSize(900,900);
		frame.setTitle("Penguin Loves Coffee");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		controlPanel.add(start);
		controlPanel.add(end);
		controlPanel.add(description);
		controlPanel.add(new JLabel("   	Player : 	"));
		controlPanel.add(new JLabel(playerName));

		coverPanel = new CoverPanel();
		coverPanel.setBounds(0, 0, WIDTH, HEIGHT);
		gamePanel = new GamePanel();
		gamePanel.setBounds(0,0, WIDTH, HEIGHT);
		descriptionPanel = new DescriptionPanel();
		descriptionPanel.setBounds(0, 0, WIDTH, HEIGHT);

		//gamePanel.add(coffeeLabel);

		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.add(coverPanel, BorderLayout.CENTER);
		frame.add(lPane);
		frame.add(lPane, BorderLayout.CENTER);

		d_frame.add(descriptionPanel, BorderLayout.CENTER);

		lPane.add(gamePanel, new Integer(0));
		lPane.add(coverPanel, new Integer(1));

		gamePanelWidth = gamePanel.getWidth()-50;
		gamePanelHeight = gamePanel.getHeight()-100;

		// 시간 디스플레이, 객체의 움직임을 자동화 하기 위한 타이머들 
		//clockListener = new ClockListener();
		//goClock = new Timer(1000, clockListener);			// 시간을 초단위로 나타내기 위한 리스너
		//goAnime = new Timer(SPEED, new AnimeListener());	// 그림의 이동을 처리하기 위한 리스너


		// set up button listener
		start.addActionListener(new StartListener());
		end.addActionListener(new EndListener());
		description.addActionListener(new DescriptionListener());
		gamePanel.addKeyListener(new DirectionListener());
//		prepareList();

		//player = new PosImageIcon(getClass().getResource(PLAY_CH), ,0,gamePanelWidth, gamePanelHeight);

		try 
		{
			// backgroundSound = JApplet.newAudioClip(new URL("file", "localhost","/res/start.wav"));
			// boomSound = JApplet.newAudioClip(new URL("file", "localhost","/res/boom.wav"));
			// 위의 방법은 상대경로를 나타내지 못하는 방법이어서, jar파일로 배포판을 만들때 경로를 찾지 못하는
			// 문제가 생김. 따라서 getClass()를 사용하여 상대적인 URL을 구하는 방법을 아래처럼 사용해야 함
			// 여기에서 root가 되는 폴더는 현재 이 프로그램이 수행되는 곳이니 같은 레벨에 넣어주어야 함
			//			backgroundSound = JApplet.newAudioClip(getClass().getResource(START_SOUND));
			;
		}
		catch(Exception e)
		{
			System.out.println("음향 파일 로딩 실패");
		}

		/*	for(int i = 0; i<800; i++)
		{
			y++;
			gamePanel.repaint();

			try
			{
				Thread.sleep(10);
			}catch(Exception ex) {}
		}*/
	}
	//(int)(Math.random()*WIDTH-50)

	void prepareList() {
		int rand_item_n = 0;
		fallingList = new ArrayList<>();
		
		rand_item_n = (int) (Math.random()*4);
		fallingList.add(new Direction_CS(item_list[rand_item_n], (int) (Math.random()*200),0,CS_MARGIN,CS_MARGIN));
		rand_item_n = (int) (Math.random()*4);
		fallingList.add(new Direction_CS(item_list[rand_item_n], (int) (Math.random()*200+200),0,CS_MARGIN,CS_MARGIN));
		rand_item_n = (int) (Math.random()*4);
		fallingList.add(new Direction_CS(item_list[rand_item_n], (int) (Math.random()*200+400),0,CS_MARGIN,CS_MARGIN));
		rand_item_n = (int) (Math.random()*4);
		fallingList.add(new Direction_CS(item_list[rand_item_n], (int) (Math.random()*200+600),0,CS_MARGIN,CS_MARGIN));
	}
	
	





	class CoverPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Image image = new ImageIcon(getClass().getResource(BACKGROUND)).getImage();
			g.drawImage(image,5,100,this);
		}
	}

	class GamePanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{

			Image playImage = new ImageIcon(getClass().getResource(PLAY_BACKGROUND)).getImage();
			g.drawImage(playImage, 0, 0, gamePanelWidth,gamePanelHeight,this);

			Image penguinImage = new ImageIcon(getClass().getResource(PLAY_CH)).getImage();
			g.drawImage(penguinImage, X, INIT_POS_Y, 100, 150,this);

			for(Direction_CS cs : fallingList) {
				cs.draw(g); 
			}


			//Image penguinImage = new ImageIcon(getClass().getResource(PLAY_CH)).getImage();
			//g.drawImage(penguinImage, 0, 500, 100, 150, this);

			/*
			Image coffee1Image = new ImageIcon(getClass().getResource(COFFEE1)).getImage();
			Image coffee2Image = new ImageIcon(getClass().getResource(COFFEE2)).getImage();
			Image play_penguin = new ImageIcon(getClass().getResource(PLAY_CH)).getImage();

			g.drawImage(coffee1Image, x, y, CS_MARGIN,CS_MARGIN,this);
			g.drawImage(coffee2Image, x1, y, CS_MARGIN, CS_MARGIN, this);
			g.drawImage(play_penguin, x, y0, 100, 150, this);
			 */

		}

	}


	class DescriptionPanel extends JPanel
	{
		public void paintComponent(Graphics g) {
			Image description = new ImageIcon(getClass().getResource(D_IMAGE)).getImage();
			g.drawImage(description, 0, 0, this);
		}
	}


	// ActionPerformed
	class StartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			lPane.setLayer(gamePanel, 2);
			gamePanel.setFocusable(true);
			gamePanel.requestFocus();
			gamePanel.repaint();

			//			backgroundSound.play();
			goAnime.start();
			prepareList();


		}
	}

	class DescriptionListener implements ActionListener  // playing game 일시 중지 추가해야함!
	{
		public void actionPerformed(ActionEvent e)
		{
			d_frame.setSize(700,700);
			d_frame.setTitle("Description");
			d_frame.setVisible(true);
		}
	}

	class EndListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			finishGame();
		}
	}

	class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			falling();
		}
	}
	
	void falling()
	{
		
		for(int i =0; i<fallingList.size();i++)
		{
			if(((X-100) < fallingList.get(i).pX && fallingList.get(i).pX < (X+200)) && INIT_POS_Y==fallingList.get(i).pY+100)
			{
				String _name = fallingList.get(i).name;
				switch(_name)
				{
				case "COFFEE1" : score++; break;
				case "COFFEE2" : score++; break;
				case "COFFEE3" : score++; break;
				case "STONE" : finishGame(); break;
				}
			}
			
			fallingList.get(i).pY++;
			if(fallingList.get(i).pY==700)
			{
				fallingList.remove(i);
				
				int rand_item_n = 0;
				rand_item_n = (int) (Math.random()*4);
//				fallingList[i] = (new Direction_CS(item_list[rand_item_n], (int) (Math.random()*200),0,CS_MARGIN,CS_MARGIN));
				fallingList.add(i, (new Direction_CS(item_list[rand_item_n], (int) (Math.random()*200 + i*200),0,CS_MARGIN,CS_MARGIN)));
			}
		}

		//WIDTH = frame.getWidth();
		//HEIGHT = frame.getHeight();

		frame.repaint();
	}



	// finish game 
	private void finishGame()
	{
		//		backgroundSound.stop();
		gamePanel.setFocusable(false);

	}

	class DirectionListener implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			int keycode = e.getKeyCode();
			switch(keycode)
			{
			case KeyEvent.VK_LEFT: 
				System.out.println("pos_x: " + X);
				X -= MOV_POS_X;
				if(X<=0)
					X=0;
				break;

			case KeyEvent.VK_RIGHT: 
				System.out.println("pos_x: " + X);
				X += MOV_POS_X;
				if(X>=800)
					X=800;
				break;
			}

		}

		public void keyTyped(KeyEvent event) {}
		public void keyReleased(KeyEvent event) {}

	}	
	
}