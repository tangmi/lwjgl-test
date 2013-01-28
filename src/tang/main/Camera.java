package tang.main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import tang.entities.Entity;
import tang.helper.Heading;
import tang.helper.Vector3;

/**
 * This camera assumes that +z is upwards
 * @author michael
 *
 */
public class Camera {
	public Vector3 pos;
	public Heading heading;
	public Vector3 focus;
	public Entity target;
	
	public Camera() {
		this.pos = new Vector3();
		this.heading = new Heading();
		this.focus = new Vector3();
		
		
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();
		
		glViewport(0, 0, width, height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(50.0f, ((float) width / (float) height), 0.1f, 256.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glShadeModel(GL_SMOOTH);
		glClearColor(0.55f, 0.804f, 0.97f, 0.0f);
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}
	
	/**
	 * Sets the Entity to "view" from
	 * @param e
	 */
	public void setTarget(Entity e) {
		this.target = e;
	}
	
	public void update() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		boolean invert = false;
		this.pos = this.target.getPos();
		this.heading = this.target.getHeading();
		
		this.focus = this.pos.add(heading.getDirectionVector());

		GLU.gluLookAt(this.pos.getX(), this.pos.getY() + 1.61f, this.pos.getZ(),
				this.focus.getX(), this.focus.getY()  + 1.61f, this.focus.getZ(),
				0.0f, 1.0f, 0.0f);
	}
	
	public void setHeading(Heading h) {
		this.heading = h;
	}
	
	public void setPitch(float f) {
		heading.setPitch(f);
	}
	
	public void setYaw(float f) {
		heading.setYaw(f);
	}
}
