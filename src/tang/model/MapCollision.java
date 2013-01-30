package tang.model;

import tang.helper.obj.Model;
import tang.helper.struct.Vector3;

public class MapCollision {
	
	Model collisionMap;
	
	public MapCollision() {
		collisionMap = null;
	}
	
	public void setModel(Model cm) {
		this.collisionMap = cm;
		
		
	}
	
	public void trace(Vector3 pos, Vector3 delta, Vector3 size) {
		//calculate collision with faces of collision map
	}
}
