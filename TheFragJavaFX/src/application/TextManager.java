package application;

/**
 * Der Übersicht halber werden besonders grosse Strings in diese Klasse ausgelagert und können
 * als static Methoden wieder aufgerufen werden
 * 
 * @author Jonas Hess
 *
 */
public class TextManager implements PublicAccess {
	private static String[] aside = new String[BG.getyFields()];

	public TextManager() {
		addTexts();
	}
	
	/**
	 * Erstellen und hinzufügen mancher Textbausteine zu einer Map(String, String)
	 */
	public void addTexts() {
		OUTPUTS.put("endTurn", "Willst du den Zug beenden? (Y/n");
		OUTPUTS.put("Welcome", "Willkommen bei FRAG. Dem Spiel mit ordentlich BUMMS!");
		OUTPUTS.put("Wieviele", "Wieviele SpielerInnen wollen mitmachen?");
		OUTPUTS.put("nextTurn", "Danke. Der nächste ist dran.");
		OUTPUTS.put("shootOrMove",
				"\nDu hast zwei Möglickeiten, schiessen (S/s) oder laufen (m/M) \nWas willst du tun: ");
		OUTPUTS.put("notPassable", "Das Feld, auf das du gehen willst ist besetzt. Geh in eine andere Richtung");
		OUTPUTS.put("leaveBG", "Wenn du weitergehst, verlässt du das Spielfeld und fällst runter");
	}

	/**
	 * Prints Text from OUTPUTS-Map with Key in
	 * 
	 * @param in
	 */
	public static void printText(String in) {
		System.out.println(OUTPUTS.get(in));
	}

	/**
	 * Speicherung und ausgabe des Titels
	 */
	public static void writeTitel() {
		String[] titel = new String[7];

		titel[0] = "______                             _  _ ______                             _  _ ";
		titel[1] = "| ___ \\                           | |(_)| ___ \\                           | |(_)";
		titel[2] = "| |_/ / _   _  _ __ ___   ___   __| | _ | |_/ / _   _  _ __ ___   ___   __| | _ ";
		titel[3] = "|    / | | | || '_ ` _ \\ / __| / _` || || ___ \\| | | || '_ ` _ \\ / __| / _` || |";
		titel[4] = "| |\\ \\ | |_| || | | | | |\\__ \\| (_| || || |_/ /| |_| || | | | | |\\__ \\| (_| || |";
		titel[5] = "\\_| \\_| \\__,_||_| |_| |_||___/ \\__,_||_|\\____/  \\__,_||_| |_| |_||___/ \\__,_||_|";
		titel[6] = "                                                                                ";

		for (int i = 0; i < titel.length; i++) {
			System.out.println(titel[i]);
		}
	}

	/**
	 * Neben dem Spielfeld wird diese Infotafel angezeigt,
	 * @param comb
	 * @return
	 */
	public static String[] generateAside(Combatant comb) {
		String[] constr = new String[9];
		String hp = "	\u2551 HP: " + comb.getHitpoints();
		String speed = "	\u2551 Speed: " + comb.getSpeed();
		String accur = "	\u2551 Accur: " + comb.getAccuracy();
		String wasFragged = "Fragd: 3 \u2551";
		String movepoints = "Movepoints: " + comb.getMovePoints() + " \u2551";
		String ammo = "Ammo: " + comb.getAmmo() + " \u2551";
		String frags = comb.getAmountFrags() + " \u2551";
		constr[1] = "	\u2551 It's your turn: " + comb.getName() + " - Let's Rock! \u2551";

		constr[3] = calcSpace(constr[1], hp, wasFragged);
		constr[4] = calcSpace(constr[1], speed, movepoints);
		constr[5] = calcSpace(constr[1], accur, ammo);
		constr[7] = calcSpace(constr[1], "	\u2551 Fraggs:", frags);

		int boxwidth = constr[1].length();
		String between = "";

		for (int i = 0; i < boxwidth - 3; i++) {
			between += "\u2550";
		}

		// constr[7] = "\u2551" + "Frags: " + comb.getAmountFrags()+ " \u2551";
		constr[0] = "	\u2554" + between + "\u2557";
		constr[2] = "	\u2560" + between + "\u2563";
		constr[6] = "	\u2560" + between + "\u2563";
		constr[8] = "	\u255A" + between + "\u255D";

		for (int i = 0; i < aside.length; i++) {
			aside[i] = "";
		}
		for (int i = 0; i < constr.length; i++) {
			aside[i + 4] = constr[i];
		}

		return aside;
	}
	/**
	 * Die breite der Infotafel wird anhand der ersten Zeile berechnet.
	 * @param ref
	 * @param work1
	 * @param work2
	 * @return
	 */
	public static String calcSpace(String ref, String work1, String work2) {
		String out = "";
		String between = "";
		int counter = 0;
		
		do {
			out = work1 + between + work2;
			between += ".";
			counter++;
		} while (out.length() != ref.length());
		return out;
	}
}
