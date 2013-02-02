package tang.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import tang.helper.Console;

/**
 * GameContainer holds all the LWJGL code for displaying a window and updating the game loop
 * @author michael
 *
 */
public class GameContainer {
	
	public static final GameContainer INSTANCE = new GameContainer();
	
	private Game game;
	
	public GameContainer() {}
	
	public GameContainer(Game game) {
		this.game = game;
		
		try {
			Display.setDisplayMode(new DisplayMode(game.getDisplayWidth(), game.getDisplayHeight()));
			Display.setTitle(game.getDisplayTitle());
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		game.init();
		Console.info("Game loaded");
		
		while(!Display.isCloseRequested()) {
			game.update();
			game.draw();
			Display.update();
			Display.sync(60);
		}
		
		//quit the game if outside the loop
		this.exit();
	}
	
	public static void exit() {
		Display.destroy();
		System.exit(0);
	}
}
