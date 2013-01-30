package tang.helper.obj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tang.helper.Console;
import tang.helper.struct.Vector2;
import tang.helper.struct.Vector3;


public class OBJLoader {
	
	public static Model loadModel(File f) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		Model m = new Model();
		String line;
		Texture currentTexture = null;
		List<Material> materials = null;
		
		Material currentMaterial = null;
		while((line=reader.readLine()) != null) {
			if(line.startsWith("mtllib ")) {
				materials = loadMaterialsFromMtl(new File(f.getParent() + "/" + line.split(" ")[1]));
			} else if(line.startsWith("usemtl ")) {
				currentMaterial = findMaterialByName(line.split(" ")[1], materials);
			} else if(line.startsWith("v ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				m.vertices.add(new Vector3(x,y,z));
			} else if(line.startsWith("vn ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				m.normals.add(new Vector3(x,y,z));
			} else if(line.startsWith("vt ")) {
				float u = Float.valueOf(line.split(" ")[1]);
				float v = 1.0f - Float.valueOf(line.split(" ")[2]);	//texture y coordinates need to be flipped
				m.texVertices.add(new TextureCoordinates(u, v));
//			} else if(line.startsWith("g ")) {
//				if(line.length()>2) {
//					String name = line.split(" ")[1];
//					currentTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + name + ".png"));
//					System.out.println(currentTexture.getTextureID());
//				}
			} else if(line.startsWith("f ")) {
				String[] data = line.split(" ");

				try {
					FaceIndices vertexIndicies = new FaceIndices(
							Integer.valueOf(data[1].split("/")[0]), 
							Integer.valueOf(data[2].split("/")[0]), 
							Integer.valueOf(data[3].split("/")[0])
							);
					if(data.length - 1 == 4) {
						vertexIndicies.setD(Integer.valueOf(data[4].split("/")[0]));
					}

					FaceIndices textureIndicies = null;
					try {
						textureIndicies = new FaceIndices(
								Integer.valueOf(data[1].split("/")[1]), 
								Integer.valueOf(data[2].split("/")[1]), 
								Integer.valueOf(data[3].split("/")[1])
								);
						if(data.length - 1 == 4) {
							textureIndicies.setD(Integer.valueOf(data[4].split("/")[1]));
						}
					} catch(NumberFormatException e) {
						//just ignore if there's no texture coordinates to parse
					}

					FaceIndices normalIndicies = new FaceIndices(
							Integer.valueOf(data[1].split("/")[2]), 
							Integer.valueOf(data[2].split("/")[2]), 
							Integer.valueOf(data[3].split("/")[2])
							);
					if(data.length - 1 == 4) {
						normalIndicies.setD(Integer.valueOf(data[4].split("/")[2]));
					}

					m.faces.add(new Face(vertexIndicies, normalIndicies, textureIndicies, currentMaterial));
					
				} catch(Exception e) {
					Console.error(f.getName() + ": could not parse: \"" + line + "\"");
				}
			}
		}
		reader.close();
		materials = null;

		Console.info(f.getName() + " loaded, " + m.vertices.size() + " vertices, " + m.normals.size() + " normals, " + m.faces.size() + " faces, " + m.texVertices.size() + " texture coordinates");

		return m;
	}

	//TODO probably not the best idea implementing my own search; i should try and maybe use a HashMap
	private static Material findMaterialByName(String materialName, List<Material> materials) {
		for(Material material : materials) {
			if(material.getName().equals(materialName)) {
				return material;
			}
		}
		return null;
	}

	private static List<Material> loadMaterialsFromMtl(File f) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));

		List<Material> materials = new ArrayList<Material>();

		String line;
		while((line=reader.readLine()) != null) {
			if(line.startsWith("newmtl ")) {
				Material material = new Material(line.split(" ")[1]);
				while((line=reader.readLine()) != null && !line.equals("")) {
					//TODO: support all textures in Material
					if(line.startsWith("Ka ")) {
						float r = Float.valueOf(line.split(" ")[1]);
						float g = Float.valueOf(line.split(" ")[2]);
						float b = Float.valueOf(line.split(" ")[3]);
						material.setAmbientColor(new Color(r, g, b));
					} else if(line.startsWith("Kd ")) {
						float r = Float.valueOf(line.split(" ")[1]);
						float g = Float.valueOf(line.split(" ")[2]);
						float b = Float.valueOf(line.split(" ")[3]);
						material.setDiffuseColor(new Color(r, g, b));
					} else if(line.startsWith("Ks ")) {
						float r = Float.valueOf(line.split(" ")[1]);
						float g = Float.valueOf(line.split(" ")[2]);
						float b = Float.valueOf(line.split(" ")[3]);
						material.setSpecularColor(new Color(r, g, b));
					} else if(line.startsWith("Ns ")) {
						material.setSpecularCoefficient(Float.valueOf(line.split(" ")[1]));
					} else if(line.startsWith("d ") || line.startsWith("Tr ")) {
						material.setDissolve(Float.valueOf(line.split(" ")[1]));
					} else if(line.startsWith("illum ")) {
						material.setIlluminationModel(Integer.valueOf(line.split(" ")[1]));
					} else if(line.startsWith("map_Kd ")) {
						String textureName = line.split(" ")[1];
						Texture texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(f.getParent() + "/" + textureName)));
						TextureMap diffuseMap = new TextureMap(texture.getTextureID());
						material.setDiffuseMap(diffuseMap);
						Console.debug(textureName + " => assigned ID " + texture.getTextureID());
					}
				}
				materials.add(material);
			} 
		}
		reader.close();
		
		Console.debug(f.getName() + " loaded, " + materials.size() + " materials found");
		
		return materials;
	}
}
