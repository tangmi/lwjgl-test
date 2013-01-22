package tang.model;

import java.io.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class Block {
	Vector3f pos;
	float width;
	public Texture texture;
	
	public Block(Vector3f pos) {
		this.pos = pos;
		this.width = 1;
		
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("assets/dev/tex64.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.r = 1;
		this.g = 1;
		this.b = 1;
	}
	
	float r, g, b;
	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void draw() {

		glEnable(GL_TEXTURE_2D);

		texture.bind();

		glBegin(GL_QUADS);
		
		glColor3f(this.r,this.g,this.b);
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z +width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z +width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z +width/2);
		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z +width/2);
		
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z +width/2);
		
		glTexCoord2f(0,0);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(0,1);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z +width/2);

		
		GL11.glEnd();
		
		glDisable(GL_TEXTURE_2D);

	}
	
}
