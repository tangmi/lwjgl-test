package tang.main;

import java.io.File;

import org.lwjgl.opengl.Display;

import tang.helper.Console;
import tang.helper.WorldLoader;
import tang.helper.Updatable;
import tang.model.World;

public abstract class Game implements Updatable {

	/**
	 * Stores the currently loaded map, so all classes can point to it
	 */
	public static World loadedWorld;

	public abstract int getDisplayWidth();
	public abstract int getDisplayHeight();
	public abstract String getDisplayTitle();
	
	/**
	 * Retrieves the currently loaded map
	 * @return loadedMap
	 */
	public static final World getLoadedWorld() {
		return Game.loadedWorld;
	}
	
	public static final void setLoadedWorld(File f) {
		Game.loadedWorld = WorldLoader.loadWorld(f);
		Game.loadedWorld.init();
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
