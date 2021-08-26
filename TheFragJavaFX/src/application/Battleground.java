package application;

import java.util.Optional;
/**
 * Klasse zur graphischen Ausgabe des Spielfelds
 * 
 * @author Jonas Hess
 *
 */
public class Battleground implements PublicAccess {
	String[] horizontaleLines;
	
	private String[] aside;

	// Amount of Fields in x and y Direction in GameCoords
	private int xFields = 26;
	private int yFields = 16;

	public String[][] vertLines = new String[xFields][yFields];

	/**
	 * Konstruktor, erstellt ein Battlegroundobjekt und lädt die Karte
	 */
	public Battleground() {
		createHorizontaleLines();
		createVerticaleLines();
	}

	/**
	 * Zeichnet das Spielfeld und ein Statistikfeld des aktuellen Spielers in die
	 * Konsole
	 * 
	 * @param aside
	 */
	public void drawBattleground(String[] aside) {
		this.aside = aside;
		;
		String out;
		String end;
		Optional<ObjectOnField> onField;
		System.out.print("\n\u2554");

		for (int ii = 0; ii < xFields - 1; ii++) {
			System.out.print("\u2550\u2550\u2550\u2564");
		}
		System.out.print("\u2550\u2550\u2550\u2557\n");
		for (int i = 0; i < yFields; i++) {
			System.out.print("\u2551");
			for (int j = 0; j < xFields; j++) {
				int[] coords = { j, i };

				onField = FieldManager.isFieldEmptyWithStream(coords);

				end = vertLines[j][i];

				if (onField.isPresent()) {
					out = onField.get().getSymbol() + end;
				} else {
					out = "   " + end;
				}

				System.out.print(out);
			}

			System.out.print(aside[i]);
			if (i < yFields) {
				System.out.print(horizontaleLines[i]);
			}

		}

	}

	// Getters and Setters
	// @return

	public int getxFields() {
		return xFields;
	}

	public int getyFields() {
		return yFields;
	}

	/**
	 * Zeichnet die Vertikalen Felderlinien und überschreibtg anschliessend die entsprechenden Stellen mit Wänden
	 */
	public void createVerticaleLines() {
		String line = "\u250A";
		String wall = "\u2551";

		for (int i = 0; i < yFields; i++) {
			for (int j = 0; j < xFields; j++) {
				if (j < xFields - 1)
					vertLines[j][i] = line;
				else
					vertLines[j][i] = wall;
			}
		}
		vertLines[17][0] = wall;
		vertLines[20][0] = wall;
		vertLines[17][1] = wall;
		vertLines[20][1] = wall;
		vertLines[0][3] = wall;
		vertLines[4][3] = wall;
		vertLines[5][3] = wall;
		vertLines[7][3] = wall;
		vertLines[8][3] = wall;
		vertLines[11][3] = wall;
		vertLines[15][3] = wall;
		vertLines[16][3] = wall;
		vertLines[0][4] = wall;
		vertLines[3][4] = wall;
		vertLines[5][4] = wall;
		vertLines[7][4] = wall;
		vertLines[9][4] = wall;
		vertLines[11][4] = wall;
		vertLines[13][4] = wall;
		vertLines[15][4] = wall;
		vertLines[17][4] = wall;
		vertLines[0][5] = wall;
		vertLines[2][5] = wall;
		vertLines[5][5] = wall;
		vertLines[7][5] = wall;
		vertLines[10][5] = wall;
		vertLines[11][5] = wall;
		vertLines[13][5] = wall;
		vertLines[19][5] = wall;
		vertLines[0][6] = wall;
		vertLines[2][6] = wall;
		vertLines[5][6] = wall;
		vertLines[7][6] = wall;
		vertLines[10][6] = wall;
		vertLines[0][7] = wall;
		vertLines[2][7] = wall;
		vertLines[5][7] = wall;
		vertLines[7][7] = wall;
		vertLines[10][7] = wall;
		vertLines[14][7] = wall;
		vertLines[17][7] = wall;
		vertLines[19][7] = wall;
		vertLines[22][7] = wall;
		vertLines[0][8] = wall;
		vertLines[14][8] = wall;
		vertLines[17][8] = wall;
		vertLines[0][9] = wall;
		vertLines[14][9] = wall;
		vertLines[17][9] = wall;
		vertLines[19][9] = wall;
		vertLines[22][9] = wall;
		vertLines[0][10] = wall;
		vertLines[0][11] = wall;
		vertLines[2][11] = wall;
		vertLines[0][12] = wall;
		vertLines[4][12] = wall;
		vertLines[7][12] = wall;
		vertLines[9][12] = wall;
		vertLines[12][12] = wall;
		vertLines[14][12] = wall;
		vertLines[15][12] = wall;
		vertLines[18][12] = wall;
		vertLines[1][13] = wall;
		vertLines[4][13] = wall;
		vertLines[2][14] = wall;
		vertLines[7][14] = wall;
		vertLines[9][14] = wall;
		vertLines[12][14] = wall;
		vertLines[14][14] = wall;
		vertLines[15][14] = wall;
		vertLines[18][14] = wall;
		vertLines[23][15] = wall;

	}

	/**
	 * Zeichnet die horzontalen Felderlinien, dabei werden möglichst grosse Linienstücke zusammengesetzt
	 */
	public void createHorizontaleLines() {
		horizontaleLines = new String[16];
		// Line 1
		String out = "\n\u255F";
		out += line1(17);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(4);
		out += lineE();
		horizontaleLines[0] = out;

		// Line 2
		out = "\n\u255F";
		out += line1(4);
		out += line3(1);
		out += line2(4);
		out += line1(8);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(4);
		out += lineE();
		horizontaleLines[1] = out;

		// Line 3
		out = "\n\u255F";
		out += line4(1);
		out += line1(10);
		out += "\u2500\u2500\u2500\u2554";
		out += line2(3);
		out += "\u2550\u2550\u2550\u2557";
		out += line1(2);
		out += line3(1);
		out += line2(1);
		out += line1(5);
		out += lineE();
		horizontaleLines[2] = out;

		// Line4
		out = "\n\u255F";
		out += line4(1);
		out += line1(2);
		out += line12();
		out += line13();
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line5();
		out += line15();
		out += line1(1);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line5();
		out += line15();
		out += line1(7);
		out += lineE();
		horizontaleLines[3] = out;

		// Line 5
		out = "\n\u255F";
		out += line4(1);
		out += line1(1);
		out += line12();
		out += line13();
		out += line1(1);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(1);
		out += line5();
		out += line15();
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(1);

		out += line5();
		out += line2(1);
		out += line15();
		out += line1(5);
		out += lineE();
		horizontaleLines[4] = out;

		// Line6
		out = "\n\u255F";
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line5();
		out += line2(1);
		out += line5();
		out += line2(5);
		out += line13();
		out += line1(5);
		out += lineE();
		horizontaleLines[5] = out;

		// Line 7
		out = "\n\u255F";
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(3);
		out += line6();
		out += line2(1);
		out += line1(1);
		out += line7();
		out += line1(1);
		out += line6();
		out += line2(2);
		out += line7();
		out += line1(2);
		out += lineE();
		horizontaleLines[6] = out;

		// Line8
		out = "\n\u255F";
		out += line4(1);
		out += line1(1);
		out += line5();
		out += line2(2);
		out += line8();
		out += line1(1);
		out += line5();
		out += line3(1);
		out += line2(1);
		out += line9();
		out += line1(3);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(2);
		out += lineE();
		horizontaleLines[7] = out;

		// Line9
		out = "\n\u255F";
		out += line4(1);
		out += line1(13);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(1);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(2);
		out += lineE();
		horizontaleLines[8] = out;

		// Line10
		out = "\n\u255F";
		out += line4(1);
		out += line1(13);
		out += line5();
		out += line2(1);
		out += line3(1);
		out += line9();
		out += line1(1);
		out += line5();
		out += line2(2);
		out += line9();
		out += line1(2);
		out += lineE();
		horizontaleLines[9] = out;

		// Line 11
		out = "\n\u255F";
		out += line4(1);
		out += line1(1);
		out += line12();
		out += line2(2);
		out += line7();
		out += line1(3);
		out += line3(1);
		out += line2(3);
		out += line1(12);
		out += lineE();
		horizontaleLines[10] = out;

		// Linie 12
		out = "\n\u255F";
		out += line4(1);
		out += line12();
		out += line13();
		out += line1(1);
		out += line6();
		out += line9();
		out += line1(1);
		out += line6();
		out += line2(1);
		out += line7();
		out += line1(2);
		out += line6();
		out += line2(1);
		out += line7();
		out += line6();
		out += line2(2);
		out += line7();
		out += line1(6);
		out += lineE();
		horizontaleLines[11] = out;

		// Linie 13
		out = "\n\u255F";
		out += line4(2);
		out += line1(2);
		out += line4(1);
		out += line1(2);
		out += line4(1);
		out += line1(1);
		out += line5();
		out += line2(2);
		out += line9();
		out += line1(1);
		out += line4(2);
		out += line1(2);
		out += line4(1);
		out += line1(6);
		out += lineE();
		horizontaleLines[12] = out;

		// Linie 14
		out = "\n\u255F";
		out += line1(1);
		out += line14();
		out += line15();
		out += line1(1);
		out += line5();
		out += line7();
		out += line1(1);
		out += line4(1);
		out += line1(1);
		out += line6();
		out += line2(2);
		out += line7();
		out += line1(1);
		out += line4(2);
		out += line1(2);
		out += line4(1);
		out += line1(5);
		out += line3(1);
		out += "\u2550\u2550\u2550\u2562\n";
		horizontaleLines[13] = out;
		
		// Linie 15
		out = "\n\u255F";
		out += line1(2);
		out += line14();
		out += line2(2);
		out += line9();
		out += line1(1);
		out += line5();
		out += line2(1);
		out += line9();
		out += line1(2);
		out += line5();
		out += line2(1);
		out += line9();
		out += line5();
		out += line2(2);
		out += line9();
		out += line1(4);
		out += line4(1);
		out += line1(1);
		out += lineE();
		horizontaleLines[14] = out;

		// Line16
		out = "\n\u255A";
		out += line11(23);
		out += "\u2550\u2550\u2550\u2569";
		out += line11(1);
		out += "\u2550\u2550\u2550\u255D\n";
		horizontaleLines[15] = out;
	}

		
	//folgende Methoden dienen dem erstellen von Teilstücken der horizontalen Feldlinien
	
	public String line1(int j) {
		String out = "";
		for (int i = 0; i < j; i++) 
			out += "\u2508\u2508\u2508\u253C";
		return out;
	}

	public String line2(int j) {
		String out = "";
		for (int i = 0; i < j; i++) 
			out += "\u2550\u2550\u2550\u256A";
		return out;
	}

	public String line3(int j) {
		String out = "";
		for (int i = 0; i < j; i++) 
			out += "\u2508\u2508\u2508\u256A";
		return out;
	}

	public String line4(int j) {
		String out = "";
		for (int i = 0; i < j; i++) 
			out += "\u2508\u2508\u2508\u256B";
		return out;
	}

	public String line5() {
		return "\u2508\u2508\u2508\u255A";
	}

	public String line6() {
		return "\u2508\u2508\u2508\u2554";
	}

	public String line7() {
		return "\u2550\u2550\u2550\u2557";
	}

	public String line8() {
		return "\u2508\u2508\u2508\u255D";
	}

	public String line9() {
		return "\u2550\u2550\u2550\u255D";
	}

	public String line10() {
		return "\u2550\u2550\u2550\u255D";
	}

	public String line11(int i) {
		String out = "";
		for (int j = 0; j < i; j++) 
			out += "\u2550\u2550\u2550\u2567";
		return out;
	}

	public String line12() {
		return "\u2508\u2508\u2508\u2554";
	}

	public String line13() {
		return "\u2550\u2550\u2550\u255D";
	}

	public String line14() {
		return "\u2508\u2508\u2508\u255A";
	}

	public String line15() {
		return "\u2550\u2550\u2550\u2557";
	}

	public String lineE() {
		return "\u2508\u2508\u2508\u2562\n";
	}

}
