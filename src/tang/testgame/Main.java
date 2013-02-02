package tang.testgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tang.entities.Entity;
import tang.entities.EntityPlayer;
import tang.helper.Axis;
import tang.helper.MapLoader;
import tang.main.Game;
import tang.main.GameContainer;
import tang.model.BlockMap;
import tang.model.Map;
import static org.lwjgl.opengl.GL11.*;

public class Main extends Game {
	
	public static void main(String[] args) {
		GameContainer game = new GameContainer(new Main());
	}

	/**
	 * Allows the creation of a float buffer from an array of floats
	 * @param f Input float[] to be turned into a FloatBuffer
	 * @return FloatBuffer from float[]
	 */
	public static FloatBuffer asFloatBuffer(float[] f) {
		//TODO this should move to it's own class, FloatUtils?
		FloatBuffer buffer = BufferUtils.createFloatBuffer(f.length);
		buffer.put(f);
		buffer.flip();
		return buffer;
	}
	
	private Texture texture;
	
	@Override
	public void init() {
		glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);

		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1.0f}));
		glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{1f, 1f, 1f, 1.0f}));

		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);		

		//		BlockMap map = new BlockMap();

		this.setLoadedMap(MapLoader.loadMap(null));
		this.getLoadedMap().init();

		//this is just for testing
		texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("assets/dev/tex512.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update() {

		this.getLoadedMap().update();

	}

	@Override
	public void draw() {
		glLight(GL_LIGHT0, GL_POSITION, Main.asFloatBuffer(new float[]{0, 15, 0, 1.0f}));

		this.getLoadedMap().draw();


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

		Axis.draw();			
	}
}