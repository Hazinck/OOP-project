package oop.voetbalmanager.controller;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.LoginPanel;
import oop.voetbalmanager.view.PandS;
import oop.voetbalmanager.view.SaveOpstellingDialog;
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
	private XMLwriter writer = new XMLwriter(Driver.path);
	private VeldPanel veldPanel;
	private boolean pause = false;
	
	public Controller(ViewFrame viewFrame, Login l, Home home, TeamPanel teamPanel, Competition comp, PandS ps) {
		this.viewFrame = viewFrame;
		this.l = l;
		this.home = home;
		this.teamPanel = teamPanel;
		this.comp = comp;
		this.ps = ps;
	}

	public void control() {
		ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");
		
		ActionListener actionListener = new ActionListener() {
             public void actionPerformed(ActionEvent actionEvent) { 
            	 System.out.println("Inloggen");
            	 User.setNaam(LoginPanel.setName());
                 vulSpelerlijst(User.getTeam());
            	 tabs = new Tabs(viewFrame, home, teamPanel, comp, ps);
                tabs.showThis(l);
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
             }
       };                
       l.getButton().addActionListener(actionListener);   
       
       
		
	}
	
	public void addLogoutListener(){
		JButton logout = tabs.getTable().getImagePanel().getLogoutButton();
       logout.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	       viewFrame.dispose();
    	    }
    	});
	}
	
	public void play(){
		JButton playButton = home.getHm().getPlayButton();
		playButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent actionEvent) { 
	        	int geluksfactor = RNG.getalTot(600);
	       		Spel s = new Spel(User.getWteam(), Bot.getWteam(), geluksfactor);
	       		s.winner();
	       		System.out.println(User.getWteam().getNaam() + ": " + s.getScore().width + " " +
	       				Bot.getWteam().getNaam() + ": " + s.getScore().height + " - geluksfactor: "+geluksfactor);
	       		veldPanel = new VeldPanel(viewFrame);
	       		veldPanel.showThis(tabs);
	       		spel(s);
	       		addPauseListener();
	       		addGoBackListener();
	       		updateStats(s);
	       		}
		});
		                
	 //    p2.getButton().addActionListener(actionListener2);   
		
	}
	
	public void addGoBackListener(){
		JButton goBack = veldPanel.getTerugButton();
       goBack.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	veldPanel.getGr().stop();
    	    	viewFrame.remove(veldPanel);
    	    	tabs.showThis(veldPanel);
    	    }
    	});
	}
	
	public void addPauseListener(){
		JButton pauseResume = veldPanel.getPauseResume();
		pauseResume.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	if(pause){
    	    		veldPanel.getGr().start();
    	    		pause = false;
    	    		veldPanel.getPauseResume().setText("Pause");
    	    	}else{
    	    		veldPanel.getGr().stop();
    	    		pause = true;
    	    		veldPanel.getPauseResume().setText("Resume");
    	    	}
    	    }
    	});
	}
	
	public void updateStats(Spel s){
		Bot.volgendeTeam();
		Bot.teamToWTeam(teamPanel.getOpst().getOpstellingen());
		int speeldag = tabs.getTable().getSpeeldag() + 1;
		tabs.getTable().setSpeeldag(speeldag);
   		home.getHm().getScores().setText(User.getTeam().getNaam() + " VS " + Bot.getBotTeam().getNaam());
   		tabs.getTable().getTable().setValueAt(User.getTeam().getBudget(),0,1);
   		tabs.getTable().getTable().setValueAt(speeldag,1,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getScore(),2,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getRank(),3,1);
   		tabs.getTable().getTable().setValueAt(Bot.getBotTeam().getNaam(),4,1);
   		ranking();
   	//	teamPanel.get;
	}
	
	public void spel(Spel s){
		Document doc = home.getHm().getGoals().getDocument();
    	for(String v: s.verslag()){
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat tijd = new SimpleDateFormat("HH:mm");
	    	System.out.println( tijd.format(cal.getTime()) );
//	    	String old =veldPanel.getVerslagPanel().getVerslag().getText();
//	    	veldPanel.getVerslagPanel().getVerslag().setText();
	    	veldPanel.getVerslagPanel().getVerslag().append( tijd.format(cal.getTime()) + " " + v + "\n");
//	    	veldPanel.getVerslagPanel().getVerslag().setCaretPosition(veldPanel.getVerslagPanel().getVerslag().getDocument().getLength());
    		try {
				doc.insertString(doc.getLength(), tijd.format(cal.getTime()) + " " + v + "\n", null);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//home.getHm().getGoals().setText(tijd.format(cal.getTime()) + " " + v + "\n");
    	}
    	//home.getHm().getGoals().setText((s.winner().getNaam()+" heeft gewonnen!"));
    	try {
    		Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat tijd = new SimpleDateFormat("HH:mm");
			doc.insertString(doc.getLength(),tijd.format(cal.getTime()) + " " + s.winner().getNaam()+" heeft gewonnen!"
					+ "\n\n===================================\n\n", null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    	    	String opstellingNaam = SaveOpstellingDialog.popup();
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
	    	    	opstellingToXML(posities, opstellingNaam);
    	    	}
    	    }	
    	});
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
    	
    	wedstrijdteamToXML();
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
}
