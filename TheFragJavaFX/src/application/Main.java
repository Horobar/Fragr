package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main implements PublicAccess {
	Battleground bg;

	// Liste mit Spielern
	List<Combatant> Combatants = new ArrayList<Combatant>();

	public static void main(String[] args) throws IOException {

		TextManager.writeTitel();

		String consoleInput;
		new TextManager();
		int players = 0;
		TextManager.printText("Welcome");
		TextManager.printText("Wieviele");

		consoleInput = Reader.input();
		players = Integer.parseInt(consoleInput);

		GameManager gm = new GameManager(players);
		FieldManager fm = new FieldManager();

		gm.startGame();
		gm.placeCombatantsInit();
		gm.printCoordsCombatants();
		gm.playTheGame();

	}

}
