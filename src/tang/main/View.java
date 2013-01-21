package tang.main;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;


public class View {
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

		this.run();
	}

	public void run() {
		while(!Display.isCloseRequested()) {

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			GLU.gluLookAt(-5, -5, -5,
					0, 0, 0.5f,
					0.0f, 1.0f, 0.0f);

			glBegin(GL_TRIANGLES);
			glColor3f(1,1,0);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glColor3f(0,1,1);
			glVertex3f(2.0f, 0.0f, 0.0f);
			glColor3f(1,0,1);
			glVertex3f(0.0f, 0.0f, 6f);
			glEnd();


			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}
