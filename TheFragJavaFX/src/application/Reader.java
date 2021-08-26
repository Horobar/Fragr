package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Liest aus der Console aus und gibt String zurück
 * 
 * @author jhkw
 */
public class Reader {

	/**
	 * Liest die nächste Zeile ein und gibt den Inhalt als String zurück
	 * 
	 * @return Console-Input as String
	 * @throws IOException
	 */
	public static String input() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

	/**
	 * Liest die nächste Zeile der Konsole ein, prüft ob sie numerisch und zwischen
	 * min und max liegt
	 * 
	 * @param min
	 * @param max
	 * @return Console-input as Int
	 * @throws IOException
	 */
	public static int input(int min, int max) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String in = "b";
		do {
			in = reader.readLine();
		} while (!isNumeric(in) && isInRange(in, min, max));
		return Integer.parseInt(in);
	}

	/**
	 * Check if Console-Input is Numeric
	 * 
	 * @param strNum
	 * @return boolean
	 */
	public static boolean isNumeric(String strNum) {
		try {
			Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			System.out.println("Bitte nur Zahlen eingeben");
			return false;
		}
		return true;
	}
	
	/**
	 * prüft ob die eingegebne Zahl zwischen min und max liegt
	 * 
	 * @param strNum
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInRange(String strNum, int min, int max) {
		try {
			int check = Integer.parseInt(strNum);
			if (check > max || check < min)
				System.out.println("Bitte nur Zahlen zwischen " + min + " und " + max + " eingeben");
				return false;
		} catch (NumberFormatException nfe) {
			System.out.println("Bitte nur Zahlen eingeben");
		}
		return true;
	}
}
