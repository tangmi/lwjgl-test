package tang.model;

import static org.lwjgl.opengl.GL11.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tang.helper.Model;
import tang.helper.OBJLoader;
import tang.helper.Vector3;

/**
 * Depreceated, use Map instead
 * @author michael
 *
 */
public class BlockMap {
	
	List<Block> blockList;
	Model map;
	
	Texture texture;
	public BlockMap() {
		blockList = new ArrayList<Block>();
		
		blockList.add((new Block(new Vector3(0,0,1.0f))).setColor(0.8f, 0.8f, 1.0f));
		
		blockList.add(new Block(new Vector3(2,0,1)));
		blockList.add(new Block(new Vector3(0,2,1)));
		blockList.add(new Block(new Vector3(0,0,5)));
		blockList.add(new Block(new Vector3(50,0,5)));
		

		blockList.add((new Block(new Vector3(10,10,1.0f))).setColor(1.0f, 0.8f, 0.8f));
		blockList.add((new Block(new Vector3(11.5f,10,1.0f))).setColor(0.8f, 1.0f, 0.8f));
		blockList.add((new Block(new Vector3(13.0f,10,1.0f))).setColor(0.8f, 0.8f, 1.0f));
		
		for(int i = 0; i < 40; i++) {
			Block block = new Block(new Vector3(-50, -50 + i*2, (float) (10 * Math.random()) + 5f));
			block.setColor((float) (0.5f + 0.5f*Math.random()), (float) (0.5f + 0.5f*Math.random()), (float) (0.5f + 0.5f*Math.random()));
			blockList.add(block);
		}
		
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("assets/dev/tex512.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		map = null;
		try {
			map = OBJLoader.loadModel(new File("assets/levels/test.obj"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

	}
	
	public void draw() {
		
		map.draw();
		
		for(Block block : blockList) {
			block.draw();
		}
		
//		int mapSize = 50;
//		
//		texture.bind();
//		
//		glBegin(GL_QUADS);
//			glColor3f(0.5f, 1.0f, 0.5f);
//			glTexCoord2f(0,0);
//			glVertex3f(-mapSize, -mapSize, 0);
//			glTexCoord2f(1,0);
//			glVertex3f(+mapSize, -mapSize, 0);
//			glTexCoord2f(1,1);
//			glVertex3f(+mapSize, +mapSize, 0);
//			glTexCoord2f(0,1);
//			glVertex3f(-mapSize, +mapSize, 0);
//		glEnd();
	}
	
	
}
