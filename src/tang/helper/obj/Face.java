package tang.helper.obj;

import org.lwjgl.util.vector.Vector;



public class Face {
	public FaceIndices vertex;
	public FaceIndices normal;
	public FaceIndices texture;
	private Material material;
	
	public Face(FaceIndices vertex, FaceIndices normal, FaceIndices texture) {
		this.vertex = vertex;
		this.normal = normal;
		this.texture = texture;
	}
	public boolean isQuad() {
		//check if the value for the D indices is not the default 3d indices value
		return vertex.getD() != -1.0f;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
}
