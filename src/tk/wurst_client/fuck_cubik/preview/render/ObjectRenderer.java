package tk.wurst_client.fuck_cubik.preview.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

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
		for(int i = 0; i < element.faces.length; i++)
		{
			RenderObjectFace face = element.faces[i];
			float u1 = 1F / 16F * face.uv[0];
			float u2 = 1F / 16F * face.uv[2];
			float v1 = 1F / 16F * face.uv[1];
			float v2 = 1F / 16F * face.uv[3];
			if(face.side == Side.UP)
			{
				try
				{
					TextureLoader.getTexture("PNG", new FileInputStream(new File(".\\texture.png"))).bind();
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glColor3f(1F, 1F, 1F);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y2, z2);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y2, z2);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y2, z1);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y2, z1);
				}
				GL11.glEnd();
			}else if(face.side == Side.DOWN)
			{
				try
				{
					TextureLoader.getTexture("PNG", new FileInputStream(new File(".\\texture.png"))).bind();
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glColor3f(1F, 1F, 1F);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y1, z1);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y1, z1);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y1, z2);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y1, z2);
				}
				GL11.glEnd();
			}else if(face.side == Side.NORTH)
			{
				try
				{
					TextureLoader.getTexture("PNG", new FileInputStream(new File(".\\texture.png"))).bind();
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glColor3f(1F, 1F, 1F);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x1, y2, z1);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x2, y2, z1);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x2, y1, z1);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x1, y1, z1);
				}
				GL11.glEnd();
			}else if(face.side == Side.SOUTH)
			{
				try
				{
					TextureLoader.getTexture("PNG", new FileInputStream(new File(".\\texture.png"))).bind();
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glColor3f(1F, 1F, 1F);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y1, z2);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y1, z2);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y2, z2);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y2, z2);
				}
				GL11.glEnd();
			}else if(face.side == Side.WEST)
			{
				try
				{
					TextureLoader.getTexture("PNG", new FileInputStream(new File(".\\texture.png"))).bind();
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glColor3f(1F, 1F, 1F);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x1, y2, z2);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x1, y2, z1);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x1, y1, z1);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x1, y1, z2);
				}
				GL11.glEnd();
			}else if(face.side == Side.EAST)
			{
				try
				{
					TextureLoader.getTexture("PNG", new FileInputStream(new File(".\\texture.png"))).bind();
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glColor3f(1F, 1F, 1F);
					GL11.glTexCoord2f(u2, v1);
					GL11.glVertex3f(x2, y2, z1);
					GL11.glTexCoord2f(u1, v1);
					GL11.glVertex3f(x2, y2, z2);
					GL11.glTexCoord2f(u1, v2);
					GL11.glVertex3f(x2, y1, z2);
					GL11.glTexCoord2f(u2, v2);
					GL11.glVertex3f(x2, y1, z1);
				}
				GL11.glEnd();
			}
		}
	}
}
