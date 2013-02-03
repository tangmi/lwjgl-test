package tang.main;

import java.io.File;

import org.lwjgl.opengl.Display;

import tang.helper.Console;
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
	public static final Map getLoadedMap() {
		return Game.loadedMap;
	}
	
	public static final void setLoadedMap(File f) {
		Game.loadedMap = MapLoader.loadMap(f);
		Game.loadedMap.init();
	}
	
	/**
	 * Closes the OpenGL window and exits the game
	 */
	public static final void exit() {
		Console.info("Quitting game");
		Display.destroy();
		System.exit(0);
	}
	
}
