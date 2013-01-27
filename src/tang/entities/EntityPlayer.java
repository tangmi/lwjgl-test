package tang.entities;


import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.glLight;

import org.lwjgl.input.*;

import tang.helper.Console;
import tang.helper.Heading;
import tang.helper.Vector3;
import tang.main.Main;

public class EntityPlayer extends Entity{

	float gravity;

	public EntityPlayer(Vector3 pos) {
		super(pos);

		//this should move into input class
		Mouse.setCursorPosition(5, 5); //force the mouse to become "grabbable" (inside window)
		Mouse.setGrabbed(true);

		this.gravity = -0.03f;
		this.accel.setY(this.gravity);

		this.friction = 1.2f;

	}

	public void update() {
		super.update();

		this.updatePosition();

		Console.debug(heading);
		
		//TODO: input should be abstracted out into an input class
		float mouseSensitivity = 1.5f;
		this.heading.addPitch(Mouse.getDY() / mouseSensitivity);
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
			if(standing) {
				this.vel.y += 0.6f;
			}
		}

	}

	boolean standing = false;
	private void updatePosition() {
		if(this.pos.y > 1) {
			standing = false;
		} else {
			this.vel.y = 0;
			this.pos.y = 1;
			this.standing = true;
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
