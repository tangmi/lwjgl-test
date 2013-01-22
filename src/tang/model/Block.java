package tang.model;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class Block {
	Vector3f pos;
	float width;
	
	public Block(Vector3f pos) {
		this.pos = pos;
		this.width = 1;
	}
	
	public void draw() {
		glBegin(GL_QUADS);
		
		glColor3f(1,1,0);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z +width/2);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z +width/2);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		
		glColor3f(1,0,1);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z +width/2);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z +width/2);
		
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		
		glColor3f(0,1,1);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z +width/2);
		
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z +width/2);

		
		GL11.glEnd();
	}
	
}
