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
		font.drawString(x, y, text, Color.white);
		
		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}
}
