package tang.helper.obj;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tang.helper.Console;
import tang.helper.struct.Heading;
import tang.helper.struct.Vector2;
import tang.helper.struct.Vector3;

import static org.lwjgl.opengl.GL11.*;

public class Model {

	public List<Vector3> vertices;
	public List<Vector3> normals;
	public List<TextureCoordinates> texVertices;
	public List<Face> faces;
	
	public Model() {
		this.vertices = new ArrayList<Vector3>();
		this.normals = new ArrayList<Vector3>();
		this.texVertices = new ArrayList<TextureCoordinates>();
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
		
//		glColor3f(1.0f, 1.0f, 1.0f);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		
		for(Face face : this.faces) {
			if(face.getMaterial() != null) {
				Color diffuseColor = face.getMaterial().getDiffuseColor();
				glColor3f(diffuseColor.getRed() / 255.0f, diffuseColor.getGreen() / 255.0f, diffuseColor.getBlue() / 255.0f);
				
				if(face.getMaterial().getDiffuseMap() != null) {
		            glBindTexture(GL_TEXTURE_2D, face.getMaterial().getDiffuseMap().getTextureId());
				}
			}
			
			if(face.isQuad()) {
				glBegin(GL_QUADS);
				Vector3 n1 = normals.get(face.normal.getA() - 1);
				glNormal3f(n1.x, n1.y, n1.z);
				Vector3 v1 = vertices.get(face.vertex.getA() - 1);
				glVertex3f(v1.x, v1.y, v1.z);
				if(face.texture != null) {
					TextureCoordinates t1 = texVertices.get(face.texture.getA() - 1);
					glTexCoord2f(t1.getU(), t1.getV());
				}

				Vector3 n2 = normals.get(face.normal.getB() - 1);
				glNormal3f(n2.x, n2.y, n2.z);
				Vector3 v2 = vertices.get(face.vertex.getB() - 1);
				glVertex3f(v2.x, v2.y, v2.z);
				if(face.texture != null) {
					TextureCoordinates t2 = texVertices.get(face.texture.getB() - 1);
					glTexCoord2f(t2.getU(), t2.getV());
				}

				Vector3 n3 = normals.get(face.normal.getC() - 1);
				glNormal3f(n3.x, n3.y, n3.z);
				Vector3 v3 = vertices.get(face.vertex.getC() - 1);
				glVertex3f(v3.x, v3.y, v3.z);
				if(face.texture != null) {
					TextureCoordinates t3 = texVertices.get(face.texture.getC() - 1);
					glTexCoord2f(t3.getU(), t3.getV());
				}

				Vector3 n4 = normals.get(face.normal.getD() - 1);
				glNormal3f(n4.x, n4.y, n4.z);
				Vector3 v4 = vertices.get(face.vertex.getD() - 1);
				glVertex3f(v4.x, v4.y, v4.z);
				if(face.texture != null) {
					TextureCoordinates t4 = texVertices.get(face.texture.getD() - 1);
					glTexCoord2f(t4.getU(), t4.getV());
				}
				glEnd();
			} else {
				glBegin(GL_TRIANGLES);
				Vector3 n1 = normals.get(face.normal.getA() - 1);
				glNormal3f(n1.x, n1.y, n1.z);
				Vector3 v1 = vertices.get(face.vertex.getA() - 1);
				glVertex3f(v1.x, v1.y, v1.z);
				if(face.texture != null) {
					TextureCoordinates t1 = texVertices.get(face.texture.getA() - 1);
					glTexCoord2f(t1.getU(), t1.getV());
				}

				Vector3 n2 = normals.get(face.normal.getB() - 1);
				glNormal3f(n2.x, n2.y, n2.z);
				Vector3 v2 = vertices.get(face.vertex.getB() - 1);
				glVertex3f(v2.x, v2.y, v2.z);
				if(face.texture != null) {
					TextureCoordinates t2 = texVertices.get(face.texture.getB() - 1);
					glTexCoord2f(t2.getU(), t2.getV());
				}

				Vector3 n3 = normals.get(face.normal.getC() - 1);
				glNormal3f(n3.x, n3.y, n3.z);
				Vector3 v3 = vertices.get(face.vertex.getC() - 1);
				glVertex3f(v3.x, v3.y, v3.z);
				if(face.texture != null) {
					TextureCoordinates t3 = texVertices.get(face.texture.getC() - 1);
					glTexCoord2f(t3.getU(), t3.getV());
				}
				glEnd();
			}
			
			glColor3f(1.0f, 1.0f, 1.0f);

		}
		
		
		
		glDisable(GL_CULL_FACE);
		
		glPopMatrix();
	}
}
