package oop.voetbalmanager.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Driver;
import oop.voetbalmanager.model.User;

public class SaveDialog {
	private static JOptionPane LoadGameDialog = new JOptionPane();
	private static JOptionPane gameOverDialog = new JOptionPane();
	private static int gameOverClosed = 0;
	private static int save = 0;
	 
	public static String saveOpstellingPopup() {
		String naam = "";
		JTextField naamField = new JTextField("");
	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    panel.add(new JLabel("Noem je opstelling:"));
	    panel.add(naamField);
	    int result = JOptionPane.showConfirmDialog(null, panel, "Nieuwe opstelling",
	    		JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    if (result == JOptionPane.OK_OPTION) {
	    	//System.out.println("Niewe opstelling: " + naam);
	    	naam = naamField.getText();
	    	if(naam.equals("")){
	    		JOptionPane.showMessageDialog(null,
	    			    "Naamveld is leeg!",
	    			    "Opstelling is niet opgeslagen.",
	    			    JOptionPane.ERROR_MESSAGE);
	    	}
	    } else {
	        System.out.println("Cancelled");
	    }
	    
	    
	    return naam;
	}
	
	public static String saveGamePopup() {
		String saveFile = "";
		save = JOptionPane.showConfirmDialog(
			    null,
			    "Save game?",
			    "Quit",
			    JOptionPane.YES_NO_CANCEL_OPTION);
		if (save == JOptionPane.YES_OPTION) {
			if( !Driver.path.equals(System.getProperty("user.dir") + "/database.xml") ){
				saveFile = Driver.path;
			}else{
				Date date = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
				saveFile = System.getProperty("user.dir") + "/saved/"+User.getNaam()+" "+sdf.format(date)+".xml";
			}
		} else if(save == JOptionPane.CANCEL_OPTION){
			saveFile = "cancel";
		}
		
		return saveFile;
	}
	
	public static void loadGamePopup(LoadGamePanel lgp) {
		JScrollPane scrollPane = new JScrollPane(lgp);  
		scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
		LoadGameDialog.showMessageDialog(null, scrollPane, "LoadGame",  
		                                       JOptionPane.CLOSED_OPTION);
	}
	
	public static void gameOverPopup(Competition comp) {
		
		String msg = "Game Over";
		Icon endGame = new ImageIcon("images/loser.png");;
		if(User.getTeam().equals(Divisie.getTeamList().get(0))){
			msg = "Jij hebt gewonnen!!!!!!!";
			endGame = new ImageIcon("images/kampioen.png");
		}
		JScrollPane scrollPane = comp.getRankPane();  
		scrollPane.setPreferredSize( new Dimension( 421, 650 ) );
		gameOverClosed = gameOverDialog.showConfirmDialog(null, scrollPane, msg,  
		                                       JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, endGame);
		
	}
	
	/**
	 * @return the loadGameDialog
	 */
	public static JOptionPane getLoadGameDialog() {
		return LoadGameDialog;
	}

	/**
	 * @return the gameOverDialog
	 */
	public static JOptionPane getGameOverDialog() {
		return gameOverDialog;
	}

	/**
	 * @return the gameOverClosed
	 */
	public static int getGameOverClosed() {
		return gameOverClosed;
	}

	/**
	 * @return the save
	 */
	public static int getSave() {
		return save;
	}
}