package tang.helper;

public class Face {
	public Vector3 vertex; //three indices
	public Vector3 normal; //three indices
	public Face(Vector3 vertex, Vector3 normal) {
		this.vertex = vertex;
		this.normal = normal;
	}
}
