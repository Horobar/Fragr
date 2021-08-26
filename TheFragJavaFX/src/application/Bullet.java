/**
 * Diese Klasse representiert eine Kugel, die auf dem Spielfeld angezeigt werden kann
 */
package application;

/**
 * 
 * 	@author jhkw
 *	
 */
public class Bullet extends ObjectOnField {
	
	public Bullet(int[] Coords) {
		symbol = " o ";
		super.name = "bullet";
		super.type = "bullet";
		super.Coords[0] = Coords[0];
		super.Coords[1] = Coords[1];
	}
	
}
