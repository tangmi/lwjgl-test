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

	/**
	 * Defines the width of the game window; will get called once during initialization
	 * @return width
	 */
	public abstract int getDisplayWidth();
	
	/**
	 * Defines the height of the game window; will get called once during initialization
	 * @return height
	 */
	public abstract int getDisplayHeight();
	
	/**
	 * Defines the title of the game window; will get called once during initialization
	 * @return title
	 */
	public abstract String getDisplayTitle();
	
	/**
	 * Retrieves the currently loaded world
	 * @return world currently loaded
	 */
	public static final World getLoadedWorld() {
		return Game.loadedWorld;
	}
	
	/**
	 * Loads a world into the game, overriding the currently loaded world
	 * @param file to load
	 */
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
