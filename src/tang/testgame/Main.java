package tang.testgame;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import tang.helper.entities.Entity;
import tang.helper.game.Game;
import tang.helper.game.GameContainer;
import tang.helper.input.Input;
import tang.helper.utils.Axis;
import tang.helper.utils.FloatUtils;
import tang.helper.utils.FontDrawer;
import tang.helper.world.BlockMap;
import tang.helper.world.World;
import tang.helper.world.WorldLoader;
import static org.lwjgl.opengl.GL11.*;

public class Main extends Game {
	
	public static void main(String[] args) {
		GameContainer game = new GameContainer(new Main());
	}
	
	private Texture texture;

	public static TrueTypeFont font;
	public static String text = "";

	@Override
	public void init() {
				
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_DIFFUSE, FloatUtils.asFloatBuffer(new float[]{1f, 1f, 1f, 1.0f}));

		
		
		glLightModel(GL_LIGHT_MODEL_AMBIENT, FloatUtils.asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1.0f}));
		
//		glEnable(GL_LIGHT1);
//		glLight(GL_LIGHT1, GL_AMBIENT, FloatUtils.asFloatBuffer(new float[]{0.1f, 0.1f, 0.1f, 0.1f}));

		
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);		


		Game.setLoadedWorld(new File("lolThisDoesntMatterYet.hax"));
		
		Input.bind(Keyboard.KEY_A, "strafeLeft");
		Input.bind(Keyboard.KEY_D, "strafeRight");
		Input.bind(Keyboard.KEY_W, "moveForward");
		Input.bind(Keyboard.KEY_S, "moveBackward");
		Input.bind(Keyboard.KEY_SPACE, "jump");
		
		Input.bind(Keyboard.KEY_ESCAPE, "quit");
		Input.bind(Keyboard.KEY_F5, "wireframeMode");

		Input.grabMouse();
		
		FontDrawer.loadFont("assets/fonts/SourceCodePro-Semibold.ttf", 16.0f, true);
		
	}

	boolean wireframeToggle = false;

	
	@Override
	public void update() {
	
		if(Input.state("quit")) {
			Game.exit();
		}
		if(Input.pressed("wireframeMode")) {
			wireframeToggle = !wireframeToggle;
		}
		
		if(wireframeToggle) {
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		} else {
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		}
		
	}

	@Override
	public void draw() {
		glLight(GL_LIGHT0, GL_POSITION, FloatUtils.asFloatBuffer(new float[]{0, 15, 0, 1.0f}));
		
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
	
	@Override
	public void draw2d() {

		
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();
		glBegin(GL_LINES);
			glColor3f(1.0f, 1.0f, 1.0f);

			int size = 10;
			glVertex2f(width/2 - size, height/2);
			glVertex2f(width/2 + size, height/2);

			glVertex2f(width/2, height/2 - size);
			glVertex2f(width/2, height/2 + size);
		glEnd();
		
		FontDrawer.draw(15, 15, Game.getLoadedWorld().getEntitiesByClass(EntityPlayer.class).get(0).getPos().toString());
	}
	
	@Override
	public int getDisplayWidth() {
		return 800;
	}

	@Override
	public int getDisplayHeight() {
		return 600;
	}

	@Override
	public String getDisplayTitle() {
		return "tangmi/lwjgl-test";
	}

	
}