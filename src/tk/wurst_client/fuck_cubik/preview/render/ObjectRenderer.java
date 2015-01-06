package tk.wurst_client.fuck_cubik.preview.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.files.ResourceManager;

public class ObjectRenderer
{
	private HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
	
	public void checkTextureMap()
	{
		if(textureMap.isEmpty())
		{
			try
			{
				String textureLink = "__missing";
				Texture texture = TextureLoader.getTexture("PNG", ResourceManager.getStream(ResourceManager.MISSING_TEXTURE));
				textureMap.put(textureLink, texture);
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}
			for(Entry<String, File> entry : Main.renderer.textureLinkMap.entrySet())
			{
				String textureLink = entry.getKey();
				File textureFile = entry.getValue();
				Texture texture = null;
				try
				{
					texture = TextureLoader.getTexture("PNG", new FileInputStream(textureFile));
				}catch(NullPointerException | IOException e)
				{	
					
				}
				textureMap.put(textureLink, texture);
			}
		}
	}
	
	public void renderElement(RenderObject element, boolean marked)
	{
		TextureImpl.bindNone();
		float x1 = 1F / 16F * (float)element.from[0] - 0.5F;
		float y1 = 1F / 16F * (float)element.from[1];
		float z1 = 1F / 16F * (float)element.from[2] - 0.5F;
		float x2 = 1F / 16F * (float)element.to[0] - 0.5F;
		float y2 = 1F / 16F * (float)element.to[1];
		float z2 = 1F / 16F * (float)element.to[2] - 0.5F;
		for(RenderObjectFace face : element.faces)
		{
			float u1 = 1F / 16F * face.uv[0];
			float u2 = 1F / 16F * face.uv[2];
			float v1 = 1F / 16F * face.uv[1];
			float v2 = 1F / 16F * face.uv[3];
			if(textureMap.get(face.textureLink) != null)
			{
				if(marked)
					setGLColorToMarked();
				else
					GL11.glColor3f(1F, 1F, 1F);
				textureMap.get(face.textureLink).bind();
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			}else if(textureMap.get("__missing") != null)
			{
				if(marked)
					setGLColorToMarked();
				else
					GL11.glColor3f(1F, 1F, 1F);
				textureMap.get("__missing").bind();
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			}else
			{
				if(marked)
					setGLColorToMarked();
				else
					GL11.glColor3f(1F, 0F, 0.5F);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
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
	
	private void setGLColorToMarked()
	{
		float red = (1F - (float)Math.sin((float)(System.currentTimeMillis() % 1000L) / 1000L * Math.PI * 2)) / 2F;
		float green = (1F - (float)Math.sin((float)((System.currentTimeMillis() + 333L) % 1000L) / 1000L * Math.PI * 2)) / 2F;
		float blue = (1F - (float)Math.sin((float)((System.currentTimeMillis() + 666L) % 1000L) / 1000L * Math.PI * 2)) / 2F;
		GL11.glColor3f(red, green, blue);
	}

	public void clearTextureMap()
	{
		textureMap.clear();
	}
}
