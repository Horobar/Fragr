package application;



import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * - Verwaltet den Ablauf des ganzen Spiels 
 * 		/ bereitet das Spiel vor 
 * 		/ legt ein Array mit den Teilnehmern an 
 * 		/ verwaltet ihre Werte 
 * 		/ übergibt den Spielern die Züge
 * 		/ prüft ob ein Spieler gewonnen hat.
 * 
 * @author Jonas Hess
 *
 */
public class GameManager implements PublicAccess{
	private int players;
	private String[] playOrder;
	private Map<String, Combatant> combatants = new HashMap<String, Combatant>();

	public GameManager(int players) {
		this.players = players;
		playOrder = new String[players];
	}
	
	/**
	 * 1. Ruft das Setup auf
	 * 2. Ruft für jeden Spieler den Wizard zur Spezifizierung der Spielfigur auf
	 * 3. erzeugt die Spielreihenfolge
	 * 4. Gibt eine Zusammenfassung jedes Spielers aus
	 * 5. Gibt die Zugreihenfolge aus
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void startGame() throws NumberFormatException, IOException {
		/**
		 * Frage Namen ab und erstelle für jeden Spieler einen Key => Value Eintrag in
		 * der map combatants
		 */
		setup();
		
		/**
		 * Lese Attributswerte der Spieler ein
		 */
		for (Combatant comb : combatants.values()) {
			comb.setupCombatant();
		}

		/**
		 * erzeuge eine randomisierte Spielreiheinfolge
		 */
		createPlayOrder();

		/**
		 * Gib eine Zusammenfassung aller Spieler
		 */
		combatants.forEach(
				(k, v) -> System.out.println("****************" + "\nName: " + k + "\nHitpoints: " + v.getHitpoints()
						+ "\nSpeed: " + v.getSpeed() + "\nAccuracy: " + v.getAccuracy() + "\n****************\n"));
		
		/**
		 * Gib die Spielreihenfolge bekannt
		 */
		System.out.println("Die Reihenfolge der Spielerinnen und Spieler lautet:");
		for (int i = 0; i < playOrder.length; i++) {
			System.out.println((i + 1) + ". " + playOrder[i]);
		}
	}

	
	/**
	 * Setze die Spieler auf die Spawnpoints, zuerst kann der Einfachheit nicht
	 * gewählt werden
	 */
	public void placeCombatantsInit() {
		for (int i = 0; i < playOrder.length; i++) {
			for(ObjectOnField obj : OBJECTS_ON_BG) {
				if(obj.name == playOrder[i]) {
					obj.Coords = SpawnPoint.spawnpoints.get(i).Coords;
				}
			}
		}
	}

	/**
	 * Testmethode: gibt die Koordinaten aller Spieler aus
	 */
	public void printCoordsCombatants() {
		for (Combatant comb : combatants.values()) {
			System.out.println(comb.Coords[0] + ", " + comb.Coords[1]);
		}

	}

	/**
	 * 1. Fragt die Spieler nach ihren Namen
	 * 2. erstellt für jeden Spieler einen Kämpfer
	 * 3. Fügt jeden Kämpfer der globalen Liste OBJECTS_ON_BG hinzu
	 * 
	 * @throws IOException
	 */

	public void setup() throws IOException {

		System.out.println(players + "! So viele? Dann gebt mal eure Namen ein: ");

		for (int i = 0; i < players; i++) {
			System.out.print("Player " + (i + 1) + ": ");
			String name = Reader.input();
			combatants.put(name, new Combatant(name));
			OBJECTS_ON_BG.add(combatants.get(name));
		}
	}

	/**
	 * Fügt all Keys(Spielernamen), einem Array hinzu, dieser wird in eine List
	 * transformiert, gemischelt, und wieder in ein Array transformiert
	 */
	public void createPlayOrder() {
		int i = 0;
		for (String comb : combatants.keySet()) {
			playOrder[i] = comb;
			i++;
		}
		List<String> intList = Arrays.asList(playOrder);
		Collections.shuffle(intList);
		intList.toArray(playOrder);
		
	}
	/**
	 * Solange kein Spieler mehr als 2 Frags hat, geht das Spiel weiter
	 * Die Spieler kommen der Reihe nach dran.
	 * Für jeden Zug wird ein neuer TurnManager erstellt. Der den nächsten Zug managet
	 * Am Ende des Zuges wird überprüft, ob der Spieler genug Frags hat, falls ja ist das Spiel sofort zu Ende.
	 * 
	 * @throws IOException
	 */
	public void playTheGame() throws IOException {
		boolean enoughFrags = true;
		
		while(enoughFrags)
		{
			for(int i = 0; i < playOrder.length; i++)
			{
				TurnManager tM = new TurnManager(combatants.get(playOrder[i]));
				tM.manageTurn();
				
				if(combatants.get(playOrder[i]).getAmountFrags() == 3) {
					enoughFrags = false;
					break;
				}
			}
			
		}
	}
}
