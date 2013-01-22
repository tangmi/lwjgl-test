package tang.entities;

import org.lwjgl.input.*;
import org.lwjgl.util.vector.Vector3f;

import tang.main.Camera;

public class Player {
	
	Vector3f pos, vel;
	Vector3f focus;
	float yaw, pitch;
	Camera camera;
	float gravity;
	
	public Player(Camera camera) {
		this.camera = camera;
		Mouse.setGrabbed(true);
		
		this.pos = new Vector3f(5, 5, 1);
		this.vel = new Vector3f();
		
		this.gravity = 0.03f;
		
		this.pitch = -90.0f;
	}
	
	private Vector3f calculateUnitVector(float pitch, float yaw) {
		boolean invert = false;
		float theta = (float) (-pitch *(Math.PI/180.0f));
		float phi = (float) (-yaw *(Math.PI/180.0f));
		return new Vector3f(
				(float) ( Math.sin(theta) * Math.cos(phi)  ),
				(float) ( Math.sin(theta) * Math.sin(phi) ),
				(float) ( Math.cos(theta) * (invert ? -1.0f : 1.0f) )
				);
	}
	
	public void update() {
		this.updatePosition();

		this.camera.pos = this.pos;
		
		//TODO: input should be abstracted out into an input class
		this.yaw += Mouse.getDX() / 1.5f;
		this.pitch += Mouse.getDY() / 1.5f;
		this.pitch = Math.max(-179.9f, Math.min(pitch, -0.1f)); //limit our pitch to look only up and down

		this.yaw = yaw % 360;
		
		camera.setYaw(yaw);
		camera.setPitch(pitch);
		
		this.focus = calculateUnitVector(this.pitch, this.yaw);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.move(MOVE_FORWARD);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.move(MOVE_BACKWARD);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.strafe(MOVE_LEFT);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.strafe(MOVE_RIGHT);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
//			this.moveVertical(MOVE_UP);
			if(standing) {
				this.vel.z += 0.6f;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
//			this.moveVertical(MOVE_DOWN);
		}
		
	}
	
	boolean standing = false;
	private void updatePosition() {
		this.vel.z -= this.gravity;
		this.pos.z += this.vel.z;

		if(this.pos.z > 1) {
			standing = false;
		} else {
			this.vel.z = 0;
			this.pos.z = 1;
			this.standing = true;
		}
	}
	
	
	public final int MOVE_UP = 1;
	public final int MOVE_DOWN = -1;
	private void moveVertical(int direction) {
		this.pos.z += 0.3f * direction;
	}
	public final int MOVE_FORWARD = 1;
	public final int MOVE_BACKWARD = -1;
	public void move(int direction) {
		 Vector3f.add((Vector3f) calculateUnitVector(-90, yaw).scale(direction * 0.3f), this.pos, this.pos);
	}
	public final int MOVE_LEFT = -1;
	public final int MOVE_RIGHT = 1;
	public void strafe(int direction) {
		Vector3f.add((Vector3f) calculateUnitVector(-90.0f, yaw + (90.0f * direction)).scale(0.3f), this.pos, this.pos);
	}
}
