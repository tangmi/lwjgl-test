package tang.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tang.entities.Player;
import tang.model.BlockMap;
import static org.lwjgl.opengl.GL11.*;
 
public class Main {
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("A fresh display!");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		Camera camera = new Camera();
		Player player = new Player(camera);
		
		View view = new View(camera);
		view.init();
		
		BlockMap map = new BlockMap();
		
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("assets/dev/tex512.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		while(!Display.isCloseRequested()) {
			player.update();
			camera.update(); //TODO: this has to go after player.update, i should find a better solution

			
			view.run();
			
			map.draw();
			
			texture.bind();
			glBegin(GL_TRIANGLES);
				glColor3f(1,1,0);
				glTexCoord2f(0,0);
				glVertex3f(1.0f, 1.0f, 1.0f);
				glColor3f(0,1,1);
				glTexCoord2f(0.5f,0);
				glVertex3f(2.0f, 0.0f, 0.0f);
				glColor3f(1,0,1);
				glTexCoord2f(0.5f,1);
				glVertex3f(0.0f, 0.0f, 6f);
			glEnd();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}