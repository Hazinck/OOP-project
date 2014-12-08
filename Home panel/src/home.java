
import javax.swing.*;

import java.awt.*;


public class home extends JPanel{
	public home(){
		GridBagLayout gl=new GridBagLayout();
		GridBagConstraints c=new GridBagConstraints();
		setLayout(gl);
		JPanel linkertabel=new JPanel();
		homemidden hm=new homemidden();
		homerechts hr=new homerechts();
		c.ipadx = 468;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		add(hm);
		c.ipadx=0;
		c.gridx = 2;
		c.gridy = 0;
		add(hr);
		
	}
	

}
