package oop.voetbalmanager.spel2D;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.RNG;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.Tabs;
import oop.voetbalmanager.view.ViewFrame;

public class VeldPanel extends JPanel {
	private static int frameWidth = ViewFrame.getFrameWidth();
	private static int frameHeight = ViewFrame.getFrameHeight();
	private JButton terugButton = new JButton("Terug");
	private JButton pauseResume = new JButton("Pause");
	private GameRunnable gr;
	
	  public VeldPanel(ViewFrame viewFrame) {
		  setLayout(new BorderLayout());;
		  viewFrame.getControlPanel().add(this);  

		Wedstrijdteam team1 = User.getWteam();
		Wedstrijdteam team2 = Bot.getWteam();
		Ball ball = new Ball();
		
		final GamePanel gp = new GamePanel(ball);
		
		for(int i = 0; i < team1.getWSpelers().length; i++){//team1.getSpelerList()
			Speler s1 = team1.getWSpelers()[i];
			Player p1 = new Player(s1, 1, i, ball, team1, gp);
			p1.getSpriteObj().setxSubSprite(RNG.getalTot(15));
			gp.addPlayer(p1, 1);
			
			Speler s2 = team2.getWSpelers()[i];
			Player p2 = new Player(s2, 2, 10+i, ball, team2, gp);
			p2.getSpriteObj().setxSubSprite(RNG.getalTot(15));
			gp.addPlayer(p2, 2);
		}
		
//		Position position1 = new Position(gp.getPlayerListTeam1(), team1.getOpstelling(), 1);
//		position1.setPosition();
//		
//		Position position2 = new Position(gp.getPlayerListTeam2(), team2.getOpstelling(), 2);
//		position2.setPosition();
		
		gr = new GameRunnable(gp, this);
	//	thread = new Thread(gr);
		gr.moveCam();
		JPanel buttonPanel = new JPanel();
		final JButton startButton = new JButton("Start");
	    startButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	    	//  thread.start();
	    	  gr.run();
	    	  startButton.setEnabled(false);
	      }
	    });
	    buttonPanel.add(startButton);
		buttonPanel.add(pauseResume);
	    buttonPanel.add(terugButton);
		
	    add(buttonPanel, BorderLayout.SOUTH);
		
		  
		add(gp);
	  }
	  
	  public void addButton(Container c, String title, ActionListener listener) {
		    JButton button = new JButton(title);
		    c.add(button);
		    button.addActionListener(listener);
		  }

	/**
	 * @return the frameWidth
	 */
	public static int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * @return the frameHeight
	 */
	public static int getFrameHeight() {
		return frameHeight;
	}

	public void showThis(JPanel tabsPanel){
		tabsPanel.setVisible(false);
		this.setVisible(true);
	}
	

	/**
	 * @return the button
	 */
	public JButton getButton() {
		return terugButton;
	}

	/**
	 * @return the terugButton
	 */
	public JButton getTerugButton() {
		return terugButton;
	}

	/**
	 * @return the gr
	 */
	public GameRunnable getGr() {
		return gr;
	}

	/**
	 * @return the pauseResume
	 */
	public JButton getPauseResume() {
		return pauseResume;
	}

	
}
