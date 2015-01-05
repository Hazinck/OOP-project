package oop.voetbalmanager.view;

import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoadGamePanel extends JPanel{
	
	private ArrayList<String> saveFiles = new ArrayList<String>();
	private ArrayList<JButton> loadButtons = new ArrayList<JButton>();
	
	public LoadGamePanel(){
		listFilesForFolder();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(String s: saveFiles){
			JButton load = new JButton(s);
			loadButtons.add(load);
		    add(load);
		}
	}
	
	public void listFilesForFolder() {
		File folder = new File( System.getProperty("user.dir") +"/saved");
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder();
	        } else {
	            String saveFile = fileEntry.getName();
	            String saveFileNaam = saveFile.replaceAll(".xml", "");
	            saveFiles.add(saveFileNaam);
	        }
	    }
	}

	/**
	 * @return the loadButtons
	 */
	public ArrayList<JButton> getLoadButtons() {
		return loadButtons;
	}

	/**
	 * @return the saveFiles
	 */
	public ArrayList<String> getSaveFiles() {
		return saveFiles;
	}
}

