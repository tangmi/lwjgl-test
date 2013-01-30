package tang.helper.obj;

import org.newdawn.slick.opengl.Texture;

import tang.helper.struct.Vector3;

public class TextureMap {
	private int textureId;
	private Vector3 o, s, t; //origin, scale, turbulence; to be implemented
	private Vector3 bm; //bumpMultiplier; to be implemented
	
	//there's more stuff
	//http://en.wikipedia.org/wiki/Wavefront_.obj_file#File_format
	
	public TextureMap(int textureId) {
		this.textureId = textureId;
	}
	
	public int getTextureId() {
		return this.textureId;
	}
}
