package tang.helper.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import tang.helper.Console;
import tang.helper.input.Input;

/**
 * GameContainer holds all the LWJGL code for displaying a window and updating the game loop
 * @author michael
 *
 */
public class GameContainer {
	
	private Game game;
	
	public GameContainer(Game game) {
		Input input = new Input();
		this.game = game;

		try {
			Display.setDisplayMode(new DisplayMode(game.getDisplayWidth(), game.getDisplayHeight()));
			Display.setTitle(game.getDisplayTitle());
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Game.exit();
		}
		
		input.init();
		game.init();
		Console.info("Game loaded");
		
		while(!Display.isCloseRequested()) {
			input.update();
			if(Game.getLoadedWorld() != null) {
				Game.getLoadedWorld().update();
			}
			game.update();
			
			if(Game.getLoadedWorld() != null) {
				Game.getLoadedWorld().draw();
			}
			game.draw();
			
			Display.update();
			Display.sync(60);
		}
		
		//quit the game if outside the loop
		Game.exit();
	}
}
