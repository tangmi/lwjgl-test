package tang.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;


public class OBJLoader {
	public static Model loadModel(File f) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));

		Model m = new Model();
		String line;
		Texture currentTexture = null;

		while((line=reader.readLine()) != null) {
			if(line.startsWith("v ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				m.vertices.add(new Vector3(x,y,z));
			} else if(line.startsWith("vn ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				m.normals.add(new Vector3(x,y,z));
			} else if(line.startsWith("f ")) {
				String[] data = line.split(" ");

				try {
					Vector3 vertexIndicies = new Vector3(
							Float.valueOf(data[1].split("/")[0]), 
							Float.valueOf(data[2].split("/")[0]), 
							Float.valueOf(data[3].split("/")[0])
							);
					
					Vector3 textureIndicies = null;
					try {
						textureIndicies = new Vector3(
								Float.valueOf(data[1].split("/")[1]), 
								Float.valueOf(data[2].split("/")[1]), 
								Float.valueOf(data[3].split("/")[1])
								);
					} catch(NumberFormatException e) {
					}
					Vector3 normalIndicies = new Vector3(
							Float.valueOf(data[1].split("/")[2]), 
							Float.valueOf(data[2].split("/")[2]), 
							Float.valueOf(data[3].split("/")[2])
							);

					m.faces.add(new Face(vertexIndicies, normalIndicies, textureIndicies));
//					m.faces.add(new Face(vertexIndicies,textureIndicies,normalIndicies,currentTexture.getTextureID()));
//				} else if(line.startsWith("vt ")) {
//					float x = Float.valueOf(line.split(" ")[1]);
//					float y = Float.valueOf(line.split(" ")[2]);
//					m.texVerticies.add(new Vector2(x,y));
//				} else if(line.startsWith("g ")) {
//					if(line.length()>2) {
//						String name = line.split(" ")[1];
//						currentTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + name + ".png"));
//						System.out.println(currentTexture.getTextureID());
//					}
				} catch(Exception e) {
					Console.error(f.getName() + ": could not parse: \"" + line + "\"");
				}
			}
		}
		reader.close();

		Console.info(f.toURI() + " loaded, " + m.vertices.size() + " vertices, " + m.normals.size() + " normals, " + m.faces.size() + " faces, " + m.texVertices.size() + " texture coordinates");
//		
//		System.out.println(f.toURI() + " loaded");
//		System.out.println(m.vertices.size() + " vertices");
//		System.out.println(m.normals.size() + " normals");
//		System.out.println(m.texVertices.size() + " texture coordinates");
//		System.out.println(m.faces.size() + " faces");
//		System.out.println();

		return m;
	}
}
