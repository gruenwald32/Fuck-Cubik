package tk.wurst_client.fuck_cubik.preview.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tk.wurst_client.fuck_cubik.Main;

public class ObjectRenderer
{
	public void renderElement(RenderObject element)
	{
		float x1 = 1F / 16F * (float)element.from[0] - 0.5F;
		float y1 = 1F / 16F * (float)element.from[1];
		float z1 = 1F / 16F * (float)element.from[2] - 0.5F;
		float x2 = 1F / 16F * (float)element.to[0] - 0.5F;
		float y2 = 1F / 16F * (float)element.to[1];
		float z2 = 1F / 16F * (float)element.to[2] - 0.5F;
		HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
		for(int i = 0; i < element.textureLinks.length; i++)
		{
			String textureLink = element.textureLinks[i];
			File textureFile = Main.renderer.textureLinkMap.get(textureLink);
			Texture texture = null;
			try
			{
				texture = TextureLoader.getTexture("PNG", new FileInputStream(textureFile));
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			textureMap.put(textureLink, texture);
		}
		Texture missingTexture = null;
		try
		{
			missingTexture = TextureLoader.getTexture("PNG", this.getClass().getClassLoader().getResourceAsStream("resources/missing.png"));
		}catch(IOException e)
		{
			
		}
		for(int i = 0; i < element.faces.length; i++)
		{
			RenderObjectFace face = element.faces[i];
			float u1 = 1F / 16F * face.uv[0];
			float u2 = 1F / 16F * face.uv[2];
			float v1 = 1F / 16F * face.uv[1];
			float v2 = 1F / 16F * face.uv[3];
			Texture texture = textureMap.get(face.textureLink);
			if(texture != null)
			{
				texture.bind();
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				GL11.glColor3f(1F, 1F, 1F);
			}else
			{
				if(missingTexture != null)
				{
					missingTexture.bind();
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
					GL11.glColor3f(1F, 1F, 1F);
				}else
				{
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
					GL11.glColor3f(1F, 0F, 0.5F);
				}
			}
			GL11.glBegin(GL11.GL_QUADS);
			{
				if(face.side == Side.UP)
				{
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y2, z2);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y2, z2);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y2, z1);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y2, z1);
				}else if(face.side == Side.DOWN)
				{
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y1, z1);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y1, z1);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y1, z2);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y1, z2);
				}else if(face.side == Side.NORTH)
				{
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x1, y2, z1);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x2, y2, z1);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x2, y1, z1);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x1, y1, z1);
				}else if(face.side == Side.SOUTH)
				{
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y1, z2);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y1, z2);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y2, z2);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y2, z2);
				}else if(face.side == Side.WEST)
				{
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x1, y2, z2);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y2, z1);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y1, z1);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x1, y1, z2);
				}else if(face.side == Side.EAST)
				{
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y2, z1);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x2, y2, z2);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x2, y1, z2);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y1, z1);
				}
			}
			GL11.glEnd();
		}
	}
}
