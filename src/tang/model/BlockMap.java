package tang.model;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.util.vector.Vector3f;

public class BlockMap {
	
	List<Block> blockList;
	public BlockMap() {
		blockList = new ArrayList<Block>();
		
		blockList.add(new Block(new Vector3f(0,0,1)));
		blockList.add(new Block(new Vector3f(2,0,1)));
		blockList.add(new Block(new Vector3f(0,2,1)));
		blockList.add(new Block(new Vector3f(0,0,5)));
		blockList.add(new Block(new Vector3f(50,0,5)));
	}
	
	public void draw() {
		for(Block block : blockList) {
			block.draw();
		}
		
		int mapSize = 50;
		glBegin(GL_QUADS);
			glColor3f(0.5f, 1.0f, 0.5f);
			glVertex3f(-mapSize, -mapSize, 0);
			glVertex3f(+mapSize, -mapSize, 0);
			glVertex3f(+mapSize, +mapSize, 0);
			glVertex3f(-mapSize, +mapSize, 0);
		glEnd();
	}
	
	
}
