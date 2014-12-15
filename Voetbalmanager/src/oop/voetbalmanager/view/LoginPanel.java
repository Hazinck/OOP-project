package oop.voetbalmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{
	
	private JLabel userLabel = new JLabel("Gebruikersnaam");
	private JLabel passLabel = new JLabel("Wachtwoord       ");
	private JTextField userText = new JTextField(10);
	private JTextField passText = new JTextField(10);
	private JPanel user = new JPanel();
	private JPanel pass = new JPanel();
	private JPanel loginpanel = new JPanel();
	
	public LoginPanel(){
		
		
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

}
