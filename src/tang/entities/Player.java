package tang.entities;

import org.lwjgl.input.*;
import org.lwjgl.util.vector.Vector3f;

import tang.main.Camera;

public class Player {
	
	Vector3f pos;
	Vector3f focus;
	float yaw, pitch;
	Camera camera;
	
	public Player(Camera camera) {
		this.camera = camera;
		Mouse.setGrabbed(true);
		
		this.pos = new Vector3f(-5, -5, 1);
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
		this.camera.pos = this.pos;
		
		//TODO: input should be abstracted out into an input class
		this.yaw += Mouse.getDX() / 1.5f;
		this.pitch += Mouse.getDY() / 1.5f;
		this.yaw = yaw % 360;
		
		camera.setYaw(yaw);
		camera.setPitch(pitch);
		
		this.focus = calculateUnitVector(this.pitch, this.yaw);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.move(FORWARD);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.move(BACKWARD);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.strafe(LEFT);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.strafe(RIGHT);
		}
		
	}
	
	public final int FORWARD = 1;
	public final int BACKWARD = -1;
	public void move(int direction) {
		 Vector3f.add((Vector3f) calculateUnitVector(pitch, yaw).scale(direction * 0.3f), this.pos, this.pos);
	}
	public final int LEFT = -1;
	public final int RIGHT = 1;
	public void strafe(int direction) {
		Vector3f.add((Vector3f) calculateUnitVector(-90.0f, yaw + (90.0f * direction)).scale(0.3f), this.pos, this.pos);
	}
}
