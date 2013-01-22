package tang.main;

import org.lwjgl.util.vector.Vector3f;

public class Camera {
	public Vector3f pos;
	public Vector3f focus;
	public float pitch, yaw;
	
	public Camera() {
		this.pos = new Vector3f();
		this.focus = new Vector3f();
		this.pitch = 0f;
		this.yaw = 0f;
	}
	
	public Vector3f lookAt() {
		boolean invert = false;
		float theta = (float) (-this.pitch * (Math.PI/180.0f));
		float phi = (float) (-this.yaw * (Math.PI/180.0f));
		return new Vector3f(
				(float) ( Math.sin(theta) * Math.cos(phi)  ),
				(float) ( Math.sin(theta) * Math.sin(phi) ),
				(float) ( Math.cos(theta) * (invert ? -1.0f : 1.0f) )
				);
	}
	
	public void update() {
		Vector3f.add(lookAt(), this.pos, this.focus);
	}

	public void setPitch(float f) {
		this.pitch = f;
	}
	
	public void setYaw(float f) {
		this.yaw = f;
	}
}
