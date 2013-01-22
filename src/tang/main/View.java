package tang.main;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.*;

import tang.model.BlockMap;

import static org.lwjgl.opengl.GL11.*;


public class View {
	BlockMap map;
	Camera camera;

	public View(Camera camera) {
		this.camera = camera;
	}

	public void init() {
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();

		glViewport(0, 0, width, height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(45.0f, ((float) width / (float) height), 0.1f, 256.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glShadeModel(GL_SMOOTH);
		glClearColor(0.55f, 0.804f, 0.97f, 0.0f);
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

	}


	public void run() {

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		if(this.camera != null){
		GLU.gluLookAt(this.camera.pos.x, this.camera.pos.y, this.camera.pos.z,
				this.camera.focus.x, this.camera.focus.y, this.camera.focus.z,
				0.0f, 0.0f, 1.0f);
		}

	}
}
