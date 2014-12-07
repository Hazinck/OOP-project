
import javax.swing.*;

import java.awt.*;


public class home {
	public home(){
		JPanel card=new JPanel();
		GridBagLayout gl=new GridBagLayout();
		GridBagConstraints c=new GridBagConstraints();
		card.setLayout(gl);
		JPanel linkertabel=new JPanel();
		homemidden hm=new homemidden();
		homerechts hr=new homerechts();
		c.gridx = 0;
		c.gridy = 0;
		card.add(linkertabel);
		c.ipadx = 468;
		c.weightx = 1.0;
		c.gridx = 1;
		c.gridy = 0;
		card.add(hm);
		c.ipadx=0;
		c.gridx = 3;
		c.gridy = 0;
		card.add(hr);
		
		JFrame gui=new JFrame();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.add(card);
		gui.pack();
		gui.setVisible(true);
	}
	

	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				home test=new home();
			}
		});
	}
}
