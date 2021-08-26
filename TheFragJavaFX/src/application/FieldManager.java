package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FieldManager implements PublicAccess{
	Battleground bg;
	private static ArrayList<int[][]> forbiddenPassages = new ArrayList<int[][]>();
	

	/**
	 * erstellt einen FieldManager für das eingegebene Spielfeld, 
	 * dabei lädt er die manuell eingegebenen verbotenen Übergänge zweier Felder
	 * @param bg
	 * @throws FileNotFoundException
	 */
	public FieldManager() throws FileNotFoundException {
		SpawnPoint.objectsToList();
		OBJECTS_ON_BG.addAll(SpawnPoint.spawnpoints);
		setupForbiddenPassages();
	}
	

	/**
	 * Diese Funktion prüft, ob ein Objekt auf einem Feld liegt, ist das der Fall, gibt sie das Objekt zurück
	 * ansonsten ist der Return null, dabei wird stehts das letzthinzugefügte Objekt beachte
	 * 
	 * @param coords
	 * @return obOut
	 */
	public static Optional<ObjectOnField> isFieldEmptyWithStream(int[] coords) {
		List<ObjectOnField> out = new ArrayList<ObjectOnField>();
		out = OBJECTS_ON_BG
				.stream()
				.filter( obj -> obj.getCoords()[0] == coords[0] && obj.getCoords()[1] == coords[1])
				.sorted(Comparator.comparing( obj -> obj.getZIndex()))
				.collect(Collectors.toList());
		
		Collections.reverse(out);
	
		return out.stream().findFirst();
	}
	
	/**
	 * Testmethode: zeigt alle verbotenenen Übergänge in der Konsole an
	 */
	public static void printForbiddenPassages() {
		forbiddenPassages
		.stream()
		.forEach(forb -> System.out.println("Forb[0][0]: " + forb[0][0] 
				+ ", Forb[0][1]: " + forb[0][1]
						+ ", Forb[1][0]: " + forb[1][0]
						+ ", Forb[1][1]: " + forb[1][1]));
	}
	
	/**
	 * Prüft ob von einem Feld auf das andere gewechselt werden darf 
	 * @param coord1
	 * @param coord2
	 * @return
	 */
	public static boolean isPassageForbidden(int[] coord1, int[] coord2) {
		
		return forbiddenPassages
			.stream()
			.anyMatch(forbPas ->
				(coord1[0] == forbPas[0][0] && coord1[1] == forbPas[0][1] && coord2[0] == forbPas[1][0] && coord2[1] == forbPas[1][1]) ||
				(coord2[0] == forbPas[0][0] && coord2[1] == forbPas[0][1] && coord1[0] == forbPas[1][0] && coord1[1] == forbPas[1][1]));
	}
	
	/**
	 * fügt einen verbotenenen horizontalen Übergang zur Liste hinzu
	 * @param x1
	 * @param y1
	 */
	public void addForbiddenPassageHorizontal(int x1, int y1) {
		int[][] forbPas = {{0,0},{0,0}};
		forbPas[0][0] = x1;
		forbPas[0][1] = y1;
		forbPas[1][0] = x1 + 1; 
		forbPas[1][1] = y1;	
		forbiddenPassages.add(forbPas);
	}
	
	/**
	 * fügt einen verbotenenen vertikalen Übergang zur Liste hinzu
	 * @param x1
	 * @param y1
	 */
	public void addForbiddenPassageVertikal(int x1, int y1) {
		int[][] forbPas = {{0,0},{0,0}};
		forbPas[0][0] = x1;
		forbPas[0][1] = y1;
		forbPas[1][0] = x1; 
		forbPas[1][1] = y1 + 1;	
		forbiddenPassages.add(forbPas);
	}
	
	/**
	 * erfasst alle verbotenenen Übergänge
	 */
	public void setupForbiddenPassages() {
		addForbiddenPassageHorizontal(17, 0);
		addForbiddenPassageHorizontal(20, 0);
		
		addForbiddenPassageHorizontal(17, 1);
		addForbiddenPassageHorizontal(20, 1);
		
		addForbiddenPassageHorizontal(0, 3);
		addForbiddenPassageHorizontal(4, 3);
		addForbiddenPassageHorizontal(5, 3);
		addForbiddenPassageHorizontal(7, 3);
		addForbiddenPassageHorizontal(11, 3);
		addForbiddenPassageHorizontal(15, 3);
		addForbiddenPassageHorizontal(16, 3);
		
		addForbiddenPassageHorizontal(0, 4);
		addForbiddenPassageHorizontal(3, 4);
		addForbiddenPassageHorizontal(5, 4);
		addForbiddenPassageHorizontal(7, 4);
		addForbiddenPassageHorizontal(9, 4);
		addForbiddenPassageHorizontal(11, 4);
		addForbiddenPassageHorizontal(13, 4);
		addForbiddenPassageHorizontal(15, 4);
		addForbiddenPassageHorizontal(17, 4);
		
		addForbiddenPassageHorizontal(0, 5);
		addForbiddenPassageHorizontal(2, 5);
		addForbiddenPassageHorizontal(5, 5);
		addForbiddenPassageHorizontal(7, 5);
		addForbiddenPassageHorizontal(10, 5);
		addForbiddenPassageHorizontal(11, 5);
		addForbiddenPassageHorizontal(13, 5);
		addForbiddenPassageHorizontal(19, 5);
		
		addForbiddenPassageHorizontal(0, 6);
		addForbiddenPassageHorizontal(2, 6);
		addForbiddenPassageHorizontal(5, 6);
		addForbiddenPassageHorizontal(7, 6);
		addForbiddenPassageHorizontal(10, 6);
		
		addForbiddenPassageHorizontal(0, 7);
		addForbiddenPassageHorizontal(14, 7);
		addForbiddenPassageHorizontal(19, 7);
		addForbiddenPassageHorizontal(22, 7);
		
		addForbiddenPassageHorizontal(0, 8);
		addForbiddenPassageHorizontal(14, 8);
		
		addForbiddenPassageHorizontal(0, 9);
		addForbiddenPassageHorizontal(14, 9);
		addForbiddenPassageHorizontal(19, 9);
		addForbiddenPassageHorizontal(22, 9);
		
		addForbiddenPassageHorizontal(0, 10);
		
		addForbiddenPassageHorizontal(0, 11);
		addForbiddenPassageHorizontal(2, 11);
		
		addForbiddenPassageHorizontal(0, 12);
		addForbiddenPassageHorizontal(4, 12);
		addForbiddenPassageHorizontal(7, 12);
		addForbiddenPassageHorizontal(9, 12);
		addForbiddenPassageHorizontal(12, 12);
		addForbiddenPassageHorizontal(14, 12);
		addForbiddenPassageHorizontal(15, 12);
		addForbiddenPassageHorizontal(18, 12);
		
		addForbiddenPassageHorizontal(1, 13);
		addForbiddenPassageHorizontal(4, 13);
		
		addForbiddenPassageHorizontal(2, 14);
		addForbiddenPassageHorizontal(5, 14);
		addForbiddenPassageHorizontal(7, 14);
		addForbiddenPassageHorizontal(9, 14);
		addForbiddenPassageHorizontal(12, 14);
		addForbiddenPassageHorizontal(14, 14);
		addForbiddenPassageHorizontal(15, 14);
		addForbiddenPassageHorizontal(18, 14);
		
		addForbiddenPassageHorizontal(23, 15);
		
		addForbiddenPassageVertikal(2, 11);
		addForbiddenPassageVertikal(2, 13);
		
		addForbiddenPassageVertikal(3, 4);
		addForbiddenPassageVertikal(3, 7);
		addForbiddenPassageVertikal(3, 10);
		addForbiddenPassageVertikal(3, 14);
		
		addForbiddenPassageVertikal(4, 3);
		addForbiddenPassageVertikal(4, 7);
		addForbiddenPassageVertikal(4, 10);
		addForbiddenPassageVertikal(4, 14);
		
		addForbiddenPassageVertikal(5, 1);
		addForbiddenPassageVertikal(5, 10);
		addForbiddenPassageVertikal(5, 11);
		addForbiddenPassageVertikal(5, 13);
		addForbiddenPassageVertikal(5, 14);
		
		addForbiddenPassageVertikal(6, 1);
		
		addForbiddenPassageVertikal(7, 1);
		
		addForbiddenPassageVertikal(8, 1);
		addForbiddenPassageVertikal(8, 11);
		addForbiddenPassageVertikal(8, 14);
		
		addForbiddenPassageVertikal(9, 7);
		addForbiddenPassageVertikal(9, 11);
		addForbiddenPassageVertikal(9, 14);
		
		addForbiddenPassageVertikal(10, 7);
		addForbiddenPassageVertikal(10, 12);
		addForbiddenPassageVertikal(10, 13);
		
		addForbiddenPassageVertikal(11, 7);
		addForbiddenPassageVertikal(11, 12);
		addForbiddenPassageVertikal(11, 13);
		
		addForbiddenPassageVertikal(12, 2);
		addForbiddenPassageVertikal(12, 5);
		addForbiddenPassageVertikal(12, 7);
		addForbiddenPassageVertikal(12, 12);
		addForbiddenPassageVertikal(12, 13);
		
		addForbiddenPassageVertikal(13, 2);
		addForbiddenPassageVertikal(13, 11);
		addForbiddenPassageVertikal(13, 14);
		
		addForbiddenPassageVertikal(14, 2);
		addForbiddenPassageVertikal(14, 5);
		addForbiddenPassageVertikal(14, 11);
		addForbiddenPassageVertikal(14, 14);
		
		addForbiddenPassageVertikal(15, 2);
		addForbiddenPassageVertikal(15, 5);
		addForbiddenPassageVertikal(15, 6);
		addForbiddenPassageVertikal(15, 9);
		
		addForbiddenPassageVertikal(16, 5);
		addForbiddenPassageVertikal(16, 11);
		addForbiddenPassageVertikal(16, 14);
		
		addForbiddenPassageVertikal(17, 3);
		addForbiddenPassageVertikal(17, 5);
		addForbiddenPassageVertikal(17, 6);
		addForbiddenPassageVertikal(17, 9);
		addForbiddenPassageVertikal(17, 11);
		addForbiddenPassageVertikal(17, 14);
		
		addForbiddenPassageVertikal(18, 4);
		addForbiddenPassageVertikal(18, 5);
		addForbiddenPassageVertikal(18, 11);
		addForbiddenPassageVertikal(18, 14);
		
		addForbiddenPassageVertikal(19, 4);
		addForbiddenPassageVertikal(19, 2);
		addForbiddenPassageVertikal(19, 5);
		
		addForbiddenPassageVertikal(20, 6);
		addForbiddenPassageVertikal(20, 9);
		
		addForbiddenPassageVertikal(21, 6);
		addForbiddenPassageVertikal(21, 9);
		
		addForbiddenPassageVertikal(22, 6);
		addForbiddenPassageVertikal(22, 9);
		
		addForbiddenPassageVertikal(25, 13);		
	}
	

}
	
	



