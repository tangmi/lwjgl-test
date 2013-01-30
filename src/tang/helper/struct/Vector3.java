package tang.helper.struct;

import org.lwjgl.util.vector.Vector3f;

public class Vector3 extends Vector3f {
	private static final long serialVersionUID = 7053736861893015187L;
	
	public Vector3() {
		super();
	}
	public Vector3(float x, float y, float z) {
		super(x, y, z);
	}
	
	//provides an add function without the need for static methods
	public Vector3 add(Vector3 v) {
		return new Vector3(
				this.getX() + v.getX(),
				this.getY() + v.getY(),
				this.getZ() + v.getZ()
				);
	}
	
	//a scale function that returns a Vector3
	public Vector3 scale(float f) {
		return new Vector3(
				this.getX() * f,
				this.getY() * f,
				this.getZ() * f
				);
	}
	
	public String toString() {
		return "Vector3(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
