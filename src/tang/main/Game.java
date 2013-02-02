package tang.main;

import java.io.File;

import tang.helper.MapLoader;
import tang.helper.Updatable;
import tang.model.Map;

public abstract class Game implements Updatable {

	/**
	 * Stores the currently loaded map, so all classes can point to it
	 */
	public static Map loadedMap;

	public abstract int getDisplayWidth();
	public abstract int getDisplayHeight();
	public abstract String getDisplayTitle();
	
	/**
	 * Retrieves the currently loaded map
	 * @return loadedMap
	 */
	public static Map getLoadedMap() {
		return Game.loadedMap;
	}

	
	public static void setLoadedMap(File f) {
		Game.loadedMap = MapLoader.loadMap(f);;
		Game.loadedMap.init();
	}
	
}
