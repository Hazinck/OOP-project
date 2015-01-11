package oop.voetbalmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oop.voetbalmanager.model.User;

public class LoginPanel extends JPanel{
	
	private static String Username;
	private static String Password;
	
	private JLabel userLabel = new JLabel("Gebruikersnaam");
	private JLabel passLabel = new JLabel("Wachtwoord       ");
	private static JTextField userText = new JTextField(10);
	private JTextField passText = new JTextField(10);
	private JPanel user = new JPanel();
	private JPanel pass = new JPanel();
	private JPanel loginpanel = new JPanel();
	
	public LoginPanel(){
		setBackground(Color.white);
		setOpaque(true);
		user.setOpaque(false);
		pass.setOpaque(false);
		loginpanel.setOpaque(false);
		user.setBackground(null);
		pass.setBackground(null);
		loginpanel.setBackground(null);
		
		
		user.setLayout(new FlowLayout());
		user.add(userLabel);
		user.add(userText);
		pass.setLayout(new FlowLayout());
		pass.add(passLabel);
		pass.add(passText);
		loginpanel.setLayout(new BorderLayout());
		loginpanel.add("North", user);
		loginpanel.add("South", pass);
		
		add(loginpanel);
	}
	
	public static void setNaam(String nieuw){
		Username=nieuw;
	}
	
	public static void setWachtwoord(String nieuw){
		Password=nieuw;
	}
	
	public static String setName(){
		Username = userText.getText();
		if(Username.length() == 0){
			return Username = "Andy Zaidman";
		}
		else{
				return Username;
			}
				
		}


	public String getUser(){
		return Username = userText.getText();
	}
	
	public String getPass(){
		return Password = passText.getText();
	}
}

