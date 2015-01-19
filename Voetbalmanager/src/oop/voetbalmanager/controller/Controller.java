package oop.voetbalmanager.controller;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Driver;
import oop.voetbalmanager.model.Opstelling;
import oop.voetbalmanager.model.Positie;
import oop.voetbalmanager.model.RNG;
import oop.voetbalmanager.model.Spel;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;
import oop.voetbalmanager.model.XMLwriter;
import oop.voetbalmanager.spel2D.VeldPanel;
import oop.voetbalmanager.view.Competition;
import oop.voetbalmanager.view.Home;
import oop.voetbalmanager.view.LoadGamePanel;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.NewGamePanel;
import oop.voetbalmanager.view.PandS;
import oop.voetbalmanager.view.SaveDialog;
import oop.voetbalmanager.view.Tabs;
import oop.voetbalmanager.view.TeamPanel;
import oop.voetbalmanager.view.ViewFrame;


public class Controller {
	private ViewFrame viewFrame; 
	private Login l;
	public Tabs tabs;
	private Home home;
	private TeamPanel teamPanel;
	private Competition comp;
	private PandS ps;
	private ArrayList<String> ranglijst = new ArrayList<String>();
	private XMLwriter writer;
	private XMLreader reader = new XMLreader();
	private Divisie divisie;
	private VeldPanel veldPanel;
	private ArrayList<Positie> positiesToSave;
	private String opstellingnaamToSave;
	private Spel s;
	
	public Controller(ViewFrame viewFrame, Login l, Home home, TeamPanel teamPanel, Competition comp, PandS ps) {
		this.viewFrame = viewFrame;
		this.l = l;
		this.home = home;
		this.teamPanel = teamPanel;
		this.comp = comp;
		this.ps = ps;
	}
	public Controller(ViewFrame viewFrame, Login l) {
		this.viewFrame = viewFrame;
		this.l = l;
	}

	public void control() {
		divisie = reader.readDivisie(Driver.path);
		ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");
		
		newGame();
		loadGame();
		exitGame();
       
       
		
	}
	
	public void newGame(){
		ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) { 
            	final NewGamePanel ngPanel = new NewGamePanel(viewFrame);
            	ngPanel.showThis(l);
            	for(int i = 0; i < ngPanel.getTeamButtons().size(); i++){
            		final int idx = i;
            		ActionListener actionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent actionEvent) { 
                        	String username = ngPanel.getUserText().getText();
                        	String teamNaam = ngPanel.getTeamNames().get(idx);
                        	
                        	if(username.equals("")){
                	    		JOptionPane.showMessageDialog(null,
                	    			    "Naamveld is leeg!",
                	    			    "Voer je naam in!",
                	    			    JOptionPane.ERROR_MESSAGE);
                	    	}else{
                	    		User.setNaam(username);
                	    		Team team = Divisie.findTeamByName(teamNaam);
                	    		User.setTeam(team);
                	    	//	System.out.println("Controller:" + username + " " + User.getTeam().getNaam());
                	    		startGame(team, ngPanel);
                	    	}
                        }
	                };                
	                ngPanel.getTeamButtons().get(idx).addActionListener(actionListener); 
            	}
            	//startGame(); 
            }
      };                
      l.getNewGame().addActionListener(actionListener); 
	}
	
	public void startGame(Team team, JPanel fromPanel){
		Wedstrijdteam wteam = reader.readWedstrijdteam(team, Driver.path);
	//	Team team = divisie.getTeamList().get(8);
//		User.setNaam(username);
//		User.setTeam(team);
	  	User.setWteam(wteam);
	  	  
	  	Bot.setDivisie(divisie);
	  	Bot.setUserTeam(team);
	  	Bot.volgendeTeam();
		
		home =  new Home();
		teamPanel = new TeamPanel();
		comp = new Competition(viewFrame);
		teamsToCompRank();
		spelersToCompTransfer();
		 
		ps = new PandS(viewFrame);
		ArrayList <Opstelling> opstellingen = teamPanel.getOpst().getOpstellingen();
		int opIdx = RNG.getalTot(opstellingen.size());
		int tactiek = RNG.getalTot(101);
		Bot.teamToWTeam(opstellingen, opIdx, tactiek);
		
		System.out.println("Inloggen");
		if(User.getNaam()==null){
			User.setNaam("Noname");
		}
        vulSpelerlijst(User.getTeam());
      	tabs = new Tabs(viewFrame, home, teamPanel, comp, ps);
        tabs.showThis(fromPanel);
   //   controlPanel2();
        addLogoutListener();
        play();
        ranking();
        opstellingOpslaan();
        for(int i = 0; i < teamPanel.getOpst().getPlayersDDList().length; i++){
        	removeItems(teamPanel.getOpst().getPlayersDDList()[i]);
        }
        addItemRemover();
        wedstrijdteamOpslaan();
        opstellingKiezen();
        tabs.getTable().getTable().setValueAt(Divisie.getSpeeldag(),1,1);
	}
	
	
	public void loadGame(){
		ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) { 
        		final LoadGamePanel lgp = new LoadGamePanel(); 
            	for(int i = 0; i < lgp.getLoadButtons().size(); i++){
            		final int idx = i;
            		ActionListener actionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent actionEvent) { 
                        	Driver.path = System.getProperty("user.dir") + "/saved/"+lgp.getSaveFiles().get(idx)+".xml";
                        	String username = lgp.getSaveFiles().get(idx);
                        	username = username.substring(0, username.length()-20);
                        	User.setNaam(username);
                        	
            	    		//User.setTeam(team);
                        	startGame(null, l);
                        	System.out.println("Load button: "+idx);
                        	SaveDialog.getLoadGameDialog().getRootFrame().dispose();
                        }
	                };                
	                lgp.getLoadButtons().get(idx).addActionListener(actionListener); 
            	}
            	SaveDialog.loadGamePopup(lgp);
            }
      };                
      l.getLoadGame().addActionListener(actionListener); 
	}
	
	public void exitGame(){
		ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) { 
            	viewFrame.dispose();
            }
      };                
      l.getExit().addActionListener(actionListener); 
	}
	
	public void addLogoutListener(){
		JButton logout = tabs.getTable().getImagePanel().getLogoutButton();
       logout.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e){
    	    	String saveFile = SaveDialog.saveGamePopup();
    	    	if(!saveFile.equals("cancel")){
	    	    	if(!saveFile.equals("")){
	    	    		if(Driver.path.equals(System.getProperty("user.dir") + "/database.xml")){
	    	    			createSaveFile(saveFile);
	    	    		}
	    	    		writer = new XMLwriter(saveFile);
	    	    		wedstrijdteamToXML();
	    	    		divisieTeamsToXML();
	    	    		if(opstellingnaamToSave!=null){
	    	    			System.out.println(opstellingnaamToSave);
	    	    			opstellingToXML(positiesToSave, opstellingnaamToSave);
	    	    		}
	    	    	}
	    	    	viewFrame.dispose();
    	    	}
    	    }
    	});
	}
	
	public void play(){
		JButton playButton = home.getHm().getPlayButton();
		playButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent actionEvent) { 
	        	int geluksfactor = RNG.getalTot(600);
	       		s = new Spel(User.getWteam(), Bot.getWteam(), geluksfactor);
	       		int score1 = RNG.getalTot(4);
	    		int score2 = RNG.getalTot(4);
	       		s.winner(score1, score2);
	       		Dimension score = s.getScore();
	       		System.out.println(User.getWteam().getNaam() + ": " + score.width + " " +
	       				Bot.getWteam().getNaam() + ": " + score.height + " - geluksfactor: "+geluksfactor);
	       		veldPanel = new VeldPanel(viewFrame);
	       		veldPanel.getBall().setFinalResult(score);
	       		veldPanel.showThis(tabs);
	       		spel(s);
	       		addPauseListener();
	       		addGoBackListener();
	       		addSkipListener();
	       		addSpeelZelfListener();
	       		updateStats(s);
	       		}
		});
		                
	 //    p2.getButton().addActionListener(actionListener2);   
		
	}
	
	public void addSkipListener(){
		JButton skip = veldPanel.getSkipButton();
       skip.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	veldPanel.getGr().stop();
    	    	
    	    	Dimension newScore = new Dimension(
    	    			(int)veldPanel.getBall().getScore().getWidth() + (int)s.getScore().getWidth(),
    	    			(int)veldPanel.getBall().getScore().getHeight() + (int)s.getScore().getHeight());
    	    	System.out.println(newScore.toString());
    	    	
    	    	spelResults(newScore);
    	    	
    	    	Document doc = home.getHm().getGoals().getDocument();
    	    	try {
    				doc.insertString(doc.getLength(), "\n============================\n" + Divisie.getSkipVerslag(), null);
    			} catch (BadLocationException ble) {
    				// TODO Auto-generated catch block
    				ble.printStackTrace();
    			}
    	    	
    	    	
    	    	viewFrame.remove(veldPanel);
    	    	tabs.showThis(veldPanel);
    	    }
    	});
	}
	
	public void addGoBackListener(){
		JButton goBack = veldPanel.getTerugButton();
       goBack.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	veldPanel.getGr().stop();
    	    	
    	    	spelResults(veldPanel.getBall().getScore());
    	    	
    	    	Document doc = home.getHm().getGoals().getDocument();
    	    	try {
    				doc.insertString(doc.getLength(), "\n============================\n" + veldPanel.getVerslagPanel().getVerslag().getText(), null);
    			} catch (BadLocationException ble) {
    				// TODO Auto-generated catch block
    				ble.printStackTrace();
    			}
    	    	
    	    	
    	    	viewFrame.remove(veldPanel);
    	    	tabs.showThis(veldPanel);
    	    }
    	});
	}
	
	public void spelResults(Dimension score){
		Divisie.rankTeams();
    	
    	Divisie.rekenDoelpunten(score, 
    								1, veldPanel.getBall().getTeam1());
    	Divisie.rekenDoelpunten(score, 
									2, veldPanel.getBall().getTeam2());
    	Divisie.teamsToDiv(veldPanel.getBall().getTeam1(), veldPanel.getBall().getTeam2());
    	Divisie.rankTeams();
    	
    	User.setTeam(veldPanel.getBall().getTeam1());
    	
    	voegGespeeldeTeam(veldPanel.getBall().getTeam1());
    	voegGespeeldeTeam(veldPanel.getBall().getTeam2());
    	
    	updateStats(s);
	}
	
	public void voegGespeeldeTeam(Wedstrijdteam team){
		if(Divisie.getTeamsGespeeld().contains(team)){
			for(int i = 0; i < Divisie.getTeamsGespeeld().size(); i++){
				if(Divisie.getTeamsGespeeld().get(i).equals(team)){
					Divisie.getTeamsGespeeld().set(i, team);
				}
			}
		}else{
			Divisie.getTeamsGespeeld().add(team);
		}
	}
	
	public void addPauseListener(){
		JButton pauseResume = veldPanel.getPauseResume();
		pauseResume.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	pauze();
    	    }
    	});
	}
	
	public void pauze(){
		if(veldPanel.isPause()){
    		veldPanel.getGp().setGoal(false);
    		veldPanel.getGr().start();
    	    veldPanel.setPause(false);// = false;
    	    veldPanel.getPauseResume().setText("Pause");
    	}else{
    		veldPanel.getGr().stop();
    	    veldPanel.setPause(true);// pause = true;
    	    veldPanel.getPauseResume().setText("Resume");
    	}
	}
	
	public void addSpeelZelfListener(){
		JButton playAutoManual = veldPanel.getSpeelZelf();
		playAutoManual.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	if(veldPanel.getGp().isManualPlay()){
    	    		veldPanel.getGp().setManualPlay(false);
    	    		veldPanel.getSpeelZelf().setText("Manual Play");
    	    	}else{
    	    		pauze();
    	    		veldPanel.getGp().setManualPlay(true);// pause = true;
    	    		veldPanel.getSpeelZelf().setText("AutoPlay");
    	    		ImageIcon icon = new ImageIcon("images/manual.png");
					int ok = JOptionPane.showConfirmDialog(
                            null,
                            "",
                            "Manual play", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            icon);
					if(ok == JOptionPane.OK_OPTION){
						pauze();
					}else{
						veldPanel.getGp().setManualPlay(false);
	    	    		veldPanel.getSpeelZelf().setText("Manual Play");
						pauze();
					}
    	    	}
    	    }
    	});
	}
	
	public void updateStats(Spel s){
		Bot.volgendeTeam();
		ArrayList <Opstelling> opstellingen = teamPanel.getOpst().getOpstellingen();
		int opIdx = RNG.getalTot(opstellingen.size());
		int tactiek = RNG.getalTot(101);
		Bot.teamToWTeam(opstellingen, opIdx, tactiek);
		int speeldag = tabs.getTable().getSpeeldag() + 1;
		Divisie.setSpeeldag(speeldag);
		tabs.getTable().setSpeeldag(speeldag);
   		home.getHm().getScores().setText(User.getTeam().getNaam() + " VS " + Bot.getBotTeam().getNaam());
   		tabs.getTable().getTable().setValueAt(User.getTeam().getBudget(),0,1);
   		tabs.getTable().getTable().setValueAt(speeldag,1,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getScore(),2,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getRank(),3,1);
   		tabs.getTable().getTable().setValueAt(Bot.getBotTeam().getNaam(),4,1);
   		rankingUpdate();
   	//	teamPanel.get;
	}
	
	public void spel(Spel s){
//		Document doc = home.getHm().getGoals().getDocument();
    	for(String v: s.verslag()){
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat tijd = new SimpleDateFormat("HH:mm");
	    	System.out.println( tijd.format(cal.getTime()) );
//	    	String old =veldPanel.getVerslagPanel().getVerslag().getText();
//	    	veldPanel.getVerslagPanel().getVerslag().setText();
	    	veldPanel.getVerslagPanel().getVerslag().append( tijd.format(cal.getTime()) + " " + v + "\n");
//	    	veldPanel.getVerslagPanel().getVerslag().setCaretPosition(veldPanel.getVerslagPanel().getVerslag().getDocument().getLength());
//    		try {
//				doc.insertString(doc.getLength(), tijd.format(cal.getTime()) + " " + v + "\n", null);
//			} catch (BadLocationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    	//home.getHm().getGoals().setText(tijd.format(cal.getTime()) + " " + v + "\n");
    	}
    	//home.getHm().getGoals().setText((s.winner().getNaam()+" heeft gewonnen!"));
//    	try {
//    		Calendar cal = Calendar.getInstance();
//	    	cal.getTime();
//	    	SimpleDateFormat tijd = new SimpleDateFormat("HH:mm");
//			doc.insertString(doc.getLength(),tijd.format(cal.getTime()) + " " + s.winner().getNaam()+" heeft gewonnen!"
//					+ "\n\n===================================\n\n", null);
//		} catch (BadLocationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	public void ranking(){
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie(Driver.path);
		
		for(int i = 0; i<18; i++){
			int rank = divisie.getTeamList().get(i).getRank();
			
			String team = rank + ". " + divisie.getTeamList().get(i).getNaam() + " " + divisie.getTeamList().get(i).getScore();
			ranglijst.set(rank-1, team);
		}
		
		String rankList="";
		
		for(String s: ranglijst){
			rankList += s + "\n";
		}
		
		home.getHr().getRankings().setText(rankList);
	}
	
	public void rankingUpdate(){
		
		String rankList="";
		for(Team t: Divisie.getTeamList()){
			rankList += t.getRank() + ". " + t.getNaam() +" "+ t.getScore() + "\n";
		}
		
		home.getHr().getRankings().setText(rankList);
	}
	
	public void vulSpelerlijst(Team team){
		ArrayList<Speler> spelers = team.getSpelerList();
		for (int i = 0; i < spelers.size(); i++){
			Speler speler = spelers.get(i);
			if (speler.getType().equals("doelman")){
				teamPanel.addKeeper(speler.getNaam());
				teamPanel.getOpst().getPlayersDDList()[0].addItem(speler.getNaam());
			}else {
				for(int k = 1; k< teamPanel.getOpst().getPlayersDDList().length; k++){
					teamPanel.getOpst().getPlayersDDList()[k].addItem(speler.getNaam());					
				}
				if (speler.getType().equals("aanvaller")){
					teamPanel.addAanvaller(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[8].addItem(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[9].addItem(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[10].addItem(speler.getNaam());
				}else if(speler.getType().equals("middenvelder")){
					teamPanel.addMiddenvelder(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[1].addItem(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[2].addItem(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[3].addItem(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[4].addItem(speler.getNaam());
				}else if(speler.getType().equals("verdediger")){
					teamPanel.addVerdediger(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[5].addItem(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[6].addItem(speler.getNaam());
	//				teamPanel.getOpst().getPlayersDDList()[7].addItem(speler.getNaam());
				}
			}
		}
		for(int i = 0; i < teamPanel.getOpst().getPlayersDDList().length; i++){
			//System.out.println("Controller: vulSpelerLijst "+User.getWteam().getWSpelers()[0]);
			teamPanel.getOpst().getPlayersDDList()[i].setSelectedItem(User.getWteam().getWSpelers()[i].getNaam());
    	//	System.out.println(User.getWteam().getWSpelers()[i].getNaam() + " " + teamPanel.getOpst().getPlayersDDList()[i].getSelectedItem());
		}
	}
	
	public void opstellingOpslaan(){
		JButton opslaan = teamPanel.getOpslaanButton();
       opslaan.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	ArrayList<Positie> posities = new ArrayList<Positie>();
    	    	String opstellingNaam = SaveDialog.saveOpstellingPopup();
    	    	System.out.println("Opstelling: " + opstellingNaam);
    	    	if(!opstellingNaam.equals("")){
	    	    	for(int i = 0; i < teamPanel.getOpst().getPlayersDDList().length; i++){
	    	    		String name = (String)teamPanel.getOpst().getPlayersDDList()[i].getSelectedItem();
	    	    		String type = getSpelerByName(name).getType();
//	    	    		if(i==0){
//				    		type = "Keeper";
//				    	}else if(i<5){
//				    		type = "Middenvelder";
//				    	}
//				    	else if(i<8){
//				    		type = "Vergediger";
//				    	}else if(i<11){
//				    		type = "Aanvaller";
//				    	}
	    	    		Dimension pos = teamPanel.getOpst().getPlayerPos()[i];
	    	    		Positie positie = new Positie(pos.width, pos.height, type);
	    	    		posities.add(positie);
	    	    	//	System.out.println(positie.toString());//type + " op: " + pos.width + "," + pos.height);
	    	    	}
	    	    //	opstellingToXML(posities, opstellingNaam);
	    	    	positiesToSave = posities;
	    	    	opstellingnaamToSave = opstellingNaam;
	    	    	nieweOpstellingGebruiken(posities, opstellingNaam);
    	    	}
    	    }	
    	});
	}
	
	public void nieweOpstellingGebruiken(ArrayList<Positie> posities, String naam){
		Opstelling nieweOpst = new Opstelling(naam, posities);
		teamPanel.getOpst().getOpstellingen().add(nieweOpst);
		teamPanel.getOpstellingKeuze().addItem(naam);
		teamPanel.getOpstellingKeuze().setSelectedItem(naam);
	}
	
	public static Speler getSpelerByName(String name){
		Speler speler = null;
		for(Speler s: User.getTeam().getSpelerList()){
			if((name).equals(s.getNaam())){
				speler = s;
			}
		}
		return speler;
	}
	
	public void wedstrijdteamOpslaan(){
		JButton wteamOpslaan = teamPanel.getWteamOpslaanButton();
		wteamOpslaan.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e){
    	    	createWedstrijdteam();
    	    }
    	});
	}
	
	
	/**
	 * voert het kopen van een speler uit
	 * @param speler	de speler om te kopen
	 * @param eigenaar	het team waarvan de speler gekocht word
	 * @param koper		het team dat de speler koopt
	 */
	public void spelerKopen(Speler speler, Team eigenaar, Team koper){
		int prijs = speler.getPrijs();
		ArrayList<Speler> eigenaarSpelers = eigenaar.getSpelerList();
		ArrayList<Speler> koperSpelers = koper.getSpelerList();
		
		//voert de aankoop alleen uit als de speler in het aangegeven team zit
		if (eigenaarSpelers.contains(speler)){
			koper.setBudget(koper.getBudget() - prijs);
			eigenaar.setBudget(eigenaar.getBudget() + prijs);
			
			eigenaarSpelers.remove(speler);
			koperSpelers.add(speler);
			
			eigenaar.setSpelerList(eigenaarSpelers);
			koper.setSpelerList(koperSpelers);
		}
	}
	
	public void addItemRemover(){
		
		for(int i=0; i<teamPanel.getOpst().getPlayersDDList().length;i++){
			teamPanel.getOpst().getPlayersDDList()[i].addActionListener (new ActionListener () {
    		    public void actionPerformed(ActionEvent e) {
    		    	JComboBox spelers = (JComboBox)e.getSource();
    		    	removeItems(spelers);
    		    	teamPanel.getOpst().repaint();
    		    }
    		});
		}
	}
	
	public void opstellingKiezen(){
		teamPanel.getOpstellingKeuze().addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	teamPanel.getOpst().opstToPlayerPos(teamPanel.getOpstellingKeuze());
		    	teamPanel.getOpst().repaint();
		    }
		});
	}
	
	public void removeItems(JComboBox spelers){
   // 	System.out.println("item selected " + spelers.getSelectedItem()+ " " + spelers.getName());
    	int min = 1;//0;
    	int max = 11;//0;
	   
    	for(int j = min; j<max; j++){
    	   	JComboBox b = teamPanel.getOpst().getPlayersDDList()[j];
    	   	ArrayList<String> spelerList = new ArrayList<String>();
    	   	for(int k = 0; k < b.getItemCount(); k++){
	            spelerList.add((String)b.getItemAt(k));
            }
    	   	for(int k = 0; k < spelers.getItemCount(); k++){
    	   		String type = getSpelerByName((String)spelers.getItemAt(k)).getType();
    	   		if(!spelerList.contains(spelers.getItemAt(k)) && !type.equals("doelman")){
    	   			b.addItem(spelers.getItemAt(k));
    	   		}
            }
    	    	
    	  	if(!b.getName().equals(spelers.getName())){
    	  		b.removeItem(spelers.getSelectedItem());
    	   	}
    	 }
    }
	
	public void teamsToCompRank(){
		Divisie.rankTeams();
		Object[][] data = new Object[ Divisie.getTeamList().size()][2];
		for(int i=0; i < Divisie.getTeamList().size(); i++ ){
			Team t = Divisie.getTeamList().get(i);
//			imgList.add(new ImageIcon("images/logos/"+t.getNaam()+".png"));
//			teamDescrList.add(t.getScore() + ". "+t.getNaam()+"\nWinst:"+t.getWinst()+"\nGelijkspel: "+t.getGelijkspel()+"\nVerlies: "+t.getVerlies());
			data[i][0] = new ImageIcon("images/logos/"+t.getNaam()+".png");
			data[i][1] = t.getScore() + ". "+t.getNaam()+"\nWinst:"+t.getWinst()+
					"\nGelijkspel: "+t.getGelijkspel()+"\nVerlies: "+t.getVerlies()+
					"\nDoelvoor: "+t.getDoelvoor()+"\nDoeltegen: "+t.getDoeltegen()+
					"\nDoelsaldo: "+t.getDoelsaldo()+"\nBudget: "+t.getBudget();
		}
		
		
		String[] columnNames = {"Ranking", "Uitgebreide ranking met doelpunten saldo etc"};
		
		comp.addPane(data, columnNames);
	}
	
	public void spelersToCompTransfer(){
		int aantalSp =0;
		for(int i=0; i < Divisie.getTeamList().size(); i++ ){
			Team t = Divisie.getTeamList().get(i);
			for(int k =0; k < t.getSpelerList().size(); k++){
				aantalSp++;
			}
		}
		
		Divisie.rankTeams();
		Object[][] data = new Object[aantalSp][3];
		comp.getPane().setKoopButtons(new Object[aantalSp][2]);
		int spIdx = 0;
		for(int i=0; i < Divisie.getTeamList().size(); i++ ){
			Team t = Divisie.getTeamList().get(i);
//			imgList.add(new ImageIcon("images/logos/"+t.getNaam()+".png"));
//			teamDescrList.add(t.getScore() + ". "+t.getNaam()+"\nWinst:"+t.getWinst()+"\nGelijkspel: "+t.getGelijkspel()+"\nVerlies: "+t.getVerlies());
			for(int k =0; k < t.getSpelerList().size(); k++){
				final Speler s = t.getSpelerList().get(k);
				data[spIdx][0] = new ImageIcon("images/logos/"+t.getNaam()+".png");
				data[spIdx][1] = s.getNaam()+"\nType:"+s.getType()+
								"\nOffence: "+s.getOffense()+"\nDefence: "+s.getDefence()+
								"\nUithouding: "+s.getUithouding()+"\nBeschikbaarheid: "+s.getBeschikbaarheid()+
								"\nPrijs: "+s.getPrijs();
				
//				JButton koopButton = new JButton(s.getNaam()+"\nKopen");
//				koopButton.addActionListener(new ActionListener() {
//				      public void actionPerformed(ActionEvent event) {
//				    	  	JOptionPane.showMessageDialog(null, s.getNaam());
//					      }
//					    });
				data[spIdx][2] = s.getNaam();//koopButton;
//				comp.getPane().getKoopButtons()[spIdx][0] = s.getNaam();
//				comp.getPane().getKoopButtons()[spIdx][1] = koopButton;
				spIdx++;
			}
		//	System.out.println("Controller: spelersToCompTransfer: " + t.getNaam());
		}
		
		
		String[] columnNames = {"Transferlijst","Spelers die te koop zijn:", "Kopen"};
		
		comp.addPane(data, columnNames);
	}
	
	public void createWedstrijdteam(){
		ArrayList<Opstelling> opstellingen = teamPanel.getOpst().getOpstellingen();
		String selectedOpst = (String)teamPanel.getOpstellingKeuze().getSelectedItem();
		
		Opstelling opstelling = null;
		for(Opstelling op: opstellingen){
			if(op.getNaam().equals(selectedOpst)){
				opstelling = op;
			}
		}
		for(int i = 0; i < teamPanel.getOpst().getPlayersDDList().length; i++){
    		String naam = (String)teamPanel.getOpst().getPlayersDDList()[i].getSelectedItem();
    		
    		for(Speler s :  User.getWteam().getSpelerList()){
    	        if(s.getNaam() != null && s.getNaam().contains(naam)){
    	        	User.getWteam().getWSpelers()[i] = s;
    	        }
    	        
    	    }
    		
    	}

		int tactiek = teamPanel.getSlider().getValue();
    	User.getWteam().setTactiek(tactiek); 
    	
    	User.getWteam().setOpstelling(opstelling);
    	
    //	wedstrijdteamToXML();
    	System.out.println(User.getWteam().toString());
    	//System.out.println(wSpelers[0].getNaam() + "\n" +wSpelers[10].getNaam() + "\nTactiek = "+ tactiek);
	}
	
	public void wedstrijdteamToXML(){
		Wedstrijdteam wteam = User.getWteam();
		
		writer.updaten("Wedstrijdteam" , "Wedstrijdteam", "offence", Integer.toString(wteam.getOff()));
		writer.updaten("Wedstrijdteam" , "Wedstrijdteam", "defence", Integer.toString(wteam.getDef()));
		writer.updaten("Wedstrijdteam" , "Wedstrijdteam", "uithouding", Integer.toString(wteam.getUith()));
		writer.updaten("Wedstrijdteam" , "Wedstrijdteam", "opstelling", wteam.getOpstelling().getNaam());
		writer.updaten("Wedstrijdteam" , "Wedstrijdteam", "tactiek", Integer.toString(wteam.getTactiek()));
		//TeamNaam
		writer.updaten("Wedstrijdteam" , "Wedstrijdteam", "TeamNaam", wteam.getNaam());
		String spelers = "";
		for(int i = 0; i< wteam.getWSpelers().length; i++){
			if(i == wteam.getWSpelers().length - 1){
				spelers += wteam.getWSpelers()[i].getNaam();
			}else {
				spelers += wteam.getWSpelers()[i].getNaam() + ",";
			}
		}
		writer.updaten("Wedstrijdteam" , "Wedstrijdteam", "spelers", spelers);
	}
	
	
	public void opstellingToXML(ArrayList<Positie> posities, String naam){	

    	System.out.println(posities.toString());
		writer.add("opstellingen", "opstellingen", "opstelling_posities", naam);
		
		for(int i = 0; i< posities.size(); i++){
			String coordinaten = posities.get(i).getX() + "," + posities.get(i).getY();
			writer.updaten("opstelling_posities" , naam , posities.get(i).getType() + i, coordinaten);
			
		}
	}
	
	public void divisieTeamsToXML(){	
		writer.updaten("divisie" , "Eredivisie", "speeldag", Divisie.getSpeeldag()+"");
		//System.out.println("divisie" + "Eredivisie" + "speeldag" + Divisie.getSpeeldag()+"");
		for(Team t: Divisie.getTeamsGespeeld()){
			writer.updaten("team" , t.getNaam() , "doelvoor" , t.getDoelvoor()+"");
			writer.updaten("team" , t.getNaam() , "doeltegen" , t.getDoeltegen()+"");
			writer.updaten("team" , t.getNaam() , "doelsaldo" , t.getDoelsaldo()+"");
			
			writer.updaten("team" , t.getNaam() , "winst" , t.getWinst()+"");
			writer.updaten("team" , t.getNaam() , "gelijkspel" , t.getGelijkspel()+"");
			writer.updaten("team" , t.getNaam() , "verlies" , t.getVerlies()+"");
			
			writer.updaten("team" , t.getNaam() , "score" , t.getScore()+"");
			
		//	writer.updaten("team" , t.getNaam() , "rank" , t.getRank()+"");
		}
		for(Team t: Divisie.getTeamList()){		
			writer.updaten("team" , t.getNaam() , "rank" , t.getRank()+"");
		}
	}
	
	public void createSaveFile(String destination){
		File source = new File(Driver.path);
		File dest = new File(destination);

		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
