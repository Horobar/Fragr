package application;
/**
 * enthält nützliche Funktionen
 *  
 * @author Jonas Hess
 *
 */
public class Utilitys {
	/**
	 * 
	 * @param min
	 * @param max
	 * @return Random Int between min and max
	 */
	 public static int generateRandomInt(int min,int max) {
	        return min + (int)(Math.random() * ((max - min) + 1));
	    }
	 
	 

}
