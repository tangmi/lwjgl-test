package tang.helper.game;

import java.io.File;

import org.lwjgl.opengl.Display;

import tang.helper.Updatable;
import tang.helper.utils.Console;
import tang.helper.world.World;
import tang.helper.world.WorldLoader;

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
	 * @return world currently loaded world
	 */
	public static final World getLoadedWorld() {
		return Game.loadedWorld;
	}
	
	/**
	 * Loads a world into the game, overriding the currently loaded world
	 * @param f file to load
	 */
	public static final void setLoadedWorld(File f) {
		Game.loadedWorld = WorldLoader.loadWorld(f);
		Game.loadedWorld.init();
	}
	
	/**
	 * Closes the OpenGL window and exits the game
	 */
	public static final void exit() {
		Game.exit(false);
	}
	
	/**
	 * Closes the game with an error, if parameter is true
	 * @param error should exit unpeacefully?
	 */
	public static final void exit(boolean error) {
		if(error == false) {
			Console.info("Quitting game");
			Display.destroy();
			System.exit(0);
		} else {
			Console.error("Quitting game unpeacefully");
			Display.destroy();
			System.exit(1);
		}
	}
	
}
