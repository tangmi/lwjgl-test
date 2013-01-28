package tang.helper;

public class Face {
	public Vector3 vertex; //three indices
	public Vector3 normal; //three indices
	public Vector3 texture;
	public Face(Vector3 vertex, Vector3 normal, Vector3 texture) {
		this.vertex = vertex;
		this.normal = normal;
		this.texture = texture;
	}
}
