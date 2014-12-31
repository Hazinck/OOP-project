package oop.voetbalmanager.model;

public class Positie {
	
	private int x ,y;
	private String type;
	
	
	/**
	 * @param x
	 * @param y
	 */
	public Positie(int x, int y, String type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public String toString(){
		return type + " op: " + x + "," + y;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
