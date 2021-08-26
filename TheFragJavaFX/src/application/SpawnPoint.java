package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * Spawnpoint dienen dem Setzen der Spielfiguren auf dem Feld.
 * 
 * @author Jonas Hess
 *
 */
public class SpawnPoint extends ObjectOnField  {
	public static ArrayList<SpawnPoint> spawnpoints = new ArrayList<SpawnPoint>() ;
	

	public SpawnPoint(int xCoord, int yCoord) throws FileNotFoundException {
		super(xCoord, yCoord);
		setupVars();
	}
	
	public void setupVars() {
		setMovable(false);
		setShootable(false);
		setPassable(true);
		super.type = "Spawnpoint";
		super.symbol = " * ";
		super.zIndex = 1;
	}
	
	/**
	 * Creates all SpawnPoints and add to SpawnPoint-List
	 * @throws FileNotFoundException
	 */
	public static void objectsToList() throws FileNotFoundException {
		spawnpoints.add(new SpawnPoint(0, 0));
		spawnpoints.add(new SpawnPoint(2, 0));
		spawnpoints.add(new SpawnPoint(6, 15));
		spawnpoints.add(new SpawnPoint(16, 7));
		spawnpoints.add(new SpawnPoint(20, 16));
		spawnpoints.add(new SpawnPoint(25, 1));
	}
}
