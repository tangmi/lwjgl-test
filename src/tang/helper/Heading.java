package tang.helper;

import org.lwjgl.util.vector.Vector3f;

/**
 * Helper class for handling pitch and yaw heading in degrees
 * 
 * @author michael
 */
public class Heading {
	public float pitch;
	public float yaw;
	protected Vector3f directionVector;
	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_DOWN = 1;
	public static final int DIRECTION_LEFT = 2;
	public static final int DIRECTION_RIGHT = 3;
	public static final int DIRECTION_FORWARD = 4;
	public static final int DIRECTION_BACKWARD = 5;
	
	
	/** 
	 * Create a Heading object with all fields set to 0
	 */
	public Heading() {
		this.pitch = 0.0f;
		this.yaw = 0.0f;
		this.directionVector = new Vector3f();
	}
	
	/**
	 * Create a Heading object with a pitch and yaw
	 */
	public Heading(float pitch, float yaw) {
		this();
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	/**
	 * Gets the value for the pitch
	 * @return pitch
	 */
	public float getPitch() {
		return this.pitch;
	}
	/**
	 * Sets the value of the pitch and limits it so we can only look down to up
	 * @param f pitch
	 */
	public void setPitch(float f) {
		this.pitch = f;
		this.pitch = Math.max( -179.9f, Math.min( this.pitch, -0.1f ) );
	}
	/**
	 * Adds to the value of the pitch
	 * @param f pitch
	 */
	public void addPitch(float f) {
		this.setPitch(this.pitch + f);
	}
	
	/**
	 * Gets the value of the yaw
	 * @return yaw
	 */
	public float getYaw() {
		return this.yaw;
	}
	/**
	 * Sets the value of the yaw
	 * @param f yaw
	 */
	public void setYaw(float f) {
		this.yaw = f;
		this.yaw = this.yaw % 360;
	}
	/**
	 * Adds to the value of the yaw
	 * @param f yaw
	 */
	public void addYaw(float f) {
		this.setYaw(this.yaw + f);
	}
	
	private Vector3 calculateUnitVector(float pitch, float yaw) {
		float theta = (float) (-pitch * (Math.PI/180.0f));
		float phi = (float) (-yaw * (Math.PI/180.0f));
		return new Vector3(
				(float) ( Math.sin(theta) * Math.cos(phi)  ),
				(float) ( Math.sin(theta) * Math.sin(phi) ),
				(float) ( Math.cos(theta) )
				);
	}
	
	/**
	 * Calculates and returns a unit vector in Cartesian coordinates pointing in the direction of the pitch and yaw
	 * @return directionVector
	 */
	public Vector3 getDirectionVector() {
		return this.calculateUnitVector(this.pitch, this.yaw);
	}
	
	public Vector3 getMovementVector(int d) {
		Vector3 vector;
		if(d == DIRECTION_UP) {
			vector = this.calculateUnitVector(0.0f, 0.0f);
		} else if(d == DIRECTION_DOWN) {
			vector = this.calculateUnitVector(-180.0f, 0.0f);
		} else if(d == DIRECTION_LEFT) {
			vector = this.calculateUnitVector(-90, this.yaw - 90.0f);
		} else if(d == DIRECTION_RIGHT) {
			vector = this.calculateUnitVector(-90, this.yaw + 90.0f);
		} else if(d == DIRECTION_FORWARD) {
			vector = this.calculateUnitVector(-90, this.yaw);
		} else if(d == DIRECTION_BACKWARD) {
			vector = this.calculateUnitVector(-90, this.yaw + 180.0f);
		} else {
			vector = new Vector3();
		}
		return vector;
	}
}
