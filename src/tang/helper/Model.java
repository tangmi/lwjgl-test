package tang.helper;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Model {

	public List<Vector3> vertices;
	public List<Vector3> normals;
	public List<Vector2> texVertices;
	public List<Face> faces;
	
	public Model() {
		this.vertices = new ArrayList<Vector3>();
		this.normals = new ArrayList<Vector3>();
		this.texVertices = new ArrayList<Vector2>();
		this.faces = new ArrayList<Face>();
	}
	
	public void draw() {
		this.draw(new Vector3(), new Heading());
	}

	public void draw(Vector3 pos, Heading heading) {
		
//		Axis.draw(pos, heading);
		
//		glCallList(displayList);
		glPushMatrix();
		
		glTranslatef(pos.x, pos.y, pos.z);
		glRotatef(-heading.yaw - 90.0f,
				0.0f, 1.0f, 0.0f);
		
//		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		
		glColor3f(1.0f, 1.0f, 1.0f);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		glBegin(GL_TRIANGLES);
		for(Face face : this.faces){

			Vector3 n1 = normals.get((int) (face.normal.x - 1));
			glNormal3f(n1.x, n1.y, n1.z);
			Vector3 v1 = vertices.get((int) (face.vertex.x - 1));
			glVertex3f(v1.x, v1.y, v1.z);
			
			Vector3 n2 = normals.get((int) (face.normal.y - 1));
			glNormal3f(n2.x, n2.y, n2.z);
			Vector3 v2 = vertices.get((int) (face.vertex.y - 1));
			glVertex3f(v2.x, v2.y, v2.z);
			
			Vector3 n3 = normals.get((int) (face.normal.z - 1));
			glNormal3f(n3.x, n3.y, n3.z);
			Vector3 v3 = vertices.get((int) (face.vertex.z - 1));
			glVertex3f(v3.x, v3.y, v3.z);
		}
		glEnd();
		
		
		glDisable(GL_CULL_FACE);
		
		glPopMatrix();
	}
}
