package tang.helper.utils;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.util.ResourceLoader;

public class FontDrawer {
	
	private static TrueTypeFont font = null;
	private static Color color = Color.white;
	
	public static void loadFont(String fontLocation, float fontSize, boolean antiAliased) {
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream(fontLocation);
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(fontSize); // set font size
			font = new TrueTypeFont(awtFont2, antiAliased);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setColor(float r, float g, float b) {
		FontDrawer.color = new Color(r, g, b);
	}
	
	public static void draw(float x, float y, String text) {
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		TextureImpl.bindNone();
		
		String[] lines = text.split("\n");
		for(int i = 0; i < lines.length; i++) {
			font.drawString(x, y + font.getLineHeight() * i, lines[i], Color.white);
		}
		
		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}

	public static int getLineHeight() {
		return font.getHeight();
	}
	
	public static int getHeight(String input) {
		return font.getHeight(input) * input.split("\n").length;
	}
	
	public static int getWidth(String input) {
		//TODO make this return the width of the longest line
		return font.getWidth(input);
	}
}
