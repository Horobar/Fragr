package application;

import java.io.IOException;
import static application.Reader.input;

/**
 * Diese Klasse managed die einzelnen Spielzüge
 * 
 * @author Jonas Hess
 *
 */
public class TurnManager {
	Combatant combatant;
	boolean stillTurn;
	boolean stdBool1;
	boolean stdBool2;
	boolean firstIteration;
	String[] aside;

	public TurnManager(Combatant combatant) {
		this.combatant = combatant;
		firstIteration = true;
		stillTurn = true;
		stdBool1 = true;
		stdBool2 = true;
	}

	/**
	 * Zug-Ablauf:
	 * 	1. Falls Spieler tot, wird er an random-Spawnpoint wieder neu gesetzt
	 * 	2. Der Spieler kann entscheiden seinen Zug zu überspringen
	 * 	3. Falls der Spieler keine MovePoints oder Ammo mehr hat wird sein Zug beendet
	 * 	4. Der Spieler entscheidet zu schiessen oder zu laufen
	 * 	5. Der Spieler schiesst oder läuft
	 * 
	 * @throws IOException
	 */
	public void manageTurn() throws IOException {

		/**
		 * Falls der Spieler in der letzten Runde getötet wurde, kann er zu beginn seine
		 * Figur an einem Respawnpunkt neu platzieren
		 */

		if (combatant.isFraged()) {
			combatant.respawn();
		}

		/**
		 * Zu Beginn der Runde muss jeder Spieler seine Bewegungspunkte auswürfeln, die
		 * Formel lautet speed*W6
		 */
		if (firstIteration) {
			System.out.println("\nEs ist dein Zug " + combatant.getName());
			combatant.calcMovePoints();
			firstIteration = false;
		}

		while (stillTurn) {
			aside = TextManager.generateAside(combatant);

			stdBool2 = true;
			if (combatant.getAmmo() <= 0 && combatant.getMovePoints() <= 0) {
				stillTurn = false;
				firstIteration = true;
				stdBool2 = false;
				System.out.println("\nDu kannst nichts mehr machen, dein Zug wird beendet");
			}

			/**
			 * Abbruchmöglichkeit um aus Turn-Loop auszubrechen
			 */
			while (stdBool1) {
				stdBool1 = false;
				TextManager.printText("endTurn");
				switch (input()) {
				case ("Y"):
				case ("y"):
					TextManager.printText("nextTurn");
					firstIteration = true;
					stillTurn = false;
					stdBool2 = false;
					break;
				case "N":
				case "n":
					System.out.println("Ok, leg los.");
					stdBool1 = false;
					break;
				default:
					stdBool1 = true;
					System.out.println("Nur Y oder n eingeben");
					break;
				}
			}

			while (stdBool2) {
				stdBool2 = false;
				TextManager.printText("shootOrMove");

				switch (input()) {
				case "s":
				case "S":
					combatant.shoot();
					break;
				case "m":
				case "M":
					combatant.move();
					break;
				default:
					stdBool2 = true;
					System.out.println("Nur s/S oder m/M eingeben");
					break;
				}
			}
		}

		System.out.println("\nDein Zug ist fertig, der nächste ist dran");
	}

}
