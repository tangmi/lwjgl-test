package tang.entities;


import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.*;

import tang.helper.Axis;
import tang.helper.Console;
import tang.helper.struct.Heading;
import tang.helper.struct.Vector3;
import tang.testgame.Main;

public class EntityPlayer extends Entity {

	float gravity;

	public EntityPlayer(Vector3 pos) {
		super(pos);

		//this should move into input class
		Mouse.setCursorPosition(5, 5); //force the mouse to become "grabbable" (inside window)
		Mouse.setGrabbed(true);

		this.gravity = -0.03f;
		this.accel.setY(this.gravity);

		this.friction = 1.2f;
		
		this.size = new Vector3(1,2,1);

	}

	public void update() {
		super.update();
		
//		glEnable(GL_LIGHT1);
//		glLight(GL_LIGHT1, GL_DIFFUSE, Main.asFloatBuffer(new float[]{1f, 1f, 1f, 1.0f}));
//		glLight(GL_LIGHT1, GL_POSITION, Main.asFloatBuffer(new float[]{this.pos.x, this.pos.y + 1, this.pos.z, 1.0f}));

//		this.updatePosition();
		
		//TODO: input should be abstracted out into an input class
		float mouseSensitivity = 1.5f;
		this.heading.addPitch(-1 * Mouse.getDY() / mouseSensitivity);
		this.heading.addYaw(Mouse.getDX() / mouseSensitivity);

		float moveSensitivity = 0.3f;

		Vector3 moveVel;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveVel = heading.getMovementVector(Heading.DIRECTION_FORWARD).scale(moveSensitivity);
			moveVel.setY(vel.getY());
			this.vel = moveVel;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveVel = heading.getMovementVector(Heading.DIRECTION_BACKWARD).scale(moveSensitivity);
			moveVel.setY(vel.getY());
			this.vel = moveVel;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			moveVel = heading.getMovementVector(Heading.DIRECTION_LEFT).scale(moveSensitivity);
			moveVel.setY(vel.getY());
			this.vel = moveVel;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			moveVel = heading.getMovementVector(Heading.DIRECTION_RIGHT).scale(moveSensitivity);
			moveVel.setY(vel.getY());
			this.vel = moveVel;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if(this.standing) {
				this.vel.y += 0.6f;
			}
		}

	}

//	boolean standing = false;
	private void updatePosition() {
//		if(this.pos.y > 0) {
//			standing = false;
//		} else {
//			this.vel.y = 0;
//			this.pos.y = 0;
//			this.standing = true;
//		}
	}

	@Override
	public void init() {

	}

	@Override
	public void draw() {
		this.drawBoundingBox();
//		this.drawAxis();
	}

}
