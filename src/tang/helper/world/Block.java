package tang.helper.world;


import java.io.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tang.helper.struct.Vector3;

import static org.lwjgl.opengl.GL11.*;

/**
 * Depreceated, use Map instead
 * @author michael
 *
 */
public class Block {
	Vector3 pos;
	float width;
	public Texture texture;
	
	@Deprecated
	public Block(Vector3 pos) {
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
	public Block setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		return this;
	}
	
	public void draw() {

		glEnable(GL_TEXTURE_2D);

		texture.bind();

		glEnable(GL_CULL_FACE);
//		glCullFace(GL_BACK);
		glBegin(GL_QUADS);
		
		glColor3f(this.r,this.g,this.b);
		//bottom
		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);
		
		//top
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
		

		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);

		glTexCoord2f(0,1);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z +width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x -width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(0,0);
		glVertex3f(pos.x -width/2, pos.y -width/2, pos.z -width/2);

		glTexCoord2f(0,0);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z -width/2);
		glTexCoord2f(1,0);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z -width/2);
		glTexCoord2f(1,1);
		glVertex3f(pos.x +width/2, pos.y +width/2, pos.z +width/2);
		glTexCoord2f(0,1);
		glVertex3f(pos.x +width/2, pos.y -width/2, pos.z +width/2);

		
		GL11.glEnd();
		
		glDisable(GL_CULL_FACE);

		
		glDisable(GL_TEXTURE_2D);

	}
	
}
