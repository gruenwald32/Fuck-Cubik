package tk.wurst_client.fuck_cubik.preview.render;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

public class GUIRenderer
{
	private TrueTypeFont font;
	
	public void init()
	{
		font = new TrueTypeFont(new Font("Monospaced", Font.PLAIN, 16), false);
	}

	public void renderGrid()
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		for(float i = 0F; i <= 1F; i += 1F / 16F)
		{
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				GL11.glColor3f(0F, 0F, 0F);
				GL11.glVertex3f(0.5F, i, -0.5F);
				GL11.glVertex3f(-0.5F, i, -0.5F);
				GL11.glVertex3f(-0.5F, i, 0.5F);
				GL11.glVertex3f(0.5F, i, 0.5F);
			}
			GL11.glEnd();
		}
		for(float i = -0.5F; i <= 0.5F; i += 1F / 16F)
		{
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				GL11.glColor3f(0F, 0F, 0F);
				GL11.glVertex3f(i, 0F, -0.5F);
				GL11.glVertex3f(i, 1F, -0.5F);
				GL11.glVertex3f(i, 1F, 0.5F);
				GL11.glVertex3f(i, 0F, 0.5F);
			}
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				GL11.glColor3f(0F, 0F, 0F);
				GL11.glVertex3f(0.5F, 0F, i);
				GL11.glVertex3f(-0.5F, 0F, i);
				GL11.glVertex3f(-0.5F, 1F, i);
				GL11.glVertex3f(0.5F, 1F, i);
			}
			GL11.glEnd();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public void renderFocus()
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_LINES);
		{
			float size = 1 / 16F;
			GL11.glColor3f(1F, 1F, 0F);
			GL11.glVertex3f(size, -size, -size);
			GL11.glVertex3f(-size, -size, -size);
			GL11.glVertex3f(-size, -size, -size);
			GL11.glVertex3f(-size, -size, size);
			GL11.glVertex3f(-size, -size, size);
			GL11.glVertex3f(size, -size, size);
			GL11.glVertex3f(size, -size, size);
			GL11.glVertex3f(size, -size, -size);
			GL11.glVertex3f(size, -size, -size);
			GL11.glVertex3f(size, size, -size);
			GL11.glVertex3f(-size, -size, -size);
			GL11.glVertex3f(-size, size, -size);
			GL11.glVertex3f(-size, -size, size);
			GL11.glVertex3f(-size, size, size);
			GL11.glVertex3f(size, -size, size);
			GL11.glVertex3f(size, size, size);
			GL11.glVertex3f(size, size, -size);
			GL11.glVertex3f(-size, size, -size);
			GL11.glVertex3f(-size, size, -size);
			GL11.glVertex3f(-size, size, size);
			GL11.glVertex3f(-size, size, size);
			GL11.glVertex3f(size, size, size);
			GL11.glVertex3f(size, size, size);
			GL11.glVertex3f(size, size, -size);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public void renderCompass()
	{
		TextureImpl.bindNone();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glColor3f(1F, 0F, 0F);
			GL11.glVertex3f(1F / 32F, 0F, -20F / 32F);
			GL11.glVertex3f(-1F / 32F, 0F, -20F / 32F);
			GL11.glVertex3f(-1F / 32F, 0F, -1F / 32F);
			GL11.glVertex3f(1F / 32F, 0F, -1F / 32F);
			
			GL11.glColor3f(0F, 0F, 0F);
			GL11.glVertex3f(1F / 32F, 0F, 1F / 32F);
			GL11.glVertex3f(-1F / 32F, 0F, 1F / 32F);
			GL11.glVertex3f(-1F / 32F, 0F, 20F / 32F);
			GL11.glVertex3f(1F / 32F, 0F, 20F / 32F);
			
			GL11.glVertex3f(20F / 32F, 0F, -1F / 32F);
			GL11.glVertex3f(-20F / 32F, 0F, -1F / 32F);
			GL11.glVertex3f(-20F / 32F, 0F, 1F / 32F);
			GL11.glVertex3f(20F / 32F, 0F, 1F / 32F);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glScalef(1F / 32F, 1F / 32F, 1F / 32F);
		GL11.glRotatef(90F, 1F, 0F, 0F);
		GL11.glTranslatef(-4F, -40F, 0F);
		font.drawString(0, 0, "N", new Color(0F, 0F, 0F));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTranslatef(4F, 40F, 0F);
		GL11.glTranslatef(-4F, 14F, 0F);
		font.drawString(0, 0, "S", new Color(0F, 0F, 0F));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTranslatef(4F, -14F, 0F);
		GL11.glTranslatef(-32F, -12F, 0F);
		font.drawString(0, 0, "W", new Color(0F, 0F, 0F));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTranslatef(32F, 12F, 0F);
		GL11.glTranslatef(20F, -12F, 0F);
		font.drawString(0, 0, "E", new Color(0F, 0F, 0F));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTranslatef(-20F, 12F, 0F);
		GL11.glScalef(32F, 32F, 32F);
		GL11.glRotatef(-90F, 1F, 0F, 0F);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
}
