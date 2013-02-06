package tang.helper.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

import tang.helper.input.Input;
import tang.helper.utils.Console;

/**
 * GameContainer holds all the LWJGL code for displaying a window and updating the game loop
 * @author michael
 *
 */
public class GameContainer {
	public GameContainer(Game game) {

		try {
			Display.setDisplayMode(new DisplayMode(game.getDisplayWidth(), game.getDisplayHeight()));
			Display.setTitle(game.getDisplayTitle());
			Display.create();
		} catch (LWJGLException e) {
			Console.error("Display could not be initialized");
			e.printStackTrace();
			Game.exit(true);
		}

		//initialize the viewport
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
		glShadeModel(GL_SMOOTH);

		Input.init();
		game.init();
		Console.info("Game loaded");

		//game loop
		while(!Display.isCloseRequested()) {
			Input.update();

			GameContainer.ready3d();
			if(Game.getLoadedWorld() != null) {
				Game.getLoadedWorld().update();
			}
			game.update();

			if(Game.getLoadedWorld() != null) {
				Game.getLoadedWorld().draw();
			}
			game.draw();


			GameContainer.ready2d();
			glDisable(GL_LIGHTING);
			glDisable(GL_COLOR_MATERIAL);
			game.draw2d();
			glEnable(GL_COLOR_MATERIAL);
			glEnable(GL_LIGHTING);
			
			
			Display.update();
			Display.sync(60);
		}

		//quit the game if outside the loop
		Game.exit();

	}

	private static void ready3d() {
		//Setup for 3d
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(50.0f, ((float) Display.getDisplayMode().getWidth() / (float) Display.getDisplayMode().getHeight()), 0.1f, 256.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glDepthFunc(GL_LEQUAL);
		glEnable(GL_DEPTH_TEST);
		

	}

	private static void ready2d() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0f, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0.0f, -1.0f, 1.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glClear(GL_DEPTH_BUFFER_BIT);

		glDisable(GL_CULL_FACE);
		glDisable(GL_DEPTH_TEST);
		


	}
}
