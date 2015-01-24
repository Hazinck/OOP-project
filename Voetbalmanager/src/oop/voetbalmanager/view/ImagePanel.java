package oop.voetbalmanager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oop.voetbalmanager.model.User;

public class ImagePanel extends JPanel{
	private BufferedImage image;
	private JButton logout;
	private boolean ps = false;
	private String avatarPath = "images/user_default.png";
	
	static JLabel naamLable = new JLabel();
	private String username;
    public ImagePanel(ViewFrame vframe) {
    	this.username = User.getNaam();
    	setOpaque(false);
    	String imgName = vframe.getImgName();
    	setLayout(null);
       try {                
    	   if(imgName == null || imgName.equals("")){
    		   image = ImageIO.read(new File(avatarPath));
    	   }
    	   else{
    		   image = ImageIO.read(new File(vframe.getImgPath() + imgName));
    	   }
       } catch (IOException ex) {
            // handle exception...
       }
       
       
    }
    
    public void addNameLable(){
    	naamLable.setFont(new Font("Arial", Font.BOLD, 20)); 
		naamLable.setText("<html><body style='text-align: center; width: "+(int)(ViewFrame.getFrameWidth()*0.15)+"px'>"+username);//150px
		naamLable.setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		add(naamLable);//, BorderLayout.PAGE_START);
		naamLable.setBounds(0, 0 ,(int)(ViewFrame.getFrameWidth()*0.20), (int)(ViewFrame.getFrameHeight()*0.15));//150
    }
    
    public static void setNameLable(String naam){
    	naamLable.setText("<html><body style='text-align: center; width: "+(int)(ViewFrame.getFrameWidth()*0.15)+"px'>"+naam);
    }
    
    public void setImage(Image img){
    	image=(BufferedImage) img;
    	repaint();
    }
    
    public void setImageFromPath(String path){
    	 try {                
      	   if(path != null && !path.equals("")){
      		   image = ImageIO.read(new File(path));
      		   avatarPath = path;
      	   }
         } catch (IOException ex) {
              // handle exception...
         }
    	repaint();
    }
    
    public void addLogoutButton(){
	//	add(Box.createRigidArea(new Dimension(0,(int)(ViewFrame.getFrameHeight()*0.66))));//image.getHeight()*2 + 30
		logout = new JButton("Quit and Save");
    	add(logout);
		logout.setBounds(0, (int)(ViewFrame.getFrameHeight()*0.40) ,(int)(ViewFrame.getFrameWidth()*0.20), 30);//150
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        int w = image.getWidth(null);
//        int h = image.getHeight(null);
        int y = naamLable.getHeight();
        if(ps){
        	y = 0;
        }
        g.drawImage(image, ((int)(ViewFrame.getFrameWidth()*0.20)-150)/2, y, 150, 150, this);//null); 
    }
	@Override
	public Dimension getPreferredSize() {
		int w = (int)(ViewFrame.getFrameWidth()*0.25);//150;
		int h = (int)(ViewFrame.getFrameHeight()*0.25);//250;// * percent / 100;
		return new Dimension(w,h);
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	public JButton getLogoutButton(){
		return logout;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @param ps the ps to set
	 */
	public void setPs(boolean ps) {
		this.ps = ps;
	}

	/**
	 * @return the avatarPath
	 */
	public String getAvatarPath() {
		return avatarPath;
	}

	/**
	 * @param avatarPath the avatarPath to set
	 */
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
}