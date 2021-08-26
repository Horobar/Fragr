package application;

import java.io.FileNotFoundException;

/**
 * Creates a generic Object on the playfield, the Object has x-, and y-Coords,
 * which sets its Position on the Field and some bools to specify its behaviour
 */
public class ObjectOnField {
	// Attributes
	protected String type; //Flag um FieldManager anzuzeigen, wei auf das Objekt zu reagieren ist
	protected String symbol;
	
	protected boolean isMovable = false;
	protected boolean isShootable = false;
	protected boolean isPassable = true;
	protected String name = "";
	
	// Koordinatenposition
	protected int[] Coords = { 0, 0 };
	public int[] getCoords() {
		return Coords;
	}

	protected int zIndex;

	protected Battleground bg;

	

	/**
	 * Methods
	 * 
	 * @throws FileNotFoundException
	 */

	// Constructor
	public ObjectOnField() {
		
	}
	
	public ObjectOnField(int xCoord, int yCoord) throws FileNotFoundException {
		this.Coords[0] = xCoord;
		this.Coords[1] = yCoord;
	}


	// Getter & Setter
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public void setShootable(boolean isShootable) {
		this.isShootable = isShootable;
	}

	public void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}

	public int getZIndex() {
		return zIndex;
	}
	
	/**
	 * Zeigt an, was getroffen wurde
	 * 
	 * @return
	 */
	public boolean getHit() {
		System.out.println("Haha, du hast " + this.type + "getroffen");
		return true;
	}

}
