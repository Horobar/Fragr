package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Listen, auf die im ganzen Programm einfach zugegriffen werden¨
 * soll
 * 
 * @author Jonas Hess
 *
 */
public interface PublicAccess {
	public static final List<ObjectOnField> OBJECTS_ON_BG = new ArrayList<ObjectOnField>();
	public static final HashMap<String, String> OUTPUTS = new HashMap<String, String>();
	public static final Battleground BG = new Battleground();
}
