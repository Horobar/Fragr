package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static application.Reader.input;
/**
 * 
 * @author Jonas Hess
 *
 */
public class Combatant extends ObjectOnField implements PublicAccess {
	/**
	 * Attribute
	 */

	private int hitpoints;
	private int speed;
	private int movePoints;
	private int accuracy;
	private int amountFrags = 0;
	private boolean fraged;
	private int ammo;
	private String name;
	private String[] aside;

	
	
	/**
	 * @param xCoord
	 * @param yCoord
	 * @throws FileNotFoundException
	 */
	
	public Combatant(String name) 
	{
		super();
		setupVars();
		this.name = name;
		super.name = name;
		super.symbol = createSymbol(name);
	}

	
	
	/**
	 * Im Constructor zu setztende Variabeln
	 */
	
	public void setupVars() 
	{
		setMovable(true);
		setShootable(true);
		setPassable(false);
		super.type = "COMBATANT";
		super.zIndex = 2;
		this.ammo = 2;
	}

	
	
	/**
	 * Kreiert ein Kürzel aus den ersten 3 Buchstaben eines Namens
	 * @param name
	 * @return Symbol des Combatanten
	 */
	
	public String createSymbol(String name) 
	{
		String symbol = name.substring(0, 3);
		return symbol;
	}

	
	/**
	 * Führt Wizard zur Erstellung eines neuen Kämpfers aus
	 * 
	 * @param reader
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void setupCombatant() throws NumberFormatException, IOException 
	{

		int points = 7;

		while (points > 0) 
		{
			System.out.println("Du hast drei Werte: Leben, Geschwindigkeit und Zielsicherheit."
					+ "Darauf kannst du 7 Punkte verteilen. \nAuf jeden Wert im Minimum 1 Punkt und Maxiaml 4 Punkte");
			System.out.println("Setze Punkte auf Leben (1-" + getPointsToSet(points) + "): ");

			setHitpoints(input(1, getPointsToSet(points)));
			points -= getHitpoints();

			if (points <= 0)
				break;

			System.out.println("\nDu hast " + getHitpoints() + " eingeben und noch " + points + " Punkte");
			System.out.println("Setze Punkte auf Geschwindigkeit (1-" + getPointsToSet(points) + "): ");

			speed = input(1, getPointsToSet(points));
			points -= speed;

			if (points <= 0)
				break;

			System.out.println("\nDu hast " + speed + " eingeben und noch " + points + " Punkte");
			System.out.println("Setze Punkte auf Zielsicherheit (1-" + getPointsToSet(points) + "): ");

			accuracy = input(1, getPointsToSet(points));
			points -= accuracy;

			if (points <= 0)
				break;

			System.out.println("\nDu hast " + accuracy + " eingeben und noch " + points + " Punkte");
		}

		System.out.println("Danke, der nächste bitte.");
	}
	
	
	/**
	 * Prüft, ob noch mehr als 4 Punkte zu verteilen sind
	 * @param points
	 * @return Anzahl pro Attribut zu verteilende Punkte
	 */
	
	public int getPointsToSet(int points) 
	{
		if (points > 4)
			return 4;
		else
			return points;
	}
	
	
	
	
	/**
	 * Berechnet die Felder, die ein Spieler jeden Zug ziehen kann
	 */
	
	public void calcMovePoints() 
	{
		for (int i = 0; i < this.speed; i++) 
		{
			int roll = rollDice(6);
			System.out.println("Du hast eine " + roll + " gewürfelt.");
			this.movePoints += roll;
			aside = TextManager.generateAside(this);
			BG.drawBattleground(aside);

		}
		System.out.println("Du hast " + this.movePoints + " Bewegungspunkte");
	}

	
	/**
	 * roll max-sided die
	 * 
	 * @param max
	 * @return random
	 */
	public int rollDice(int max) {
		return (int) ((Math.random() * (max - 1)) + 1);
	}

	
	
	/**
	 * Schiesst eine Kugel ab, ruft bei Treffer die Methode getHit() auf
	 * @throws IOException
	 */
	
	public void shoot() throws IOException 
	{
		if (this.ammo > 0) 
		{
			boolean falseInput = true;
			int range = 0;
			String direction = "";
			
			System.out.println("Du willst also schiessen?");
			System.out.println("Mal schauen wie weit du kommst(Zielsicherheit*W6)");
			
			for (int i = 0; i < accuracy; i++) 
			{
				range += rollDice(6);
			}
			System.out.println("Du kommst " + range + " Felder weit.");

			
			do 
			{
				System.out.print("Wähle deine Schussrichtung(W,A,S,D): ");
				direction = input().toLowerCase();
				
				if (direction.equals("a") || direction.equals("w") || direction.equals("s") || direction.equals("d")) {
					falseInput = false;
					this.ammo -= 1;
				} 
				else 
				{
					System.out.println("Ungültiger Input");
					falseInput = true;
				}
				
			} while (falseInput);

			int[] newCoords = this.Coords.clone();
			for (int i = 0; i < range; i++) 
			{

				newCoords = getNewCoords(newCoords, direction);
				Optional<ObjectOnField> onField = FieldManager.isFieldEmptyWithStream(newCoords);
				Bullet bullet = new Bullet(newCoords);
				OBJECTS_ON_BG.add(bullet);
				
				if (FieldManager.isPassageForbidden(newCoords, this.Coords)) 
				{
					System.out.println("Gratuliere, du hast die Wand getroffen");
					OBJECTS_ON_BG.remove(bullet);
					BG.drawBattleground(aside);
					break;
				}
				
				else if (onField.isPresent() && onField.get().getZIndex() > 1) 
				{
					boolean hasFraged = onField.get().getHit();
					OBJECTS_ON_BG.remove(bullet);
					BG.drawBattleground(aside);
					if (hasFraged)
						amountFrags += 1;
					break;
				}
				
				else if (newCoords[0] < 0 || newCoords[0] == BG.getxFields() || newCoords[1] < 0 || newCoords[1] == BG.getyFields()) 
				{
					System.out.println("Du hast vom Spielbrett runter geschossen");
					OBJECTS_ON_BG.remove(bullet);
					BG.drawBattleground(aside);
					break;
				} 
				
				else 
				{
					// System.out.println(this.Coords[0] + ", " + this.Coords[1]);

					BG.drawBattleground(aside);
					OBJECTS_ON_BG.remove(bullet);
					try {
					 Thread.sleep(200);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
			
			aside = TextManager.generateAside(this);
			BG.drawBattleground(aside);

		} 
		
		else 
			System.out.println("Du hast alle Munition verschossen und kannst nicht mehr schiessen");
	}

	
	
	/**
	 * Geht einen Schritt auf dem Spielfeld
	 * @throws IOException
	 */
	public void move() throws IOException 
	{
		if (this.movePoints > 0) 
		{

			String direction = "";
			boolean notWalked = true;
			int[] newCoords = new int[2];
			
			System.out.println("Du willst also laufen?");
			System.out.println("Du kommst noch " + this.movePoints + " Felder weit.");

			do {
				newCoords[0] = this.Coords[0];
				newCoords[1] = this.Coords[1];

				System.out.println("In welche Richtung willst du gehen(WASD): ");

				direction = input();
				newCoords = getNewCoords(this.Coords, direction);

				Optional<ObjectOnField> onField = FieldManager.isFieldEmptyWithStream(newCoords);
				
				if (FieldManager.isPassageForbidden(newCoords, this.Coords)) {
					System.out.println("Da ist ne Wand, da kommst du nicht durch!");
				} 
				
				else if (onField.isPresent() && onField.get().getZIndex() > 1) 
				{
					TextManager.printText("notPassable");
					System.out.println(onField.get().name);
				} 
				
				else if (newCoords[0] < 0 || newCoords[0] == BG.getxFields() || newCoords[1] < 0 || newCoords[1] == BG.getyFields()) 
				{
					newCoords[0] = this.Coords[0];
					newCoords[1] = this.Coords[1];
					TextManager.printText("leaveBG");
				} 
				
				else 
				{
					notWalked = false;
					this.movePoints -= 1;
				}
			} while (notWalked);

			for (int i = 0; i < 2; i++) 
			{
				this.Coords[i] = newCoords[i];
				super.Coords[i] = newCoords[i];
			}
			
			aside = TextManager.generateAside(this);
			BG.drawBattleground(aside);

		} 
		else 
			System.out.println("\nDu hast keine Bewegungspunkte mehr und kannst nicht mehr laufen.");
	}

	
	
	/**
	 * Ein Spieler wurde getroffen, ein HP Abzug
	 */
	@Override
	public boolean getHit() 
	{
		System.out.println("Aua, du hast mich getroffen");
		this.hitpoints -= 1;
		
		if (this.hitpoints > 0)
		{
			System.out.println(this.name + " hat noch " + this.hitpoints + " Trefferpunkte");
			return false;
		} 
		
		else 
		{
			System.out.println("Gratuliere du hast " + this.name + " gefragt");
			this.dead(this);
			return true;
		}
	}
	
	
	
	/**
	 * Ein Spieler ist gestorben, er wird am Anfang seiner Runde an einem random-Spawnpoint wieder
	 * auftauchen
	 * 
	 * @param comb
	 */
	private void dead(Combatant comb) 
	{
		comb.setFraged(true);
		this.symbol = "\u2620";
		comb.setPassable(true);
	}


	/**
	 * der gestorbene Spieler wird am Anfang seiner Runde an einen random-Spawnpunkt neu gesetzt
	 */
	public void respawn() 
	{
		this.setFraged(false);
		this.symbol = this.name.substring(0, 3);
		this.setPassable(false);
		
		int i = Utilitys.generateRandomInt(0, 5);
		
		this.Coords[0] = SpawnPoint.spawnpoints.get(i).getCoords()[0];
		this.Coords[1] = SpawnPoint.spawnpoints.get(i).getCoords()[1];
		
		aside = TextManager.generateAside(this);
		BG.drawBattleground(aside);
	}

	
	/**
	 * Durch die Eingabe der bestehenden Koordinaten und der Richtung werden die neuen Koordinaten ein Feld weiter
	 * berechnet
	 * 
	 * @param i
	 * @param direction
	 * @return neue Koordinaten
	 */
	public int[] getNewCoords(int[] i, String direction) 
	{
		int[] newCoords = new int[2];
		newCoords[0] = i[0];
		newCoords[1] = i[1];

		switch (direction) 
		{
		case "A":
		case "a":
			newCoords[0] -= 1;
			break;
		case "W":
		case "w":
			newCoords[1] -= 1;
			break;
		case "S":
		case "s":
			newCoords[1] += 1;
			break;
		case "D":
		case "d":
			newCoords[0] += 1;
			break;
		default:
			System.out.println("Bitte nur w, a, s, d als Eingabe");
			break;
		}

		return newCoords;
	}

	
	public String getName() {
		return name;
	}
	
	/**
	 * get speed
	 * 
	 * @return speed
	 */

	public int getSpeed() {
		return speed;
	}


	/**
	 * get move points
	 * 
	 * @return movePoints
	 */
	public int getMovePoints() {
		return movePoints;
	}

	/**
	 * get Ammo
	 * 
	 * @return ammo
	 */
	public int getAmmo() {
		return ammo;
	}


	/**
	 * get amount of frags
	 * 
	 * @return amountFrags
	 */
	public int getAmountFrags() {
		return amountFrags;
	}


	/**
	 * is Fraged
	 * 
	 * @return fraged
	 */
	public boolean isFraged() {
		return fraged;
	}


	/**
	 * get Hitpoints
	 * 
	 * @return Hitpoints
	 */
	public int getHitpoints() {
		return hitpoints;
	}

	/**
	 * set Hitpoint
	 * 
	 * @param hitpoints
	 */
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	/**
	 * get Accuracy
	 * 
	 * @return
	 */
	public int getAccuracy() {
		return accuracy;
	}

	public void setFraged(boolean isFraged) {
		this.fraged = isFraged;
	}

}
