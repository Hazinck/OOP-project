package oop.voetbalmanager.model;

import java.util.Random;

public abstract class RNG {
	private static Random generator = new Random();
	
	/**
	 * Geeft een willekeurige integer
	 * @param n		tot welk getal de uitkomst moet zijn
	 * @return		een willekeurig getal van 0 tot n (exclusief n)
	 */
	public static int getalTot(int n) {
		return generator.nextInt(n);
	}
	
	/**
	 * Geeft een willekeurige double
	 * @param d		tot welk getal de uitkomst moet zijn
	 * @return		een willekeurig getal van 0 tot d (exclusief d)
	 */
	public static double getalTot(double d) {
		return (generator.nextDouble() * d);
	}
	
	/**
	 * Bepaalt of iets met de opgegeven kans gebeurt of niet
	 * @param percent	hoeveel procent kans er is dat het gebeurt (0-100)
	 * @return			true als het wel moet gebeurt, anders false
	 */
	public static boolean kans(double percent) {
		return (getalTot(100) < percent);
	}
}
